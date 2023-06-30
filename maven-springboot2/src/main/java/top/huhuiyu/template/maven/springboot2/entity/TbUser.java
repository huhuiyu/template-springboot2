package top.huhuiyu.template.maven.springboot2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import top.huhuiyu.template.maven.springboot2.util.JsonUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_user表对应的实体类
 *
 * @author 胡辉煜
 */
@ApiModel(value = "用户信息", description = "用户信息")
public class TbUser implements Serializable {
  private static final long serialVersionUID = 1L;
  @ApiModelProperty(value = "用户编号，主键", example = "1")
  @ApiParam(hidden = true)
  private Integer uid;
  @ApiModelProperty(value = "登录名", example = "user")
  @ApiParam(hidden = true)
  private String username;
  @ApiModelProperty(value = "密码", example = "user")
  @ApiParam(hidden = true)
  private String password;
  @ApiModelProperty(value = "用户名", example = "黑暗骑士")
  @ApiParam(hidden = true)
  private String nickname;
  @ApiModelProperty(value = "是否启用(y/n)", example = "y")
  @ApiParam(hidden = true)
  private String enable;
  @ApiModelProperty(value = "用户注册时间", example = "1687680479856")
  @ApiParam(hidden = true)
  private Date lastupdate;

  public TbUser() {
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEnable() {
    return enable;
  }

  public void setEnable(String enable) {
    this.enable = enable;
  }

  public Date getLastupdate() {
    return lastupdate;
  }

  public void setLastupdate(Date lastupdate) {
    this.lastupdate = lastupdate;
  }

  @Override
  public String toString() {
    try {
      return JsonUtil.stringify(this);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
