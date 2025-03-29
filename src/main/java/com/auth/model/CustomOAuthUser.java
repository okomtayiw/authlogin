package com.auth.model;

import com.auth.entity.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuthUser implements OAuth2User {

    @Getter
    private final Users users;
    private final Map<String, Object> attributes;

    public CustomOAuthUser(Users users, Map<String, Object> attributes) {
        this.users = users;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + users.getRole());
    }

    @Override
    public String getName() {
        return String.valueOf(users.getId());
    }

}

