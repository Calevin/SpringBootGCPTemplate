package com.calevin.springbootgcptemplate.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApiError {
    private HttpStatus estado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime fecha;
    private String mensaje;

    public ApiError(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.fecha = LocalDateTime.now();
        this.mensaje = mensaje;
    }
}
