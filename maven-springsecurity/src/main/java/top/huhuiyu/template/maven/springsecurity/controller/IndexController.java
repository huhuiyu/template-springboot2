package top.huhuiyu.template.maven.springsecurity.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.huhuiyu.template.maven.springsecurity.base.BaseResult;
import top.huhuiyu.template.maven.springsecurity.dao.TbSecurityUserMapper;
import top.huhuiyu.template.maven.springsecurity.security.TokenManager;
import top.huhuiyu.template.maven.springsecurity.service.SecurityService;

@Api(tags = "首页")
@ApiSupport(order = 100)
@RestController
public class IndexController {
  private static Logger logger = LoggerFactory.getLogger(IndexController.class);
  private final SecurityService securityService;
  private final TbSecurityUserMapper tbSecurityUserMapper;
  private final TokenManager tokenManager;


  public IndexController(SecurityService securityService, TbSecurityUserMapper tbSecurityUserMapper, TokenManager tokenManager) {
    this.securityService = securityService;
    this.tbSecurityUserMapper = tbSecurityUserMapper;
    this.tokenManager = tokenManager;
  }

  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "回声测试", notes = "参数通过应答message返回")
  @ApiImplicitParams({@ApiImplicitParam(name = "echo", value = "回声参数", dataTypeClass = String.class)})
  @GetMapping("")
  public BaseResult<String> index(String echo) throws Exception {
    return StringUtils.hasText(echo) ? BaseResult.getSuccessResult(echo) : BaseResult.getFailResult("缺少echo参数");
  }

  @ApiIgnore
  @GetMapping("/login.error")
  public BaseResult<String> login_page(String echo) throws Exception {
    return BaseResult.getFailResult("需要登录");
  }

  @GetMapping("/auth/userinfo")
  public BaseResult<Object> userinfo() throws Exception {
    BaseResult<Object> result = BaseResult.getSuccessResult("");
    result.setToken(tokenManager.getToken());
    result.setData(tokenManager.get(tokenManager.getToken()));
    return result;
  }


}
