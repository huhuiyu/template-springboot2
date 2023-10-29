package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbAuthUser;

@Mapper
public interface TbAuthUserDAO {
  TbAuthUser queryByName(TbAuthUser user);

  TbAuthUser queryByKey(Integer userId);

}
