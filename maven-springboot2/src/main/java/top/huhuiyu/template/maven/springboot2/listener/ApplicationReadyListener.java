package top.huhuiyu.template.maven.springboot2.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * 监听应用程序启动
 *
 * @author 胡辉煜
 */
@Configuration
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

  private static final Logger log = LoggerFactory.getLogger(ApplicationReadyListener.class);

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    log.info("应用程序启动完成");
  }
}
