package com.example.reservas.Servicios;

import com.example.reservas.DTO.HotelDTO;
import com.example.reservas.Entitys.Hotel;
import com.example.reservas.Repos.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepo hotelRepo;

    //método para crear un hotel
    public Boolean crearHotel(HotelDTO dto) {
        if (!hotelRepo.findByNombreAndDireccion(dto.getNombre(), dto.getDireccion()).isPresent()) { //si no existe ese hotel
            hotelRepo.save(parsearDtoToEntity(dto)); //lo creamos
            return true;
        } else {
            throw new RuntimeException("Ya existe ese hotel.");
        }
    }

    //método para crear un hotel
    public Boolean actualizarHotel(HotelDTO dto) {
        if (hotelRepo.findById(dto.getId()).isPresent()) { //si existe ese hotel
            hotelRepo.save(parsearDtoToEntityWithId(dto)); //lo machacamos
            return true;
        } else {
            throw new RuntimeException("No existe un hotel con ese id.");
        }
    }

    //método para crear un hotel
    public Boolean eliminarHotel(Integer id) {
        Optional<Hotel> hotel = hotelRepo.findById(id);

        if (hotel.isPresent()) { //si existe el hotel con dicho id
            hotelRepo.delete(hotel.get()); //lo eliminamos
            return true;
        } else {
            throw new RuntimeException("No se pudo eliminar el hotel.");
        }
    }

    //método para recuperar el nombre a partir del id
    public String obtenerNombreAPartirDelId(Integer id) {
        Optional<Hotel> hotel = hotelRepo.findById(id);

        if (hotel.isPresent()) { //si existe el hotel con dicho id
            return hotel.get().getNombre(); //devuelvo el nombre
        } else {
            throw new RuntimeException("No existe ningún hotel con dicho id.");
        }
    }

    //método para recuperar el id a partir del nombre
    public String obtenerIdAPartirDelNombre(String nombre) {
        Optional<Hotel> hotel = hotelRepo.findByNombre(nombre);

        if (hotel.isPresent()) { //si existe el hotel con dicho nombre
            return String.valueOf(hotel.get().getId()); //devuelvo el id com String
        } else {
            throw new RuntimeException("No existe ningún hotel con dicho nombre.");
        }
    }

    //método para parsear el DTO a la entity sin el id
    public Hotel parsearDtoToEntity(HotelDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setNombre(dto.getNombre());
        hotel.setDireccion(dto.getDireccion());
        return hotel;
    }

    //método para parsear el DTO a la entity con el id
    public Hotel parsearDtoToEntityWithId(HotelDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setId(dto.getId());
        hotel.setNombre(dto.getNombre());
        hotel.setDireccion(dto.getDireccion());
        return hotel;
    }

}

