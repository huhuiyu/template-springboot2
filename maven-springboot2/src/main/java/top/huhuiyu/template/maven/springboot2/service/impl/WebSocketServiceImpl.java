package top.huhuiyu.template.maven.springboot2.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.huhuiyu.template.maven.springboot2.base.BaseWebSocketResult;
import top.huhuiyu.template.maven.springboot2.dao.SystemMapper;
import top.huhuiyu.template.maven.springboot2.service.WebSocketService;
import top.huhuiyu.template.maven.springboot2.util.JsonUtil;

import javax.websocket.Session;
import java.util.*;

/**
 * WebSocket服务实现
 *
 * @author 胡辉煜
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WebSocketServiceImpl implements WebSocketService {

  private static final Logger log = LoggerFactory.getLogger(WebSocketServiceImpl.class);
  private final SystemMapper systemMapper;

  private Set<Session> sessions = new HashSet<>();
  private Map<String, Set<Session>> publishes = new HashMap<String, Set<Session>>();

  public WebSocketServiceImpl(SystemMapper systemMapper) {
    this.systemMapper = systemMapper;
  }

  @Override
  public void addSession(Session session) {
    sessions.add(session);
  }

  @Override
  public void removeSession(Session session) {
    sessions.remove(session);
    // 删除订阅消息session
    for (String key : publishes.keySet()) {
      unsubscribe(key, session);
    }
  }

  @Override
  public void broadcast(Object message) throws Exception {
    String json = JsonUtil.stringify(message);
    for (Session session : sessions) {
      session.getBasicRemote().sendText(json);
    }
  }

  @Override
  public void subscription(String channel, Session session) {
    if (!publishes.containsKey(channel)) {
      publishes.put(channel, new HashSet<Session>());
    }
    publishes.get(channel).add(session);
    log.debug("subscription:" + publishes.toString());
  }

  @Override
  public void unsubscribe(String channel, Session session) {
    if (publishes.containsKey(channel)) {
      publishes.get(channel).remove(session);
    }
    log.debug("unsubscribe:" + publishes.toString());
  }

  @Override
  public void publish(String channel, Object message) throws Exception {
    String json = JsonUtil.stringify(message);
    Set<Session> set = publishes.get(channel);
    log.debug(String.format("set:%s", set));
    if (set == null) {
      return;
    }
    for (Session session : set) {
      session.getBasicRemote().sendText(json);
    }
  }

  @Override
  public void sendTimestamp(Session session) throws Exception {
    BaseWebSocketResult<Date> result = BaseWebSocketResult.getSuccessInfo(systemMapper.queryTime());
    session.getBasicRemote().sendText(JsonUtil.stringify(result));
  }

  @Override
  public void sendMessage(Session session, String message) throws Exception {
    session.getBasicRemote().sendText(message);
  }

}