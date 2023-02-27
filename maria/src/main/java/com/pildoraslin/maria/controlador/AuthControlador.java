package com.pildoraslin.maria.controlador;

import com.pildoraslin.maria.dto.LoginDto;
import com.pildoraslin.maria.dto.UsuarioDto;
import com.pildoraslin.maria.entidad.Rol;
import com.pildoraslin.maria.entidad.Usuario;
import com.pildoraslin.maria.exception.UsuarioNotFoundException;
import com.pildoraslin.maria.repositorio.RolRepositorio;
import com.pildoraslin.maria.repositorio.UsuarioRepositorio;
import com.pildoraslin.maria.seguridad.JwtAuthRespuestaDto;
import com.pildoraslin.maria.seguridad.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/usuarios")
public class AuthControlador {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    RolRepositorio rolRepositorio;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    //comentario para checar git

    @PostMapping("/iniciarSesion")
    public ResponseEntity<JwtAuthRespuestaDto> iniciarSesion(@RequestBody LoginDto loginDto){

        //esta linea es super importante es el que autentica al usuario por medio del perzonalizadoUserDeatilsService
        //Intenta autenticar el Authenticationobjeto pasado, devolviendo un objeto completo Authentication(incluidas las autorizaciones otorgadas) si tiene éxito.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        //este es el usuario autenticado q tiene la sesion abierta navegando entre la rest
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generarToken(authentication);



        return ResponseEntity.ok(new JwtAuthRespuestaDto(token));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearUsuario")
    public Usuario creaUsuario(@RequestBody UsuarioDto usuarioDto){

        if(usuarioRepositorio.existsByUsername(usuarioDto.getUsername())){

            throw new UsuarioNotFoundException(usuarioDto.getUsername(), HttpStatus.BAD_GATEWAY);
        }

        if(usuarioRepositorio.existsByEmail(usuarioDto.getEmail())){

            throw new UsuarioNotFoundException(usuarioDto.getEmail(), HttpStatus.BAD_GATEWAY);
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioDto.getNombre());
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setEmail(usuarioDto.getEmail());
        //usuario.setPassword(usuarioDto.getPassword());
        usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));

        Rol rol = rolRepositorio.findByNombre("ROLE_ADMIN");

        usuario.setRoles(Collections.singleton(rol));

        usuarioRepositorio.save(usuario);

        return usuario;

    }
}
