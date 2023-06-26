package top.huhuiyu.template.maven.springboot2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "部门带员工列表信息", description = "部门带员工列表信息")
public class DeptAndEmployeeList extends TbDept implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "员工集合")
  @ApiParam(hidden = true)
  private List<TbEmployee> employees;

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
