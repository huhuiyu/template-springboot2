package top.huhuiyu.template.maven.springboot2.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.huhuiyu.template.maven.springboot2.util.DateUtils;

public class TestDateUtil {
  private static final Logger logger = LoggerFactory.getLogger(TestDateUtil.class);

  @Test
  public void one() throws Exception {
    String info = "2020-09-09";
    logger.info("{}", DateUtils.parse(info));
    info = "2020-09-09 09:10:11";
    logger.info("{}", DateUtils.parse(info));
    info = "2020-09-09T09:10:11";
    logger.info("{}", DateUtils.parse(info));
  }
}
