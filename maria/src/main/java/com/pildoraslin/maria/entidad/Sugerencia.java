package com.pildoraslin.maria.entidad;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sugerencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sugerencia_id")
    private int sugerencia_id;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "mensaje")
    private String mensaje;

    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    public Sugerencia(String departamento, String mensaje) {
        this.departamento = departamento;
        this.mensaje = mensaje;
    }


}
