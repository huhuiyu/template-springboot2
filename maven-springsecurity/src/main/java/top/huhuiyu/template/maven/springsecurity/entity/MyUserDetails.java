package top.huhuiyu.template.maven.springsecurity.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class MyUserDetails implements UserDetails {

  private TbSecurityUser user;

  public MyUserDetails(TbSecurityUser user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    TbSecurityUser user = Optional.ofNullable(this.user).orElse(new TbSecurityUser());
    TbSecurityRole role = Optional.ofNullable(user.getRole()).orElse(new TbSecurityRole());
    return Arrays.asList(new SimpleGrantedAuthority(Optional.ofNullable(role.getRoleName()).orElse("error")));
  }

  @Override
  public String getPassword() {
    TbSecurityUser user = Optional.ofNullable(this.user).orElse(new TbSecurityUser());
    return Optional.ofNullable(user.getPassword()).orElse("");
  }

  @Override
  public String getUsername() {
    TbSecurityUser user = Optional.ofNullable(this.user).orElse(new TbSecurityUser());
    return Optional.ofNullable(user.getUsername()).orElse("");
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public TbSecurityUser getUser() {
    return user;
  }

}
