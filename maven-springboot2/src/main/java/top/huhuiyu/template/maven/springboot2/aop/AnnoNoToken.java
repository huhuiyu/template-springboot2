package top.huhuiyu.template.maven.springboot2.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类注解,用于标识不需要token信息的控制器或者控制器方法
 *
 * @author 胡辉煜
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AnnoNoToken {
}