package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.val;
import org.example.dto.request.AlterRoleRequest;
import org.example.dto.response.UserListResponse;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Tag(name="用户表格", description = "RestFul风格")
public class RestUserController {
    private UserService userService;
    //private UserMapper userMapper;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @GetMapping("/")
    public ResponseEntity<UserListResponse> fetchList(
    ) {
        System.out.println("获取用户列表");
        List<User> userList = userRepository.findAll();
        for(User user: userList) {
            System.out.println(user);
        }
        val userListResponse = new UserListResponse();
        userListResponse.setMessage("success");
        userListResponse.setUserList(userList);
        return ResponseEntity.ok(userListResponse);
    }
    @PostMapping("/roles")
    public ResponseEntity<String> alterRole(
            @RequestBody AlterRoleRequest body
            ) {
        
        String email = body.getEmail();
        List<Role> roles = body.getRoles();
        User user = userRepository.findByEmail(email);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok("success");
    }
}
