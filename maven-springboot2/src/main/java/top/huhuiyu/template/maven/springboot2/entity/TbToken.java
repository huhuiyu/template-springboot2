package top.huhuiyu.template.maven.springboot2.entity;

import org.springframework.util.StringUtils;
import top.huhuiyu.template.maven.springboot2.util.JsonUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_tokan表的实体类
 *
 * @author 胡辉煜
 */
public class TbToken implements Serializable {
  private static final long serialVersionUID = 1L;

  private String token;
  private String tokenInfo;
  private Date lastupdate;

  public TbToken() {
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getTokenInfo() {
    return tokenInfo;
  }

  public void setTokenInfo(String tokenInfo) {
    this.tokenInfo = tokenInfo;
  }

  public Date getLastupdate() {
    return lastupdate;
  }

  public void setLastupdate(Date lastupdate) {
    this.lastupdate = lastupdate;
  }

  /**
   * 获取tokenInfo信息对应的java对象
   *
   * @return tokenInfo信息对应的java对象
   */
  public TokenInfo content() {
    try {
      if (StringUtils.hasText(this.tokenInfo)) {
        return JsonUtil.parse(this.tokenInfo, TokenInfo.class);
      }
    } catch (Exception ex) {
      return null;
    }
    return null;
  }

  @Override
  public String toString() {
    return "TbToken{" + "token='" + token + '\'' + ", tokenInfo='" + tokenInfo + '\'' + ", lastupdate=" + lastupdate + '}';
  }
}
