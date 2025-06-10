package com.example.reservas.Controladores;

import com.example.reservas.DTO.HabitacionDTO;
import com.example.reservas.DTO.UsuarioDTO;
import com.example.reservas.Servicios.HabitacionService;
import com.example.reservas.Utilidades.ConectorUsuariosMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class HabitacionesController {

    @Autowired
    private HabitacionService habitacionService;

    public HabitacionesController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping("/habitacion")
    public ResponseEntity<String> crearHabitacion(@RequestBody HabitacionDTO dto) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                habitacionService.crearHabitaction(dto);
                return ResponseEntity.ok("Habitación creada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO se pudo crear la habitación");
        }
    }

    @PatchMapping("/habitacion")
    public ResponseEntity<String> actualizarHabitacion(@RequestBody HabitacionDTO dto) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                habitacionService.actualizarHabitacion(dto);
                return ResponseEntity.ok("Habitación actualizada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo actualizar la habitación");
        }
    }

    @DeleteMapping("/habitacion/{id}")
    public ResponseEntity<String> eliminarHabitacion(@RequestBody HabitacionDTO dto, @PathVariable Integer id) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                habitacionService.eliminarHabitacion(id);
                return ResponseEntity.ok("Habitación eliminada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo eliminar la habitación");
        }
    }
}
