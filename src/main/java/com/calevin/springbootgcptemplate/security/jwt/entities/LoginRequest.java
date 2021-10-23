package com.calevin.springbootgcptemplate.security.jwt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "username requerido")
    private String username;
    @NotBlank(message = "password requerido")
    private String password;
}
