package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    // 关系维护端
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
