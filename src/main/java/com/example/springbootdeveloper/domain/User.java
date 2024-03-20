package com.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 추가
@Getter
@Entity
public class User implements UserDetails { // UserDetails를 상속 받아 인증 객체로 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    // 사용자의 id를 반환(고유값)
    @Override
    public String getUsername() {
        return email;
    }
    
    // 사용자의 password를 반환
    @Override
    public String getPassword() {
        return password;
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
