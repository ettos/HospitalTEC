package utilidades;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.EmptyListException;
import logicadenegocios.AreaDeTrabajo;
import logicadenegocios.CentroDeAtencion;
import logicadenegocios.Cita;
import logicadenegocios.Diagnostico;
import logicadenegocios.Persona;
import logicadenegocios.Tratamiento;
import utilidades.ConexionMySQL;

/**
 * Clase utilizada para gestionar las consultas entre controladores y la base de
 * datos
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class FuncionesDB {
	
	public static void agregarDiagnosticoTratamiento(String nombreDiagnostico,String nombreTratamiento) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "INSERT INTO DiagnosticoTratamiento(nombreDiagnostico,nombreTratamiento) VALUES(?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1,nombreDiagnostico);
		preparedStmt.setString(2,nombreTratamiento);
		
		preparedStmt.execute();

	
	}
	
	public static void agregarDiagnostico(String nombre) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "INSERT INTO Diagnostico(nombre) VALUES(?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1,nombre);
		
		preparedStmt.execute();

	
	}
		
	public static void agregarDiagnosticoPaciente(int cedula, String nombre,String nivel,String observaciones) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();
	
		String query = "INSERT INTO DiagnosticoPaciente(cedula,nombre,nivel,observaciones) VALUES(?,?,?,?)";
	
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	
		preparedStmt.setInt(1,cedula);
		preparedStmt.setString(2,nombre);
		preparedStmt.setString(3,nivel);
		preparedStmt.setString(4,observaciones);
		
		preparedStmt.execute();
	
	
	}

	public static void eliminarDiagnostico(String nombre) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "DELETE FROM Diagnostico WHERE nombre='"+nombre+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute(query);
	}
	
	public static void updateDiagnostico(String nombre,String nNombre) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "UPDATE Diagnostico SET nombre= '" + nNombre+"' WHERE nombre='"+nombre+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.executeUpdate(query);
		
	}
	
	public static void agregarTratamiento(String nombre,String tipo) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "INSERT INTO Tratamiento(nombre,tipo) VALUES(?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1,nombre);
		preparedStmt.setString(2,tipo);
		
		preparedStmt.execute();

	
	}
	
	public static void eliminarTratamiento(String nombre) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "DELETE FROM Tratamiento WHERE nombre='"+nombre+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute(query);
	}
	
	public static void updateTratamiento(String nombre,String nNombre,String nTipo) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "UPDATE Tratamiento SET nombre= '" + nNombre+"', tipo='"+nTipo+"' WHERE nombre='"+nombre+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.executeUpdate(query);
		
	}
	
	public static void agregarTratamientoPaciente(int cedula, String nombre,String dosis) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "INSERT INTO TratamientoPaciente(cedula,nombre,dosis) VALUES(?,?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1,cedula);
		preparedStmt.setString(2,nombre);
		preparedStmt.setString(3,dosis);
		
		preparedStmt.execute();

	
	}

	public static void agregarCita(int pCedula, int pArea,String pObservacion,String fecha) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "INSERT INTO Cita(cedula,areaDeTrabajo,estado,observacion,fecha) VALUES(?,?,?,?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1,pCedula);
		preparedStmt.setInt(2,pArea);
		preparedStmt.setString(3,"Registrada");
		preparedStmt.setString(4,pObservacion);
		preparedStmt.setString(5, fecha);
		
		preparedStmt.execute();

	
	}
	
	public static void agregarHospitalizacion(int codigoCentro, int cedula,String diagnostico,String fechaInicio, String fechaFinal,int idArea,int identificacion) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = " INSERT INTO Hospitalizacion(codigoCentro,cedula,diagnostico,fechaInicio,fechaFinal,idArea,identificacion)VALUES (?,?,?,?,?,?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1,codigoCentro);
		preparedStmt.setInt(2,cedula);
		preparedStmt.setString(3,diagnostico);
		preparedStmt.setString(4,fechaInicio);
		preparedStmt.setString(5,fechaFinal);
		preparedStmt.setInt(6,idArea);
		preparedStmt.setInt(7,identificacion);
		
		preparedStmt.execute();

	
	}
	
	public static void agregarSeguimiento(int cedula,String observaciones, String tratamiento, String fecha,int identificacion) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = " INSERT INTO Seguimiento (cedula,observaciones,tratamiento,fecha,identificacion) VALUES (?,?,?,?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1,cedula);
		preparedStmt.setString(2,observaciones);
		preparedStmt.setString(3,tratamiento);
		preparedStmt.setString(4,fecha);
		preparedStmt.setInt(5,identificacion);
		
		preparedStmt.execute();

	
	}
	
	public static void agregarVacuna(int cedula, String fechaAplicacion,String nombreVacuna,String farmaceutica, int numeroLote) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = " INSERT INTO Vacuna(cedula,fechaAplicacion,nombreVacuna,farmaceutica,numeroLote)VALUES (?,?,?,?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1,cedula);
		preparedStmt.setString(2,fechaAplicacion);
		preparedStmt.setString(3,nombreVacuna);
		preparedStmt.setString(4,farmaceutica);
		preparedStmt.setInt(5,numeroLote);
		
		preparedStmt.execute();

	
	}
	
	public static void agregarBitacora(int idFuncionario, int idPaciente,int idCita,String fecha,String descripcion ) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "INSERT INTO BitacoraCitas(idFuncionario,idPaciente,idCita,fecha,descripcion) VALUES(?,?,?,?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1,idFuncionario);
		preparedStmt.setInt(2,idPaciente);
		preparedStmt.setInt(3,idCita);
		preparedStmt.setString(4,fecha);
		preparedStmt.setString(5, descripcion);
		
		preparedStmt.execute();

	
	}
	
	public static void agregarPersona(int cedula, String nombre,String primerApellido,String segundoApellido) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "INSERT INTO Persona(cedula,nombre,primerApellido,segundoApellido) VALUES(?,?,?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1,cedula);
		preparedStmt.setString(2,nombre);
		preparedStmt.setString(3,primerApellido);
		preparedStmt.setString(4,segundoApellido);

		preparedStmt.execute();

	}
	
	public static void agregarPaciente(int cedula, String contrasenna,String fechaNacimiento,String tipoDeSangre,String nacionalidad, String lugarDeResidencia,int telefono) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "INSERT INTO Paciente(cedula,contrasenna,fechaNacimiento,tipoDeSangre,nacionalidad,lugarDeResidencia,telefono) VALUES(?,?,?,?,?,?,?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1,cedula);
		preparedStmt.setString(2,contrasenna);
		preparedStmt.setString(3,fechaNacimiento);
		preparedStmt.setString(4,tipoDeSangre);
		preparedStmt.setString(5,nacionalidad);
		preparedStmt.setString(6,lugarDeResidencia);
		preparedStmt.setInt(7,cedula);

		preparedStmt.execute();

	}
	public static void agregarDiagnosticoCita(String nombre, int id) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();
	
		String query = " INSERT INTO DiagnosticoCita (nombre,identificador) VALUES (?,?)";
	
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	
		preparedStmt.setString(1,nombre);
		preparedStmt.setInt(2,id);
		
		preparedStmt.execute();
	
	
	}
	
	public static void agregarTratamientoCita(String nombre, int id) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();
	
		String query = " INSERT INTO TratamientoCita (nombre,identificador) VALUES (?,?)";
	
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	
		preparedStmt.setString(1,nombre);
		preparedStmt.setInt(2,id);
		
		preparedStmt.execute();
	
	
	}
	
	public static void agregarCitaFuncionario(int idFuncionario, int idCita) throws SQLException {
		Connection conn = ConexionMySQL.getConexion();
	
		String query = " INSERT INTO FuncionarioCita (idFuncionario,idCita) VALUES (?,?)";
	
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	
		preparedStmt.setInt(1,idFuncionario);
		preparedStmt.setInt(2,idCita);
		
		preparedStmt.execute();
	
	
	}

	public static ArrayList<Cita> consultarCitas(String pEstado) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Cita WHERE estado= '" + pEstado+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Cita> lista=ResultSetToArrayList(rs,"Cita");
		return lista;
	}
	public static ArrayList<Cita> consultarCitas() throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Cita";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Cita> lista=ResultSetToArrayList(rs,"Cita");
		return lista;
	}
	
	public static ArrayList<Cita> consultarCitas(int cedula) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Cita WHERE cedula= " + cedula+"";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Cita> lista=ResultSetToArrayList(rs,"Cita");
		return lista;
	}
	
	public static ArrayList<Cita> consultarCitasArea(int area) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Cita WHERE areaDeTrabajo= " + area+"";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Cita> lista=ResultSetToArrayList(rs,"Cita");
		return lista;
	}
	
	public static ArrayList<Cita> consultarCitas(int idFuncionario,String pEstado) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Cita JOIN FuncionarioCita ON idCita=identificador WHERE estado= '" + pEstado+"' AND idFuncionario="+idFuncionario;

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Cita> lista=ResultSetToArrayList(rs,"Cita");
		return lista;
	}
	
	public static ArrayList<Cita> consultarCitas(String nombre,int area,String estado,String fecha1,String fecha2) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();
	
		String query = "SELECT Cita.identificador, Cita.cedula, Cita.areaDeTrabajo, Cita.estado, Cita.observacion, Cita.fecha  FROM (Cita INNER JOIN Persona ON Cita.cedula=Persona.cedula) Where "+
		"Persona.nombre='"+nombre+"' AND "+
		"Cita.areaDeTrabajo="+area+" AND "+
		"Cita.estado='"+estado+"' AND "+
		"(fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"')";

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(query);
		
		ArrayList<Cita> lista=ResultSetToArrayList(rs,"Cita");
		return lista;
	}
	
	public static ArrayList<Cita> consultarCitas(int cedula,int area,String estado,String fecha1,String fecha2) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();
	
		String query = "SELECT Cita.identificador, Cita.cedula, Cita.areaDeTrabajo, Cita.estado, Cita.observacion, Cita.fecha  FROM (Cita INNER JOIN Persona ON Cita.cedula=Persona.cedula) Where "+
		"Persona.cedula='"+cedula+"' AND "+
		"Cita.areaDeTrabajo="+area+" AND "+
		"Cita.estado='"+estado+"' AND "+
		"(fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"')";

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(query);
		
		ArrayList<Cita> lista=ResultSetToArrayList(rs,"Cita");
		return lista;
	}
	

	public static void updateEstadoCitas(int identificador,String pEstado) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "UPDATE Cita SET estado= '" + pEstado+"' WHERE identificador="+identificador;

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.executeUpdate(query);
		
	}
	
	
	
	public static ArrayList<AreaDeTrabajo> consultarAreas() throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM AreaDeTrabajo";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<AreaDeTrabajo> lista=ResultSetToArrayList(rs,"AreaDeTrabajo");
		return lista;
	}
	
	
	public static ArrayList<CentroDeAtencion> consultarCentros() throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM CentroDeAtencion";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<CentroDeAtencion> lista=ResultSetToArrayList(rs,"CentroDeAtencion");
		return lista;
	}
	
	public static ArrayList<Persona> consultarPersona(int pCedula) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Persona WHERE cedula= " + pCedula;

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Persona> lista=ResultSetToArrayList(rs,"Persona");
		return lista;
	}
	
	
	public static ArrayList<Persona> consultarLogInPaciente(int pCedula,String pContraseña) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Paciente JOIN Persona ON Persona.cedula=Paciente.cedula WHERE Paciente.cedula= " + pCedula + " AND contrasenna='"+pContraseña+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Persona> lista=ResultSetToArrayList(rs,"Paciente");
		return lista;
	}
	
	public static ArrayList<Persona> consultarLogInFuncionario(int pIdentificacion,String pContraseña) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Funcionario JOIN Persona ON Persona.cedula=Funcionario.cedula WHERE identificacion= " + pIdentificacion +  " AND contrasenna='"+pContraseña+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Persona> lista=ResultSetToArrayList(rs,"Funcionario");
		return lista;
	}
	
	public static ArrayList<Diagnostico> consultarDiagnostico() throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Diagnostico";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Diagnostico> lista=ResultSetToArrayList(rs,"Diagnostico");
		return lista;
	}
	
	public static ArrayList<Diagnostico> consultarDiagnostico(String nombre) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Diagnostico WHERE nombre='"+nombre+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Diagnostico> lista=ResultSetToArrayList(rs,"Diagnostico");
		return lista;
	}
	
	public static ArrayList<Diagnostico> consultarDiagnostico(String nombre, String diagnostico, String nivel, String fecha1,
			String fecha2) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT DiagnosticoPaciente.cedula, DiagnosticoPaciente.nombre,DiagnosticoPaciente.nivel,DiagnosticoPaciente.observaciones FROM "
				+ "(((DiagnosticoPaciente JOIN DiagnosticoCita ON DiagnosticoPaciente.nombre=DiagnosticoCita.nombre) JOIN "
				+ " Cita ON Cita.identificador=DiagnosticoCita.identificador) JOIN "
				+ " Persona ON DiagnosticoPaciente.cedula=Persona.cedula) WHERE "
				+ " (Cita.fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"') AND "
				+ " Persona.nombre='"+nombre+"' AND "
				+ " DiagnosticoPaciente.nombre='"+diagnostico+"' AND "
				+ " DiagnosticoPaciente.nivel='"+nivel+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Diagnostico> lista=ResultSetToArrayList(rs,"DiagnosticoPaciente");
		return lista;
	}
	
	public static ArrayList<Diagnostico> consultarDiagnostico(int cedula, String diagnostico, String nivel, String fecha1,
			String fecha2) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT DiagnosticoPaciente.cedula, DiagnosticoPaciente.nombre,DiagnosticoPaciente.nivel,DiagnosticoPaciente.observaciones FROM "
				+ "(((DiagnosticoPaciente JOIN DiagnosticoCita ON DiagnosticoPaciente.nombre=DiagnosticoCita.nombre) JOIN "
				+ " Cita ON Cita.identificador=DiagnosticoCita.identificador) JOIN "
				+ " Persona ON DiagnosticoPaciente.cedula=Persona.cedula) WHERE "
				+ " (Cita.fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"') AND "
				+ " Persona.cedula="+cedula+" AND "
				+ " DiagnosticoPaciente.nombre='"+diagnostico+"' AND "
				+ " DiagnosticoPaciente.nivel='"+nivel+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Diagnostico> lista=ResultSetToArrayList(rs,"DiagnosticoPaciente");
		return lista;
	}
	
	public static ArrayList<Tratamiento> consultarTratamiento(String nombre, String tratamiento, String tipo, String fecha1,
			String fecha2) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query="SELECT TratamientoPaciente.cedula,TratamientoPaciente.nombre,TratamientoPaciente.dosis,Tratamiento.tipo FROM  "
				+ "((((TratamientoPaciente JOIN Tratamiento ON Tratamiento.nombre=TratamientoPaciente.nombre ) "
				+ "JOIN TratamientoCita ON TratamientoCita.nombre=TratamientoPaciente.nombre)  "
				+ "JOIN Cita ON TratamientoCita.identificador AND Cita.identificador) "
				+ "JOIN Persona ON Persona.cedula=TratamientoPaciente.cedula) "
				+ "WHERE "
				+ "Persona.nombre='"+nombre+"' AND "
				+ "TratamientoPaciente.nombre='"+tratamiento+"' AND "
				+ "Tratamiento.tipo='"+tipo+"' AND "
				+ "(Cita.fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"')";


		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Tratamiento> lista=ResultSetToArrayList(rs,"TratamientoPaciente");
		return lista;
	}
	
	public static ArrayList<Tratamiento> consultarTratamiento(int cedula, String tratamiento, String fecha1,
			String fecha2) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query="SELECT TratamientoPaciente.cedula,TratamientoPaciente.nombre,TratamientoPaciente.dosis,Tratamiento.tipo FROM  "
				+ "((((TratamientoPaciente JOIN Tratamiento ON Tratamiento.nombre=TratamientoPaciente.nombre ) "
				+ "JOIN TratamientoCita ON TratamientoCita.nombre=TratamientoPaciente.nombre)  "
				+ "JOIN Cita ON TratamientoCita.identificador AND Cita.identificador) "
				+ "JOIN Persona ON Persona.cedula=TratamientoPaciente.cedula) "
				+ "WHERE "
				+ "Persona.cedula="+cedula+" AND "
				+ "TratamientoPaciente.nombre='"+tratamiento+"' AND "
				+ "(Cita.fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"')";


		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Tratamiento> lista=ResultSetToArrayList(rs,"TratamientoPaciente");
		return lista;
	}
	
	public static ArrayList<Tratamiento> consultarTratamiento() throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();
	
		String query = "SELECT * FROM Tratamiento";
	
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	
		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Tratamiento> lista=ResultSetToArrayList(rs,"Tratamiento");
		return lista;
	}
	
	public static ArrayList<Tratamiento> consultarTratamiento(String nombre) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();
	
		String query = "SELECT * FROM Tratamiento WHERE nombre='"+nombre+"'";
	
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	
		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Tratamiento> lista=ResultSetToArrayList(rs,"Tratamiento");
		return lista;
	}

	public static ArrayList<Tratamiento> consultarDiagnosticoTratamiento(String nombreDiagnostico) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Tratamiento JOIN DiagnosticoTratamiento ON DiagnosticoTratamiento.nombreTratamiento=Tratamiento.nombre WHERE nombreDiagnostico='"+nombreDiagnostico+"'";

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Tratamiento> lista=ResultSetToArrayList(rs,"Tratamiento");
		return lista;
	}
	
	public static ArrayList<Diagnostico> consultarDiagnosticoPaciente(int cedula) throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM DiagnosticoPaciente WHERE cedula="+cedula;

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		
		ArrayList<Diagnostico> lista=ResultSetToArrayList(rs,"DiagnosticoPaciente");
		return lista;
	}
	
	public static boolean consultarExistenciaPaciente(int cedula)throws SQLException, EmptyListException {
		Connection conn = ConexionMySQL.getConexion();

		String query = "SELECT * FROM Paciente WHERE cedula= " + cedula;

		PreparedStatement preparedStmt = conn.prepareStatement(query);

		ResultSet rs = preparedStmt.executeQuery(query);
		if (rs.next()) {
			return true;
		}
		return false;
	}
	
	

	public static ArrayList ResultSetToArrayList(ResultSet pResultSet, String nombreObj) throws SQLException, EmptyListException {
		ArrayList lista=new ArrayList();

		switch (nombreObj) {
			case "Persona":
				lista=ResultsetToArrayList.tPersona(pResultSet);
				break;
			case "Paciente":
				lista=ResultsetToArrayList.tPaciente(pResultSet);
				break;
			case "Funcionario":
				lista=ResultsetToArrayList.tFuncionario(pResultSet);
				break;
			case "AreaDeTrabajo":
				lista=ResultsetToArrayList.tCentroDeAtencion(pResultSet);
				break;
			case "Cita":
				lista=ResultsetToArrayList.tCita(pResultSet);
				break;
			case "CentroDeAtencion":
				lista=ResultsetToArrayList.tCentro(pResultSet);
				break;
			case "Diagnostico":
				lista=ResultsetToArrayList.tDiagnostico(pResultSet);
				break;
			case "DiagnosticoPaciente":
				lista=ResultsetToArrayList.tDiagnosticoPaciente(pResultSet);
				break;
			case "Tratamiento":
				lista=ResultsetToArrayList.tTratamiento(pResultSet);
				break;
			case "TratamientoPaciente":
				lista=ResultsetToArrayList.tTratamientoPaciente(pResultSet);
				break;
			case "HospitalizacionReporte":
				lista=ResultsetToArrayList.tHospitalizacion(pResultSet);
				break;
			default:
				System.out.println("No hay caso");
				break;
		}
		
		
		if(Utilidad.emptyList(lista)) {
			return null;
		}
		
		return lista;
	}

	
}
