package com.calevin.springbootgcptemplate.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateUserDTO {
    @NotBlank(message = "username requerido")
    private String username;

    @NotBlank(message = "password requerido")
    private String password;

    @JsonProperty("password_confirmation")
    @NotBlank(message = "password_confirmation requerido")
    private String passwordConfirmation;
}
