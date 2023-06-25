package top.huhuiyu.template.maven.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.template.maven.springboot2.entity.DeptAndEmployeeList;
import top.huhuiyu.template.maven.springboot2.entity.TbDept;

import java.util.List;

@Mapper
public interface TbDeptMapper {
  List<TbDept> queryAll(TbDept dept) throws Exception;

  List<DeptAndEmployeeList> queryDeptAndEmployeeList(TbDept dept) throws Exception;

  int add(TbDept tbDept) throws Exception;

  TbDept queryLast() throws Exception;

  int update(TbDept tbDept) throws Exception;

  int delete(TbDept tbDept) throws Exception;
}
