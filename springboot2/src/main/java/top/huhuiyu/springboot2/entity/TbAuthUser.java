package top.huhuiyu.springboot2.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbAuthUser implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer userId;
  private String username;
  private String password;
  private String salt;
  private String enable;
  private Integer roleId;
  private Date lastupdate;
}
