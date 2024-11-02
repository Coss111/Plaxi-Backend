package plaxi.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class GmailGerenciaServices {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarAlerta(String toEmail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("⚠️ ¡Alerta de Seguridad en tu Domicilio!");

        String htmlMsg = "<html>"
                + "<body style='font-family: Arial, sans-serif; color: #333; background-color: #f9f9f9; padding: 20px;'>"
                + "<div style='max-width: 600px; margin: auto; border-radius: 8px; overflow: hidden; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);'>"
                + "<div style='background-color: #e74c3c; padding: 15px; text-align: center; color: white;'>"
                + "<h1 style='margin: 0;'>⚠️ Atención</h1>"
                + "</div>"
                + "<div style='padding: 20px; background-color: white;'>"
                + "<p style='font-size: 16px; line-height: 1.6;'>"
                + "Alguien está intentando acceder a tu casa. Por favor, verifica la situación <strong>inmediatamente</strong> para asegurar tu seguridad."
                + "</p>"
                + "<div style='text-align: center; margin-top: 20px;'>"
                + "<a href='#' style='display: inline-block; padding: 10px 20px; color: white; background-color: #e74c3c; border-radius: 5px; text-decoration: none; font-weight: bold;'>Verificar ahora</a>"
                + "</div>"
                + "<hr style='margin: 20px 0; border: 0; border-top: 1px solid #ddd;' />"
                + "<p style='font-size: 14px; color: #777;'>Este es un mensaje automático de seguridad.</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        helper.setText(htmlMsg, true);
        mailSender.send(message);
    }

}
