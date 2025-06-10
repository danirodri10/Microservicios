package com.example.reservas.Repos;

import com.example.reservas.Entitys.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {
    Optional<Reserva> findByReservaId(Integer id);
    List<Reserva> findByUsuarioId(Integer id);
    List<Reserva> findByEstado(String estado);

}
