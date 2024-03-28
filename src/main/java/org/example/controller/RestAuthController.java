package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.val;
import org.example.dto.UserDto;
import org.example.dto.response.JwtAuthResponse;
import org.example.dto.response.RegisterResponse;
import org.example.entity.User;
import org.example.service.AuthService;
import org.example.service.UserService;
import org.example.util.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name="登录认证", description = "RestFul风格")
public class RestAuthController {
    private AuthService authService;
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticate (
            @RequestBody User user
    ) {
        String token = authService.login(user);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setMessage("user login successfully");
        return ResponseEntity.ok(jwtAuthResponse);
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register (
            @RequestBody UserDto user
    ) {
        val registerResponse = new RegisterResponse();
        User existing = userService.findUserByEmail(user.getEmail());
        if (existing != null) {
            registerResponse.setMessage("There is already an account registered with that email");
            registerResponse.setStatus(Status.FAILURE);
            return ResponseEntity.ok(registerResponse);
        }
        // @TODO: 对字段的校验，选择在前端做好。
        userService.saveUser(user);
        registerResponse.setMessage("user created successfully");
        registerResponse.setStatus(Status.SUCCESS);
        return ResponseEntity.ok(registerResponse);
    }
}
