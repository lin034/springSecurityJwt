package com.pildoraslin.maria.controlador;

import com.pildoraslin.maria.dto.SugerenciaDto;
import com.pildoraslin.maria.service.SugerenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SugerenciaControlador {

    @Autowired
    SugerenciaService dao;



    @GetMapping("/clientes/sugerencias")
    public List<SugerenciaDto> dameSugerencias(){


        return dao.dameSugerencias();
    }

    @GetMapping("/clientes/{clienteId}/sugerencias")
    public List<SugerenciaDto> dameSugerenciaPorClienteId(@PathVariable(name = "clienteId") int clienteId){


        return dao.dameSugerenciaPorClienteId(clienteId);
    }

    @PostMapping("clientes/{clienteId}/sugerencias")
    public SugerenciaDto creaSugerencia(@PathVariable(name = "clienteId") int clienteId, @Valid @RequestBody SugerenciaDto sugerenciaDto){

        return dao.creaSugerencia(clienteId, sugerenciaDto);
    }

    @DeleteMapping("/clientes/{clienteId}/sugerencias/{id}")
    public ResponseEntity<String> eliminaSugerencia(@PathVariable(name = "clienteId") int clienteId, @PathVariable(name = "id") int id){

        dao.eliminaSugerencia(clienteId, id);

        return new ResponseEntity<>("sugerencia eliminada exitosamente", HttpStatus.ACCEPTED);
    }

    @PutMapping("/clientes/{clienteId}/sugerencias/{id}")
    public SugerenciaDto editaSugerencia(@PathVariable(name = "clienteId") int clienteId, @PathVariable(name = "id") int id, @Valid @RequestBody SugerenciaDto sugerenciaDto){

        return dao.editaCliente(clienteId, id , sugerenciaDto);
    }

}
