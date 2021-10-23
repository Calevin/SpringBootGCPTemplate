package com.calevin.springbootgcptemplate.controllers;

import com.calevin.springbootgcptemplate.dtos.user.GetUserDTO;
import com.calevin.springbootgcptemplate.dtos.user.UserConverterDTO;
import com.calevin.springbootgcptemplate.entities.User;
import com.calevin.springbootgcptemplate.security.jwt.JwtTokenProvider;
import com.calevin.springbootgcptemplate.security.jwt.entities.JwtUserResponse;
import com.calevin.springbootgcptemplate.security.jwt.entities.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserConverterDTO userConverterDTO;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody @Valid LoginRequest loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(convertUserToJwtUserResponse(user, jwtToken));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me_jwt")
    public GetUserDTO me(@AuthenticationPrincipal User user) {
        return userConverterDTO.convertUserToGetUser(user);
    }

    //TODO move a userConverterDTO???
    private JwtUserResponse convertUserToJwtUserResponse(User user, String jwtToken) {
        JwtUserResponse jwtUserResponse = new JwtUserResponse();
        jwtUserResponse.setUsername(user.getUsername());
        jwtUserResponse.setRoles(user.getRoles());
        jwtUserResponse.setToken(jwtToken);

        return jwtUserResponse;
    }
}
