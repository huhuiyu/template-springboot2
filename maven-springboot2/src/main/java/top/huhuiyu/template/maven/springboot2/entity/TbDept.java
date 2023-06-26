package top.huhuiyu.template.maven.springboot2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_dept表对应实体类
 *
 * @author 胡辉煜
 */
@ApiModel(value = "部门信息", description = "部门信息")
public class TbDept implements Serializable {

  private static final long serialVersionUID = -1638648482804363761L;

  @ApiModelProperty(value = "部门编号，主键", example = "1")
  @ApiParam(hidden = true)
  private Integer deptId;
  @ApiModelProperty(value = "部门名称", example = "开发部")
  @ApiParam(hidden = true)
  private String deptName;
  @ApiModelProperty(value = "部门描述", example = "写代码的部门")
  @ApiParam(hidden = true)
  private String deptInfo;
  @ApiModelProperty(value = "信息最后修改时间", example = "1687680479856")
  @ApiParam(hidden = true)
  private java.util.Date lastupdate;

  public TbDept() {
  }

  public Integer getDeptId() {
    return deptId;
  }

  public void setDeptId(Integer deptId) {
    this.deptId = deptId;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public String getDeptInfo() {
    return deptInfo;
  }

  public void setDeptInfo(String deptInfo) {
    this.deptInfo = deptInfo;
  }

  public Date getLastupdate() {
    return lastupdate;
  }

  public void setLastupdate(Date lastupdate) {
    this.lastupdate = lastupdate;
  }

  @Override
  public String toString() {
    return "TbDept{" + "deptId=" + deptId + ", deptName='" + deptName + '\'' + ", deptInfo='" + deptInfo + '\'' + ", lastupdate=" + lastupdate + '}';
  }
}