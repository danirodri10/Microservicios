package com.example.reservas.DTO;

import lombok.Data;

@Data
public class HotelDTO {
    //atributos del hotel
    private Integer id;
    private String nombre;
    private String direccion;

    //atributos del usuario
    private String usuario;
    private String contrasena;
}
