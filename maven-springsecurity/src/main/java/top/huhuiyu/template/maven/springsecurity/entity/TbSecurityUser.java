package top.huhuiyu.template.maven.springsecurity.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TbSecurityUser implements Serializable {
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
  @ApiModelProperty(value = "角色编号", example = "1")
  @ApiParam(hidden = true)
  private Integer rid;
  @ApiModelProperty(value = "是否启用(y/n)", example = "y")
  @ApiParam(hidden = true)
  private String enable;
  @ApiModelProperty(value = "用户注册时间", example = "1687680479856")
  @ApiParam(hidden = true)
  private Date lastupdate;
  @ApiModelProperty(value = "用户角色")
  @ApiParam(hidden = true)
  private TbSecurityRole role;

  public TbSecurityUser() {
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

  public Integer getRid() {
    return rid;
  }

  public void setRid(Integer rid) {
    this.rid = rid;
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

  public TbSecurityRole getRole() {
    return role;
  }

  public void setRole(TbSecurityRole role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "TbSecurityUser{" + "uid=" + uid + ", username='" + username + '\'' + ", password='" + password + '\'' + ", nickname='" + nickname + '\'' + ", rid=" + rid + ", enable='" + enable + '\'' + ", lastupdate=" + lastupdate + ", role=" + role + '}';
  }

  /**
   * 转换用户信息为springsecurity格式
   *
   * @return springsecurity格式用户信息
   */
  public UserDetails getUserDetails() {
    List<SimpleGrantedAuthority> list = Arrays.asList(new SimpleGrantedAuthority(this.role.getRoleName()));
    UserDetails user = new User(this.getUsername(), this.getPassword(), list);
    return user;
  }
}
