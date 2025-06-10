package com.example.reservas.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer id;

    private String nombre;

    private String direccion;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;
}
