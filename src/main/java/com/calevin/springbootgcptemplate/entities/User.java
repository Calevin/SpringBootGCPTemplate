package com.calevin.springbootgcptemplate.entities;

import com.calevin.springbootgcptemplate.security.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @CreatedDate
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_date_password_modification")
    private LocalDateTime lastDayPasswordModification = LocalDateTime.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        //TODO impl
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //TODO impl
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO impl
        return true;
    }

    @Override
    public boolean isEnabled() {
        //TODO impl
        return true;
    }
}
