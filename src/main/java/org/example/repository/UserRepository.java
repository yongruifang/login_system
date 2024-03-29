package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
// 通过用户ID查找用户角色
   // 方法1：在user repository
   @Query(nativeQuery=true,
           value="select role.name from role, users_roles"
                + " where users_roles.role_id=role.id "
                + " and users_roles.user_id=?1")
   List<String> findUserRolesName(long userid);
   // 方法2: 在role repository
}
