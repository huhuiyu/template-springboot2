package top.huhuiyu.template.maven.springboot2.base;

public class BasePageResult<T> extends BaseResult<T> {

  private static final long serialVersionUID = 1L;

  private Page page;

  public BasePageResult() {
  }

  public Page getPage() {
    return page;
  }

  public void setPage(Page page) {
    this.page = page;
  }
}
