package top.huhuiyu.template.maven.springboot2.websocket.processor;

import top.huhuiyu.template.maven.springboot2.base.BaseProcessor;
import top.huhuiyu.template.maven.springboot2.base.BaseWebSocketResult;
import top.huhuiyu.template.maven.springboot2.service.WebSocketService;
import top.huhuiyu.template.maven.springboot2.util.ApplicationUtil;
import top.huhuiyu.template.maven.springboot2.util.JsonUtil;
import top.huhuiyu.template.maven.springboot2.websocket.WebSocketApplication;

import javax.websocket.Session;

/**
 * echo应答处理器
 *
 * @author 胡辉煜
 */
public class EchoProcessor implements BaseProcessor {

  @Override
  public void onOpen(Session session) throws Exception {
    WebSocketService webSocketService = ApplicationUtil.getApplicationContext().getBean(WebSocketService.class);
    webSocketService.addSession(session);
  }

  @Override
  public void onMessage(String message, Session session) throws Exception {
    WebSocketService webSocketService = ApplicationUtil.getApplicationContext().getBean(WebSocketService.class);
    BaseWebSocketResult<String> result = BaseWebSocketResult.getSuccessInfo("服务器应答:" + message);
    result.setType(WebSocketApplication.APP_ECHO);
    webSocketService.sendMessage(session, JsonUtil.stringify(result));
  }

}
