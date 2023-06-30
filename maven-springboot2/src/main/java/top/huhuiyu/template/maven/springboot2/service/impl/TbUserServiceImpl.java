package top.huhuiyu.template.maven.springboot2.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.dao.TbTokenMapper;
import top.huhuiyu.template.maven.springboot2.dao.TbUserMapper;
import top.huhuiyu.template.maven.springboot2.entity.AuthTokenInfo;
import top.huhuiyu.template.maven.springboot2.entity.TbUser;
import top.huhuiyu.template.maven.springboot2.service.TbUserService;
import top.huhuiyu.template.maven.springboot2.service.TokenService;
import top.huhuiyu.template.maven.springboot2.util.ApplicationUtil;

import java.util.Optional;

/**
 * 用户服务实现
 *
 * @author 胡辉煜
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TbUserServiceImpl implements TbUserService {
  private static final Logger logger = LoggerFactory.getLogger(TbUserService.class);
  private final TbTokenMapper tbTokenMapper;
  private final TbUserMapper tbUserMapper;
  private final TokenService tokenService;

  public TbUserServiceImpl(TbTokenMapper tbTokenMapper, TbUserMapper tbUserMapper, TokenService tokenService) {
    this.tbTokenMapper = tbTokenMapper;
    this.tbUserMapper = tbUserMapper;
    this.tokenService = tokenService;
  }

  @Override
  public BaseResult<TbUser> login(TbUser tbUser) throws Exception {
    BaseResult<TbUser> result = new BaseResult<>();
    // 校验用户名
    TbUser check = tbUserMapper.queryByName(tbUser);
    if (check == null) {
      result.setCode(500);
      result.setSuccess(false);
      result.setMessage("用户不存在");
      return result;
    }
    // 校验密码
    if (!check.getPassword().equalsIgnoreCase(tbUser.getPassword())) {
      result.setCode(500);
      result.setSuccess(false);
      result.setMessage("密码错误");
      return result;
    }
    // 是否启用
    if (!"y".equalsIgnoreCase(check.getEnable())) {
      result.setCode(500);
      result.setSuccess(false);
      result.setMessage("用户已经被禁用");
      return result;
    }
    // 处理token信息
    AuthTokenInfo authTokenInfo = ApplicationUtil.getApplicationContext().getBean(AuthTokenInfo.class);
    tokenService.handleTokenUser(authTokenInfo.getToken(), check);
    // 删除敏感信息
    check.setPassword("****");
    result.setCode(200);
    result.setSuccess(true);
    result.setMessage("登录成功");
    result.setData(check);
    return result;
  }

  @Override
  public BaseResult<String> logout() throws Exception {
    BaseResult<String> result = new BaseResult<>();
    AuthTokenInfo authTokenInfo = ApplicationUtil.getApplicationContext().getBean(AuthTokenInfo.class);
    tokenService.handleTokenUser(authTokenInfo.getToken(), null);
    result.setCode(200);
    result.setSuccess(true);
    result.setMessage("安全退出成功");
    return result;
  }

  @Override
  public BaseResult<TbUser> userinfo() throws Exception {
    BaseResult<TbUser> result = new BaseResult<>();
    AuthTokenInfo authTokenInfo = ApplicationUtil.getApplicationContext().getBean(AuthTokenInfo.class);
    TbUser loginUser = authTokenInfo.getLoginUser();
    Optional.ofNullable(loginUser).ifPresent(tbUser -> {
      tbUser.setPassword(null);
    });
    result.setCode(authTokenInfo.getLoginUser() == null ? 500 : 200);
    result.setSuccess(authTokenInfo.getLoginUser() != null);
    result.setMessage("");
    result.setData(authTokenInfo.getLoginUser());
    return result;
  }


}
