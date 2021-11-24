package utilidades;

import logicadenegocios.Persona;

/**
 * Clase utilizada para realizar para simular el login de un usuario 
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class UsuarioLogueado {
	
	private static Persona persona=null;
	/**
	 * Metodo utilizado para establecer a la persona logueada
	 * @param pPersona es la persona que se desea loguear
	 */
	public static void setLog(Persona pPersona) {
		persona=pPersona;
	}
	/**
	 * Metodo utilizado para establcer a la persona logueada
	 * 
	 */
	public static void setLog() {
		persona=null;
	}
	/**
	 * Metodo utilizado para obtener a la persona logueada
	 * 
	 */
	public static Persona getUsuarioLogueado() {
		return persona;
	}
	/**
	 * Metodo utilizado para obtener el rol de la persona logueada 
	 * @return retorna el rol de la persona. 
	 */
	public static String getRolLogeado() {
		return persona.getClass().getSimpleName();
	}
	
}
