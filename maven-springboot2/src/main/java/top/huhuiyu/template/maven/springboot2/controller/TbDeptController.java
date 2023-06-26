package top.huhuiyu.template.maven.springboot2.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.entity.TbDept;
import top.huhuiyu.template.maven.springboot2.service.TbDeptService;

import java.util.List;

@Api(tags = "部门管理")
@ApiSupport(order = 101)
@RestController
@RequestMapping("/dept")
public class TbDeptController {
  private final TbDeptService tbDeptService;

  public TbDeptController(TbDeptService tbDeptService) {
    this.tbDeptService = tbDeptService;
  }

  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "部门查询", notes = "部门信息查询")
  @ApiImplicitParams({@ApiImplicitParam(name = "deptName", value = "部门名称模糊查询", dataTypeClass = String.class)})
  @GetMapping("/queryAll")
  public BaseResult<List<TbDept>> queryAll(TbDept dept) throws Exception {
    return tbDeptService.queryAll(dept);
  }

  @ApiOperationSupport(order = 11)
  @ApiOperation(value = "添加部门", notes = "部门信息添加，本接口参数需要json格式提交")
  @ApiImplicitParams({@ApiImplicitParam(name = "deptName", value = "部门名称", dataTypeClass = String.class, required = true), @ApiImplicitParam(name = "deptInfo", value = "部门描述", dataTypeClass = String.class, required = true)})
  @PostMapping("/add")
  public BaseResult<TbDept> add(@RequestBody TbDept dept) throws Exception {
    return tbDeptService.add(dept);
  }

  @ApiOperationSupport(order = 12)
  @ApiOperation(value = "删除部门", notes = "部门信息删除，参数通过路径传入")
  @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "部门编号", dataTypeClass = Integer.class, required = true)})
  @DeleteMapping("/delete/{id}")
  public BaseResult<String> delete(@PathVariable(name = "id") Integer deptId) throws Exception {
    TbDept dept = new TbDept();
    dept.setDeptId(deptId);
    return tbDeptService.delete(dept);
  }
}
