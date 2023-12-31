package top.huhuiyu.template.maven.springsecurity.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.template.maven.springsecurity.base.BaseResult;
import top.huhuiyu.template.maven.springsecurity.dao.TbSecurityUserMapper;
import top.huhuiyu.template.maven.springsecurity.security.MyAuthenticationToken;
import top.huhuiyu.template.maven.springsecurity.service.SecurityService;

@Api(tags = "首页")
@ApiSupport(order = 100)
@RestController
public class IndexController {
  private static Logger logger = LoggerFactory.getLogger(IndexController.class);
  private final SecurityService securityService;
  private final TbSecurityUserMapper tbSecurityUserMapper;

  public IndexController(SecurityService securityService, TbSecurityUserMapper tbSecurityUserMapper) {
    this.securityService = securityService;
    this.tbSecurityUserMapper = tbSecurityUserMapper;
  }

  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "回声测试", notes = "参数通过应答message返回")
  @ApiImplicitParams({@ApiImplicitParam(name = "echo", value = "回声参数", dataTypeClass = String.class)})
  @GetMapping("")
  public BaseResult<String> index(String echo) throws Exception {
    return StringUtils.hasText(echo) ? BaseResult.getSuccessResult(echo) : BaseResult.getFailResult("缺少echo参数");
  }

  @ApiOperationSupport(order = 101)
  @ApiOperation(value = "获取登录用户信息", notes = "获取登录用户信息，需要user角色登录")
  @GetMapping("/auth/user/userinfo")
  public BaseResult<Object> userinfo() throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    BaseResult<Object> result = BaseResult.getSuccessResult("");
    MyAuthenticationToken token = (MyAuthenticationToken) authentication;
    result.setData(token.getUser());
    return result;
  }

  @ApiOperationSupport(order = 102)
  @ApiOperation(value = "获取登录用户信息", notes = "获取登录用户信息，需要admin角色登录")
  @GetMapping("/auth/admin/userinfo")
  public BaseResult<Object> admininfo() throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    BaseResult<Object> result = BaseResult.getSuccessResult("");
    MyAuthenticationToken token = (MyAuthenticationToken) authentication;
    result.setData(token.getUser());
    return result;
  }
}
