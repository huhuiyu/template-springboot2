package top.huhuiyu.springboot2.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbAuthUserInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer userId;
  private String nickname;
  private String sex;
  private String email;
  private String phone;
  private String imgurl;
  private Date lastupdate;

}
