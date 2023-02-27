package com.pildoraslin.maria.exception;

public class RecursoNotFoundException extends RuntimeException{

    private String entidadError;

    private String campoError;

    private int valorCampoError;

    public RecursoNotFoundException(String entidadError, String campoError, int valorCampoError) {
        super(String.format("%s con %s numero: '%s' no existe en la base de datos intente de nuevo", entidadError, campoError, valorCampoError));
        this.entidadError = entidadError;
        this.campoError = campoError;
        this.valorCampoError = valorCampoError;
    }
}
