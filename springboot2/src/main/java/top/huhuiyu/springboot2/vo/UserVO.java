package top.huhuiyu.springboot2.vo;

import lombok.Data;
import top.huhuiyu.springboot2.entity.TbAuthRole;
import top.huhuiyu.springboot2.entity.TbAuthUser;
import top.huhuiyu.springboot2.entity.TbAuthUserInfo;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
  private final static long serialVersionUID = 1L;
  private TbAuthUser tbAuthUser;
  private TbAuthUserInfo tbAuthUserInfo;
  private TbAuthRole tbAuthRole;

  public UserVO toViewData() {
    // 删除敏感信息后的视图信息
    if (this.tbAuthUser != null) {
      this.tbAuthUser.setPassword(null);
      this.tbAuthUser.setEnable(null);
      this.tbAuthUser.setSalt(null);
    }
    return this;
  }
}