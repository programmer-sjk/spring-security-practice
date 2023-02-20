package com.example.security.user;

import com.example.security.user.dto.UserRequest;
import com.example.security.user.dto.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @PostMapping()
    public void signUp(@RequestBody UserRequest request) {
        userService.signUp(request);
    }
}
