package com.calevin.springbootgcptemplate.security.jwt.entities;

import com.calevin.springbootgcptemplate.dtos.user.GetUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDTO {

    private String token;
}
