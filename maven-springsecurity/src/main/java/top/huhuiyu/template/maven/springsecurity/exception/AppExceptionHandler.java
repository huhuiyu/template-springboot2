package top.huhuiyu.template.maven.springsecurity.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.huhuiyu.template.maven.springsecurity.base.BaseResult;

/**
 * 异常统一处理
 *
 * @author 胡辉煜
 */
@ControllerAdvice
public class AppExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

  /**
   * 自定义异常处理
   *
   * @param ex 异常信息
   * @return 自定义应答结果
   */
  @ExceptionHandler({AppException.class})
  @ResponseBody
  public BaseResult<String> handler(AppException ex) {
    logger.error("自定义异常", ex);
    BaseResult<String> result = new BaseResult<>();
    result.setCode(ex.getCode());
    result.setSuccess(false);
    result.setMessage(ex.getMessage());
    return result;
  }

  /**
   * 标准异常处理
   *
   * @param ex 异常信息
   * @return 标准应答结果
   */
  @ExceptionHandler({Exception.class})
  @ResponseBody
  public BaseResult<String> handler(Exception ex) {
    logger.error("系统异常", ex);
    BaseResult<String> result = new BaseResult<>();
    result.setCode(500);
    result.setSuccess(false);
    result.setMessage(ex.getMessage());
    return result;
  }
}
