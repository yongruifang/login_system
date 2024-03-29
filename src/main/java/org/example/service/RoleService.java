package org.example.service;


import org.example.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRole(Long userId);
}
