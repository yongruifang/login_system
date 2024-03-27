package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.dto.JwtAuthResponse;
import org.example.entity.User;
import org.example.service.AuthService;
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
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticate (
            @RequestBody User user
    ) {
        String token = authService.login(user);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
}
