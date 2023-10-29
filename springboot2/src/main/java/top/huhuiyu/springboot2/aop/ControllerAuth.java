package top.huhuiyu.springboot2.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.huhuiyu.springboot2.dao.TbAuthApiDAO;
import top.huhuiyu.springboot2.dao.TbAuthRoleApiDAO;
import top.huhuiyu.springboot2.entity.AuthInfo;
import top.huhuiyu.springboot2.entity.TbAuthApi;
import top.huhuiyu.springboot2.entity.TbAuthRoleApi;
import top.huhuiyu.springboot2.exception.AppException;
import top.huhuiyu.springboot2.utils.JwtUtils;

@Aspect
@Component
@Slf4j
@Order(20)
@RequiredArgsConstructor
public class ControllerAuth implements BaseControllerAop {
  private final ThreadLocal<AuthInfo> authInfoThreadLocal;
  private final TbAuthApiDAO tbAuthApiDAO;
  private final TbAuthRoleApiDAO tbAuthRoleApiDAO;

  @Before("controller()")
  public void before(JoinPoint jp) {
    log.debug("===========================================================================");
    log.debug("授权信息拦截器");
    AuthInfo authInfo = authInfoThreadLocal.get();
    // 查询路径信息是否被数据库管理(也就是是否需要授权才能访问)
    TbAuthApi tbAuthApi = new TbAuthApi();
    tbAuthApi.setUrl(authInfo.getMappingPath());
    tbAuthApi.setMethod(authInfo.getMethod());
    tbAuthApi = tbAuthApiDAO.queryByUrl(tbAuthApi);
    if (tbAuthApi == null) {
      log.debug("{}不需要授权", authInfo.getMappingPath());
      log.debug("===========================================================================");
      return;
    }
    // 获取token解析信息
    Integer userId = JwtUtils.parseUserIdToken(authInfo.getToken());
    if (userId == null) {
      log.debug("===========================================================================");
      throw AppException.build("无法获取用户信息");
    }
    // 获取用户是否拥有路径权限
    TbAuthRoleApi tbAuthRoleApi = new TbAuthRoleApi();
    tbAuthRoleApi.setApiId(tbAuthApi.getApiId());
    // 这里使用角色字段传递用户信息，在dao的查询中会将用户信息转换成角色
    tbAuthRoleApi.setRoleId(userId);
    tbAuthRoleApi = tbAuthRoleApiDAO.queryByUser(tbAuthRoleApi);
    if (tbAuthRoleApi == null) {
      log.debug("===========================================================================");
      throw AppException.build("请使用相关角色登录");
    }
    // 放置用户信息到授权对象中，方便其它服务使用
    authInfo.setUserId(userId);
    log.debug("授权校验通过：{},{}", userId, tbAuthApi);
    log.debug("===========================================================================");
  }

}
