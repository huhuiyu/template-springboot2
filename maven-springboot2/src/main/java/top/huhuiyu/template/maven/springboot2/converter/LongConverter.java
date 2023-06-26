package top.huhuiyu.template.maven.springboot2.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 长整数转换器
 *
 * @author 胡辉煜
 */
public class LongConverter implements Converter<String, Long> {

  @Override
  public Long convert(String source) {
    if (!StringUtils.hasText(source)) {
      return null;
    }
    source = source.trim();
    try {
      return Long.parseLong(source);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

}
