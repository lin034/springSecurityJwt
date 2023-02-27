package com.pildoraslin.maria.controlador;

import com.pildoraslin.maria.dto.ClienteDto;
import com.pildoraslin.maria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClienteControlador {

    @Autowired
    ClienteService dao;

    @RequestMapping("/clientes")
    public List<ClienteDto> dameClientes(){

        return dao.dameClientes();
    }

    @RequestMapping("/clientes/{clienteId}")
    public ClienteDto dameCliente(@PathVariable(name = "clienteId") int clienteId){

        return dao.dameCliente(clienteId);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/clientes")
    public ClienteDto creaCliente(@Valid @RequestBody ClienteDto clienteDto){

        return dao.creaCliente(clienteDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<String> eliminaCliente(@PathVariable(name = "id") int id){

        dao.eliminaCliente(id);

       // return new ResponseEntity<>("cliente eliminado exitosamente", HttpStatus.ACCEPTED);

        return  ResponseEntity.ok("ola lin");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/clientes/{id}")
    public ClienteDto editaCliente(@PathVariable(name = "id") int id, @Valid @RequestBody ClienteDto clienteDto){

        return dao.editaCliente(id,clienteDto);
    }

}
