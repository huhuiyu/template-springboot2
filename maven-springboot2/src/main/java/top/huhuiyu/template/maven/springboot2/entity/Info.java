package top.huhuiyu.template.maven.springboot2.entity;

import java.io.Serializable;
import java.util.Date;

public class Info implements Serializable {
  private static final long serialVersionUID = 1L;
  private Date now;
  private String message;

  public Info() {
  }

  public Info(Date now, String message) {
    this.now = now;
    this.message = message;
  }

  public Date getNow() {
    return now;
  }

  public void setNow(Date now) {
    this.now = now;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
