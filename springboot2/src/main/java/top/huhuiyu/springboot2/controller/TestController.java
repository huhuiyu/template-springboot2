package top.huhuiyu.springboot2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2.base.BaseDataResult;
import top.huhuiyu.springboot2.entity.AuthInfo;
import top.huhuiyu.springboot2.service.AuthService;
import top.huhuiyu.springboot2.vo.UserVO;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {
  private final ThreadLocal<AuthInfo> authInfoThreadLocal;
  private final AuthService authService;

  @GetMapping("/user/auth")
  public BaseDataResult<UserVO> userAuth() {
    return BaseDataResult.ok("", authService.queryUserById(authInfoThreadLocal.get().getUserId()).toViewData());
  }

  @GetMapping("/admin/auth")
  public BaseDataResult<UserVO> adminAuth() {
    return BaseDataResult.ok("", authService.queryUserById(authInfoThreadLocal.get().getUserId()).toViewData());
  }
}
