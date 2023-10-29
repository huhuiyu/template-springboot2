package top.huhuiyu.springboot2.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.dao.TbAuthRoleDAO;
import top.huhuiyu.springboot2.dao.TbAuthUserDAO;
import top.huhuiyu.springboot2.dao.TbAuthUserInfoDAO;
import top.huhuiyu.springboot2.entity.TbAuthUser;
import top.huhuiyu.springboot2.service.AuthService;
import top.huhuiyu.springboot2.utils.JwtUtils;
import top.huhuiyu.springboot2.utils.Utils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
  private final TbAuthUserDAO tbAuthUserDAO;
  private final TbAuthUserInfoDAO tbAuthUserInfoDAO;
  private final TbAuthRoleDAO tbAuthRoleDAO;

  @Override
  public BaseResult login(TbAuthUser user) {

    // 通过姓名查询用户是否存在
    TbAuthUser check = tbAuthUserDAO.queryByName(user);
    if (check == null) {
      return BaseResult.fail("用户不存在");
    }
    // 校验密码
    String pwd = Utils.saltMd5(user.getPassword(), check.getSalt());
    if (!pwd.equalsIgnoreCase(check.getPassword())) {
      return BaseResult.fail("密码不正确");
    }
    // 用户冻结的情况
    if ("n".equalsIgnoreCase(check.getEnable())) {
      return BaseResult.fail("用户已经被冻结");
    }
    // 生成jwt
    BaseResult result = BaseResult.ok("登录成功");
    result.setToken(JwtUtils.makeUserIdToken(check.getUserId()));
    return result;

  }
}
