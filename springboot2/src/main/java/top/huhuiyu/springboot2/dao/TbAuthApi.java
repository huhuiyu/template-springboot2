package top.huhuiyu.springboot2.dao;

import java.io.Serializable;
import java.util.Date;

public class TbAuthApi implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer apiId;
  private String url;
  private String method;
  private String info;
  private String enable;
  private Date lastupdate;
}
