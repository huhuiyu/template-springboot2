package top.huhuiyu.template.maven.springboot2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring容器上下文工具
 *
 * @author 胡辉煜
 */
@Component
public class ApplicationUtil implements ApplicationContextAware {

  private static final Logger log = LoggerFactory.getLogger(ApplicationUtil.class);

  private static ApplicationContext applicationContext;

  /**
   * 获取applicationContext
   *
   * @return ApplicationContext
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    log.debug("设置应用程序上下文：{}", applicationContext);
    if (ApplicationUtil.applicationContext == null) {
      ApplicationUtil.applicationContext = applicationContext;
    }
  }

}