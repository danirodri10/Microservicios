package com.example.comentarios.Entitys;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "comentarios") //esta clase representa un documento en la colección comentarios
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    private String id;

    private Integer usuarioId;
    private Integer hotelId;
    private Integer reservaId;
    private Float puntuacion;
    private String comentario;
    private Instant fechaCreacion;
}
