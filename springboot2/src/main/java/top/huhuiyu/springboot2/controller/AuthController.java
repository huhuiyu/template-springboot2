package top.huhuiyu.springboot2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.TbAuthUser;
import top.huhuiyu.springboot2.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("")
  public BaseResult login(TbAuthUser user) {
    return authService.login(user);
  }

}
