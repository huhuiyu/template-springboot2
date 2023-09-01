package top.huhuiyu.template.maven.springboot2.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.net.URI;

/**
 * 监听应用程序启动
 *
 * @author 胡辉煜
 */
@Configuration
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {
  @Value("${server.port}")
  private String port;

  private static final Logger log = LoggerFactory.getLogger(ApplicationReadyListener.class);

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    if("10100".equals(port)) {
      System.setProperty("java.awt.headless", "false");
      try {
        Desktop.getDesktop().browse(new URI(String.format("http://127.0.0.1:%s?echo=DarkKnight", port)));
      } catch (Exception e) {
        log.error("无法启动浏览器",e);
      }
    }
    log.info("应用程序启动完成");
  }
}
