package top.huhuiyu.template.maven.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.huhuiyu.template.maven.springsecurity.base.BaseResult;
import top.huhuiyu.template.maven.springsecurity.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
  private static Logger logger = LoggerFactory.getLogger(MyAuthenticationEntryPoint.class);

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    logger.debug("拒绝访问处理:{}", authException.getMessage());
    BaseResult<String> result = BaseResult.getFailResult("需要登录才能访问");
    result.setCode(403);
    result.setToken(null);
    response.setContentType("application/json;charset=utf-8");
    PrintWriter out = response.getWriter();
    try {
      out.println(JsonUtil.stringify(result));
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.flush();
    out.close();
  }
}
