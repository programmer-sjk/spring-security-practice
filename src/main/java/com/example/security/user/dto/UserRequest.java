package com.example.security.user.dto;

import com.example.security.user.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phone;
    @NotNull
    private Boolean allowMarketing;

    public User toEntity(String encryptPassword) {
        return new User(email, encryptPassword, phone, allowMarketing);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getAllowMarketing() {
        return allowMarketing;
    }
}
