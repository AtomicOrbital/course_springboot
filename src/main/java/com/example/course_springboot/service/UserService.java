package com.example.course_springboot.service;

import com.example.course_springboot.dto.request.UserCreationRequest;
import com.example.course_springboot.dto.request.UserUpdateRequest;
import com.example.course_springboot.dto.response.UserResponse;
import com.example.course_springboot.entity.User;
import com.example.course_springboot.exception.AppException;
import com.example.course_springboot.exception.ErrorCode;
import com.example.course_springboot.mapper.UserMapper;
import com.example.course_springboot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
   UserRepository userRepository;
   UserMapper userMapper;

    public UserResponse createReqest(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

//        User newUser = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User newUser = User.builder()
                .username(request.getUsername())
//                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dob(request.getDob())
                .build();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));


        return userMapper.toUserResponse(userRepository.save(newUser));
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(getUser(id).getId());
    }
}
