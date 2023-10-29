package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbAuthRole;

@Mapper
public interface TbAuthRoleDAO {

  TbAuthRole queryByKey(Integer roleId);
}
