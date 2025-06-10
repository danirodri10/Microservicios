package com.example.reservas.Repos;

import com.example.reservas.Entitys.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {
    Optional<Hotel> findById(Integer id);
    Optional<Hotel> findByNombreAndDireccion(String nombre, String direccion);
    Optional<Hotel> findByNombre(String nombre);
}
