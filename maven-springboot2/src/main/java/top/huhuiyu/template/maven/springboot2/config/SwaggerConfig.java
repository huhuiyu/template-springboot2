package top.huhuiyu.template.maven.springboot2.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 *
 * @author 胡辉煜
 */
@Configuration
public class SwaggerConfig {
  @Value("${springfox.documentation.swagger-ui.enabled}")
  private boolean enabled = true;

  @Bean
  Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30).enable(enabled).pathMapping("/").apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).build().globalRequestParameters(getGlobalRequestParameters());
  }

  private ApiInfo apiInfo() {
    // api描述信息
    return new ApiInfoBuilder().contact(new Contact("胡辉煜", "https://huhuiyu.top", "1069306849@qq.com")).title("胡辉煜的springboot2演示项目").description("胡辉煜的教学后端接口项目").version("1.0.0").build();
  }

  private List<RequestParameter> getGlobalRequestParameters() {
    // 全局token参数
    List<RequestParameter> parameters = new ArrayList<>();
    parameters.add(new RequestParameterBuilder().name("token").description("token令牌").required(false).in(ParameterType.HEADER).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(false).build());
    return parameters;
  }

}