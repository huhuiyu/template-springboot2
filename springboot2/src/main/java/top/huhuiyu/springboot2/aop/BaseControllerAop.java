package top.huhuiyu.springboot2.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 控制器切点定义
 *
 * @author 胡辉煜
 */
public interface BaseControllerAop {

  @Pointcut("execution(public * top.huhuiyu.springboot2.controller..*.*(..))")
  default void controller() {
  }

}