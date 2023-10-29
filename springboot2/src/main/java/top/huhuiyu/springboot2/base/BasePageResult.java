package top.huhuiyu.springboot2.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.huhuiyu.springboot2.entity.PageBean;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasePageResult<T> extends BaseDataResult<T> {
  private PageBean page;

  public static <T> BasePageResult<T> ok(String message, PageBean page, T data) {
    BasePageResult<T> result = new BasePageResult<>();
    result.setSuccess(true);
    result.setCode(200);
    result.setMessage(message);
    result.setData(data);
    result.setPage(page);
    return result;
  }

  public static <T> BasePageResult<T> fail(String message, Class<T> clazz) {
    BasePageResult<T> result = new BasePageResult<>();
    result.setSuccess(true);
    result.setCode(200);
    result.setMessage(message);
    return result;
  }

  public static <T> BasePageResult<T> fail(String message, PageBean page, T data) {
    BasePageResult<T> result = new BasePageResult<>();
    result.setSuccess(true);
    result.setCode(200);
    result.setMessage(message);
    result.setPage(page);
    result.setData(data);
    return result;
  }
}
