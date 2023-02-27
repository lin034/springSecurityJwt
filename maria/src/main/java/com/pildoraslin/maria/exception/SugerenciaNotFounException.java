package com.pildoraslin.maria.exception;

import org.springframework.http.HttpStatus;

public class SugerenciaNotFounException  extends  RuntimeException{

    private int sugerenciaId;

    private HttpStatus httpStatus;

    public SugerenciaNotFounException(int sugerenciaId, HttpStatus httpStatus) {
        super(String.format ("la sugerencia con id '%s' no pertenece al cliente indicado", sugerenciaId));
        this.sugerenciaId = sugerenciaId;
        this.httpStatus = httpStatus;
    }
}
