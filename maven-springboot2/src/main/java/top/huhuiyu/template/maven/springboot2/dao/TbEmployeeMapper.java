package top.huhuiyu.template.maven.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.template.maven.springboot2.entity.TbEmployee;

import java.util.List;

@Mapper
public interface TbEmployeeMapper {
  List<TbEmployee> queryAll(TbEmployee employee) throws Exception;

  List<TbEmployee> queryAllAssociation(TbEmployee employee) throws Exception;

  List<TbEmployee> queryEmployeeByDeptId(Integer deptId) throws Exception;

}
