package com.pildoraslin.maria.dto;

import com.pildoraslin.maria.entidad.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SugerenciaDto {

    private int sugerencia_id;

    @NotEmpty
    @Size(min = 10, message = "nombre de departamento minimo 4 caracteres")
    private String departamento;

    @NotEmpty
    @Size(min = 10, message = "sugerencia minimo de 10 caracteres")
    private String  mensaje;


    @NotEmpty
    @Size(min = 10, message = "nombre minimo de 2 caracteres")
    private Cliente cliente;

}
