package top.huhuiyu.template.maven.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenManager {
  private static Logger logger = LoggerFactory.getLogger(TokenManager.class);
  private static Map<String, UserDetails> tokenMap = new HashMap<>();
  private ThreadLocal<String> local = new ThreadLocal<>();

  public void setToken(String token) {
    local.set(checkToken(token));
  }

  public String getToken() {
    return local.get();
  }

  public UserDetails get(String token) {
    if (!StringUtils.hasText(token)) {
      return null;
    }
    return tokenMap.get(token);
  }

  public void put(String token, UserDetails user) {
    tokenMap.put(token, user);
  }

  public void remove(String token) {
    tokenMap.remove(token);
  }

  public String checkToken(String token) {
    logger.debug("tokenMap:{}", tokenMap.keySet());
    logger.debug("token验证：{},{}", token, tokenMap.containsKey(token));
    if (!StringUtils.hasText(token)) {
      token = UUID.randomUUID().toString();
      put(token, null);
      return token;
    }
    if (tokenMap.containsKey(token)) {
      return token;
    }
    token = UUID.randomUUID().toString();
    put(token, null);
    return token;
  }


}
