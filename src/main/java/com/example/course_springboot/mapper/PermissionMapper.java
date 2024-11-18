package com.example.course_springboot.mapper;


import com.example.course_springboot.dto.request.PermissionRequest;
import com.example.course_springboot.dto.response.PermissionResponse;
import com.example.course_springboot.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}

