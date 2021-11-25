package utilidades;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;

import excepciones.EmptyListException;
import gui.InicioDeSesion;
import logicadenegocios.Cita;
import logicadenegocios.Diagnostico;
import logicadenegocios.Hospitalizacion;

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
	
	public static ArrayList<Hospitalizacion> consultarHospitalización(String nombrePaciente) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query =  "SELECT * FROM Hospitalizacion JOIN Persona ON Persona.cedula=Hospitalizacion.cedula "+
				 "WHERE Persona.nombre='"+nombrePaciente+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Hospitalizacion> lista=FuncionesDB.ResultSetToArrayList(rs,"HospitalizacionReporte");
		return lista;
	}
	
	public static ArrayList<Hospitalizacion> consultarHospitalización(int cedula) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query =  "SELECT * FROM Hospitalizacion WHERE cedula="+cedula;

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Hospitalizacion> lista=FuncionesDB.ResultSetToArrayList(rs,"HospitalizacionReporte");
		return lista;
	}
	
	public static void generarReporte(String obj,ArrayList pLista) throws SQLException, EmptyListException, DocumentException, IOException {
		
		Exportable exportar=new HTML();
		exportar.exportar(obj, pLista);
		exportar=new PDF();
		exportar.exportar(obj, pLista);
		exportar=new CSV();
		exportar.exportar(obj, pLista);

	}
	
public static void generarReporte(ArrayList pLista) throws SQLException, EmptyListException, DocumentException, IOException {
		
		Exportable exportar=new CSV();
		exportar.exportar(pLista);
		exportar=new PDF();
		exportar.exportar(pLista);
		exportar=new HTML();
		exportar.exportar(pLista);

	}
	

}
