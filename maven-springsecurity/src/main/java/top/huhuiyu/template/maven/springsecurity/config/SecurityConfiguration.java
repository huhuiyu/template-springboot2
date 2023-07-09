package top.huhuiyu.template.maven.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import top.huhuiyu.template.maven.springsecurity.security.*;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AuthenticationFailureHandler authenticationFailureHandler;

  private final AuthenticationSuccessHandler authenticationSuccessHandler;

  private final UserDetailsService securityService;
  private final TokenManager tokenManager;

  public SecurityConfiguration(AuthenticationFailureHandler authenticationFailureHandler, AuthenticationSuccessHandler authenticationSuccessHandler, UserDetailsService securityService, TokenManager tokenManager) {
    super();
    this.authenticationFailureHandler = authenticationFailureHandler;
    this.authenticationSuccessHandler = authenticationSuccessHandler;
    this.securityService = securityService;
    this.tokenManager = tokenManager;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // 自定义错误提示页面
    web.ignoring().antMatchers("/login.error");
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
    // 自定义用户认证
    MyAuthenticationProcessingFilter myAuthenticationProcessingFilter = new MyAuthenticationProcessingFilter();
    myAuthenticationProcessingFilter.setAuthenticationManager(super.authenticationManager());
    myAuthenticationProcessingFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler(this.tokenManager));
    myAuthenticationProcessingFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler(this.tokenManager));
    http.addFilterAfter(myAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class);
//    http.formLogin().loginProcessingUrl("/auth/login").usernameParameter("username")//请求验证参数
//            .passwordParameter("password")//请求验证参数
//            .and().rememberMe();
//    http.formLogin()// 登录配置
//            .failureHandler(authenticationFailureHandler)//验证失败处理
//            .successHandler(authenticationSuccessHandler)//验证成功处理
//            .and().authorizeRequests().anyRequest().authenticated() // 定义url过滤
//            .and().sessionManagement().maximumSessions(1).and() //session管理
//            .and().formLogin().loginPage("/login.error") //登录页面
//            .loginProcessingUrl("/auth/login")  // 自定义登录接口
//            .usernameParameter("username")//请求验证参数
//            .passwordParameter("password")//请求验证参数
//            .permitAll().and().rememberMe() //自动登录
    ;


  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider(this.securityService, this.tokenManager);
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
