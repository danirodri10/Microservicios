package com.example.reservas.DTO.Reservas;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaDTO {
    //atributos de la reserva
    private Integer reserva_id;
    private Integer usuario_id;
    private Integer habitacion_id;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String estado;

    //atributos del usuario
    private String usuario;
    private String contrasena;
}
