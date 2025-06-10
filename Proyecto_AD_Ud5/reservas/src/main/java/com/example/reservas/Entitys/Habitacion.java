package com.example.reservas.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "habitacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitacion_id")
    private Integer id;

    @Column(name = "numero_habitacion")
    private Integer numeroHabitacion;
    private String tipo;
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;
    private Boolean disponible;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL)
    private List<Reserva> reservas;
}

