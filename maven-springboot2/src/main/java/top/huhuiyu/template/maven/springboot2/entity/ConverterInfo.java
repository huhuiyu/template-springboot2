package top.huhuiyu.template.maven.springboot2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import top.huhuiyu.template.maven.springboot2.util.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 转换器演示类
 *
 * @author 胡辉煜
 */
@ApiModel(value = "转换器演示数据类", description = "转换器演示数据类")
public class ConverterInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  @ApiModelProperty(value = "整数", example = "111")
  @ApiParam(hidden = true)
  private Integer iv;
  @ApiModelProperty(value = "十进制数", example = "111.222")
  @ApiParam(hidden = true)
  private BigDecimal bdv;
  @ApiModelProperty(value = "双精度浮点数", example = "222.333")
  @ApiParam(hidden = true)
  private Double dv;
  @ApiModelProperty(value = "时间日期", example = "2020-08-09 10:11:12")
  @ApiParam(hidden = true)
  private Date datev;
  @ApiModelProperty(value = "长整数", example = "333333")
  @ApiParam(hidden = true)
  private Long lv;

  public ConverterInfo() {
  }

  public Integer getIv() {
    return iv;
  }

  public void setIv(Integer iv) {
    this.iv = iv;
  }

  public BigDecimal getBdv() {
    return bdv;
  }

  public void setBdv(BigDecimal bdv) {
    this.bdv = bdv;
  }

  public Double getDv() {
    return dv;
  }

  public void setDv(Double dv) {
    this.dv = dv;
  }

  public Date getDatev() {
    return datev;
  }

  public void setDatev(Date datev) {
    this.datev = datev;
  }

  public Long getLv() {
    return lv;
  }

  public void setLv(Long lv) {
    this.lv = lv;
  }

  @ApiModelProperty(value = "只读字段，日期字段格式化显示结果", example = "2020-08-09 10:11:12")
  @ApiParam(hidden = true)
  public String getDateInfo() {
    return this.datev == null ? "" : DateUtils.format(this.datev);
  }
}
