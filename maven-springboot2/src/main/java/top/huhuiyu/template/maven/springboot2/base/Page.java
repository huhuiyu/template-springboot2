package top.huhuiyu.template.maven.springboot2.base;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;

/**
 * 分页信息
 *
 * @author 胡辉煜
 */

public class Page implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final int PAGE_SIZE = 10;
  public static final int PAGE_SIZE_MAX = 1000;
  public static final int PAGE_SIZE_MIN = 1;
  public static final int PAGE_NUMBER_MIN = 1;

  @ApiModelProperty(value = "分页大小", example = "10")
  @ApiParam(hidden = true)
  private int pageSize = PAGE_SIZE;

  @ApiModelProperty(value = "当前页码", example = "1")
  @ApiParam(hidden = true)
  private int pageNumber = PAGE_NUMBER_MIN;
  @ApiModelProperty(value = "分页总数", example = "24")
  @ApiParam(hidden = true)
  private int pageCount;
  @ApiModelProperty(value = "记录总数", example = "234")
  @ApiParam(hidden = true)
  private long total;

  public Page() {
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public void setPageInfo(PageInfo<?> pageInfo) {
    this.setPageNumber(pageInfo.getPageNum());
    this.setPageSize(pageInfo.getPageSize());
    this.setPageCount(pageInfo.getPages());
    this.setTotal(pageInfo.getTotal());
  }
}
