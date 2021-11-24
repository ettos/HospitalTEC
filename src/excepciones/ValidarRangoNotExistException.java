package excepciones;

/**
 * Clase utilizada para gestionar las restricciones de las cadenas de caracteres
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class ValidarRangoNotExistException extends Exception {
	private String palabra;

	/**
	 * validarRangoNotExistException Constructor
	 *
	 * @param pPalabra Un par�metro
	 */
	public ValidarRangoNotExistException(String pPalabra) {
		palabra = pPalabra;
	}

	/**
	 * M�todo getPalabra
	 *
	 * @return El valor de retorno
	 */
	public String getPalabra() {
		return palabra;
	}

}
