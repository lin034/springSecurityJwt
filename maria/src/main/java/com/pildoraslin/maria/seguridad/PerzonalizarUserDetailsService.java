package com.pildoraslin.maria.seguridad;

import com.pildoraslin.maria.entidad.Rol;
import com.pildoraslin.maria.entidad.Usuario;
import com.pildoraslin.maria.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PerzonalizarUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado con ese nombre de usuario o email "));

        return new User(usuario.getUsername(), usuario.getPassword(), mapearRoles(usuario.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles){
        return roles.stream().map(e -> new SimpleGrantedAuthority(e.getNombre())).collect(Collectors.toList());
    }
}
