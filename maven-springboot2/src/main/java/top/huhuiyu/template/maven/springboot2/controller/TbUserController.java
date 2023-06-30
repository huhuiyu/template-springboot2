package top.huhuiyu.template.maven.springboot2.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.entity.TbUser;
import top.huhuiyu.template.maven.springboot2.service.TbUserService;

@Api(tags = "用户认证")
@ApiSupport(order = 102)
@RestController
@RequestMapping("/user")
public class TbUserController {
  private final TbUserService tbUserService;

  public TbUserController(TbUserService tbUserService) {
    this.tbUserService = tbUserService;
  }

  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "用户登录", notes = "用户登录")
  @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", dataTypeClass = String.class, required = true), @ApiImplicitParam(name = "password", value = "登陆密码", dataTypeClass = String.class, required = true)})
  @PostMapping("/login")
  public BaseResult<TbUser> login(TbUser tbUser) throws Exception {
    return tbUserService.login(tbUser);
  }

  @ApiOperationSupport(order = 11)
  @ApiOperation(value = "安全退出", notes = "安全退出")
  @PostMapping("/logout")
  public BaseResult<String> logout() throws Exception {
    return tbUserService.logout();
  }

  @ApiOperationSupport(order = 12)
  @ApiOperation(value = "获取当前登录用户信息", notes = "获取当前登录用户信息")
  @PostMapping("/userinfo")
  public BaseResult<TbUser> userinfo() throws Exception {
    return tbUserService.userinfo();
  }

}
