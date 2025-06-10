package com.example.reservas.Controladores;

import com.example.reservas.DTO.HotelDTO;
import com.example.reservas.DTO.UsuarioDTO;
import com.example.reservas.Servicios.HotelService;
import com.example.reservas.Utilidades.ConectorUsuariosMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class HotelesController {

    @Autowired
    private HotelService hotelService;

    public HotelesController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/hotel")
    public ResponseEntity<String> crearHotel(@RequestBody HotelDTO dto) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                hotelService.crearHotel(dto);
                return ResponseEntity.ok("Hotel creado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO se pudo crear el hotel");
        }
    }

    @PatchMapping("/hotel")
    public ResponseEntity<String> actualizarHotel(@RequestBody HotelDTO dto) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                hotelService.actualizarHotel(dto);
                return ResponseEntity.ok("Hotel actualizado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO se pudo actualizar el hotel");
        }
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<String> eliminarHotel(@RequestBody HotelDTO dto, @PathVariable Integer id) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                hotelService.eliminarHotel(id);
                return ResponseEntity.ok("Hotel eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO se pudo eliminar el hotel");
        }
    }

    @PostMapping("/hotel/id/{nombre}")
    public ResponseEntity<String> obtenerIdAPartirDeNombre(@RequestBody HotelDTO dto, @PathVariable String nombre) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                return ResponseEntity.ok(hotelService.obtenerIdAPartirDelNombre(nombre)); //devolvemos el id del nombre
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO existe ningún hotel con dicho id");
        }
    }

    @PostMapping("/hotel/nombre/{id}")
    public ResponseEntity<String> obtenerNombreAPartirDeId(@RequestBody HotelDTO dto, @PathVariable Integer id) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                return ResponseEntity.ok(hotelService.obtenerNombreAPartirDelId(id)); //devolvemos el nombre del id
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO existe ningún hotel con dicho id");
        }
    }
}
