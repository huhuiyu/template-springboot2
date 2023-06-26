package top.huhuiyu.template.maven.springboot2.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * 十进制数转换器
 *
 * @author 胡辉煜
 */
public class BigDecimalConverter implements Converter<String, BigDecimal> {

  @Override
  public BigDecimal convert(String source) {
    if (!StringUtils.hasText(source)) {
      return null;
    }
    source = source.trim();
    try {
      return new BigDecimal(source);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

}
