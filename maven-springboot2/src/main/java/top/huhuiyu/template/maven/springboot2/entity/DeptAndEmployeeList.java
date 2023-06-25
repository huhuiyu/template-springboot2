package top.huhuiyu.template.maven.springboot2.entity;

import java.io.Serializable;
import java.util.List;

public class DeptAndEmployeeList extends TbDept implements Serializable {
  private static final long serialVersionUID = 1L;
  List<TbEmployee> employees;

  public DeptAndEmployeeList() {
  }

  public List<TbEmployee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<TbEmployee> employees) {
    this.employees = employees;
  }

  @Override
  public String toString() {
    return super.toString() + ",DeptAndEmployeeList{" + "employees=" + employees + '}';
  }
}
