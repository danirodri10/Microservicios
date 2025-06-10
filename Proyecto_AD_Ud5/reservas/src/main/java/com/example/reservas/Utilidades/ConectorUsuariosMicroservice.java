package com.example.reservas.Utilidades;

import com.example.reservas.DTO.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ConectorUsuariosMicroservice {

    //método para validar un usuario en su Microservicio con Post
    public static Boolean validarUsuarioConPost(UsuarioDTO dto) {

        System.out.println("Enviando petición a /usuarios/validar con: " + dto.getUsuario() + ", " + dto.getContrasena());

        RestTemplate restTemplate = new RestTemplate();
        String urlServicio = "http://localhost:8502/usuarios/validar"; //url del método a consultar
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(urlServicio, dto, Boolean.class); //información a enviar y tipo de respuesta obtenida

        System.out.println("Respuesta recibida: " + responseEntity.getBody());

        Boolean resultado = responseEntity.getBody(); //resultado de la respuesta
        return resultado;
    }

    //método para recuperar el id de un usuario en su Microservicio con Post
    public static String recuperarIdUsuarioConGet(UsuarioDTO dto) {

        System.out.println("Enviando petición a /usuarios/info/nombre/{nombre} con: " + dto.getUsuario());

        RestTemplate restTemplate = new RestTemplate();
        String urlServicio = "http://localhost:8502/usuarios/info/nombre/" + dto.getUsuario(); //url del método a consultar
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlServicio, String.class); //información a enviar y tipo de respuesta obtenida

        System.out.println("Respuesta recibida: " + responseEntity.getBody());

        String resultado = responseEntity.getBody(); //resultado de la respuesta
        return resultado;
    }
}
