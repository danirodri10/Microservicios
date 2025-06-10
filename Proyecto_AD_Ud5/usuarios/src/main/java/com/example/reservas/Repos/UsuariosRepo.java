package com.example.reservas.Repos;

import com.example.reservas.Entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepo extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuario(String usuario);
    Optional<Usuario> findById(Integer id);
    Optional<Usuario> findByUsuarioAndContrasena(String usuario, String contrasena);
}
