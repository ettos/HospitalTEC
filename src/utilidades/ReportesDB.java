package utilidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.EmptyListException;
import logicadenegocios.Cita;

public class ReportesDB {
	
	public static ArrayList<Cita> consultarCitaRangoFechas(int cedula,int areaDeTrabajo,String estado,String fecha1,String fecha2) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Cita where (fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"')"+
		" AND cedula="+cedula+" AND areaDeTrabajo="+areaDeTrabajo+" AND estado='"+estado+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Cita> lista=FuncionesDB.ResultSetToArrayList(rs,"Cita");
		return lista;
	}
	

}