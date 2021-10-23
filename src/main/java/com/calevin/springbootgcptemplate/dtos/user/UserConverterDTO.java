package com.calevin.springbootgcptemplate.dtos.user;

import com.calevin.springbootgcptemplate.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Mapper(componentModel = "spring", imports = { Stream.class, Collectors.class})
public abstract class UserConverterDTO {
    public abstract GetUserDTO convertUserToGetUser(User user);

    @Mapping(target = "roles", expression = "java( Stream.of( UserRole.USER ).collect( Collectors.toSet() ) )")
    public abstract GetUserDTO convertCreateUserDTOToGetUserDTO(CreateUserDTO createUserDTO);
}
