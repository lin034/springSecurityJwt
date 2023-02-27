package com.pildoraslin.maria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetalles {

    private Date marcaTiempo;

    private String mensaje;

    private String detalles;


}
