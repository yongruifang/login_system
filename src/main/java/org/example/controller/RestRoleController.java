package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entity.Role;
import org.example.mapper.RoleMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@AllArgsConstructor
public class RestRoleController {
    private RoleMapper roleMapper;
    @PostMapping("/")
    public ResponseEntity<String> addOne(
            @RequestBody Role role) {
        System.out.println(role);
        roleMapper.insert(role);
        return null;
    }
}
