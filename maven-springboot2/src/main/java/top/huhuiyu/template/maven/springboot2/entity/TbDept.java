package top.huhuiyu.template.maven.springboot2.entity;

import java.io.Serializable;
import java.util.Date;


public class TbDept implements Serializable {

  private static final long serialVersionUID = -1638648482804363761L;

  private Integer deptId;
  private String deptName;
  private String deptInfo;
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