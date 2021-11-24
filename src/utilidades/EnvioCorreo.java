package utilidades;

import java.util.Properties;
import javax.mail.Session;

import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.internet.InternetAddress;
import javax.mail.Transport;

/**
 * Clase utilizara para el envio de correos del plan de estudios
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class EnvioCorreo {
	/**
	 * Metodo estatico para enviar correos junto al PDF del plan de estudios
	 * 
	 * @param pPlanDeEstudio Objeto Plan de estudio
	 * @param pDestinatario  correo del destinatario
	 * @throws MessagingException
	 */
	public static void enviarCorreo(String pDestinatario, String pMensaje) throws MessagingException {

		String remitente = "proyectostecnmj@gmail.com";
		String contrasena = "proyectosnmj";
		String destinatario = pDestinatario;

		// Propiedades de la conexión
		Properties propiedad = new Properties();
		propiedad.put("mail.smtp.host", "smtp.gmail.com");
		propiedad.setProperty("mail.smtp.starttls.enable", "true");
		propiedad.setProperty("mail.smtp.port", "587");
		propiedad.setProperty("mail.smtp.user", remitente); /// Correo que envía
		propiedad.setProperty("mail.smtp.auth", "true");

		// Sesión
		Session sesion = Session.getDefaultInstance(propiedad, null);

		// Texto:
		BodyPart texto = new MimeBodyPart();
		texto.setText(pMensaje);

		// Agrupación del texto con el adjunto
		MimeMultipart multiParte = new MimeMultipart();
		multiParte.addBodyPart(texto);

		// Mensaje
		MimeMessage mensaje = new MimeMessage(sesion);
		mensaje.setFrom(new InternetAddress(remitente));
		mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
		mensaje.setSubject("Hospital TEC - Cita Asignada");
		mensaje.setContent(multiParte);

		try (Transport tp = sesion.getTransport("smtp")) {
			tp.connect(remitente, contrasena);
			tp.sendMessage(mensaje, mensaje.getAllRecipients());
			tp.close();
		}

	}
}
