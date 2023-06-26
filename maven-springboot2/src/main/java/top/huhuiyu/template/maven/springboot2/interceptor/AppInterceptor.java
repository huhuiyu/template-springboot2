package top.huhuiyu.template.maven.springboot2.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义拦截器
 *
 * @author 胡辉煜
 */
@Component
public class AppInterceptor implements HandlerInterceptor {
  private static final Logger logger = LoggerFactory.getLogger(AppInterceptor.class);
  public static final String STR_SPLIT = ",";

  /**
   * 合并字符串数组为逗号分隔的字符串
   *
   * @param infos 要合并的字符串数组
   * @return 合并的结果
   */
  public String join(String[] infos) {
    if (infos == null || infos.length == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (String info : infos) {
      sb.append(info).append(STR_SPLIT);
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    logger.debug("自定义拦截器前置处理");
    Map<String, String[]> map = request.getParameterMap();
    logger.debug("请求地址：{}", request.getRequestURI());
    logger.debug("表单请求参数信息：");
    for (String key : map.keySet()) {
      logger.debug("{}:{}", key, this.join(map.get(key)));
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.debug("自定义拦截器尾置处理");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    logger.debug("自定义拦截器完毕");
  }
}
