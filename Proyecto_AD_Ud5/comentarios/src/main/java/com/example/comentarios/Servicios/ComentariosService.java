package com.example.comentarios.Servicios;

import com.example.comentarios.DTO.ComentarioDTO;
import com.example.comentarios.DTO.HotelDTO;
import com.example.comentarios.DTO.UsuarioDTO;
import com.example.comentarios.Entitys.Comentario;
import com.example.comentarios.Repos.ComentariosRepo;
import com.example.comentarios.Utilidades.ConectorReservasMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComentariosService {

    @Autowired
    ComentariosRepo comentariosRepo;

    //método para crear un comentario
    public void crearComentario(ComentarioDTO comentario, Integer idUsuario, Integer idHotel) {
        Optional<Comentario> comentarioOpt = comentariosRepo.findByUsuarioIdAndHotelIdAndReservaId(idUsuario, idHotel, comentario.getReserva_id());

        if (comentarioOpt.isEmpty()) { //si no existe dicha combinación en la BD
            System.out.println("Reserva aceptada");
            Comentario comentario1 = new Comentario();
            comentario1.setUsuarioId(idUsuario);
            comentario1.setHotelId(idHotel);
            comentario1.setReservaId(comentario.getReserva_id());
            comentario1.setPuntuacion((float) comentario.getPuntuacion());
            comentario1.setComentario(comentario.getComentario());
            comentario1.setFechaCreacion(Instant.now());

            comentariosRepo.save(comentario1);
        }
    }

    //método para eliminar todos los comentarios
    public void eliminarComentarios() {
        comentariosRepo.deleteAll();
    }

    //método para eliminar todos los comentarios
    public void eliminarComentariosDeUsuario(String id) {
        Optional<Comentario> comentario = comentariosRepo.findById(id);

        if (comentario.isPresent()) {
            comentariosRepo.delete(comentario.get());
        }
    }

    //método para listar los comentarios de un determinado hotel
    public List<ComentarioDTO> listarComentariosHotel(Integer hotelId, String nombreHotel) {

        Optional<List<Comentario>> comentariosHotel = comentariosRepo.findByHotelId(hotelId);
        List<ComentarioDTO> listaComentariosDto = new ArrayList<>();

        if (comentariosHotel.isPresent()) {
            for (Comentario comentario : comentariosHotel.get()) {
                ComentarioDTO comentarioDTO = new ComentarioDTO();
                comentarioDTO.setNombreHotel(nombreHotel);
                comentarioDTO.setReserva_id(comentario.getReservaId());
                comentarioDTO.setPuntuacion(comentario.getPuntuacion());
                comentarioDTO.setComentario(comentario.getComentario());

                listaComentariosDto.add(comentarioDTO);
            }
        }
        return listaComentariosDto;
    }

    //método para listar los comentarios de un determinado usuario
    public List<ComentarioDTO> listarComentariosUsuario(UsuarioDTO usuarioDTO, Integer usuarioId) {

        Optional<List<Comentario>> comentariosHotel = comentariosRepo.findByUsuarioId(usuarioId);
        List<ComentarioDTO> listaComentariosDto = new ArrayList<>();

        if (comentariosHotel.isPresent()) {
            for (Comentario comentario : comentariosHotel.get()) {
                HotelDTO hotelDTO = new HotelDTO();
                hotelDTO.setHotelId(comentario.getHotelId());
                hotelDTO.setUsuario(usuarioDTO.getUsuario());
                hotelDTO.setContrasena(usuarioDTO.getContrasena());

                String nombreHotel = ConectorReservasMicroservice.obtenerNombreAPartirDeId(hotelDTO);

                ComentarioDTO comentarioDTO = new ComentarioDTO();
                comentarioDTO.setNombreHotel(nombreHotel);
                comentarioDTO.setReserva_id(comentario.getReservaId());
                comentarioDTO.setPuntuacion(comentario.getPuntuacion());
                comentarioDTO.setComentario(comentario.getComentario());

                listaComentariosDto.add(comentarioDTO);
            }
        }
        return listaComentariosDto;
    }

    //método para listar los comentarios de un determinado usuario
    public List<ComentarioDTO> mostrarComentarioUsuarioReserva(UsuarioDTO usuarioDTO, Integer reservaId) {

        Optional<List<Comentario>> comentariosReserva = comentariosRepo.findByReservaId(reservaId);
        List<ComentarioDTO> listaComentariosDto = new ArrayList<>();

        if (comentariosReserva.isPresent()) {
            for (Comentario comentario : comentariosReserva.get()) {
                HotelDTO hotelDTO = new HotelDTO();
                hotelDTO.setHotelId(comentario.getHotelId());
                hotelDTO.setUsuario(usuarioDTO.getUsuario());
                hotelDTO.setContrasena(usuarioDTO.getContrasena());

                String nombreHotel = ConectorReservasMicroservice.obtenerNombreAPartirDeId(hotelDTO);

                ComentarioDTO comentarioDTO = new ComentarioDTO();
                comentarioDTO.setNombreHotel(nombreHotel);
                comentarioDTO.setReserva_id(comentario.getReservaId());
                comentarioDTO.setPuntuacion(comentario.getPuntuacion());
                comentarioDTO.setComentario(comentario.getComentario());

                listaComentariosDto.add(comentarioDTO);
            }
        }
        return listaComentariosDto;
    }

    //método para hacer la media de puntuación de un determinado hotel
    public Double puntuacionMediaHotel(Integer hotelId) {
        int totalComentarios = 0;
        double puntuacion = 0;

        Optional<List<Comentario>> comentariosHotel = comentariosRepo.findByHotelId(hotelId);

        if (comentariosHotel.isPresent() && !comentariosHotel.get().isEmpty()) {
            for (Comentario comentario : comentariosHotel.get()) {
                totalComentarios = comentariosHotel.get().size();
                puntuacion += comentario.getPuntuacion();
            }
            System.out.println("Media: " + (puntuacion / totalComentarios));
            return puntuacion / totalComentarios;
        }
        return 0.0;
    }

    //método para hacer la media de puntuación de un determinado usuario
    public Double puntuacionesMediasUsuario(Integer usuarioId) {
        int totalComentarios = 0;
        double puntuacion = 0;

        Optional<List<Comentario>> comentariosUsuario = comentariosRepo.findByUsuarioId(usuarioId);

        if (comentariosUsuario.isPresent() && !comentariosUsuario.get().isEmpty()) {
            for (Comentario comentario : comentariosUsuario.get()) {
                totalComentarios = comentariosUsuario.get().size();
                puntuacion += comentario.getPuntuacion();
            }
            System.out.println("Media: " + (puntuacion / totalComentarios));
            return puntuacion / totalComentarios;
        }
        return 0.0;
    }
}
