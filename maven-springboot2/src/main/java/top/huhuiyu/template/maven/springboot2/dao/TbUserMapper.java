package top.huhuiyu.template.maven.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.template.maven.springboot2.entity.TbUser;

@Mapper
public interface TbUserMapper {
  TbUser queryByName(TbUser tbUser) throws Exception;
}
