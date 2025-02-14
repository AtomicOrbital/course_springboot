package com.example.course_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.course_springboot.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
