package com.pildoraslin.maria.service;

import com.pildoraslin.maria.dto.SugerenciaDto;
import com.pildoraslin.maria.entidad.Cliente;
import com.pildoraslin.maria.entidad.Sugerencia;
import com.pildoraslin.maria.exception.RecursoNotFoundException;
import com.pildoraslin.maria.exception.SugerenciaNotFounException;
import com.pildoraslin.maria.repositorio.ClienteRepositorio;
import com.pildoraslin.maria.repositorio.SugerenciaRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SugerenciaServiceImpl implements SugerenciaService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SugerenciaRepositorio repositorio;

    @Autowired
    ClienteRepositorio clienteRepositorio;


    @Override
    public List<SugerenciaDto> dameSugerencias() {

        List<Sugerencia> sugerencias = repositorio.findAll();

        List<SugerenciaDto> sugerenciaDtos = sugerencias.stream().map( e -> cambiaAsugerenciaDto(e)).collect(Collectors.toList());

        return sugerenciaDtos;
    }

    @Override
    public List<SugerenciaDto> dameSugerenciaPorClienteId(int clienteId) {

        Cliente cliente = clienteRepositorio.findById(clienteId).orElseThrow( () -> new RecursoNotFoundException("cliente", "id", clienteId));

        List<Sugerencia> sugerencias = repositorio.findByClienteId(clienteId);

        List<SugerenciaDto> sugerenciaDtos = sugerencias.stream().map( e -> cambiaAsugerenciaDto(e)).collect(Collectors.toList());

        return sugerenciaDtos;
    }

    @Override
    public SugerenciaDto creaSugerencia(int clienteId, SugerenciaDto sugerenciaDto) {

        Cliente cliente = clienteRepositorio.findById(clienteId).orElseThrow( () -> new RecursoNotFoundException("cliente", "id", clienteId));

        Sugerencia sugerencia = new Sugerencia();

        sugerencia.setDepartamento(sugerenciaDto.getDepartamento());
        sugerencia.setMensaje(sugerenciaDto.getMensaje());

        sugerencia.setCliente(cliente);

        repositorio.save(sugerencia);

        SugerenciaDto sugerenciaDto1 = cambiaAsugerenciaDto(sugerencia);

        return sugerenciaDto1;
    }

    @Override
    public void eliminaSugerencia(int clienteId, int id) {

        Cliente cliente = clienteRepositorio.findById(clienteId).orElseThrow( () -> new RecursoNotFoundException("cliente", "id" , clienteId));

        Sugerencia sugerencia = repositorio.findById(id).orElseThrow( () -> new RecursoNotFoundException("sugerencia", "id", id));

        if(cliente.getCliente_id() != sugerencia.getCliente().getCliente_id()){

            throw new SugerenciaNotFounException(id, HttpStatus.BAD_REQUEST);
        }

        repositorio.delete(sugerencia);

    }

    @Override
    public SugerenciaDto editaCliente(int clienteId, int id, SugerenciaDto sugerenciaDto) {

        Cliente cliente = clienteRepositorio.findById(clienteId).orElseThrow( () -> new RecursoNotFoundException("cliente", "id" , clienteId));

        Sugerencia sugerencia = repositorio.findById(id).orElseThrow( () -> new RecursoNotFoundException("sugerencia", "id", id));

        if(cliente.getCliente_id() != sugerencia.getCliente().getCliente_id()){

            throw new SugerenciaNotFounException(id, HttpStatus.BAD_REQUEST);
        }

        sugerencia.setDepartamento(sugerenciaDto.getDepartamento());
        sugerencia.setMensaje(sugerenciaDto.getMensaje());

        repositorio.save(sugerencia);

        SugerenciaDto sugerenciaDto1 = cambiaAsugerenciaDto(sugerencia);

        return sugerenciaDto1;
    }

    public Sugerencia cambiaAsugerencia(SugerenciaDto sugerenciaDto){

        Sugerencia sugerencia = modelMapper.map(sugerenciaDto, Sugerencia.class);

        return  sugerencia;
    }

    public SugerenciaDto cambiaAsugerenciaDto(Sugerencia sugerencia){

        SugerenciaDto sugerenciaDto = modelMapper.map(sugerencia, SugerenciaDto.class);

        return sugerenciaDto;

    }
}
