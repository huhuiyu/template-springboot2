package top.huhuiyu.template.maven.springsecurity.config;

import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Operation;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Component
public class MyApiListingScannerPlugin implements ApiListingScannerPlugin {
  @Override
  public List<ApiDescription> apply(DocumentationContext context) {
    List<RequestParameter> parameters = new ArrayList<>();

    parameters.add(new RequestParameterBuilder().name("username").description("登录名").required(true).in(ParameterType.FORM).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(true).build());
    parameters.add(new RequestParameterBuilder().name("password").description("登录密码").required(true).in(ParameterType.FORM).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(true).build());

    Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator()).tags(Sets.newHashSet("首页")).method(HttpMethod.POST).summary("用户名密码登录").notes("用户登陆获取token").consumes(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE)).requestParameters(parameters).produces(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE)).build();

    ApiDescription loginApiDescription = new ApiDescription("首页", "/login", "登录接口", "描述", Arrays.asList(usernamePasswordOperation), false);

    return Arrays.asList(loginApiDescription);
  }

  @Override
  public boolean supports(DocumentationType documentationType) {
    return DocumentationType.OAS_30.equals(documentationType);
  }
}
