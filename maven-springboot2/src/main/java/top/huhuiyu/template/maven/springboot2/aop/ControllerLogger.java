package top.huhuiyu.template.maven.springboot2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 控制器日志切面
 *
 * @author 胡辉煜
 */
@Aspect
@Component
public class ControllerLogger implements BaseAop {

  private static final Logger logger = LoggerFactory.getLogger(ControllerLogger.class);

  @Before("controller()")
  public void before(JoinPoint jp) {
    logger.debug("日志切面：进入===>{}", jp.getSignature());
    Object[] args = jp.getArgs();
    if (args == null || args.length == 0) {
      logger.debug("日志切面：方法没有参数");
    } else {
      logger.debug("日志切面：参数列表：");
      for (Object arg : args) {
        logger.debug("{}", arg);
      }
    }
  }

  @After("controller()")
  public void after(JoinPoint jp) {
    logger.debug("日志切面：{}执行完毕", jp.getSignature());
  }

  @AfterReturning(pointcut = "controller()", returning = "result")
  public void returning(JoinPoint jp, Object result) {
    logger.debug("日志切面：{}返回值：{}", jp.getSignature(), result);
  }

}