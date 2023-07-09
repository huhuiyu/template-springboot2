package top.huhuiyu.template.maven.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SecurityService {
  /**
   * 加载用户信息
   *
   * @param username 用户名
   * @return 用户详细信息
   * @throws UsernameNotFoundException 获取信息发生异常
   */
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
