package org.example.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.example.dto.UserDto;
import org.example.mapper.RoleMapper;
import org.example.service.RoleService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleMapper roleMapper;

    @Override
    public String getRole(Long userId) {

       return "";
    }
}

