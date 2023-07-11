package top.huhuiyu.template.maven.springsecurity.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.huhuiyu.template.maven.springsecurity.dao.SystemMapper;
import top.huhuiyu.template.maven.springsecurity.dao.TbSecurityUserMapper;
import top.huhuiyu.template.maven.springsecurity.entity.MyUserDetails;
import top.huhuiyu.template.maven.springsecurity.entity.TbSecurityUser;
import top.huhuiyu.template.maven.springsecurity.security.MyAuthenticationToken;
import top.huhuiyu.template.maven.springsecurity.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service("securityService")
@Transactional(rollbackFor = Exception.class)
public class SecurityServiceImpl implements SecurityService {
  private static Logger logger = LoggerFactory.getLogger(SecurityService.class);
  private final TbSecurityUserMapper tbSecurityUserMapper;
  private final SystemMapper systemMapper;

  public SecurityServiceImpl(TbSecurityUserMapper tbSecurityUserMapper, SystemMapper systemMapper) {
    this.tbSecurityUserMapper = tbSecurityUserMapper;
    this.systemMapper = systemMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.debug("用户名：{}", username);
    TbSecurityUser user = new TbSecurityUser();
    user.setUsername(username);
    try {
      TbSecurityUser check = tbSecurityUserMapper.queryByName(user);
      if (check == null) {
        throw new UsernameNotFoundException("用户不存在");
      }
      return new MyUserDetails(check);
    } catch (Exception e) {
      throw new UsernameNotFoundException("用户不存在");
    }
  }

  @Override
  public String generateToken(TbSecurityUser user) throws Exception {
    Date date = new Date(systemMapper.queryNow().getTime() + TOKEN_EXPIRE_TIME);
    String token = JWT.create().withAudience(user.getUid() + "").withExpiresAt(date).sign(Algorithm.HMAC256(user.getPassword()));
    return token;
  }

  @Override
  public TbSecurityUser parseToken(String token) throws Exception {
    if (!StringUtils.hasText(token)) {
      return null;
    }
    Integer uid;
    try {
      uid = Integer.parseInt(JWT.decode(token).getAudience().get(0));
    } catch (JWTDecodeException ex) {
      return null;
    }
    TbSecurityUser user = new TbSecurityUser();
    user.setUid(uid);
    user = tbSecurityUserMapper.queryByUid(user);
    if (user == null) {
      return null;
    }
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
    try {
      verifier.verify(token);
      return user;
    } catch (JWTVerificationException ex) {
      return null;
    }
  }

  @Override
  public boolean checkAuth(Authentication authentication, HttpServletRequest request) throws Exception {
    logger.debug("认证和请求信息:{},{},{}", authentication, request, authentication.getClass().getName());
    MyAuthenticationToken token = (MyAuthenticationToken) authentication;
    logger.debug("用户和地址信息：{},{},{}", token.getUser(), request.getRequestURI(), request.getPathInfo());
    String url = request.getRequestURI().replaceFirst(request.getContextPath(), "");
    if (url.startsWith("/auth/admin/") && !"admin".equals(token.getUser().getRole().getRoleName())) {
      return false;
    }
    if (url.startsWith("/auth/user/") && !"user".equals(token.getUser().getRole().getRoleName())) {
      return false;
    }
    return true;
  }

}
