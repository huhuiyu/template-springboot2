package top.huhuiyu.springboot2.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2.base.BaseResult;

@RestController
public class IndexController {

  @GetMapping("")
  public BaseResult index(String name) {
    return BaseResult.ok(String.format("%s你好", StringUtils.hasText(name) ? name : "黑暗骑士"));
  }
}
