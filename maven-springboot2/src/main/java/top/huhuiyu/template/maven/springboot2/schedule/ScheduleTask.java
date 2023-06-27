package top.huhuiyu.template.maven.springboot2.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.huhuiyu.template.maven.springboot2.dao.SystemMapper;
import top.huhuiyu.template.maven.springboot2.util.DateUtils;

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

  public ScheduleTask(SystemMapper systemMapper) {
    this.systemMapper = systemMapper;
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

}