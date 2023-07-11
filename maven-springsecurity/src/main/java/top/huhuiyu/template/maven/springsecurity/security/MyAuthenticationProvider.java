package top.huhuiyu.template.maven.springsecurity.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.huhuiyu.template.maven.springsecurity.entity.MyUserDetails;
import top.huhuiyu.template.maven.springsecurity.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
  private static Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);
  private final SecurityService securityService;

  public MyAuthenticationProvider(SecurityService securityService) {
    this.securityService = securityService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    logger.debug("认证信息:{}", authentication);
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    // 通过表单信息校验登录
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    username = Optional.ofNullable(username).orElse("").trim();
    password = Optional.ofNullable(password).orElse("").trim();
    MyUserDetails user = (MyUserDetails) securityService.loadUserByUsername(authentication.getName());
    logger.debug("认证用户信息：{},{}", user, user.getUser());
    if (user == null) {
      throw new BadCredentialsException("用户不存在");
    }
    if (!user.getPassword().equalsIgnoreCase(password)) {
      throw new BadCredentialsException("密码错误");
    }
    if (!user.getUser().getEnable().equalsIgnoreCase("y")) {
      throw new BadCredentialsException("用户已经停用");
    }
    try {
      authentication = new MyAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities(), securityService.generateToken(user.getUser()), user.getUser());
    } catch (Exception ex) {
      throw new AuthenticationServiceException(ex.getMessage());
    }
    return authentication;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return MyAuthenticationToken.class.isAssignableFrom(authentication);
  }


}
