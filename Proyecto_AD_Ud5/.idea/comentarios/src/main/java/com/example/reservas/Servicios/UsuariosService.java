package com.example.reservas.Servicios;

import com.example.reservas.DTO.UsuariosDTO;
import com.example.reservas.Entitys.Usuario;
import com.example.reservas.Repos.UsuariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepo usuariosRepo;

    //método para crear un usuario
    public void crearUsuario(UsuariosDTO dto) {

        //sino encontramos un usuario con ese nombre en la BD, lo guardamos
        if (usuariosRepo.findByUsuario(dto.getUsuario()).isEmpty()) {
            usuariosRepo.save(parsearDtoToEntity(dto));
        } else {
            throw new RuntimeException("El usuario ya existe.");
        }
    }

    //método para actualizar un usuario
    public void actualizarUsuario(UsuariosDTO dto) {
        //si encontramos un usuario con ese Id en la BD, lo machacamos
        if (!usuariosRepo.findById(dto.getId()).isEmpty()) {
            usuariosRepo.save(parsearDtoToEntityWithId(dto));
        } else {
            throw new RuntimeException("El usuario que intentas actualizar no existe.");
        }
    }

    //método para actualizar un usuario
    public void eliminarUsuario(UsuariosDTO dto) {

        Optional<Usuario> usuario = usuariosRepo.findByUsuarioAndContrasena(dto.getUsuario(), dto.getContrasena());

        if (usuario.isPresent()) { // es lo mismo que isNotEmpty
            usuariosRepo.delete(usuario.get()); //recupero el usuario del Optional
        } else {
            throw new RuntimeException("No existe ningún usuario con dicho nombre y contraseña.");
        }
    }

    //método para validar un usuario
    public boolean validarUsuario(UsuariosDTO dto) {
        Optional<Usuario> usuario = usuariosRepo.findByUsuarioAndContrasena(dto.getUsuario(), dto.getContrasena());

        if (usuario.isPresent()) {
            return true;
        } else {
            throw new RuntimeException("No existe ningún usuario con dicho nombre y contraseña.");
        }
    }

    //método para obtener un usuario por Id
    public String obtenerInfoUsuarioPorId(Integer id) {

        Optional<Usuario> usuario = usuariosRepo.findById(id);

        if (usuario.isPresent()) {
            return usuario.get().getUsuario();
        } else {
            throw new RuntimeException("No existe ningún usuario con dicho id.");
        }
    }

    //método para obtener un id por el nombre
    public String obtenerInfoUsuarioPorNombre(String nombre) {
        Optional<Usuario> usuario = usuariosRepo.findByUsuario(nombre);

        if (usuario.isPresent()) {
            return String.valueOf(usuario.get().getUsuario_id());
        } else {
            throw new RuntimeException("No existe ningún usuario con dicho nombre.");
        }
    }

    //método para saber si un usuario existe por Id
    public boolean checkIfExist(Integer id) {
        Optional<Usuario> usuario = usuariosRepo.findById(id);

        if (usuario.isPresent()) {
            return true;
        } else {
            throw new RuntimeException("No existe ningún usuario con dicho id.");
        }
    }


    private Usuario parsearDtoToEntity(UsuariosDTO dto) {
        return new Usuario(dto.getUsuario(), dto.getCorreo_electronico(), dto.getDireccion(), dto.getContrasena());
    }

    private Usuario parsearDtoToEntityWithId(UsuariosDTO dto) {
        return new Usuario(dto.getId(), dto.getUsuario(), dto.getCorreo_electronico(), dto.getDireccion(), dto.getContrasena());
    }
}
