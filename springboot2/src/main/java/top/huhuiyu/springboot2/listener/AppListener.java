package top.huhuiyu.springboot2.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import top.huhuiyu.springboot2.entity.AuthInfo;
import top.huhuiyu.springboot2.utils.IpUtils;

import java.util.Optional;

/**
 * 应用启动监听器
 *
 * @author 胡辉煜
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppListener implements ApplicationListener<ApplicationReadyEvent> {
  public static final String LOCAL_IP = "127.0.0.1";
  private final Environment environment;

  @Bean
  public ThreadLocal<AuthInfo> authInfoThreadLocal() {
    return new ThreadLocal<>();
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    // 获取服务路径信息
    Integer serverPort = environment.getProperty("server.port", Integer.class);
    String contextPath = environment.getProperty("server.servlet.context-path");
    String ip = IpUtils.getLocalIp();
    contextPath = Optional.ofNullable(contextPath).orElse("");
    log.info("===============================================================================================");
    log.info("应用启动完成，启动地址为：{}", String.format("http://%s:%s%s", ip, serverPort, contextPath));
    log.info("应用启动完成，启动地址为：{}", String.format("http://%s:%s%s", LOCAL_IP, serverPort, contextPath));
    log.info("===============================================================================================");
  }
}

