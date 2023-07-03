package top.huhuiyu.template.maven.springsecurity.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.template.maven.springsecurity.base.BaseResult;

@Api(tags = "首页")
@ApiSupport(order = 100)
@RestController
public class IndexController {
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "回声测试", notes = "参数通过应答message返回")
  @ApiImplicitParams({@ApiImplicitParam(name = "echo", value = "回声参数", dataTypeClass = String.class)})
  @GetMapping("")
  public BaseResult<String> index(String echo) throws Exception {
    return StringUtils.hasText(echo) ? BaseResult.getSuccessResult(echo) : BaseResult.getFailResult("缺少echo参数");
  }
}
