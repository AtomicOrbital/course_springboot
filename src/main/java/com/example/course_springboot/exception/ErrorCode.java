package com.example.course_springboot.exception;


public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Invalid key"),
    USER_EXISTS(1002, "User already exists"),
    USERNAME_INVALID(1003, "Username at least 5 characters"),
    PASSWORD_INVALID(1004, "Password at least 8 characters"),
    USER_NOT_EXISTS(1005, "User not exists"),
    USER_NOT_FOUND(1006, "User not found"),
    UNAUTHENTICATED(1007, "Unauthenticated"),
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
