package com.example.reservas.Entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuario_id;

    @NonNull
    @Column(name = "nombre")
    private String usuario;
    @NonNull
    private String correo_electronico;
    @NonNull
    private String direccion;
    @NonNull
    private String contrasena;

}
