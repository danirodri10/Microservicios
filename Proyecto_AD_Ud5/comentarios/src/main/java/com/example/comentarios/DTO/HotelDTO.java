package com.example.comentarios.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class HotelDTO {
    //Atributos del hotel
    private Integer hotelId;
    @NonNull
    private String nombreHotel;

    //atributos del usuario
    @NonNull
    private String usuario;
    @NonNull
    private String contrasena;
}
