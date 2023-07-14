package top.huhuiyu.template.maven.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import top.huhuiyu.template.maven.springsecurity.entity.MyUserDetails;
import top.huhuiyu.template.maven.springsecurity.entity.TbSecurityUser;
import top.huhuiyu.template.maven.springsecurity.service.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyJwtFilter extends GenericFilterBean {
  public static final String FORM_SECURITY_TOKEN = "security_token";
  public static final String HEAD_SECURITY_TOKEN = "token";
  private static Logger logger = LoggerFactory.getLogger(MyJwtFilter.class);

  private final SecurityService securityService;

  public MyJwtFilter(SecurityService securityService) {
    this.securityService = securityService;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    try {
      HttpServletRequest req = (HttpServletRequest) request;
      // 获取头信息中的token
      String token = req.getHeader(HEAD_SECURITY_TOKEN);
      if (!StringUtils.hasText(token)) {
        // 不存在就在请求参数中获取
        token = request.getParameter(FORM_SECURITY_TOKEN);
      }
      TbSecurityUser user = securityService.parseToken(token);
      logger.debug("MyJwtFilter获取的token信息：{},{}", token, user);
      if (user != null) {
        UserDetails userDetails = new MyUserDetails(user);
        Authentication authentication = new MyAuthenticationToken(user.getUsername(), user.getPassword(), userDetails.getAuthorities(), token, user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      logger.debug("认证信息：{}", SecurityContextHolder.getContext().getAuthentication());
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
    filterChain.doFilter(request, response);
  }
}
