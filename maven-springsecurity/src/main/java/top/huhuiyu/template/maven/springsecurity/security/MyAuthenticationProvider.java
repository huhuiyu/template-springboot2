package top.huhuiyu.template.maven.springsecurity.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
  private static Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);
  private final UserDetailsService securityService;
  private final TokenManager tokenManager;

  public MyAuthenticationProvider(UserDetailsService securityService, TokenManager tokenManager) {
    this.securityService = securityService;
    this.tokenManager = tokenManager;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    logger.debug("认证信息:{}", authentication);
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String token = request.getParameter("security_token");
    token = tokenManager.checkToken(token);
    tokenManager.setToken(token);
    logger.debug("认证的token:{}", token);
    UserDetails user = tokenManager.get(token);
    MyAuthenticationToken authenticationToken;
    // 存在信息就直接返回认证结果
    if (user != null) {
      authenticationToken = new MyAuthenticationToken(user, user.getAuthorities());
      return authentication;
    }
    // 通过表单信息校验登录
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    username = Optional.ofNullable(username).orElse("").trim();
    password = Optional.ofNullable(password).orElse("").trim();

    user = securityService.loadUserByUsername(authentication.getName());
    if (user == null) {
      throw new BadCredentialsException("用户不存在");
    }
    if (!user.getPassword().equalsIgnoreCase(password)) {
      throw new BadCredentialsException("密码错误");
    }
    authentication = new MyAuthenticationToken(user, user.getAuthorities());
    // 关联token和登录用户信息
    tokenManager.put(token, user);
    return authentication;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return MyAuthenticationToken.class.isAssignableFrom(authentication);
  }


}
