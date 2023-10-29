package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbAuthRoleApi;

@Mapper
public interface TbAuthRoleApiDAO {
  TbAuthRoleApi queryByUser(TbAuthRoleApi tbAuthRoleApi);
}
