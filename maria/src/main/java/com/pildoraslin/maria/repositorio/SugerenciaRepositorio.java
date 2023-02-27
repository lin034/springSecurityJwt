package com.pildoraslin.maria.repositorio;

import com.pildoraslin.maria.entidad.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SugerenciaRepositorio extends JpaRepository<Sugerencia, Integer> {

    @Query(value = "select * from sugerencia where cliente_id= :id", nativeQuery = true)
    public List<Sugerencia> findByClienteId(int id);
}
