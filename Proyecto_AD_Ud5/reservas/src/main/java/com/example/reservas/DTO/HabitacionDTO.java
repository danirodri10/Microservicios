package com.example.reservas.DTO;

import lombok.Data;

@Data
public class HabitacionDTO {
    //atributos de la habitación
    private Integer id;
    private Integer idHotel;
    private Integer numeroHabitacion;
    private String tipo;
    private Double precio;


    //atributos a recuperar del usuario
    private String usuario;
    private String contrasena;
}
