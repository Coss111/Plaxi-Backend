package plaxi.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.dto.RegisterRequestDto;
import plaxi.backend.dto.ResponseDto;
import plaxi.backend.dto.UsuarioDto;
import plaxi.backend.entity.Usuario;
import plaxi.backend.service.AuthService;
import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Logger para registrar los eventos de la clase
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // Inyección de dependencia para el servicio de autenticación
    @Autowired
    private AuthService authService;

    /**
     * Endpoint para registrar un nuevo usuario
     * @param registerRequest Datos del usuario a registrar
     * @return Respuesta con el resultado del registro
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDto<Usuario>> registerUser(@RequestBody RegisterRequestDto registerRequest) {
        // Registro de la solicitud de registro
        logger.info("Solicitud de registro recibida para el usuario: {}", registerRequest.getUsuario().getUsername());

        try {
            // Intento de registrar el usuario
            Usuario usuarioCreado = authService.registerUser(registerRequest.getUsuario(), registerRequest.getPersona());
            // Registro exitoso
            logger.info("Registro exitoso para el usuario: {}", registerRequest.getUsuario().getUsername());
            logger.info(null, usuarioCreado);

            // Respuesta exitosa con el mensaje de confirmación
            ResponseDto<Usuario> response = new ResponseDto<>("Usuario registrado exitosamente", null, true);
            return ResponseEntity.ok(response);

        } catch (AuthException e) {
            // Captura de excepción específica de autenticación
            logger.error("Error en el registro del usuario: {} - {}", registerRequest.getUsuario().getUsername(), e.getMessage());
            ResponseDto<Usuario> response = new ResponseDto<>(e.getMessage(), null, false);
            return ResponseEntity.status(400).body(response);
        } catch (Exception e) {
            // Captura de excepción general para otros errores
            logger.error("Error inesperado en el registro: {}", e.getMessage());
            ResponseDto<Usuario> response = new ResponseDto<>("Error inesperado", null, false);
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Endpoint para iniciar sesión de un usuario
     * @param usuarioDto Datos del usuario para autenticarse
     * @return Respuesta con el resultado del inicio de sesión
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<UsuarioDto>> loginUser(@RequestBody UsuarioDto usuarioDto) {
        // Registro de la solicitud de inicio de sesión
        logger.info("Solicitud de inicio de sesión recibida para: {}", usuarioDto.getUsername());

        try {
            // Intento de inicio de sesión
            Usuario usuario = authService.loginUser(usuarioDto.getUsername(), usuarioDto.getPassword());
            String successMessage = "Inicio de sesión exitoso para el usuario: " + usuario.getUsername();
            // Registro de inicio de sesión exitoso
            logger.info("Inicio de sesión exitoso para el usuario: {}", usuario.getUsername());

            // Preparación de los datos del usuario para la respuesta
            UsuarioDto usuarioResponse = new UsuarioDto(
                    usuario.getIdUsuario(),
                    usuario.getUsername(),
                    null,
                    usuario.getGmail(),
                    usuario.isStatus(),
                    usuario.getPersonaId().getIdPersona(),
                    usuario.getRolId().getIdRol()
            );

            // Respuesta exitosa con los datos del usuario
            ResponseDto<UsuarioDto> response = new ResponseDto<>(successMessage, usuarioResponse, true);
            return ResponseEntity.ok(response);

        } catch (AuthException e) {
            // Captura de excepción específica de autenticación
            logger.error("Error en el inicio de sesión para: {} - {}", usuarioDto.getUsername(), e.getMessage());
            ResponseDto<UsuarioDto> response = new ResponseDto<>(e.getMessage(), null, false);
            return ResponseEntity.status(401).body(response);
        } catch (Exception e) {
            // Captura de excepción general para otros errores
            logger.error("Error inesperado en el inicio de sesión: {}", e.getMessage());
            ResponseDto<UsuarioDto> response = new ResponseDto<>("Error inesperado", null, false);
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Endpoint para reiniciar la contraseña de un usuario
     * @param email Correo electrónico del usuario para enviar la nueva contraseña
     * @return Respuesta con el resultado del reinicio de contraseña
     */
    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto<Void>> resetPassword(@RequestParam String email) {
        try {
            // Intento de reiniciar la contraseña
            authService.resetPassword(email);
            // Respuesta exitosa con mensaje
            return ResponseEntity.ok(new ResponseDto<>("La nueva contraseña ha sido enviada a tu correo", null, true));
        } catch (Exception e) {
            // Captura de excepciones en caso de error
            return ResponseEntity.status(400).body(new ResponseDto<>(e.getMessage(), null, false));
        }
    }
}
