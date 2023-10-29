package top.huhuiyu.springboot2.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDataResult<T> extends BaseResult {

  private T data;

  public static <T> BaseDataResult<T> ok(String message, T data) {
    BaseDataResult<T> result = new BaseDataResult<>();
    result.setSuccess(true);
    result.setCode(200);
    result.setMessage(message);
    result.setData(data);
    return result;
  }

  public static <T> BaseDataResult<T> fail(String message, Class<T> clazz) {
    BaseDataResult<T> result = new BaseDataResult<>();
    result.setSuccess(true);
    result.setCode(200);
    result.setMessage(message);
    return result;
  }

  public static <T> BaseDataResult<T> fail(String message, T data) {
    BaseDataResult<T> result = new BaseDataResult<>();
    result.setSuccess(true);
    result.setCode(200);
    result.setMessage(message);
    result.setData(data);
    return result;
  }

}
