package top.huhuiyu.template.maven.springboot2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.template.maven.springboot2.entity.Info;

import java.util.Date;

@RestController
public class IndexController {

  @GetMapping("")
  public String index(String echo) {
    return String.format("欢迎：%s", echo);
  }

  @GetMapping("/demo.action")
  public Info demo(String echo) {
    return new Info(new Date(), echo);
  }
}
