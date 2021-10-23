package com.calevin.springbootgcptemplate.security.jwt;

import com.calevin.springbootgcptemplate.entities.User;
import com.calevin.springbootgcptemplate.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    //OncePerRequestFilter: filter that will be executed once in each request

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJwtFromRequest(request);

            if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {

                Long userId = tokenProvider.getUserIdFromJWT(token);

                User user = (User) customUserDetailsService.loadUserById(userId);

                // Make a Authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, user.getRoles(), user.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetails(request));

                // If the authentication is successful (in our case, if the token is validated)
                // store an instance of that class in the security context:

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // Error: filter chain is not continuing
            System.out.println("No se ha podido establecer la autenticacion de usuario en el contexto de seguridad");
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtTokenProvider.TOKEN_HEADER);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtTokenProvider.TOKEN_PREFIX)) {
            return bearerToken.substring(JwtTokenProvider.TOKEN_PREFIX.length(), bearerToken.length());
        }

        return null;
    }
}
