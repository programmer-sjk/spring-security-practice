package com.example.security;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {
    @GetMapping()
    public String main() {
        return "Hello!! This is Main Page";
    }
}
