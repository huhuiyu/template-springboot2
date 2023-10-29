package top.huhuiyu.springboot2.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbAuthRole implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer roleId;
  private String roleName;
  private String roleInfo;
  private Date lastupdate;
}
