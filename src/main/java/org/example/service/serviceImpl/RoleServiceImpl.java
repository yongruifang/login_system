package org.example.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.Role;
import org.example.mapper.RoleMapper;
import org.example.repository.RoleRepository;
import org.example.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRole(Long userId) {
        List<Role> roles = roleRepository.findByUser_Id(userId);
        System.out.println("^^^^^^^^^^^^^^^^^^");
        System.out.println(roles);
        System.out.println("^^^^^^^^^^^^^^^^^^");
       return roles;
    }
}

