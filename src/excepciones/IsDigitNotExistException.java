package excepciones;

/**
 * Clase utilizada para gestionar las restricciones de cadenas de caracteres
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class IsDigitNotExistException extends Exception {
	private String digito;

	/**
	 * BookDoesNotExistException Constructor
	 *
	 * @param pIdentificadorLibro Un par�metro
	 */
	public IsDigitNotExistException(String pDigito) {
		digito = pDigito;
	}

	/**
	 * M�todo getDigito
	 *
	 * @return El valor de retorno
	 */
	public String getDigito() {
		return digito;
	}

}
