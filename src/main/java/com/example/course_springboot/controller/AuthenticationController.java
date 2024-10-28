package com.example.course_springboot.controller;

import com.example.course_springboot.dto.request.AuthenticationRequest;
import com.example.course_springboot.dto.response.ApiResponse;
import com.example.course_springboot.dto.response.AuthenticationResponse;
import com.example.course_springboot.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        boolean authenticated = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(200)
                .message("Authenticated")
                .result(AuthenticationResponse.builder().authenticated(authenticated).build())
                .build();
    }
}
