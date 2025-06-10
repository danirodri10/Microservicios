package com.example.reservas.Servicios;

import com.example.reservas.DTO.Reservas.ReservaDTO;
import com.example.reservas.DTO.Reservas.ReservaResumenDTO;
import com.example.reservas.Entitys.Habitacion;
import com.example.reservas.Entitys.Reserva;
import com.example.reservas.Repos.HabitacionRepo;
import com.example.reservas.Repos.ReservaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    ReservaRepo reservaRepo;
    @Autowired
    HabitacionRepo habitacionRepo;

    //método para crear una reserva
    public Boolean crearReserva(ReservaDTO dto, Integer idUsuario) {
        Optional<Habitacion> habitacionOpt = habitacionRepo.findById(dto.getHabitacion_id());

        if (habitacionOpt.isPresent()) { //si existe la habitación
            Habitacion habitacion = habitacionOpt.get(); //recuperamos la habitación

            //parsear la reserva y guardarla
            reservaRepo.save(parsearDtoToEntity(dto, idUsuario, habitacion));
            return true;
        } else {
            throw new RuntimeException("No existe ninguna habitación con ese id.");
        }
    }

    //método para cambiar el estado de una reserva
    public Boolean cambiarEstado(ReservaDTO dto) {
        Optional<Reserva> reservaOpt = reservaRepo.findByReservaId(dto.getReserva_id());
        if (reservaOpt.isPresent()) { //si existe esa reserva
            Reserva reserva = reservaOpt.get();
            reserva.setReservaId(dto.getReserva_id());
            reserva.setEstado(dto.getEstado());
            reservaRepo.save(reserva); //lo machacamos
            return true;
        } else {
            throw new RuntimeException("No existe una reserva con ese id.");
        }
    }

    //método para listar las reservas de un usuario
    public List<ReservaResumenDTO> listarReservasUsuario(Integer usuarioId) {
        try {
            List<Reserva> reservas = reservaRepo.findByUsuarioId(usuarioId); //recuperamos la lista de reservas de dicho usuario
            List<ReservaResumenDTO> reservaResumenDTOS = new ArrayList<>(); //creamos la lista de DTOs con los campos que nos interesan

            //les asignamos sus respectivos datos
            for (Reserva reserva : reservas) {
                ReservaResumenDTO reservaResumenDTO = new ReservaResumenDTO();
                reservaResumenDTO.setFechaInicio(reserva.getFechaInicio());
                reservaResumenDTO.setFechaFin(reserva.getFechaFin());
                reservaResumenDTO.setHabitacionId(reserva.getHabitacionId());
                reservaResumenDTOS.add(reservaResumenDTO);
            }

            return reservaResumenDTOS;
        } catch (Exception e) {
            throw new RuntimeException("No existe una reserva con ese id de usuario.");
        }
    }

    //método para listar las reservas en base a un estado
    public List<ReservaResumenDTO> listarReservasSegunEstado(String estado) {
        try {
            List<Reserva> reservas = reservaRepo.findByEstado(estado); //recuperamos la lista de reservas de dicho estado
            List<ReservaResumenDTO> reservaResumenDTOS = new ArrayList<>(); //creamos la lista de DTOs con los campos que nos interesan

            //les asignamos sus respectivos datos
            for (Reserva reserva : reservas) {
                ReservaResumenDTO reservaResumenDTO = new ReservaResumenDTO();
                reservaResumenDTO.setFechaInicio(reserva.getFechaInicio());
                reservaResumenDTO.setFechaFin(reserva.getFechaFin());
                reservaResumenDTO.setHabitacionId(reserva.getHabitacionId());
                reservaResumenDTOS.add(reservaResumenDTO);
            }

            return reservaResumenDTOS;
        } catch (Exception e) {
            throw new RuntimeException("No existe una reserva con ese id de usuario.");
        }
    }

    //método para comprobar si una reserva está asociada a un usuario y habitación concretos
    public Boolean checkReserva(Integer idUsuario, Integer idHotel, Integer idReserva) {
        Optional<Reserva> reserva = reservaRepo.findByReservaId(idReserva); //recupero la reserva con dicho id de reserva
        Optional<Habitacion> habitacion = habitacionRepo.findFirstByHotel_Id(idHotel); //recupero la habitación con dicho id de hotel
        Integer idHabitacion = habitacion.get().getId(); //obtengo el id de

        Reserva reserva1 = reserva.get();

        return reserva1.getHabitacionId() == idHabitacion && reserva1.getUsuarioId() == idUsuario;
    }

    //método para parsear el DTO a la entidad
    public Reserva parsearDtoToEntity(ReservaDTO dto, Integer usuarioId, Habitacion habitacion) {
        Reserva reserva = new Reserva();
        reserva.setUsuarioId(usuarioId);
        reserva.setHabitacion(habitacion);
        reserva.setFechaInicio(dto.getFecha_inicio());
        reserva.setFechaFin(dto.getFecha_fin());
        reserva.setEstado("confirmada");
        return reserva;
    }
}
