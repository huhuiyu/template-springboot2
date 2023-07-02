package top.huhuiyu.template.maven.springboot2.base;

import java.io.Serializable;

/**
 * WebSocket统一应答对象
 *
 * @author 胡辉煜
 */
public class BaseWebSocketResult<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final int SUCCESS_CODE = 200;
  public static final int FAIL_CODE = 500;

  /**
   * 应答是否成功
   */
  private boolean success;
  /**
   * 应答代码
   */
  private int code;
  /**
   * 应答数据
   */
  private T message;

  /**
   * 消息类型，为空表示为请求的app，其它为自定义
   */
  private String type;

  public BaseWebSocketResult() {
  }

  public static <T> BaseWebSocketResult<T> getBaseWsInfo(Boolean success, int code, T message) {
    BaseWebSocketResult<T> baseWsInfo = new BaseWebSocketResult<>();
    baseWsInfo.setCode(code);
    baseWsInfo.setSuccess(success);
    baseWsInfo.setMessage(message);
    return baseWsInfo;
  }

  public static <T> BaseWebSocketResult<T> getSuccessInfo(int code, T message) {
    return getBaseWsInfo(true, code, message);
  }

  public static <T> BaseWebSocketResult<T> getSuccessInfo(T message) {
    return getBaseWsInfo(true, SUCCESS_CODE, message);
  }

  public static <T> BaseWebSocketResult<T> getFailInfo(int code, T message) {
    return getBaseWsInfo(false, code, message);
  }

  public static <T> BaseWebSocketResult<T> getFailInfo(T message) {
    return getBaseWsInfo(false, FAIL_CODE, message);
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public T getMessage() {
    return message;
  }

  public void setMessage(T message) {
    this.message = message;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
