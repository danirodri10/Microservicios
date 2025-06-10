package com.example.comentarios.Controladores;

import com.example.comentarios.DTO.ComentarioDTO;
import com.example.comentarios.DTO.EliminarComentarioUsuarioDTO;
import com.example.comentarios.DTO.HotelDTO;
import com.example.comentarios.DTO.UsuarioDTO;
import com.example.comentarios.Servicios.ComentariosService;
import com.example.comentarios.Utilidades.ConectorReservasMicroservice;
import com.example.comentarios.Utilidades.ConectorUsuariosMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class ComentariosGraphQlController {

    @Autowired
    ComentariosService comentariosService;

    //método para crear un comentario
    @MutationMapping
    public ComentarioDTO crearComentario(@Argument ComentarioDTO comentario) {
        //System.out.println("Llamando a crearComentario con " + comentario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(comentario.getUsuario(), comentario.getContrasena());

        if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
            //System.out.println("Usuario aceptado");
            Integer idUsuario = Integer.valueOf(ConectorUsuariosMicroservice.recuperarIdUsuarioConGet(usuarioDTO)); //recuperamos el id del usuario

            HotelDTO hotelDTO = new HotelDTO(comentario.getNombreHotel(), comentario.getUsuario(), comentario.getContrasena());
            Integer idHotel = ConectorReservasMicroservice.obtenerIdAPartirDeNombre(hotelDTO); //obtenemos el id del hotel

            if (ConectorReservasMicroservice.checkReserva(idUsuario, idHotel, comentario.getReserva_id())) {

                comentariosService.crearComentario(comentario, idUsuario, idHotel);

                return comentario;
            }
        }
        return null;
    }

    @MutationMapping
    public String eliminarComentarios() {
        try {
            comentariosService.eliminarComentarios();
            return "Todos los comentarios han sido eliminados correctamente.";
        } catch (Exception e) {
            //System.err.println("Error eliminando comentarios: " + e.getMessage());
            return "Error al eliminar los comentarios.";
        }
    }

    @MutationMapping
    public String eliminarComentarioDeUsuario(@Argument("comentario") EliminarComentarioUsuarioDTO eliminarComentarioUsuarioDTO) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(eliminarComentarioUsuarioDTO.getUsuario(), eliminarComentarioUsuarioDTO.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                comentariosService.eliminarComentariosDeUsuario(eliminarComentarioUsuarioDTO.getId());
                return "Comentario eliminado correctamente.";
            } else {
                return "Usuario denegado";
            }
        } catch (Exception e) {
            //System.err.println("Error eliminando comentarios: " + e.getMessage());
            return "Error al eliminar el comentario.";
        }
    }

    @QueryMapping
    public List<ComentarioDTO> listarComentariosHotel(@Argument("comentario") ComentarioDTO comentarioDTO) {
        //System.out.println("Llamando a crearComentario con " + comentario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(comentarioDTO.getUsuario(), comentarioDTO.getContrasena());

        if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
            //System.out.println("Usuario aceptado");

            HotelDTO hotelDTO = new HotelDTO(comentarioDTO.getNombreHotel(), comentarioDTO.getUsuario(), comentarioDTO.getContrasena());
            Integer idHotel = ConectorReservasMicroservice.obtenerIdAPartirDeNombre(hotelDTO); //obtenemos el id del hotel

            return comentariosService.listarComentariosHotel(idHotel, comentarioDTO.getNombreHotel());
        }
        return null;
    }

    @QueryMapping
    public List<ComentarioDTO> listarComentariosUsuario(@Argument("comentario") ComentarioDTO comentarioDTO) {
        //System.out.println("Llamando a crearComentario con " + comentario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(comentarioDTO.getUsuario(), comentarioDTO.getContrasena());

        if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {

            Integer idUsuario = Integer.valueOf(ConectorUsuariosMicroservice.recuperarIdUsuarioConGet(usuarioDTO)); //recuperamos el id del usuario
            return comentariosService.listarComentariosUsuario(usuarioDTO, idUsuario);
        }
        return null;
    }

    @QueryMapping
    public List<ComentarioDTO> mostrarComentarioUsuarioReserva(@Argument("comentario") ComentarioDTO comentarioDTO) {
        //System.out.println("Llamando a crearComentario con " + comentario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(comentarioDTO.getUsuario(), comentarioDTO.getContrasena());

        if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {

            return comentariosService.mostrarComentarioUsuarioReserva(usuarioDTO, comentarioDTO.getReserva_id());
        }
        return null;
    }

    @QueryMapping
    public Double puntuacionMediaHotel(@Argument("comentario") ComentarioDTO comentarioDTO) {
        //System.out.println("Llamando a crearComentario con " + comentario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(comentarioDTO.getUsuario(), comentarioDTO.getContrasena());

        if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {

            HotelDTO hotelDTO = new HotelDTO(comentarioDTO.getNombreHotel(), comentarioDTO.getUsuario(), comentarioDTO.getContrasena());
            Integer idHotel = ConectorReservasMicroservice.obtenerIdAPartirDeNombre(hotelDTO);

            return comentariosService.puntuacionMediaHotel(idHotel);
        }
        return 0.0;
    }

    @QueryMapping
    public Double puntuacionesMediasUsuario(@Argument("comentario") ComentarioDTO comentarioDTO) {
        //System.out.println("Llamando a crearComentario con " + comentario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(comentarioDTO.getUsuario(), comentarioDTO.getContrasena());

        if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {

            Integer usuarioID = Integer.valueOf(ConectorUsuariosMicroservice.recuperarIdUsuarioConGet(usuarioDTO));
            return comentariosService.puntuacionesMediasUsuario(usuarioID);
        }
        return 0.0;
    }
}





    


