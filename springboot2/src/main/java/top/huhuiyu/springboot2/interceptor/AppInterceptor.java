package top.huhuiyu.springboot2.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.huhuiyu.springboot2.entity.AuthInfo;
import top.huhuiyu.springboot2.utils.IpUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author 胡辉煜
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppInterceptor implements HandlerInterceptor {
  public static final String TOKEN_HEADER = "Authorization";

  private final ThreadLocal<AuthInfo> authInfoThreadLocal;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    log.debug("自定义拦截器前置处理");
    // 对Controller器请求单独进行出
    if (handler instanceof HandlerMethod) {
      log.debug("控制器信息处理");
      HandlerMethod hm = (HandlerMethod) handler;
      // 获取Controller类的RequestMapping注解
      Class<?> clazz = hm.getBeanType();
      RequestMapping classLevelMapping = clazz.getAnnotation(RequestMapping.class);
      String classPath = classLevelMapping == null ? "" : classLevelMapping.value()[0];
      // 获取Controller方法的RequestMapping注解
      RequestMapping requestMapping = hm.getMethodAnnotation(RequestMapping.class);
      String methodPath = requestMapping == null ? "" : requestMapping.value()[0];
      // 获取请求路径
      String mappingPath = String.format("%s%s", classPath, methodPath);
      log.debug("路径映射信息：{}", mappingPath);
      // 放置认证信息
      AuthInfo authInfo = new AuthInfo();
      // 设置ip信息
      authInfo.setIp(IpUtils.getIpAddress());
      // 设置控制路径信息
      authInfo.setMappingPath(mappingPath);
      // 设置请求路径信息
      String url = request.getRequestURI().replaceFirst(request.getContextPath(), "");
      String method = request.getMethod().toLowerCase();
      authInfo.setRequestUrl(url);
      authInfo.setMethod(method);
      // 设置token信息
      String token = request.getHeader(TOKEN_HEADER);
      authInfo.setToken(token);
      authInfoThreadLocal.set(authInfo);
      log.debug("初始化的权限信息：{}", authInfo);
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    log.debug("自定义拦截器尾置处理");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    log.debug("自定义拦截器完毕");
  }

}
