package top.huhuiyu.template.maven.springboot2.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.huhuiyu.template.maven.springboot2.dao.TbDeptMapper;
import top.huhuiyu.template.maven.springboot2.entity.DeptAndEmployeeList;
import top.huhuiyu.template.maven.springboot2.entity.TbDept;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class TestTbDeptDAO {
  private static Logger logger = LoggerFactory.getLogger(TestTbDeptDAO.class);
  @Autowired
  private TbDeptMapper tbDeptMapper;
  private Random random = new Random();

  @Test
  public void query() throws Exception {
    TbDept dept = new TbDept();
    List<TbDept> list = tbDeptMapper.queryAll(dept);
    logger.info("查询结果：{}", list);
    dept.setDeptName(String.format("%%%s%%", "人"));
    list = tbDeptMapper.queryAll(dept);
    logger.info("参数查询结果：{}", list);
  }

  @Test
  public void queryDeptAndEmployeeList() throws Exception {
    TbDept dept = new TbDept();
    dept.setDeptId(1);
    List<DeptAndEmployeeList> list = tbDeptMapper.queryDeptAndEmployeeList(dept);
    logger.info("查询结果：{}", list);
  }

  @Test
  public void add() throws Exception {
    TbDept dept = new TbDept();
    dept.setDeptName("部门" + random.nextInt());
    dept.setDeptInfo("部门描述" + random.nextInt());
    tbDeptMapper.add(dept);
    logger.info("添加的结果：{}", dept);
  }

  @Test
  public void update() throws Exception {
    TbDept dept = tbDeptMapper.queryLast();
    dept.setDeptName("部门修改" + random.nextInt());
    dept.setDeptInfo("部门描述修改" + random.nextInt());
    int i = tbDeptMapper.update(dept);
    logger.info("修改的结果：{}", i);
  }

  @Test
  public void delete() throws Exception {
    TbDept dept = tbDeptMapper.queryLast();
    int i = tbDeptMapper.delete(dept);
    logger.info("删除的结果：{}", i);
  }

}
