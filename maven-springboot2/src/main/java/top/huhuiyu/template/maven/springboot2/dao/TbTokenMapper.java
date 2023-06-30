package top.huhuiyu.template.maven.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.template.maven.springboot2.entity.TbToken;

@Mapper
public interface TbTokenMapper {
  TbToken queryByToken(TbToken tbToken) throws Exception;

  int add(TbToken tbToken) throws Exception;

  int update(TbToken tbToken) throws Exception;

  int deleteExpire() throws Exception;

}
