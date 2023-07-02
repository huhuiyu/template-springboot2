package top.huhuiyu.template.maven.springboot2.websocket.processor;

import top.huhuiyu.template.maven.springboot2.base.BaseProcessor;
import top.huhuiyu.template.maven.springboot2.base.BaseWebSocketResult;
import top.huhuiyu.template.maven.springboot2.entity.ChatInfo;
import top.huhuiyu.template.maven.springboot2.service.WebSocketService;
import top.huhuiyu.template.maven.springboot2.util.ApplicationUtil;
import top.huhuiyu.template.maven.springboot2.util.JsonUtil;
import top.huhuiyu.template.maven.springboot2.websocket.WebSocketApplication;

import javax.websocket.Session;

/**
 * 简易聊天室应答处理器
 *
 * @author 胡辉煜
 */
public class ChatProcessor implements BaseProcessor {
  public static final String CHANNEL = "chat_room";

  @Override
  public void onOpen(Session session) throws Exception {
    WebSocketService webSocketService = ApplicationUtil.getApplicationContext().getBean(WebSocketService.class);
    webSocketService.addSession(session);
    webSocketService.subscription(CHANNEL, session);
  }

  @Override
  public void onMessage(String message, Session session) throws Exception {
    WebSocketService webSocketService = ApplicationUtil.getApplicationContext().getBean(WebSocketService.class);
    ChatInfo chatInfo = JsonUtil.parse(message, ChatInfo.class);
    BaseWebSocketResult<ChatInfo> result = BaseWebSocketResult.getSuccessInfo(chatInfo);
    result.setType(WebSocketApplication.APP_ECHO);
    webSocketService.publish(CHANNEL, result);
  }

}
