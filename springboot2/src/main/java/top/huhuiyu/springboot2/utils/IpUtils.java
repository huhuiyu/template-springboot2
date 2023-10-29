package top.huhuiyu.springboot2.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class IpUtils {
  /**
   * ip信息最小长度
   */
  public static final int IP_MIN_LENGTH = 15;
  /**
   * ip分隔字符
   */
  public static final String IP_SPLIT = ",";
  /**
   * 未知ip
   */
  public static final String UNKNOWN_IP = "unknown";
  /**
   * 本机ip
   */
  public static final String LOCAL_IP = "127.0.0.1";

  /**
   * 获取客户端ip地址
   *
   * @return 客户端ip地址
   */
  public static String getIpAddress() {
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (servletRequestAttributes == null) {
      return getLocalIp();
    }
    HttpServletRequest request = servletRequestAttributes.getRequest();
    return getIpAddr(request);
  }

  /**
   * 获取客户端ip地址
   *
   * @param request 客户端请求
   * @return 客户端ip地址
   */
  public static String getIpAddr(HttpServletRequest request) {
    if (request == null) {
      return getLocalIp();
    }
    String ipAddress = null;
    try {
      ipAddress = request.getHeader("x-forwarded-for");
      if ((ipAddress == null) || (ipAddress.isEmpty()) || UNKNOWN_IP.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("Proxy-Client-IP");
      }
      if ((ipAddress == null) || (ipAddress.isEmpty()) || UNKNOWN_IP.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("WL-Proxy-Client-IP");
      }
      if ((ipAddress == null) || (ipAddress.isEmpty()) || UNKNOWN_IP.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getRemoteAddr();
        if (LOCAL_IP.equals(ipAddress)) {
          // 根据网卡取本机配置的IP
          InetAddress inet = InetAddress.getLocalHost();
          ipAddress = inet.getHostAddress();
        }
      }
      // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割,
      if ((ipAddress != null) && (ipAddress.length() > IP_MIN_LENGTH)) {
        if (ipAddress.indexOf(IP_SPLIT) > 0) {
          ipAddress = ipAddress.substring(0, ipAddress.indexOf(IP_SPLIT));
        }
      }
    } catch (Exception e) {
      ipAddress = "";
    }
    return ipAddress;
  }

  /**
   * 获取本地ip地址
   *
   * @return 本地ip地址
   */
  public static String getLocalIp() {
    InetAddress inet = null;
    try {
      inet = InetAddress.getLocalHost();
      return inet.getHostAddress();
    } catch (UnknownHostException e) {
      return "";
    }
  }
}
