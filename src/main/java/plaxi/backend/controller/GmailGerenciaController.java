package plaxi.backend.controller;

import jakarta.mail.MessagingException; // Importa la clase MessagingException para manejar errores relacionados con el envío de correos electrónicos.
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para inyectar dependencias automáticamente.
import org.springframework.web.bind.annotation.*; // Importa las anotaciones necesarias para manejar las solicitudes HTTP en un controlador REST.
import plaxi.backend.service.GmailGerenciaServices; // Importa el servicio que se encarga de enviar los correos electrónicos.

import java.util.Map; // Importa la clase Map, que se utilizará para obtener los datos de la solicitud.

@RestController // Indica que esta clase es un controlador REST.
@RequestMapping("/alert") // Define la ruta base para todas las solicitudes de este controlador (en este caso, "/alert").
public class GmailGerenciaController {

    @Autowired // Inyecta el servicio GmailGerenciaServices para su uso en este controlador.
    private GmailGerenciaServices gmailGerenciaServices;

    // Método para manejar las solicitudes POST a la ruta "/send" y enviar alertas por correo electrónico.
    @PostMapping("/send")
    public String sendAlert(@RequestBody Map<String, String> request) {
        // Obtiene el valor asociado a la clave "email" del cuerpo de la solicitud.
        String email = request.get("email");
        // Comentarios sobre los correos electrónicos de prueba:
        // svillegas.r@ucb.edu.bo
        // yerko.humerez@ucb.edu.bo
        try {
            // Llama al servicio para enviar la alerta por correo electrónico al correo proporcionado.
            gmailGerenciaServices.enviarAlerta(email);
            // Si el correo se envía correctamente, retorna un mensaje de éxito con el correo destinatario.
            return "Alerta enviada a " + email;
        } catch (MessagingException e) {
            // Si ocurre un error al enviar el correo, captura la excepción y retorna un mensaje de error con el detalle.
            return "Error al enviar el correo: " + e.getMessage();
        }
    }
}
