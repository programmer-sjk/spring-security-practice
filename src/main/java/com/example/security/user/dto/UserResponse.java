package com.example.security.user.dto;

import com.example.security.user.domain.User;

public class UserResponse {
    private Long id;
    private String email;
    private String password;
    private String phone;
    private Boolean allowMarketing;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.allowMarketing = user.isAllowMarketing();
    }

    public Long getId() {
        return id;
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
