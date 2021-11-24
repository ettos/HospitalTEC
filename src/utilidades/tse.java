package utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Clase utilizada para realizar la consulta al sistema de TSE. 
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */

public class tse {
	/**
	 * Metodo utilizado para realizar la conexion al sistema
	 * @param pCedula numero a consultar 
	 * @return retorna  
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String[] tse(int pCedula) throws IOException, ParseException {
		String url = "https://apis.gometa.org/cedulas/" + pCedula;
		URL urlObj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");

		System.out.println("Send 'HTTP GET' request to : " + url);
		String[] arr = extraerDatos(connection);
		return arr;

	}
	/**
	 * Metodo utilizado para extraer los datos
	 * @param connection es la URL del sitio
	 * @return retorna un string con todos los datos de la persona
	 * @throws IOException
	 * @throws ParseException
	 */
	private static String[] extraerDatos(HttpURLConnection connection) throws IOException, ParseException {
		Integer responseCode = connection.getResponseCode();
		System.out.println("Response Code : " + responseCode);
		String[] arr = {};

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = inputReader.readLine()) != null) {
				response.append(inputLine);
			}
			inputReader.close();
			arr = datosPersona(response);

		}
		return arr;

	}
	/**
	 * Metodo utilizado para obtener la informacion de la persona 
	 * @param response  un string con todos los datos de la persona
	 * @return retorna los datos de la persona 
	 * @throws ParseException
	 */
	private static String[] datosPersona(StringBuffer response) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(response.toString());
		JSONArray arrJson = (JSONArray) json.get("results");
		json = (JSONObject) arrJson.get(0);

		String[] arr = { json.get("firstname1").toString(), json.get("lastname1").toString(),
				json.get("lastname2").toString() };
		return arr;

	}

}
