package com.example.reservas.Servicios;

import com.example.reservas.DTO.HabitacionDTO;
import com.example.reservas.Entitys.Habitacion;
import com.example.reservas.Entitys.Hotel;
import com.example.reservas.Repos.HabitacionRepo;
import com.example.reservas.Repos.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    HabitacionRepo habitacionRepo;
    @Autowired
    HotelRepo hotelRepo;

    //método para crear una habitación
    public Boolean crearHabitaction(HabitacionDTO dto) {
        if (hotelRepo.findById(dto.getIdHotel()).isPresent()) { //si existe el Id del hotel en la BD
            Hotel hotel = hotelRepo.findById(dto.getIdHotel()).get(); //recupero el hotel en cuestión para pasárselo a la habitación
            habitacionRepo.save(parsearDtoToEntity(dto, hotel)); //parsear el dto a la entity y guardarlo
            return true;
        } else {
            throw new RuntimeException("No se pudo crear la habitación.");
        }
    }

    //método para actualizar una habitación
    public Boolean actualizarHabitacion(HabitacionDTO dto) {
        if (hotelRepo.findById(dto.getIdHotel()).isPresent()) { //si existe el Id del hotel en la BD
            Hotel hotel = hotelRepo.findById(dto.getIdHotel()).get(); //recupero el hotel en cuestión para pasárselo a la habitación
            habitacionRepo.save(parsearDtoToEntityWithId(dto, hotel)); //parsear el dto a la entity y machacarlo
            return true;
        } else {
            throw new RuntimeException("No se pudo actualizar la habitación.");
        }
    }

    //método para eliminar una habitación
    public Boolean eliminarHabitacion(Integer id) {
        Optional<Habitacion> habitacion = habitacionRepo.findById(id);

        if (habitacion.isPresent()) { //si existe la habitación con dicho id
            habitacionRepo.delete(habitacion.get()); //la eliminamos
            return true;
        } else {
            throw new RuntimeException("No se pudo eliminar la habitación.");
        }
    }

    //método para parsear el DTO a la Entity sin ID
    public Habitacion parsearDtoToEntity(HabitacionDTO dto, Hotel hotel) {
        Habitacion habitacion = new Habitacion();
        habitacion.setNumeroHabitacion(dto.getNumeroHabitacion());
        habitacion.setTipo(dto.getTipo());
        habitacion.setPrecio(BigDecimal.valueOf(dto.getPrecio()));
        habitacion.setDisponible(true);
        habitacion.setHotel(hotel);
        return habitacion;
    }

    //método para parsear el DTO a la Entity con ID
    public Habitacion parsearDtoToEntityWithId(HabitacionDTO dto, Hotel hotel) {
        Habitacion habitacion = new Habitacion();
        habitacion.setId(dto.getId());
        habitacion.setNumeroHabitacion(dto.getNumeroHabitacion());
        habitacion.setTipo(dto.getTipo());
        habitacion.setPrecio(BigDecimal.valueOf(dto.getPrecio()));
        habitacion.setDisponible(true);
        habitacion.setHotel(hotel);
        return habitacion;
    }

}
