package com.pildoraslin.maria.service;

import com.pildoraslin.maria.dto.SugerenciaDto;

import java.util.List;

public interface SugerenciaService {
    public List<SugerenciaDto> dameSugerencias();

    public List<SugerenciaDto> dameSugerenciaPorClienteId(int clienteId);

    public SugerenciaDto creaSugerencia(int clienteId, SugerenciaDto sugerenciaDto);

    public void eliminaSugerencia(int clienteId, int id);

    public SugerenciaDto editaCliente(int clienteId, int id, SugerenciaDto sugerenciaDto);
}
