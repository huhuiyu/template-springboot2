package top.huhuiyu.template.maven.springboot2.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.huhuiyu.template.maven.springboot2.base.BaseWebSocketResult;
import top.huhuiyu.template.maven.springboot2.dao.SystemMapper;
import top.huhuiyu.template.maven.springboot2.service.WebSocketService;
import top.huhuiyu.template.maven.springboot2.util.DateUtils;
import top.huhuiyu.template.maven.springboot2.websocket.WebSocketApplication;

import java.util.Date;

/**
 * 定时任务
 *
 * @author 胡辉煜
 */
@Component
public class ScheduleTask {
  private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
  private final SystemMapper systemMapper;
  private final WebSocketService webSocketService;

  public ScheduleTask(SystemMapper systemMapper, WebSocketService webSocketService) {
    this.systemMapper = systemMapper;
    this.webSocketService = webSocketService;
  }

  @Scheduled(initialDelay = 5 * 1000, fixedDelay = 2 * 60 * 1000)
  public void timestamp() {
    try {
      Date now = systemMapper.queryTime();
      logger.debug("数据库当前时间：{}", DateUtils.format(now));
    } catch (Exception ex) {
      logger.debug("获取时间发生错误：{}", ex.getMessage());
    }
  }

  @Scheduled(initialDelay = 10 * 1000, fixedDelay = 2 * 60 * 1000)
  public void wstimestamp() {
    try {
      logger.debug("正在广播时间戳");
      BaseWebSocketResult<Date> result = BaseWebSocketResult.getSuccessInfo(systemMapper.queryTime());
      result.setType(WebSocketApplication.TYPE_TIMESTAMP);
      webSocketService.broadcast(result);
    } catch (Exception ex) {
      logger.error("广播时间戳", ex);
    }
  }


}