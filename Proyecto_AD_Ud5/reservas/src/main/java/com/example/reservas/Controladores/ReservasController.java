package com.example.reservas.Controladores;

import com.example.reservas.DTO.HabitacionDTO;
import com.example.reservas.DTO.HotelDTO;
import com.example.reservas.DTO.Reservas.ReservaDTO;
import com.example.reservas.DTO.UsuarioDTO;
import com.example.reservas.Servicios.HabitacionService;
import com.example.reservas.Servicios.HotelService;
import com.example.reservas.Servicios.ReservaService;
import com.example.reservas.Utilidades.ConectorUsuariosMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservaService reservaService;

    public ReservasController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }


    @PostMapping
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDTO dto) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                Integer idUsuario = Integer.valueOf(ConectorUsuariosMicroservice.recuperarIdUsuarioConGet(usuarioDTO));
                reservaService.crearReserva(dto, idUsuario);
                return ResponseEntity.ok("Reserva creada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO existe ninguna habitación con dicho id");
        }
    }

    @PatchMapping
    public ResponseEntity<String> cambiarEstado(@RequestBody ReservaDTO dto) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                reservaService.cambiarEstado(dto);
                return ResponseEntity.ok("Estado de la reserva modificado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("NO existe ninguna reserva con dicho id");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarReservasUsuario(@RequestBody ReservaDTO dto) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                Integer idUsuario = Integer.valueOf(ConectorUsuariosMicroservice.recuperarIdUsuarioConGet(usuarioDTO));
                return ResponseEntity.ok(reservaService.listarReservasUsuario(idUsuario)); //devuelve la lista personalizada con la información
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No existe ninguna reserva con dicho id de usuario");
        }
    }

    @GetMapping("/{estado}")
    public ResponseEntity<?> listarReservasSegunEstado(@RequestBody ReservaDTO dto, @PathVariable String estado) {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(dto.getUsuario(), dto.getContrasena());

            if (ConectorUsuariosMicroservice.validarUsuarioConPost(usuarioDTO)) {
                return ResponseEntity.ok(reservaService.listarReservasSegunEstado(estado)); //devuelve la lista personalizada con la información
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado"); //caso de que no valide el usuario
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No existe ninguna reserva con dicho id de usuario");
        }
    }

    @GetMapping("/check/{idUsuario}/{idHotel}/{idReserva}")
    public ResponseEntity<Boolean> checkReserva(@PathVariable Integer idUsuario, @PathVariable Integer idHotel, @PathVariable Integer idReserva) {
        try {

            reservaService.checkReserva(idUsuario, idHotel, idReserva);
            return ResponseEntity.ok(true);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }


}
