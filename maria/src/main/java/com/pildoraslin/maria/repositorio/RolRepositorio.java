package com.pildoraslin.maria.repositorio;

import com.pildoraslin.maria.entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Integer> {
    public Rol findByNombre(String roleAdmin);
}
