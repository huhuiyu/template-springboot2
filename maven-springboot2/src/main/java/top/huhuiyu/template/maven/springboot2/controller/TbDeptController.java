package top.huhuiyu.template.maven.springboot2.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.huhuiyu.template.maven.springboot2.aop.AnnoNoToken;
import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.entity.DeptAndEmployeeList;
import top.huhuiyu.template.maven.springboot2.entity.TbDept;
import top.huhuiyu.template.maven.springboot2.entity.TbEmployee;
import top.huhuiyu.template.maven.springboot2.service.TbDeptService;
import top.huhuiyu.template.maven.springboot2.service.TbEmployeeService;
import top.huhuiyu.template.maven.springboot2.validate.TbDeptValidate;

import java.util.List;

@AnnoNoToken
@Api(tags = "部门和员工管理")
@ApiSupport(order = 101)
@RestController
@RequestMapping("/manage")
public class TbDeptController {
  private final TbDeptService tbDeptService;
  private final TbEmployeeService tbEmployeeService;

  public TbDeptController(TbDeptService tbDeptService, TbEmployeeService tbEmployeeService) {
    this.tbDeptService = tbDeptService;
    this.tbEmployeeService = tbEmployeeService;
  }

  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "部门查询", notes = "部门信息查询")
  @ApiImplicitParams({@ApiImplicitParam(name = "deptName", value = "部门名称模糊查询", dataTypeClass = String.class)})
  @GetMapping("/dept/queryAll")
  public BaseResult<List<TbDept>> queryAll(TbDept dept) throws Exception {
    return tbDeptService.queryAll(dept);
  }

  @ApiOperationSupport(order = 11)
  @ApiOperation(value = "添加部门", notes = "部门信息添加，本接口参数需要json格式提交")
  @ApiImplicitParams({@ApiImplicitParam(name = "deptName", value = "部门名称", dataTypeClass = String.class, required = true), @ApiImplicitParam(name = "deptInfo", value = "部门描述", dataTypeClass = String.class, required = true)})
  @PostMapping("/dept/add")
  public BaseResult<TbDept> add(@Validated(TbDeptValidate.Add.class) @RequestBody TbDept dept) throws Exception {
    return tbDeptService.add(dept);
  }

  @ApiOperationSupport(order = 12)
  @ApiOperation(value = "删除部门", notes = "部门信息删除，参数通过路径传入")
  @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "部门编号", dataTypeClass = Integer.class, required = true)})
  @DeleteMapping("/dept/delete/{id}")
  public BaseResult<String> delete(@PathVariable(name = "id") Integer deptId) throws Exception {
    TbDept dept = new TbDept();
    dept.setDeptId(deptId);
    return tbDeptService.delete(dept);
  }

  @ApiOperationSupport(order = 13)
  @ApiOperation(value = "修改部门", notes = "部门信息修改")
  @ApiImplicitParams({@ApiImplicitParam(name = "deptId", value = "部门编号", dataTypeClass = Integer.class, required = true), @ApiImplicitParam(name = "deptName", value = "部门名称", dataTypeClass = String.class, required = true), @ApiImplicitParam(name = "deptInfo", value = "部门描述", dataTypeClass = String.class, required = true)})
  @PostMapping("/dept/modify")
  public BaseResult<String> modify(@Validated(TbDeptValidate.Modify.class) TbDept dept) throws Exception {
    return tbDeptService.modify(dept);
  }

  @ApiOperationSupport(order = 14)
  @ApiOperation(value = "按照主键查询部门信息", notes = "按照主键查询部门信息，包括部门的员工列表")
  @ApiImplicitParams({@ApiImplicitParam(name = "deptId", value = "部门编号", dataTypeClass = Integer.class, required = true)})
  @PostMapping("/dept/queryById")
  public BaseResult<List<DeptAndEmployeeList>> queryById(TbDept dept) throws Exception {
    return tbDeptService.queryById(dept);
  }

  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "员工查询", notes = "员工信息查询")
  @ApiImplicitParams({@ApiImplicitParam(name = "deptId", value = "所属部门", dataTypeClass = Integer.class), @ApiImplicitParam(name = "employeeName", value = "员工名称模糊查询", dataTypeClass = String.class), @ApiImplicitParam(name = "phone", value = "员工电话模糊查询", dataTypeClass = String.class)})
  @PostMapping("/employee/queryAll")
  public BaseResult<List<TbEmployee>> queryAll(TbEmployee employee) throws Exception {
    return tbEmployeeService.queryAll(employee);
  }

  @ApiOperationSupport(order = 31)
  @ApiOperation(value = "员工（关系映射）查询", notes = "员工信息查询（关系映射版本）")
  @ApiImplicitParams({@ApiImplicitParam(name = "deptId", value = "所属部门", dataTypeClass = Integer.class), @ApiImplicitParam(name = "employeeName", value = "员工名称模糊查询", dataTypeClass = String.class), @ApiImplicitParam(name = "phone", value = "员工电话模糊查询", dataTypeClass = String.class)})
  @PostMapping("/employee/queryAllAssociation")
  public BaseResult<List<TbEmployee>> queryAllAssociation(TbEmployee employee) throws Exception {
    return tbEmployeeService.queryAllAssociation(employee);
  }

}
