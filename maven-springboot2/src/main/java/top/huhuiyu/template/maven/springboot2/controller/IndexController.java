package top.huhuiyu.template.maven.springboot2.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.template.maven.springboot2.aop.AnnoNoToken;
import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.entity.ConverterInfo;
import top.huhuiyu.template.maven.springboot2.entity.Info;
import top.huhuiyu.template.maven.springboot2.exception.AppException;
import top.huhuiyu.template.maven.springboot2.util.IpUtil;

import java.math.BigDecimal;
import java.util.Date;

@AnnoNoToken
@Api(tags = "首页")
@ApiSupport(order = 100)
@RestController
public class IndexController {
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "回声测试", notes = "参数通过应答message返回")
  @ApiImplicitParams({@ApiImplicitParam(name = "echo", value = "回声参数", dataTypeClass = String.class)})
  @GetMapping("")
  public String index(String echo) {
    return String.format("欢迎：%s", echo);
  }

  @ApiOperationSupport(order = 101)
  @ApiOperation(value = "json应答的回声测试", notes = "参数通过应答对象返回")
  @ApiImplicitParams({@ApiImplicitParam(name = "echo", value = "回声参数", dataTypeClass = String.class)})
  @GetMapping("/demo.action")
  public Info demo(String echo) throws Exception {
    return new Info(new Date(), echo, IpUtil.getIpAddress());
  }

  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "异常处理演示", notes = "异常处理演示")
  @ApiImplicitParams({@ApiImplicitParam(name = "mode", value = "异常显示模式，1：标准异常；2：应用自定义异常；其它：标准异常", dataTypeClass = String.class)})
  @GetMapping("/exception.action")
  public BaseResult<String> exception(String mode) throws Exception {
    mode = StringUtils.hasText(mode) ? mode.trim() : "";
    switch (mode) {
      case "1":
        throw new Exception("异常统一应答处理");
      case "2":
        throw AppException.getInstance(501, "自定义异常");
      default:
        throw new Exception("没有mode参数的异常统一应答处理");
    }
  }

  @ApiOperationSupport(order = 120)
  @ApiOperation(value = "转换器演示", notes = "转换器演示")
  @ApiImplicitParams({@ApiImplicitParam(name = "iv", value = "整数数据", dataTypeClass = Integer.class), @ApiImplicitParam(name = "bdv", value = "十进制数数据", dataTypeClass = BigDecimal.class), @ApiImplicitParam(name = "dv", value = "浮点数数据", dataTypeClass = Double.class), @ApiImplicitParam(name = "datev", value = "日期数据", dataTypeClass = Date.class), @ApiImplicitParam(name = "lv", value = "长整数数据", dataTypeClass = Long.class)})
  @PostMapping("/convert.action")
  public BaseResult<ConverterInfo> convert(ConverterInfo info) throws Exception {
    BaseResult<ConverterInfo> result = new BaseResult<>();
    result.setCode(200);
    result.setSuccess(true);
    result.setData(info);
    return result;
  }
}
