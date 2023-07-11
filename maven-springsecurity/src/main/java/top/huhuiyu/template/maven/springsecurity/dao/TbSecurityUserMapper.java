package top.huhuiyu.template.maven.springsecurity.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.template.maven.springsecurity.entity.TbSecurityUser;

@Mapper
public interface TbSecurityUserMapper {
  TbSecurityUser queryByName(TbSecurityUser user) throws Exception;

  TbSecurityUser queryByUid(TbSecurityUser user) throws Exception;

}
