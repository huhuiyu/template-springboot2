package top.huhuiyu.template.maven.springsecurity.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;
import top.huhuiyu.template.maven.springsecurity.entity.TbSecurityUser;

import java.util.Collection;

public class MyAuthenticationToken extends AbstractAuthenticationToken {
  private String token;
  private TbSecurityUser user;
  private final Object principal;


  private Object credentials;

  public MyAuthenticationToken(Object principal, Object credentials) {
    super(null);
    this.principal = principal;
    this.credentials = credentials;
    super.setAuthenticated(false);
  }

  public MyAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token, TbSecurityUser user) {
    super(authorities);
    this.principal = principal;
    this.credentials = credentials;
    this.token = token;
    this.user = user;
    super.setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return this.credentials;
  }

  @Override
  public Object getPrincipal() {
    return this.principal;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
    super.setAuthenticated(false);
  }

  @Override
  public void eraseCredentials() {
    super.eraseCredentials();
    this.credentials = null;
  }

  public String getToken() {
    return token;
  }

  public TbSecurityUser getUser() {
    return user;
  }
}
