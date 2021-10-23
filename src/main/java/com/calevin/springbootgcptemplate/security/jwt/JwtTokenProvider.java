package com.calevin.springbootgcptemplate.security.jwt;

import com.calevin.springbootgcptemplate.entities.User;
import com.calevin.springbootgcptemplate.security.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    @Value("${sm://jwt_secret}")
    private String jwtSecret;
    @Value("${jwt.token-expiration}")
    private int jwtTokenExpiration;

    /**
     * Generate a token from an Authentication (a logged user)
     * @param authentication logged user
     * @return token
     */
    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtTokenExpiration * 1000));

        return Jwts.builder()
                // allows to sign the token
                // allows to generate a SecretKey based on an array of bytes (ready to be encrypted).
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                // setHeaderParam: allows to indicate parameters for the header of the token
                .setHeaderParam("typ", TOKEN_TYPE)
                // setSubject: indicates the subject (the user ID)
                .setSubject(Long.toString(user.getId()))
                // setIssuedAt: indicates the creation date of the token
                .setIssuedAt(new Date())
                // setExpiration: indicates the expiration date of the token
                .setExpiration(tokenExpirationDate)
                // claim: allows to indicate additional data for the payload
                .claim("name", user.getUsername())
                .claim("roles", user
                        .getRoles().stream()
                        .map(UserRole::name)
                        .collect(Collectors.joining(", ")))
                // compact: build the token and serialize it
                .compact();
    }

    /**
     * Obtain the user ID from the payload of a token
     * @param token
     * @return user ID
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * Check if a token is valid.
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {

        try {
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            System.err.println("JWT token signing error:" + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.err.println("Malformed Token:" + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            System.err.println("The token has expired:" + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.err.println("JWT token not supported:" + ex.getMessage());
        } catch (IllegalArgumentException  ex) {
            System.err.println("JWT claims empty" + ex.getMessage());
        }

        return false;
    }
}
