package com.example.reservas.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "reserva")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private Integer reservaId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    public Integer getHabitacionId() {
        return habitacion.getId();
    }

}

