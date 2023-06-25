package top.huhuiyu.template.maven.springboot2.test;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestResources {

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
    // assertEquals(sb.toString(),"黑暗骑士");
    System.out.println("测试完成");
  }
}
