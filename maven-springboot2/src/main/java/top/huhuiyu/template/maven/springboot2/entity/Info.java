package top.huhuiyu.template.maven.springboot2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "首页应答", description = "首页应答信息对象")
public class Info implements Serializable {
  private static final long serialVersionUID = 1L;
  @ApiModelProperty(value = "当前时间", example = "1687680479856")
  @ApiParam(hidden = true)
  private Date now;
  @ApiModelProperty(value = "echo信息", example = "黑暗骑士")
  @ApiParam(hidden = true)
  private String message;

  @ApiModelProperty(value = "ip信息", example = "127.0.0.1")
  @ApiParam(hidden = true)
  private String ip;

  public Info() {
  }

  public Info(Date now, String message, String ip) {
    this.now = now;
    this.message = message;
    this.ip = ip;
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

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }
}
