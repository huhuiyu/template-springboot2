package top.huhuiyu.template.maven.springboot2.entity;

import java.io.Serializable;

/**
 * 聊天信息
 *
 * @author 胡辉煜
 */
public class ChatInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  private String username;
  private String message;

  public ChatInfo() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
