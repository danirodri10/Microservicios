package com.example.comentarios.Repos;

import com.example.comentarios.Entitys.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentariosRepo extends MongoRepository<Comentario, String> {

    Optional<Comentario> findById(String id);
    Optional<Comentario> findByUsuarioIdAndHotelIdAndReservaId(Integer usuarioId, Integer hotelId, Integer reservaId);
    Optional<List<Comentario>> findByHotelId(Integer hotelId);
    Optional<List<Comentario>> findByUsuarioId(Integer usuarioId);
    Optional<List<Comentario>> findByReservaId(Integer reservaId);
}
