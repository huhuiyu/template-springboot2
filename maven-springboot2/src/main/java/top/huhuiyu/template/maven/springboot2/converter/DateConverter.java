package top.huhuiyu.template.maven.springboot2.converter;

import org.springframework.core.convert.converter.Converter;
import top.huhuiyu.template.maven.springboot2.util.DateUtils;

import java.util.Date;

/**
 * 日期转换器
 *
 * @author 胡辉煜
 */
public class DateConverter implements Converter<String, Date> {

  @Override
  public Date convert(String source) {
    return DateUtils.parse(source);
  }

}
