package top.huhuiyu.template.maven.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;
import top.huhuiyu.template.maven.springsecurity.entity.MyUserDetails;
import top.huhuiyu.template.maven.springsecurity.entity.TbSecurityUser;
import top.huhuiyu.template.maven.springsecurity.service.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyJwtFilter extends GenericFilterBean {
  private static Logger logger = LoggerFactory.getLogger(MyJwtFilter.class);

  private final SecurityService securityService;

  public MyJwtFilter(SecurityService securityService) {
    this.securityService = securityService;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    try {
      String token = request.getParameter("security_token");
      TbSecurityUser user = securityService.parseToken(token);
      logger.debug("MyJwtFilter获取的token信息：{},{}", token, user);
//      if (user == null) {
//        response.getWriter().println(JsonUtil.stringify(BaseResult.getFailResult("需要正确的token信息")));
//        response.getWriter().flush();
//        return;
//      }
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
