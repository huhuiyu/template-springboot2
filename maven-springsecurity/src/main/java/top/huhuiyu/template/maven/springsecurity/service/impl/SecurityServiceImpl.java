package top.huhuiyu.template.maven.springsecurity.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.huhuiyu.template.maven.springsecurity.dao.TbSecurityUserMapper;
import top.huhuiyu.template.maven.springsecurity.entity.TbSecurityUser;
import top.huhuiyu.template.maven.springsecurity.service.SecurityService;

@Service
@Transactional(rollbackFor = Exception.class)
public class SecurityServiceImpl implements UserDetailsService, SecurityService {
  private static Logger logger = LoggerFactory.getLogger(SecurityService.class);
  private final TbSecurityUserMapper tbSecurityUserMapper;

  public SecurityServiceImpl(TbSecurityUserMapper tbSecurityUserMapper) {
    this.tbSecurityUserMapper = tbSecurityUserMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.debug("用户名：{}", username);
    TbSecurityUser user = new TbSecurityUser();
    user.setUsername(username);
    try {
      TbSecurityUser check = tbSecurityUserMapper.queryByName(user);
      if (check == null) {
        throw new UsernameNotFoundException("用户不存在");
      }
      return check.getUserDetails();
    } catch (Exception e) {
      throw new UsernameNotFoundException("用户不存在");
    }
  }


}
