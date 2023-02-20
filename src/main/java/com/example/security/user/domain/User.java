package com.example.security.user.domain;


import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Comment("사용자 핸드폰 번호")
    @Column(length = 20, unique = true, nullable = false)
    private String phone;

    @Comment("사용자 탈퇴 시 날짜시간")
    private LocalDateTime deletedAt;

    @Comment("마케팅 활용동의 여부")
    @Column(nullable = false)
    private boolean allowMarketing;

    protected User() {}

    public User(String email, String password, String phone, boolean allowMarketing) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.allowMarketing = allowMarketing;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public boolean isAllowMarketing() {
        return allowMarketing;
    }
}
