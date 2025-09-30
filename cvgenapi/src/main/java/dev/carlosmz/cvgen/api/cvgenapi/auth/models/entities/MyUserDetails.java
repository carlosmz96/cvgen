package dev.carlosmz.cvgen.api.cvgenapi.auth.models.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class MyUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final String enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.enabled = u.getEnabled();
        //TODO implementar authorities
        this.authorities = null;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return enabled.equals("S"); }
}
