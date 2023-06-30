package top.huhuiyu.template.maven.springboot2.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 请求作用域的token相关信息
 *
 * @author 胡辉煜
 */
@Component
@Scope("request")
public class AuthTokenInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  private String token;
  private String ip;
  private TbUser loginUser;

  public AuthTokenInfo() {
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public TbUser getLoginUser() {
    return loginUser;
  }

  public void setLoginUser(TbUser loginUser) {
    this.loginUser = loginUser;
  }
}
