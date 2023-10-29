package top.huhuiyu.springboot2.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtils {
  private static final String PASSWORD = "springboot2.huhuiyu.top.key";

  private static final int EXPIRATION_TIME = 90 * 60 * 1000;
  public static final String USER_ID = "userId";

  public static String makeUserIdToken(Integer userId) {
    Date date = new Date();
    long expire = date.getTime() + EXPIRATION_TIME;
    date.setTime(expire);
    return JWT.create().withExpiresAt(date).withClaim(USER_ID, userId).sign(Algorithm.HMAC256(PASSWORD));
  }

  public static Integer parseUserIdToken(String token) {
    DecodedJWT decodedJWT = JWT.decode(token);
    Date expiresAt = decodedJWT.getExpiresAt();
    Date now = new Date();
    if (now.after(expiresAt)) {
      return null;
    }
    return decodedJWT.getClaim(USER_ID).asInt();
  }

}
