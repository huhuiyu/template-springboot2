package top.huhuiyu.template.maven.springboot2.service;

import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.entity.TbEmployee;

import java.util.List;

/**
 * 员工表服务层
 *
 * @author 胡辉煜
 */
public interface TbEmployeeService {

  /**
   * 查询员工信息列表
   *
   * @param employee 查询参数，可以包含以下条件：（部门编号准确查询，员工姓名和员工电话模糊查询）
   * @return 员工信息列表
   * @throws Exception 处理发生异常
   */
  BaseResult<List<TbEmployee>> queryAll(TbEmployee employee) throws Exception;

  /**
   * 查询员工信息列表（关系映射版本）
   *
   * @param employee 查询参数，可以包含以下条件：（部门编号准确查询，员工姓名和员工电话模糊查询）
   * @return 员工信息列表
   * @throws Exception 处理发生异常
   */
  BaseResult<List<TbEmployee>> queryAllAssociation(TbEmployee employee) throws Exception;
}
