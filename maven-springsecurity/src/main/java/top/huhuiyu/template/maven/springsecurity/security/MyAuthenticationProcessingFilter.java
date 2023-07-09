package top.huhuiyu.template.maven.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class MyAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
  private static Logger logger = LoggerFactory.getLogger(MyAuthenticationProcessingFilter.class);
  public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
  public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
  private String usernameParameter = "username";
  private String passwordParameter = "password";

  public MyAuthenticationProcessingFilter() {
    super(new AntPathRequestMatcher("/auth/login", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    logger.debug("自定义认证");
    if (!"POST".equalsIgnoreCase(request.getMethod())) {
      throw new AuthenticationServiceException("不支持的请求方式: " + request.getMethod());
    }
    String username = this.obtainUsername(request);
    String password = this.obtainPassword(request);
    username = Optional.ofNullable(username).orElse("").trim();
    password = Optional.ofNullable(password).orElse("").trim();
    MyAuthenticationToken auth = new MyAuthenticationToken(username, password);
    this.setDetails(request, auth);
    return this.getAuthenticationManager().authenticate(auth);
  }

  private String obtainUsername(HttpServletRequest request) {
    return request.getParameter("username");
  }

  private String obtainPassword(HttpServletRequest request) {
    return request.getParameter("password");
  }

  protected void setDetails(HttpServletRequest request, MyAuthenticationToken authRequest) {
    authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
  }

}
