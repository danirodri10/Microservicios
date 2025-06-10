package com.example.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EliminarComentarioUsuarioDTO {
    private String id;
    private String usuario;
    private String contrasena;
}
