package top.huhuiyu.springboot2.service;

import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.TbAuthUser;
import top.huhuiyu.springboot2.vo.UserVO;

public interface AuthService {
  UserVO queryUserById(Integer userId);

  BaseResult login(TbAuthUser user);
}
