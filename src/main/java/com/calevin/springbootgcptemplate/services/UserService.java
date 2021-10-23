package com.calevin.springbootgcptemplate.services;

import com.calevin.springbootgcptemplate.dtos.user.CreateUserDTO;
import com.calevin.springbootgcptemplate.entities.User;
import com.calevin.springbootgcptemplate.repositories.UserRepository;
import com.calevin.springbootgcptemplate.security.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService<User, Long, UserRepository> {

    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    public User newUser(CreateUserDTO createUserDTO) {
        User userToSave = new User();

        userToSave.setUsername(createUserDTO.getUsername());
        userToSave.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        userToSave.setRoles(Stream.of(UserRole.USER).collect(Collectors.toSet()));
        return this.save(userToSave);
    }
}
