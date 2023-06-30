package top.huhuiyu.template.maven.springboot2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.huhuiyu.template.maven.springboot2.base.BaseResult;
import top.huhuiyu.template.maven.springboot2.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 控制器日志切面
 *
 * @author 胡辉煜
 */
@Aspect
@Component
public class ControllerToken implements BaseAop {

  private static final Logger logger = LoggerFactory.getLogger(ControllerToken.class);
  private static final String HEAD_TOKEN = "token";
  private static final String REQUEST_TOKEN_ATTRIBUTE = "javaweb_template_token";
  private final TokenService tokenService;

  public ControllerToken(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  private String handleToken(ProceedingJoinPoint pjp) throws Exception {
    logger.debug("控制器切面token处理");
    // 通过注解获取是否需要token信息
    AnnoNoToken annoNoToken = pjp.getTarget().getClass().getAnnotation(AnnoNoToken.class);
    if (annoNoToken != null) {
      logger.debug("获取到AnnoNoToken信息的对象:{}", pjp.getTarget().getClass().getName());
      return "";
    }
    MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    annoNoToken = methodSignature.getMethod().getAnnotation(AnnoNoToken.class);
    if (annoNoToken != null) {
      logger.debug("获取到AnnoNoToken信息的方法:{}", methodSignature.getMethod().getName());
      return "";
    }
    // 获取请求中的token信息
    ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = sra.getRequest();
    // 获取请求头中的token
    String token = request.getHeader(HEAD_TOKEN);
    // 不存在就获取请求中的token
    if (!StringUtils.hasText(token)) {
      token = request.getParameter(REQUEST_TOKEN_ATTRIBUTE);
    }
    token = Optional.ofNullable(token).orElse("");
    logger.debug("客户拿到的token信息：{}", token);
    // 处理token信息
    token = tokenService.handleToken(token);
    return token;
  }

  @Around("controller()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    logger.debug("控制器环绕切面");
    // 处理token信息
    String token = handleToken(pjp);
    // 执行控制器方法 =======================================================
    Object result = pjp.proceed();
    // 处理应答结果 =========================================================
    // 如果控制器返回的是标准应答结果就添加token信息
    if ((StringUtils.hasText(token)) && (result instanceof BaseResult)) {
      BaseResult message = (BaseResult) result;
      message.setToken(token);
    }
    return result;
  }

}