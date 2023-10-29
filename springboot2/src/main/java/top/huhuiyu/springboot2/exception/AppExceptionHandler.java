package top.huhuiyu.springboot2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.huhuiyu.springboot2.base.BaseResult;

import java.sql.SQLException;
import java.util.List;

/**
 * 异常统一处理
 *
 * @author 胡辉煜
 */
@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

  /**
   * 自定义异常处理
   *
   * @param ex 异常信息
   * @return 自定义应答结果
   */
  @ExceptionHandler({AppException.class})
  @ResponseBody
  public BaseResult handler(AppException ex) {
    log.error("自定义异常", ex);
    return BaseResult.fail(ex.getMessage());
  }

  /**
   * json提交数据校验错误处理
   *
   * @param ex 异常信息
   * @return 标准应答结果
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public BaseResult handler(MethodArgumentNotValidException ex) {
    // 获取JSON（@RequestBody）校验错误信息
    log.debug("校验异常：{}", ex.getMessage());
    String errorInfo = buildErrorInfo(ex.getAllErrors());
    return BaseResult.fail(501, errorInfo);
  }

  /**
   * form提交数据校验错误处理
   *
   * @param ex 异常信息
   * @return 标准应答结果
   */
  @ExceptionHandler(BindException.class)
  @ResponseBody
  public BaseResult handler(BindException ex) {
    log.debug("校验异常：{}", ex.getMessage());
    String errorInfo = buildErrorInfo(ex.getAllErrors());
    return BaseResult.fail(502, errorInfo);
  }

  /**
   * 构造校验错误信息
   *
   * @param errors 校验错误列表
   * @return 校验错误信息
   */
  private static String buildErrorInfo(List<ObjectError> errors) {
    // 拼接错误信息
    StringBuilder sb = new StringBuilder();
    for (ObjectError oe : errors) {
      sb.append(oe.getDefaultMessage()).append(",");
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  /**
   * 数据库操作异常处理
   *
   * @param ex 异常信息
   * @return 标准应答结果
   */
  @ExceptionHandler({DataAccessException.class})
  @ResponseBody
  public BaseResult handler(DataAccessException ex) {
    log.error(String.format("数据库操作异常%s", ex.getMessage()), ex);
    return BaseResult.fail(520, "数据信息异常，请联系网站管理员");
  }

  /**
   * 数据库异常处理
   *
   * @param ex 异常信息
   * @return 标准应答结果
   */
  @ExceptionHandler({SQLException.class})
  @ResponseBody
  public BaseResult handler(SQLException ex) {
    log.error(String.format("sql异常%s", ex.getMessage()), ex);
    return BaseResult.fail(521, "数据处理发生异常");
  }

  /**
   * 标准异常处理
   *
   * @param ex 异常信息
   * @return 标准应答结果
   */
  @ExceptionHandler({Exception.class})
  @ResponseBody
  public BaseResult handler(Exception ex) {
    log.error("系统异常", ex);
    return BaseResult.fail(ex.getMessage());
  }
}
