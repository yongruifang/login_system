package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@TableName("user")
@Entity
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {
                    @JoinColumn(
                            name = "USER_ID",
                            referencedColumnName = "ID"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ROLE_ID",
                            referencedColumnName = "ID"
                    )
            }
    )
    private List<Role> roles = new ArrayList<>();
}
