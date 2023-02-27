package com.pildoraslin.maria.dto;

import com.pildoraslin.maria.entidad.Sugerencia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDto {


    private int cliente_id;

    @NotEmpty
    @Size(min = 2, message = "nombre minimo de 2 caracteres")
    private String nombre;

    @NotEmpty
    @Size(min = 2, message = "apellido minimo de 2 caracteres")
    private String apellido;

    @NotEmpty
    @Size(min = 8, message = "email minimo de 2 caracteres")
    private String email;

    private List<Sugerencia> sugerencia;



    public ClienteDto(int cliente_id, String nombre, String apellido, String email) {
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }


}
