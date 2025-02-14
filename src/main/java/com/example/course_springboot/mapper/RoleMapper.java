package com.example.course_springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.course_springboot.dto.request.RoleRequest;
import com.example.course_springboot.dto.response.RoleResponse;
import com.example.course_springboot.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
