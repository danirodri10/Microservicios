package com.example.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {
    //Atributos del comentario
    private String nombreHotel;
    private int reserva_id;
    private double puntuacion;
    private String comentario;

    //Atributos del usuario
    private String usuario;
    private String contrasena;
}
