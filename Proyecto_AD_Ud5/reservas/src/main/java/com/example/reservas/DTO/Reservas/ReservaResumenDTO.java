package com.example.reservas.DTO.Reservas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResumenDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer habitacionId;
}
