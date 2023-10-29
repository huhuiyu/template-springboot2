package top.huhuiyu.springboot2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(10)
public class ControllerLogger implements BaseControllerAop {
  @Before("controller()")
  public void before(JoinPoint jp) {
    log.debug("进入===>{}", jp.getSignature());
    Object[] args = jp.getArgs();
    if (args == null || args.length == 0) {
      log.debug("方法没有参数");
    } else {
      log.debug("参数列表：");
      for (Object arg : args) {
        log.debug("{}", arg);
      }
    }
  }

  @After("controller()")
  public void after(JoinPoint jp) {
    log.debug("{}执行完毕", jp.getSignature());
  }

  @AfterReturning(pointcut = "controller()", returning = "result")
  public void returning(JoinPoint jp, Object result) {
    log.debug("{}返回值：{}", jp.getSignature(), result);
  }

}