package com.example.course_springboot.repository;

import com.example.course_springboot.entity.Permission;
import com.example.course_springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
