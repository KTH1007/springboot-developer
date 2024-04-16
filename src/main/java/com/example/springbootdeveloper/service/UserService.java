package com.example.springbootdeveloper.service;

import com.example.springbootdeveloper.domain.User;
import com.example.springbootdeveloper.dto.AddUserRequest;
import com.example.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(AddUserRequest dto) {

        Boolean isExist = userRepository.existsByUsername(dto.getUsername());

        if (!isExist) {
            userRepository.save(User.builder()
                    .username(dto.getUsername())
                    // password 암호화
                    .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                    .role("ROLE_ADMIN")
                    .build());
        }
    }
}
