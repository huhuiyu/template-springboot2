package top.huhuiyu.template.maven.springboot2.controller;

import org.springframework.web.bind.annotation.*;
import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.entity.TbDept;
import top.huhuiyu.template.maven.springboot2.service.TbDeptService;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class TbDeptController {
  private final TbDeptService tbDeptService;

  public TbDeptController(TbDeptService tbDeptService) {
    this.tbDeptService = tbDeptService;
  }

  @GetMapping("/queryAll")
  public BaseResult<List<TbDept>> queryAll(TbDept dept) throws Exception {
    return tbDeptService.queryAll(dept);
  }

  @PostMapping("/add")
  public BaseResult<TbDept> add(@RequestBody TbDept dept) throws Exception {
    return tbDeptService.add(dept);
  }

  @DeleteMapping("/delete/{id}")
  public BaseResult<TbDept> delete(@PathVariable(name = "id") Integer deptId) throws Exception {
    TbDept dept = new TbDept();
    dept.setDeptId(deptId);
    return tbDeptService.delete(dept);
  }
}
