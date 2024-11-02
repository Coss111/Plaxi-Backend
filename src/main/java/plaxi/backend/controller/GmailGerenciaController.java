package plaxi.backend.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.service.GmailGerenciaServices;

import java.util.Map;

@RestController
@RequestMapping("/alert")
public class GmailGerenciaController {

    @Autowired
    private GmailGerenciaServices gmailGerenciaServices;

    @PostMapping("/send")
    public String sendAlert(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        // Correos de prueba:
        // svillegas.r@ucb.edu.bo
        // yerko.humerez@ucb.edu.bo
        try {
            gmailGerenciaServices.enviarAlerta(email);
            return "Alerta enviada a " + email;
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }
}