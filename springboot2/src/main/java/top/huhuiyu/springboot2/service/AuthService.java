package top.huhuiyu.springboot2.service;

import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.TbAuthUser;

public interface AuthService {
  BaseResult login(TbAuthUser user);
}
