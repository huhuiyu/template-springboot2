package top.huhuiyu.springboot2.utils;


import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Utils {
  private static final String RAND_STRINGS = "abcdefghijklmnopqrstuvwxyz0123456789";
  public static final int SALT_LENGTH = 6;

  public static String makeRandString(int length) {
    // 字符串构造器，用于大量拼接字符串
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      sb.append(RAND_STRINGS.charAt(random.nextInt(RAND_STRINGS.length())));
    }
    return sb.toString();
  }

  public static String makeSalt() {
    return makeRandString(SALT_LENGTH);
  }

  public static String md5(String info) {
    if (StringUtils.hasText(info)) {
      return DigestUtils.md5DigestAsHex(info.getBytes(StandardCharsets.UTF_8));
    }
    return "";
  }

  public static String saltMd5(String info, String salt) {
    if (!StringUtils.hasText(info) || !StringUtils.hasText(salt)) {
      return "";
    }
    String md5 = md5(info);
    return md5(md5 + salt);
  }

}
