package com.imran.security_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    private String name;
    private String password;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.authorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }
}
