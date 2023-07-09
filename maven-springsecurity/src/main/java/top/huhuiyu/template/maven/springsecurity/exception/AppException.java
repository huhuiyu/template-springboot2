package top.huhuiyu.template.maven.springsecurity.exception;

/**
 * 自定义异常
 *
 * @author 胡辉煜
 */
public class AppException extends Exception {
  private int code;

  public AppException(String message) {
    super(message);
  }

  public AppException(int code, String message) {
    super(message);
    this.code = code;
  }

  public static AppException getInstance(int code, String message) {
    AppException exception = new AppException(code, message);
    return exception;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
