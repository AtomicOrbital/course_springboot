package com.example.course_springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.course_springboot.dto.request.UserCreationRequest;
import com.example.course_springboot.dto.request.UserUpdateRequest;
import com.example.course_springboot.dto.response.UserResponse;
import com.example.course_springboot.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponse toUserResponse(User user);
}
