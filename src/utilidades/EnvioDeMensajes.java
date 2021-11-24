package utilidades;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Clase utilizada para enviar mensajes
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class EnvioDeMensajes {
	
		/**
		 * Metodo que envia un mensaje de texto
		 * @param Texto el texto que se desea enviar
		 * @param Numero numero de telefono a donde enviar el texto
		 */
		public static void EnviarSMS(String Texto, String Numero) {
			String ACCOUNT_SID = System.getenv("AC7de3ece5e8c9ad97b33bd68875e1e615");
		    String AUTH_TOKEN = System.getenv("5627dd97396c86fb0cf8b51f0fbf982c");
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			
			Message message = Message.creator(
	                new PhoneNumber("+506"+Numero), //to
	                new PhoneNumber("+17153143067"), //from
	                Texto).create();

	        System.out.println(message.getSid());
		}
}
