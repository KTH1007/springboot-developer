package com.example.springbootdeveloper.dto;

import com.example.springbootdeveloper.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료 확인 로직
        return true; // ture -> 만료되지 않음
    }

    //계쩡 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 잠금 확인 로직
        return true; // true- > 잠금 걸리지 않음
    }

    // password 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // password 만료 확인 로직
        return true; // true -> 만료되지 않음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 사용 가능 확인 로직
        return true; // true -> 사용 가능
    }
}
