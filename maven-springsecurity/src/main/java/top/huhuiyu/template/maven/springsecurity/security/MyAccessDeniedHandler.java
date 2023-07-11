package top.huhuiyu.template.maven.springsecurity.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.huhuiyu.template.maven.springsecurity.base.BaseResult;
import top.huhuiyu.template.maven.springsecurity.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    BaseResult<String> result = BaseResult.getFailResult("需要相关角色登录");
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
