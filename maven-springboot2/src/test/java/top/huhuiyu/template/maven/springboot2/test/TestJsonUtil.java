package top.huhuiyu.template.maven.springboot2.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.huhuiyu.template.maven.springboot2.entity.TbDept;
import top.huhuiyu.template.maven.springboot2.util.JsonUtil;

import java.util.Date;

public class TestJsonUtil {
  private static final Logger logger = LoggerFactory.getLogger(TestJsonUtil.class);

  @Test
  public void one() throws Exception {
    TbDept dept = new TbDept();
    dept.setDeptId(100);
    dept.setDeptName("开发部");
    dept.setDeptInfo("很厉害的部门");
    dept.setLastupdate(new Date());
    logger.info(JsonUtil.stringify(dept));
    dept.setDeptId(null);
    dept.setLastupdate(null);
    logger.info(JsonUtil.stringify(dept));
    logger.info("测试完成");
  }
}
