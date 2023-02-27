package com.pildoraslin.maria.repositorio;

import com.pildoraslin.maria.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByUsernameOrEmail(String username, String email);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);



}
