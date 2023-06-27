package top.huhuiyu.template.maven.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface SystemMapper {
  Date queryTime() throws Exception;
}
