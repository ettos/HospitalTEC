package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

/**
 * Clase utilizada para realizar la conexion a la base de datos MySQL
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class ConexionMySQL {
	private static String connectionUrl = "jdbc:mysql://localhost:3306/HospitalTec";
	private static String user = "root";
	private static String pass = "";

	private static Connection con;

	/**
	 * Metodo utilizado para obtener la conexion de la base
	 * 
	 * @return retorna el objeto Conecction
	 */
	public static Connection getConexion() {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(connectionUrl, user, pass);
				return con;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		} else {
			return con;
		}

	}

	/**
	 * metodo utilizado para obtener el Statement de la conexion
	 * 
	 * @return retorna el objeto Statement
	 */
	public static Statement getStatement() {
		Statement stmt;
		try {
			stmt = (Statement) getConexion().createStatement();
			return stmt;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
