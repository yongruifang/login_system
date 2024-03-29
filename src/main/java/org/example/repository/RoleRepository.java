package org.example.repository;

import org.example.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    public List<Role> findByUser_Id(long userId);
}
