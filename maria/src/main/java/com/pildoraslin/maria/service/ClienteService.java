package com.pildoraslin.maria.service;

import com.pildoraslin.maria.dto.ClienteDto;

import java.util.List;

public interface ClienteService {


    public List<ClienteDto> dameClientes();

    public ClienteDto dameCliente(int clienteId);

    public ClienteDto creaCliente(ClienteDto clienteDto);

    public void eliminaCliente(int id);

    public ClienteDto editaCliente(int id, ClienteDto clienteDto);
}
