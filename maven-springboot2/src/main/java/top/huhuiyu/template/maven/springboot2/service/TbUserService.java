package top.huhuiyu.template.maven.springboot2.service;

import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.entity.TbUser;

/**
 * 用户服务层
 *
 * @author 胡辉煜
 */
public interface TbUserService {

  /**
   * 用户登录
   *
   * @param tbUser 用户西悉尼
   * @return 登录结果
   * @throws Exception 处理发生异常
   */
  BaseResult<TbUser> login(TbUser tbUser) throws Exception;

  /**
   * 安全退出
   *
   * @return 安全退出结果
   * @throws Exception 处理发生异常
   */
  BaseResult<String> logout() throws Exception;

  /**
   * 获取当前登录用户信息
   *
   * @return 当前登录用户信息
   * @throws Exception 处理发生异常
   */
  BaseResult<TbUser> userinfo() throws Exception;
}
