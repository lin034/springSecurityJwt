package com.pildoraslin.maria.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {

    private int usuarioDto_id;
    private String nombre;
    private String username;
    private String email;
    private String password;

    public UsuarioDto(String nombre, String username, String email, String password) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}