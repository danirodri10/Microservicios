package com.example.comentarios.Utilidades;

import com.example.comentarios.DTO.HotelDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ConectorReservasMicroservice {

    //método para obetener el Id de un hotel a través del nombre en su Microservicio con Post
    public static Integer obtenerIdAPartirDeNombre(HotelDTO dto) {

        System.out.println("Enviando petición a /reservas/hotel/id/{nombre} con: " + dto.getNombreHotel());

        RestTemplate restTemplate = new RestTemplate();
        String nombre = dto.getNombreHotel();
        String urlServicio = "http://localhost:8501/reservas/hotel/id/{nombre}"; //url del método a consultar
        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(urlServicio, dto, Integer.class, nombre); //información a enviar y tipo de respuesta obtenida

        System.out.println("Respuesta recibida: " + responseEntity.getBody());

        Integer hotelId = responseEntity.getBody(); //resultado de la respuesta
        return hotelId;
    }

    //método para obetener el nombre de un hotel a través del id en su Microservicio con Post
    public static String obtenerNombreAPartirDeId(HotelDTO dto) {

        System.out.println("Enviando petición a /reservas/hotel/nombre/{id} con: " + dto.getHotelId());

        RestTemplate restTemplate = new RestTemplate();
        Integer hotelId = dto.getHotelId();
        String urlServicio = "http://localhost:8501/reservas/hotel/nombre/{id}"; //url del método a consultar
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(urlServicio, dto, String.class, hotelId); //información a enviar y tipo de respuesta obtenida

        System.out.println("Respuesta recibida: " + responseEntity.getBody());

        String nombreHotel = responseEntity.getBody(); //resultado de la respuesta
        return nombreHotel;
    }

    //método para chekearReserva en su Microservicio con Get
    public static Boolean checkReserva(Integer idUsuario, Integer idHotel, Integer idReserva) {

        System.out.println("Enviando petición a /reservas/check/{idUsuario}/{idHotel}/{idReserva} con: " + idUsuario + ", " + idHotel + ", " + idReserva);

        RestTemplate restTemplate = new RestTemplate();
        String urlServicio = "http://localhost:8501/reservas/check/{idUsuario}/{idHotel}/{idReserva}"; //url del método a consultar
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(urlServicio, Boolean.class, idUsuario, idHotel, idReserva); //información a enviar y tipo de respuesta obtenida

        System.out.println("Respuesta recibida: " + responseEntity.getBody());

        Boolean respuesta = responseEntity.getBody(); //resultado de la respuesta
        return respuesta;
    }

}
