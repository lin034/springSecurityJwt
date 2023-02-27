package com.pildoraslin.maria.seguridad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRespuestaDto {

    private String tokenAcceso;

    private String tipoToken= "Bearer";

    public JwtAuthRespuestaDto(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;

    }
}
