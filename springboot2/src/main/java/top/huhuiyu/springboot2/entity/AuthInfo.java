package top.huhuiyu.springboot2.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求中的认证信息
 *
 * @author 胡辉煜
 */
@Data
public class AuthInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  private String ip;
  private Integer userId;
  private String mappingPath;
  private String requestUrl;
  private String method;
  private String token;

}
