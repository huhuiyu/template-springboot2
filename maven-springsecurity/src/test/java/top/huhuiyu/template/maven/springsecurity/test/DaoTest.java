package top.huhuiyu.template.maven.springsecurity.test;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.huhuiyu.template.maven.springsecurity.dao.TbSecurityUserMapper;
import top.huhuiyu.template.maven.springsecurity.entity.TbSecurityUser;
import top.huhuiyu.template.maven.springsecurity.util.JsonUtil;

@SpringBootTest
public class DaoTest {

  private static Logger logger = LoggerFactory.getLogger(DaoTest.class);
  @Autowired
  private TbSecurityUserMapper tbSecurityUserMapper;

  @Test
  public void one() throws Exception {
    TbSecurityUser user = new TbSecurityUser();
    user.setUsername("user");
    TbSecurityUser check = tbSecurityUserMapper.queryByName(user);
    logger.info("用户信息：{}", JsonUtil.stringify(check));

    user.setUsername("null");
    check = tbSecurityUserMapper.queryByName(user);
    logger.info("用户信息：{}", JsonUtil.stringify(check));
  }
}
