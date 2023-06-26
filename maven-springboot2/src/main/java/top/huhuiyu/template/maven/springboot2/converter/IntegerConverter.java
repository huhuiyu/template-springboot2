package top.huhuiyu.template.maven.springboot2.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 整数转换器
 *
 * @author 胡辉煜
 */
public class IntegerConverter implements Converter<String, Integer> {

  @Override
  public Integer convert(String source) {
    if (!StringUtils.hasText(source)) {
      return null;
    }
    source = source.trim();
    try {
      return Integer.parseInt(source);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

}
