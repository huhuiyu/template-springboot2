package top.huhuiyu.template.maven.springsecurity.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.huhuiyu.template.maven.springsecurity.entity.TbSecurityUser;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService extends UserDetailsService {
  /**
   * token过期时间
   */
  int TOKEN_EXPIRE_TIME = 1 * 60 * 1000;

  /**
   * 加载用户信息
   *
   * @param username 用户名
   * @return 用户详细信息
   * @throws UsernameNotFoundException 获取信息发生异常
   */
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

  /**
   * 使用用户信息生成token
   *
   * @param user 用户信息
   * @return 用户对应的token
   * @throws Exception 处理发生异常
   */
  String generateToken(TbSecurityUser user) throws Exception;

  /**
   * 从token中获取用户信息（不存在会返回null）
   *
   * @param token token信息
   * @return token对应的用户信息
   * @throws Exception 处理发生异常
   */
  TbSecurityUser parseToken(String token) throws Exception;

  /**
   * 校验url访问权限
   *
   * @param authentication 登录权限信息
   * @param request        请求信息
   * @return url访问权限
   * @throws Exception 处理发生异常
   */
  boolean checkAuth(Authentication authentication, HttpServletRequest request) throws Exception;
}
