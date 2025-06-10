package com.example.reservas.Repos;

import com.example.reservas.Entitys.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitacionRepo extends JpaRepository<Habitacion, Integer> {
    Optional<Habitacion> findById(Integer id);
    Optional<Habitacion> findFirstByHotel_Id(Integer id);
}
