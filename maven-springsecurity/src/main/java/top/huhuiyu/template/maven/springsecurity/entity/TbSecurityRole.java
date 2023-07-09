package top.huhuiyu.template.maven.springsecurity.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

public class TbSecurityRole implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "用户编号，主键", example = "1")
  @ApiParam(hidden = true)
  private Integer uid;
  @ApiModelProperty(value = "角色名称", example = "user")
  @ApiParam(hidden = true)
  private String roleName;
  @ApiModelProperty(value = "角色描述", example = "用户")
  @ApiParam(hidden = true)
  private String roleInfo;
  @ApiModelProperty(value = "信息最后修改时间", example = "1687680479856")
  @ApiParam(hidden = true)
  private Date lastupdate;

  public TbSecurityRole() {
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleInfo() {
    return roleInfo;
  }

  public void setRoleInfo(String roleInfo) {
    this.roleInfo = roleInfo;
  }

  public Date getLastupdate() {
    return lastupdate;
  }

  public void setLastupdate(Date lastupdate) {
    this.lastupdate = lastupdate;
  }

  @Override
  public String toString() {
    return "TbSecurityRole{" + "uid=" + uid + ", roleName='" + roleName + '\'' + ", roleInfo='" + roleInfo + '\'' + ", lastupdate=" + lastupdate + '}';
  }
}
