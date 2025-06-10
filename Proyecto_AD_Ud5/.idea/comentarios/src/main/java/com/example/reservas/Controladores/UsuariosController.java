package com.example.reservas.Controladores;

import com.example.reservas.DTO.UsuariosDTO;
import com.example.reservas.Servicios.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuariosDTO dto) {
        try {
            usuariosService.crearUsuario(dto);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el usuario.");
        }
    }

    @PutMapping("/registrar")
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuariosDTO dto) {
        try {
            usuariosService.actualizarUsuario(dto);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el usuario");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarUsuario(@RequestBody UsuariosDTO dto) {
        try {
            usuariosService.eliminarUsuario(dto);
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el usuario");
        }
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestBody UsuariosDTO dto) {
        try {
            usuariosService.validarUsuario(dto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/info/id/{id}")
    public ResponseEntity<String> obtenerInfoUsuarioPorId(@PathVariable Integer id) {
        try {
            String nombre = usuariosService.obtenerInfoUsuarioPorId(id);
            return ResponseEntity.ok(nombre);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No existe ingún usuario con dicho id");
        }
    }

    @GetMapping("/info/nombre/{nombre}")
    public ResponseEntity<String> obtenerInfoUsuarioPorNombre(@PathVariable String nombre) {
        try {
            String id = usuariosService.obtenerInfoUsuarioPorNombre(nombre);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No existe ingún usuario con dicho id");
        }
    }

    @GetMapping("/checkIfExist/{id}")
    public ResponseEntity<Boolean> checkIfExist(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(usuariosService.checkIfExist(id)); //true
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

}
