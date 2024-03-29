package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Role;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    private String message;
    private String accessToken;
    private String tokenType = "Bearer";
    private List<Role> roles;
}
