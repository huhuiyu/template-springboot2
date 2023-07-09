package top.huhuiyu.template.maven.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.huhuiyu.template.maven.springsecurity.base.BaseResult;
import top.huhuiyu.template.maven.springsecurity.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private static Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
  private final TokenManager tokenManager;

  public MyAuthenticationSuccessHandler(TokenManager tokenManager) {
    this.tokenManager = tokenManager;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    logger.debug("认证成功");
    BaseResult<String> result = BaseResult.getSuccessResult("登录成功");
    // 处理token信息
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
