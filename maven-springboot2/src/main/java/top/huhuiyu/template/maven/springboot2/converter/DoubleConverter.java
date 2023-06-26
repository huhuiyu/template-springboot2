package top.huhuiyu.template.maven.springboot2.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 浮点数转换器
 *
 * @author 胡辉煜
 */
public class DoubleConverter implements Converter<String, Double> {

  @Override
  public Double convert(String source) {
    if (!StringUtils.hasText(source)) {
      return null;
    }
    source = source.trim();
    try {
      return Double.parseDouble(source);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

}
