package top.huhuiyu.template.maven.springboot2.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.huhuiyu.template.maven.springboot2.dao.TbTokenMapper;
import top.huhuiyu.template.maven.springboot2.dao.TbUserMapper;
import top.huhuiyu.template.maven.springboot2.entity.AuthTokenInfo;
import top.huhuiyu.template.maven.springboot2.entity.TbToken;
import top.huhuiyu.template.maven.springboot2.entity.TbUser;
import top.huhuiyu.template.maven.springboot2.entity.TokenInfo;
import top.huhuiyu.template.maven.springboot2.service.TokenService;
import top.huhuiyu.template.maven.springboot2.util.ApplicationUtil;
import top.huhuiyu.template.maven.springboot2.util.IpUtil;
import top.huhuiyu.template.maven.springboot2.util.JsonUtil;

import java.util.UUID;

/**
 * token服务实现
 *
 * @author 胡辉煜
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TokenServiceImpl implements TokenService {
  private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
  private final TbTokenMapper tbTokenMapper;
  private final TbUserMapper tbUserMapper;

  public TokenServiceImpl(TbTokenMapper tbTokenMapper, TbUserMapper tbUserMapper) {
    this.tbTokenMapper = tbTokenMapper;
    this.tbUserMapper = tbUserMapper;
  }

  @Override
  public String handleToken(String token) throws Exception {
    AuthTokenInfo authTokenInfo = ApplicationUtil.getApplicationContext().getBean(AuthTokenInfo.class);
    // ip信息
    String ip = IpUtil.getIpAddress();
    // 校验token是否存在
    TbToken tbToken = new TbToken();
    tbToken.setToken(token);
    tbToken = tbTokenMapper.queryByToken(tbToken);
    // 不存在就新创建并添加到数据库
    if (tbToken == null) {
      tbToken = new TbToken();
      tbToken.setToken(UUID.randomUUID().toString());
      tbToken.setTokenInfo("{}");
      tbTokenMapper.add(tbToken);
    }
    // 处理ip信息并写回数据库
    TokenInfo tokenInfo = tbToken.content();
    tokenInfo.setIp(ip);
    tbToken.setTokenInfo(JsonUtil.stringify(tokenInfo));
    tbTokenMapper.update(tbToken);
    // 放置信息到请求作用域中
    authTokenInfo.setIp(ip);
    authTokenInfo.setToken(token);
    authTokenInfo.setLoginUser(tokenInfo.getUser());
    return tbToken.getToken();
  }

  @Override
  public String handleTokenUser(String token, TbUser loginUser) throws Exception {
    AuthTokenInfo authTokenInfo = ApplicationUtil.getApplicationContext().getBean(AuthTokenInfo.class);
    // ip信息
    String ip = IpUtil.getIpAddress();
    // 校验token是否存在
    TbToken tbToken = new TbToken();
    tbToken.setToken(token);
    tbToken = tbTokenMapper.queryByToken(tbToken);
    // 不存在就新创建并添加到数据库
    if (tbToken == null) {
      tbToken = new TbToken();
      tbToken.setToken(UUID.randomUUID().toString());
      tbToken.setTokenInfo("{}");
      tbTokenMapper.add(tbToken);
    }
    // 处理ip信息并写回数据库
    TokenInfo tokenInfo = tbToken.content();
    tokenInfo.setIp(ip);
    tokenInfo.setUser(loginUser);
    tbToken.setTokenInfo(JsonUtil.stringify(tokenInfo));
    tbTokenMapper.update(tbToken);
    // 放置信息到请求作用域中
    authTokenInfo.setIp(ip);
    authTokenInfo.setToken(token);
    authTokenInfo.setLoginUser(tokenInfo.getUser());
    return tbToken.getToken();
  }

}
