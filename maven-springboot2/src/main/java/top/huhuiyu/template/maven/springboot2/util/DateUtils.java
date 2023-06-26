package top.huhuiyu.template.maven.springboot2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 日期工具
 *
 * @author 胡辉煜
 */
public class DateUtils {
  private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

  /**
   * 日期补充时间
   */
  public static final String ZERO_TIME = " 00:00:00";
  /**
   * 短日期格式
   */
  public static final String SHORT_FORMAT = "yyyy-MM-dd";
  /**
   * 日期时间格式
   */
  public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
  /**
   * 日期时间格式化对象
   */
  public static final DateTimeFormatter SDF = DateTimeFormatter.ofPattern(FORMAT);
  /**
   * 短日期格式化对象
   */
  public static final DateTimeFormatter SDF_SHORT = DateTimeFormatter.ofPattern(SHORT_FORMAT);
  /**
   * 短日期正则
   */
  public static final Pattern PSHORT = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
  /**
   * 长日期正则
   */
  public static final Pattern PLONG = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");


  /**
   * 格式化(yyyy-MM-dd HH:mm:ss)日期
   *
   * @param date 日期
   * @return 日期格式化结果
   */
  public static String format(Date date) {
    return SDF.format(date.toInstant().atZone(ZoneId.systemDefault()));
  }

  /**
   * 格式化日期
   *
   * @param date      日期
   * @param shortMode true：yyyy-MM-dd,false：yyyy-MM-dd HH:mm:ss
   * @return 日期格式化结果
   */
  public static String format(Date date, boolean shortMode) {
    return shortMode ? SDF_SHORT.format(date.toInstant().atZone(ZoneId.systemDefault())) : format(date);
  }

  /**
   * 转换字符为日期信息
   *
   * @param source 包含格式日期的字符串
   * @return 转换字符为日期信息的结果
   */
  public static Date parse(String source) {
    if (!StringUtils.hasText(source)) {
      return null;
    }
    source = source.trim();
    try {
      if (PLONG.matcher(source).matches()) {
        // 长日期格式
        return Date.from(LocalDateTime.parse(source, SDF).atZone(ZoneId.systemDefault()).toInstant());
      } else if (PSHORT.matcher(source).matches()) {
        // 短日期格式
        return Date.from(LocalDateTime.parse(source + ZERO_TIME, SDF).atZone(ZoneId.systemDefault()).toInstant());
      }
    } catch (Exception ex) {
      logger.debug("转换发生错误：{}", ex.getMessage());
    }
    return null;
  }

}
