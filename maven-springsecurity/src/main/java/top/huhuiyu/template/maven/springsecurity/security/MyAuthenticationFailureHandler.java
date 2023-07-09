package top.huhuiyu.template.maven.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import top.huhuiyu.template.maven.springsecurity.base.BaseResult;
import top.huhuiyu.template.maven.springsecurity.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
  private static Logger logger = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);

  private final TokenManager tokenManager;

  public MyAuthenticationFailureHandler(TokenManager tokenManager) {
    this.tokenManager = tokenManager;
  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    logger.debug("认证失败：{}", exception.getMessage());
    BaseResult<String> result = BaseResult.getFailResult(exception.getMessage());
    result.setToken(tokenManager.getToken());
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
