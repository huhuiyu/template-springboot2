package top.huhuiyu.template.maven.springboot2.service;

import top.huhuiyu.template.maven.springboot2.entity.TbUser;

/**
 * token服务层
 *
 * @author 胡辉煜
 */
public interface TokenService {
  /**
   * 处理token信息，如果token不存在就会新建一个，处理后的结果会放置到请求作用域中
   *
   * @param token 客户端提交的token值
   * @return token值
   * @throws Exception 处理发生异常
   */
  String handleToken(String token) throws Exception;

  /**
   * 处理token信息中的用户信息
   *
   * @param token     客户端提交的token值
   * @param loginUser 登录用户信息，移除请传入null
   * @return token值
   * @throws Exception 处理发生异常
   */
  String handleTokenUser(String token, TbUser loginUser) throws Exception;
}
