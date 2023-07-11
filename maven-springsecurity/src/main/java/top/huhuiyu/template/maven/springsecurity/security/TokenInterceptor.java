package top.huhuiyu.template.maven.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author 胡辉煜
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

  private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

  public TokenInterceptor() {

  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // 处理token
    String token = request.getParameter("security_token");
    log.debug("拦截器前置处理：{}，token信息：{}", handler, token);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.debug("拦截器后置处理：{}，数据：{}", handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    log.debug("拦截器完成：{}，异常：{}", handler, ex);
  }

}
