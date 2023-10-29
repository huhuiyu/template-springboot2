package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbAuthUser;

@Mapper
public interface TbAuthUserDAO {
  public TbAuthUser queryByName(TbAuthUser user);

  public TbAuthUser queryByKey(Integer userId);

}
