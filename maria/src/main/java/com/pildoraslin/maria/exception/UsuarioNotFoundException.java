package com.pildoraslin.maria.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioNotFoundException extends RuntimeException{


    private String usernameOrEmail;

    private HttpStatus httpStatus;

    public UsuarioNotFoundException(String usernameOrEmail, HttpStatus httpStatus) {
        super(String.format("el usuario con nombre de usuario o email '%s' ya existe ",usernameOrEmail));

        this.httpStatus = httpStatus;
    }
}
