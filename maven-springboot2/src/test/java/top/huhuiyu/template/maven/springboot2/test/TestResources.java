package top.huhuiyu.template.maven.springboot2.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestResources {

  private Logger logger = LoggerFactory.getLogger(TestResources.class);
  public static final String TEST_FILE = "/test.data";

  @Test
  public void test() {
    Scanner scanner = new Scanner(TestResources.class.getResourceAsStream(TEST_FILE));
    StringBuilder sb = new StringBuilder();
    while (scanner.hasNextLine()) {
      sb.append(scanner.nextLine());
    }
    scanner.close();
    assertEquals(sb.toString(), "黑暗骑士");
    logger.info("测试完成");
  }
}
