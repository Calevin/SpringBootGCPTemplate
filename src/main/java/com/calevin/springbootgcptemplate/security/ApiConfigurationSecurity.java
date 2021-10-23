package com.calevin.springbootgcptemplate.security;

import com.calevin.springbootgcptemplate.errors.CustomAccessDeniedHandler;
import com.calevin.springbootgcptemplate.errors.JwtAuthenticationEntryPoint;
import com.calevin.springbootgcptemplate.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ApiConfigurationSecurity extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user/**").hasRole(UserRole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/entityExample/**").hasRole(UserRole.USER.toString())
                .antMatchers(HttpMethod.POST, "/entityExample/**").hasRole(UserRole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/entityExample/**").hasRole(UserRole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/entityExample/**").hasRole(UserRole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/categoryExample/**").hasAnyRole(UserRole.values().toString())
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Later is used in a filter
        return super.authenticationManagerBean();
    }
}
