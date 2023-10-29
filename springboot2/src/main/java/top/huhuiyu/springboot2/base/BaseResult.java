package top.huhuiyu.springboot2.base;

import lombok.Data;

@Data
public class BaseResult {
  private int code = 500;
  private boolean success = false;
  private String message;
  private String token;

  public static BaseResult ok(String message) {
    BaseResult result = new BaseResult();
    result.setSuccess(true);
    result.setCode(200);
    result.setMessage(message);
    return result;
  }

  public static BaseResult fail(String message) {
    BaseResult result = new BaseResult();
    result.setSuccess(false);
    result.setCode(500);
    result.setMessage(message);
    return result;
  }

}
