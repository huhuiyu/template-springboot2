package top.huhuiyu.springboot2.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbAuthRoleApi implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer raid;
  private Integer roleId;
  private Integer apiId;
}
