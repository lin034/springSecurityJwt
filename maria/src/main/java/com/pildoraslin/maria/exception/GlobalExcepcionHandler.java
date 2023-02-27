package com.pildoraslin.maria.exception;

import com.pildoraslin.maria.dto.ErrorDetalles;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExcepcionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecursoNotFoundException.class)
    public ResponseEntity<ErrorDetalles> manejarRecursoNotFoundException(RecursoNotFoundException exception, WebRequest webRequest){

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SugerenciaNotFounException.class)
    public ResponseEntity<ErrorDetalles> manejarSugerenciaNotFoundException(SugerenciaNotFounException exception, WebRequest webRequest){

        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception , WebRequest webRequest){
        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach( e -> {
            String nombreCampo = ((FieldError) e).getField();
            String valorCampo = e.getDefaultMessage();

            errores.put(nombreCampo, valorCampo);
        } );

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);

    }
}
