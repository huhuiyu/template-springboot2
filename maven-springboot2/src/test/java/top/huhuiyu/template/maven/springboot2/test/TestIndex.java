package top.huhuiyu.template.maven.springboot2.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestIndex {
  @Autowired
  private MockMvc mvc;
  private Logger logger = LoggerFactory.getLogger(TestIndex.class);

  @Test
  public void test() throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/").param("echo", "黑暗骑士");
    ResultActions action = mvc.perform(builder);
    // 创建对请求返回的内容进行验证的对象
    ContentResultMatchers content = MockMvcResultMatchers.content();
    // 设定预定返回内容
    ResultMatcher result = content.string("欢迎：黑暗骑士");
    // 与实际返回内容进行比对
    action.andExpect(result);
  }
}
