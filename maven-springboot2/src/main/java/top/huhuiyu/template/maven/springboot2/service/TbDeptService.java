package top.huhuiyu.template.maven.springboot2.service;

import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.entity.TbDept;

import java.util.List;

/**
 * 部门表服务层
 *
 * @author 胡辉煜
 */
public interface TbDeptService {
  /**
   * 部门信息查询
   *
   * @param dept 查询参数
   * @return 部门列表
   * @throws Exception 处理发生异常
   */
  BaseResult<List<TbDept>> queryAll(TbDept dept) throws Exception;

  /**
   * 添加部门
   *
   * @param dept 要添加的部门信息
   * @return 添加部门的结果
   * @throws Exception 处理发生异常
   */
  BaseResult<TbDept> add(TbDept dept) throws Exception;

  /**
   * 删除部门
   *
   * @param dept 要删除的部门信息
   * @return 删除部门的结果
   * @throws Exception 处理发生异常
   */
  BaseResult<String> delete(TbDept dept) throws Exception;
}
