package com.example.course_springboot.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.example.course_springboot.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 5, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;

    @DobConstraint(min = 2, message = "INVALID_DATE_OF_BIRTH")
    LocalDate dob;
}
