package com.example.course_springboot.service;

import com.example.course_springboot.dto.request.UserCreationRequest;
import com.example.course_springboot.dto.request.UserUpdateRequest;
import com.example.course_springboot.dto.response.UserResponse;
import com.example.course_springboot.entity.User;
import com.example.course_springboot.enums.Role;
import com.example.course_springboot.exception.AppException;
import com.example.course_springboot.exception.ErrorCode;
import com.example.course_springboot.mapper.UserMapper;
import com.example.course_springboot.repository.RoleRepository;
import com.example.course_springboot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createReqest(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        User newUser = userMapper.toUser(request);
//        User newUser = User.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .dob(request.getDob())
//                .build();
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        var userRole = roleRepository.findByName(Role.ADMIN.name())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        HashSet<com.example.course_springboot.entity.Role> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(newUser));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        return userMapper.toUserResponse(user);

    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(getUser(id).getId());
    }
}
