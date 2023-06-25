package top.huhuiyu.template.maven.springboot2.service;

import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.entity.TbDept;

import java.util.List;

public interface TbDeptService {
  BaseResult<List<TbDept>> queryAll(TbDept dept) throws Exception;

  BaseResult<TbDept> add(TbDept dept) throws Exception;

  BaseResult<TbDept> delete(TbDept dept) throws Exception;
}
