package com.example.course_springboot.service;

import com.example.course_springboot.dto.request.PermissionRequest;
import com.example.course_springboot.dto.request.RoleRequest;
import com.example.course_springboot.dto.response.PermissionResponse;
import com.example.course_springboot.dto.response.RoleResponse;
import com.example.course_springboot.entity.Permission;
import com.example.course_springboot.mapper.PermissionMapper;
import com.example.course_springboot.mapper.RoleMapper;
import com.example.course_springboot.repository.PermissionRepository;
import com.example.course_springboot.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll(){
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String role){
        roleRepository.deleteById(role);
    }
}
