package com.example.course_springboot.service;


import com.example.course_springboot.dto.request.AuthenticationRequest;
import com.example.course_springboot.entity.User;
import com.example.course_springboot.exception.AppException;
import com.example.course_springboot.exception.ErrorCode;
import com.example.course_springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    static Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public boolean authenticate(AuthenticationRequest request) {
        logger.info("Authenticating user: {}", request.getUsername());
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }

}
