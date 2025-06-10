package com.example.reservas.DTO;

import lombok.Data;

@Data
public class UsuariosDTO {
    private Integer id;
    private String usuario;
    private String correo_electronico;
    private String direccion;
    private String contrasena;
}
