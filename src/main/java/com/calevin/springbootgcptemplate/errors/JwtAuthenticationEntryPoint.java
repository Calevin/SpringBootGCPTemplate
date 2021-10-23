package com.calevin.springbootgcptemplate.errors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //AuthenticationEntryPoint: Invoked when authentication fails
    private final ObjectMapper mapperJaxson;

    @Autowired
    public JwtAuthenticationEntryPoint(ObjectMapper mapperJaxson) {
        this.mapperJaxson = mapperJaxson;
    }

    @Override
    public void commence(HttpServletRequest request
            , HttpServletResponse response
            , AuthenticationException authException) throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, authException.getMessage());
        String stringApiError = mapperJaxson.writeValueAsString(apiError);

        PrintWriter writer = response.getWriter();
        writer.println(stringApiError);
    }
}
