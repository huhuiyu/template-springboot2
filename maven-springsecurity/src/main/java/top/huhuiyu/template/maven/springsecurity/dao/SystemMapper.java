package top.huhuiyu.template.maven.springsecurity.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface SystemMapper {
  Date queryNow() throws Exception;
}
