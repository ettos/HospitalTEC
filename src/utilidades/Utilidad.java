package utilidades;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import excepciones.IsDigitNotExistException;
import excepciones.RangoFechaException;
import excepciones.EmptyListException;
import excepciones.ValidarRangoNotExistException;

/**
 * Clase utilizada para realizar funciones de utilidad para el sistema.
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class Utilidad {
	/**
	 * Metodo utilizado para verificar si una cadena de caracteres es un Digito
	 * 
	 * @param pDigito Cadena de caracteres del digito
	 * @return retorna si es digito o no
	 * @throws IsDigitNotExistException
	 */
	public static boolean esDigito(String pDigito) throws IsDigitNotExistException {
		try {
			Double.parseDouble(pDigito);
			return true;
		} catch (NumberFormatException e) {
			throw new IsDigitNotExistException(pDigito);
		}
	}

	/**
	 * Metodo utilizado para validar la longitud de una palabra
	 * 
	 * @param pPalabra Cadena de caracteres de la palabra
	 * @param pLargo   Largo esperado de la palabra
	 * @return retorna si la palabra es de la longitud que se indico el en largo
	 * @throws ValidarRangoNotExistException
	 */
	public static boolean validarRango(String pPalabra, int pLargo) throws ValidarRangoNotExistException {
		if (pPalabra.length() == pLargo) {
			return true;
		} else {
			throw new ValidarRangoNotExistException(pPalabra);
		}
	}
	
	/**
	 * Metodo utilizado para validar un rango maximo 
	 * @param pPalabra la palabra que se quiere analizar
	 * @param pLargo la cantidad del largo que se quiere
	 * @return retorna falso si no cumple y verdadero si si cumple
	 * @throws ValidarRangoNotExistException
	 */
	public static boolean validarRangoMaximo(String pPalabra, int pLargo) throws ValidarRangoNotExistException {
		if (pPalabra.length() < pLargo) {
			return true;
		} else {
			throw new ValidarRangoNotExistException(pPalabra);
		}
	}
	/**
	 * Metodo utilizado para validar el rango minimo de una palabra
	 * @param pPalabra la pralabra que se quiere analizar 
	 * @param pLargo la cantidad del largo minimo que se quiere
	 * @return retorna falso si no cumple y verdadero si si cumple 
	 * @throws ValidarRangoNotExistException
	 */
	public static boolean validarRangoMinimo(String pPalabra, int pLargo) throws ValidarRangoNotExistException {
		if (pPalabra.length() > pLargo) {
			return true;
		} else {
			throw new ValidarRangoNotExistException(pPalabra);
		}
	}

	/**
	 * Metodo utilizado para convertir de Cadena de caracteres a Entero
	 * 
	 * @param pEntero Cadena de caracteres del numero que se desea convertir
	 * @return retorna el numero convertido a Entero
	 * @throws IsDigitNotExistException
	 */
	public static int cadenaAEntero(String pEntero) throws IsDigitNotExistException {
		boolean entero = esDigito(pEntero);
		if (entero == true) {
			int number = Integer.parseInt(pEntero);
			return number;
		} else {
			throw new IsDigitNotExistException(pEntero);
		}

	}
	/**
	 * Metodo utilizado para validar si dos cadenas de caracteres son iguales
	 * @param str1 cadena de caracteres a analizar
	 * @param str2 cadena de caracteres a analizar
	 * @return retorna true si son iguale o false si no lo es
	 */
	public static boolean validar(String str1, String str2) {
		return str1.equals(str2);
	}
	
	/**
	 * Metodo que validad si dos enteros son iguales
	 * @param int1 numero que se desea comparar
	 * @param int2 numero que se desea comparar
	 * @return retorna true si son iguales y false si no lo son
	 */
	public static boolean validar(int int1, int int2) {
		return int1==int2;
	}
	
	/**
	 * Metodo utilizado para limpiar una lista
	 * @param pList arraylist 
	 * @return retorna true si la lista esta limpia y false si no lo esta
	 * @throws EmptyListException
	 */
	public static boolean emptyList(ArrayList pList) throws EmptyListException {
		if (pList.size()==0) {
			throw new EmptyListException(0);
		}
		return false;
	}
	
	/**
	 * Metedo utilizado para comparar fechas
	 * @param pFecha1 primera fecha a comparar
	 * @param pFecha2 segunda fecha a comparar
	 * @return retorna true si la fecha es la despues o si son iguales y false si es antes de la fecha
	 * @throws RangoFechaException
	 */
	public static boolean compararFechas(Date pFecha1, Date pFecha2) throws RangoFechaException  {

		if(pFecha1.compareTo(pFecha2) > 0) {
	         System.out.println("Date-1 is after Date-2");
	         return true;
	      } else if(pFecha1.compareTo(pFecha2) < 0) {
	         System.out.println("Date-1 is before Date-2");
	         throw new RangoFechaException(); 
	      } else if(pFecha1.compareTo(pFecha2) == 0) {
	         System.out.println("Date-1 is same as Date-2");
	         return true;
	      }
		return true;
		
	}
	
	/**
	 * Metodo utilizado para comparar gecha iguales
	 * @param pFecha1 primera fecha a comparar
	 * @param pFecha2 segunda fecha a comparar
	 * @return retorna true es la fecha es despues o antes de la comparacion y false si son las mismas
	 * @throws RangoFechaException
	 */
	public static boolean compararFecha1AntesFecha2(Date pFecha1, Date pFecha2) throws RangoFechaException  {

		if(pFecha1.compareTo(pFecha2) > 0) {
	         System.out.println("Date-1 is after Date-2");
	         throw new RangoFechaException(); 
	      } else if(pFecha1.compareTo(pFecha2) < 0) {
	         System.out.println("Date-1 is before Date-2");
	         return true;
	      } else if(pFecha1.compareTo(pFecha2) == 0) {
	         System.out.println("Date-1 is same as Date-2");
	         return true;
	      }
		return true;
		
	}
	
	public static boolean compararFechasIgual(Date pFecha1, Date pFecha2) throws RangoFechaException  {

		if(pFecha1.compareTo(pFecha2) > 0) {
	         System.out.println("Date-1 is after Date-2");
	         return true;
	      } else if(pFecha1.compareTo(pFecha2) < 0) {
	         System.out.println("Date-1 is before Date-2");
	         return true;
	      } else if(pFecha1.compareTo(pFecha2) == 0) {
	         System.out.println("Date-1 is same as Date-2");
	         throw new RangoFechaException(); 
	      }
		return true;
		
	}
	
	/**
	 * Metodo utilizado para obtener la fecha de mañana
	 * @return retorna la fecha de mañana
	 */
	public static Date getMannana() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		java.util.Date tomorrow = calendar.getTime();
		Date fecha=new Date(tomorrow.getTime());
		return fecha;
	}

}
