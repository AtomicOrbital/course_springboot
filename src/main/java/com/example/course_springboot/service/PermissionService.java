package com.example.course_springboot.service;

import com.example.course_springboot.dto.request.PermissionRequest;
import com.example.course_springboot.dto.response.PermissionResponse;
import com.example.course_springboot.entity.Permission;
import com.example.course_springboot.mapper.PermissionMapper;
import com.example.course_springboot.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionMapper permissionMapper;
    PermissionRepository permissionRepository;

    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        return permissionRepository.findAll().stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}
