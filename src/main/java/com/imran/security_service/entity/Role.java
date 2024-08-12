package com.imran.security_service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.imran.security_service.entity.Permission.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE

            )
    ),
    USER(Set.of(
            USER_READ
    ));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> authorities(){
        var authorities = permissions
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
