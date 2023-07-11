package top.huhuiyu.template.maven.springsecurity.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.huhuiyu.template.maven.springsecurity.dao.TbSecurityUserMapper;
import top.huhuiyu.template.maven.springsecurity.entity.TbSecurityUser;
import top.huhuiyu.template.maven.springsecurity.service.SecurityService;
import top.huhuiyu.template.maven.springsecurity.util.JsonUtil;

@SpringBootTest
public class JwtTest {
  private static Logger logger = LoggerFactory.getLogger(JwtTest.class);
  @Autowired
  private TbSecurityUserMapper tbSecurityUserMapper;
  @Autowired
  private SecurityService securityService;

  @Test
  public void one() throws Exception {
    TbSecurityUser user = new TbSecurityUser();
    user.setUid(2);
    user = tbSecurityUserMapper.queryByUid(user);
    String token = securityService.generateToken(user);

    user = securityService.parseToken(token);
    logger.debug("token信息：{}，用户信息：{}", token, JsonUtil.stringify(user));
  }

  @Test
  public void two() throws Exception {
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyIiwiZXhwIjoxNjg5MDQ0NzI4fQ.X9iHk6lhxjuuZJFs7hvO7hzETu1tTDrgxIsEUSVB9XI";
    TbSecurityUser user = securityService.parseToken(token);
    logger.debug("用户信息：{}", JsonUtil.stringify(user));
  }
}
