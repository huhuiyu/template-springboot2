package top.huhuiyu.template.maven.springboot2.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.huhuiyu.template.maven.springboot2.dao.TbEmployeeMapper;
import top.huhuiyu.template.maven.springboot2.entity.TbEmployee;

import java.util.List;

@SpringBootTest
public class TestTbEmployeeDAO {
  private static Logger logger = LoggerFactory.getLogger(TestTbEmployeeDAO.class);
  @Autowired
  private TbEmployeeMapper tbEmployeeMapper;

  @Test
  public void query() throws Exception {
    TbEmployee employee = new TbEmployee();
    List<TbEmployee> list = tbEmployeeMapper.queryAll(employee);
    showList(list, "查询结果");
    employee.setDeptId(1);
    list = tbEmployeeMapper.queryAll(employee);
    showList(list, "部门id过滤查询结果");
    employee.setDeptId(null);
    employee.setEmployeeName(String.format("%%%s%%", "n"));
    employee.setPhone(String.format("%%%s%%", "7"));
    list = tbEmployeeMapper.queryAll(employee);
    showList(list, "条件过滤查询结果");
  }

  @Test
  public void queryAllAssociation() throws Exception {
    TbEmployee employee = new TbEmployee();
    List<TbEmployee> list = tbEmployeeMapper.queryAllAssociation(employee);
    showList(list, "查询结果");
    employee.setDeptId(1);
    list = tbEmployeeMapper.queryAllAssociation(employee);
    showList(list, "部门id过滤查询结果");
    employee.setDeptId(null);
    employee.setEmployeeName(String.format("%%%s%%", "n"));
    employee.setPhone(String.format("%%%s%%", "7"));
    list = tbEmployeeMapper.queryAllAssociation(employee);
    showList(list, "条件过滤查询结果");
  }

  public void showList(List<TbEmployee> list, String title) {
    logger.info("======={}=======", title);
    for (TbEmployee emp : list) {
      logger.info("{}", emp);
    }
  }
}
