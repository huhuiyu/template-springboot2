package top.huhuiyu.template.maven.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.huhuiyu.template.maven.springboot2.entity.TbDept;
import top.huhuiyu.template.maven.springboot2.service.TbDeptService;

@Controller
public class TemplateController {
  private final TbDeptService tbDeptService;

  public TemplateController(TbDeptService tbDeptService) {
    this.tbDeptService = tbDeptService;
  }

  @GetMapping("/freemarker.html")
  public String freemark(TbDept dept, Model model) throws Exception {
    model.addAttribute("dept", dept);
    model.addAttribute("list", tbDeptService.queryAll(dept).getData());
    return "freemarker";
  }
}
