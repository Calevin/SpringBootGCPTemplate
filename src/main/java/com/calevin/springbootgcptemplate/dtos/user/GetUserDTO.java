package com.calevin.springbootgcptemplate.dtos.user;

import com.calevin.springbootgcptemplate.security.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDTO {
    private String username;
    private Set<UserRole> roles;
}
