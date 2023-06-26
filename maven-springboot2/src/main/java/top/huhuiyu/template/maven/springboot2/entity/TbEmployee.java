package top.huhuiyu.template.maven.springboot2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_employee表的实体类
 *
 * @author 胡辉煜
 */
@ApiModel(value = "员工信息", description = "员工信息")
public class TbEmployee implements Serializable {
  private static final long serialVersionUID = -2866499353041886410L;

  @ApiModelProperty(value = "员工编号，主键", example = "1")
  @ApiParam(hidden = true)
  private Integer employeeId;
  @ApiModelProperty(value = "所属部门编号，外键", example = "1")
  @ApiParam(hidden = true)
  private Integer deptId;
  @ApiModelProperty(value = "员工姓名", example = "黑暗骑士")
  @ApiParam(hidden = true)
  private String employeeName;
  @ApiModelProperty(value = "员工电话", example = "15080604020")
  @ApiParam(hidden = true)
  private String phone;
  @ApiModelProperty(value = "信息最后修改时间", example = "1687680479856")
  @ApiParam(hidden = true)
  private java.util.Date lastupdate;
  @ApiModelProperty(value = "所属部门信息")
  @ApiParam(hidden = true)
  private TbDept dept;

  public TbEmployee() {
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public Integer getDeptId() {
    return deptId;
  }

  public void setDeptId(Integer deptId) {
    this.deptId = deptId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Date getLastupdate() {
    return lastupdate;
  }

  public void setLastupdate(Date lastupdate) {
    this.lastupdate = lastupdate;
  }

  public TbDept getDept() {
    return dept;
  }

  public void setDept(TbDept dept) {
    this.dept = dept;
  }

  @Override
  public String toString() {
    return "TbEmployee{" + "employeeId=" + employeeId + ", deptId=" + deptId + ", employeeName='" + employeeName + '\'' + ", phone='" + phone + '\'' + ", lastupdate=" + lastupdate + ", dept=" + dept + '}';
  }
}