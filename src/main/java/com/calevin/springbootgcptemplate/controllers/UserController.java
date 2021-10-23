package com.calevin.springbootgcptemplate.controllers;

import com.calevin.springbootgcptemplate.dtos.user.CreateUserDTO;
import com.calevin.springbootgcptemplate.dtos.user.GetUserDTO;
import com.calevin.springbootgcptemplate.dtos.user.UserConverterDTO;
import com.calevin.springbootgcptemplate.errors.PasswordNoCoincideException;
import com.calevin.springbootgcptemplate.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserConverterDTO converterDTO;

    @PostMapping("/user")
    public ResponseEntity<GetUserDTO> newRecord(@RequestBody @Valid CreateUserDTO createUserDTO) {
        log.info("newRecord, createUserDTO: {}", createUserDTO);
        if(!createUserDTO.getPassword().contentEquals(createUserDTO.getPasswordConfirmation())) {
            throw new PasswordNoCoincideException();
        }

        userService.newUser(createUserDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(converterDTO.convertCreateUserDTOToGetUserDTO(createUserDTO));
    }
}
