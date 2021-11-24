package excepciones;

/**
 * Clase utilizada para gestionar las restricciones de usuarios incorrectos
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class EmptyListException extends Exception {
	private int cedula;

	/**
	 * BookDoesNotExistException Constructor
	 *
	 * @param pCedula Un par�metro
	 */
	public EmptyListException(int pCedula) {
		cedula = pCedula;
	}

	/**
	 * M�todo getCedula
	 *
	 * @return El valor de retorno
	 */
	public int getDigito() {
		return cedula;
	}

}
