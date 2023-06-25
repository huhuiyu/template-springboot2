package top.huhuiyu.template.maven.springboot2.entity;

import java.io.Serializable;
import java.util.Date;


public class TbEmployee implements Serializable {
  private static final long serialVersionUID = -2866499353041886410L;

  private Integer employeeId;
  private Integer deptId;
  private String employeeName;
  private String phone;
  private java.util.Date lastupdate;
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