package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbAuthApi;

@Mapper
public interface TbAuthApiDAO {
  TbAuthApi queryByUrl(TbAuthApi tbAuthApi);
}
