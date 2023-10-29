package top.huhuiyu.springboot2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import top.huhuiyu.springboot2.utils.Utils;

@Slf4j
public class PwdTest {
  @Test
  public void one() {
    String salt = Utils.makeSalt();
    String pwd = "manager";
    log.debug("管理员的盐和密码：{},{}", salt, Utils.saltMd5(pwd, salt));
    salt = Utils.makeSalt();
    pwd = "userpwd";
    log.debug("内置用户的盐和密码：{},{}", salt, Utils.saltMd5(pwd, salt));
  }

}
