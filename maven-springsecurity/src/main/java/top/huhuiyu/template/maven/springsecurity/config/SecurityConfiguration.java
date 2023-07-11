package top.huhuiyu.template.maven.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import top.huhuiyu.template.maven.springsecurity.security.*;
import top.huhuiyu.template.maven.springsecurity.service.SecurityService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private final AuthenticationFailureHandler authenticationFailureHandler;

  private final AuthenticationSuccessHandler authenticationSuccessHandler;

  private final SecurityService securityService;

  public SecurityConfiguration(AuthenticationFailureHandler authenticationFailureHandler, AuthenticationSuccessHandler authenticationSuccessHandler, SecurityService securityService) {
    super();
    this.authenticationFailureHandler = authenticationFailureHandler;
    this.authenticationSuccessHandler = authenticationSuccessHandler;
    this.securityService = securityService;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // 自定义错误提示页面
    web.ignoring().antMatchers("/login.error");
//    web.ignoring().antMatchers("/auth/login");

//    web.ignoring().antMatchers("/**/auth/login");
//    web.ignoring().antMatchers("/**/login");
    //解决静态资源被拦截的问题
    web.ignoring().antMatchers("/static/**");
    web.ignoring().antMatchers("/swagger-resources/**");
    web.ignoring().antMatchers("/**/api-docs/**");
    web.ignoring().antMatchers("/doc.html");
    web.ignoring().antMatchers("/js/**");
    web.ignoring().antMatchers("/css/**");
    web.ignoring().antMatchers("/**/*.js");
    web.ignoring().antMatchers("/**/*.css");
    web.ignoring().antMatchers("/favicon.ico");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 乱码问题
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    filter.setForceEncoding(true);
    http.addFilterBefore(filter, CsrfFilter.class);
    // 跨域设置
    http.cors().and().csrf().disable();
    // jwt权限拦截
    MyJwtFilter jwtFilter = new MyJwtFilter(securityService);
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//    JwtSecurityConfigurer configurer = new JwtSecurityConfigurer(securityService);
//    http.apply(configurer);
    // 自定义用户认证
    MyAuthenticationProcessingFilter myAuthenticationProcessingFilter = new MyAuthenticationProcessingFilter();
    myAuthenticationProcessingFilter.setAuthenticationManager(super.authenticationManager());
    myAuthenticationProcessingFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
    myAuthenticationProcessingFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
    http.addFilterBefore(myAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider(this.securityService);
    auth.authenticationProvider(myAuthenticationProvider);

    auth.userDetailsService(securityService).passwordEncoder(new PasswordEncoder() {
      @Override
      public String encode(CharSequence rawPassword) {
        return rawPassword.toString();

      }

      @Override
      public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
      }
    });
  }
}
