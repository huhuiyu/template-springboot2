package top.huhuiyu.template.maven.springboot2.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 控制器切点定义
 *
 * @author 胡辉煜
 */
public interface BaseAop {

  @Pointcut("execution(public * top.huhuiyu.template.maven.springboot2.controller..*.*(..))")
  default void controller() {
  }

}