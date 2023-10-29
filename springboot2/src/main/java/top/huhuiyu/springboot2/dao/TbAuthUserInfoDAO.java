package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbAuthUserInfo;

@Mapper
public interface TbAuthUserInfoDAO {
  public TbAuthUserInfo queryByKey(Integer userId);
}
