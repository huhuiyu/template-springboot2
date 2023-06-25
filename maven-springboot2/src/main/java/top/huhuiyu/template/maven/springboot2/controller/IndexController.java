package top.huhuiyu.template.maven.springboot2.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.template.maven.springboot2.entity.Info;

import java.util.Date;

@Api(tags = "首页")
@ApiSupport(order = 100)
@RestController
public class IndexController {
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "回声测试", notes = "参数通过应答message返回")
  @ApiImplicitParams({@ApiImplicitParam(name = "echo", value = "回声参数", dataTypeClass = String.class)})
  @GetMapping("")
  public String index(String echo) {
    return String.format("欢迎：%s", echo);
  }

  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "json应答的回声测试", notes = "参数通过应答对象返回")
  @ApiImplicitParams({@ApiImplicitParam(name = "echo", value = "回声参数", dataTypeClass = String.class)})
  @GetMapping("/demo.action")
  public Info demo(String echo) {
    return new Info(new Date(), echo);
  }
}
