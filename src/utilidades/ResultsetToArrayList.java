package utilidades;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import logicadenegocios.AreaDeTrabajo;
import logicadenegocios.CentroDeAtencion;
import logicadenegocios.Cita;
import logicadenegocios.Diagnostico;
import logicadenegocios.Funcionario;
import logicadenegocios.Paciente;
import logicadenegocios.Persona;
import logicadenegocios.Tratamiento;

/**
 * Clase utilizada para realizar la consulta al sistema de TSE. 
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */

public class ResultsetToArrayList {
	/**
	 * Metodo utilizado para convertir un resultset a un ArrayList
	 * @param rs resultset de persona
	 * @return retorna un arraylist con la informacion de la persona
	 * @throws SQLException
	 */
	public static ArrayList<Persona> tPersona(ResultSet rs) throws SQLException {
		ArrayList<Persona> lista = new ArrayList<Persona>();

		while (rs.next()) {
			Persona newPersona = new Persona(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("primerApellido"),
					rs.getString("segundoApellido"));
			lista.add(newPersona);
		}
		return lista;
	}
	/**
	 * Metodo utilizado para convertir un resultset a un ArrayList
	 * @param rs resultset de paciente
	 * @return retorna un arraylist con la informacion de paciente
	 * @throws SQLException
	 */
	public static ArrayList<Persona> tPaciente(ResultSet rs) throws SQLException {
		ArrayList<Persona> lista = new ArrayList<Persona>();

		while (rs.next()) {
			Persona newPersona = new Paciente(rs.getString("nombre"), rs.getString("primerApellido"),
					rs.getString("segundoApellido"), rs.getInt("cedula"), rs.getString("contrasenna"),
					rs.getDate("fechaNacimiento").getYear(), rs.getDate("fechaNacimiento").getMonth(),
					rs.getDate("fechaNacimiento").getDay(), rs.getString("tipoDeSangre"), rs.getString("nacionalidad"),
					rs.getString("lugarDeResidencia"), rs.getInt("telefono"));
			lista.add(newPersona);
		}
		return lista;
	}
	/**
	 * Metodo utilizado para convertir un resultset a un ArrayList
	 * @param rs resultset de funcionario
	 * @return retorna un arraylist con la informacion de funcionario
	 * @throws SQLException
	 */
	public static ArrayList<Persona> tFuncionario(ResultSet rs) throws SQLException {
		ArrayList<Persona> lista = new ArrayList<Persona>();

		while (rs.next()) {
			Persona newPersona = new Funcionario(rs.getString("nombre"), rs.getString("primerApellido"),
					rs.getString("segundoApellido"), rs.getInt("cedula"), rs.getInt("identificacion"),
					rs.getString("contrasenna"), rs.getString("tipoFuncionario"), rs.getDate("fechaDeIngreso").getYear(),
					rs.getDate("fechaDeIngreso").getMonth(), rs.getDate("fechaDeIngreso").getDay());
			lista.add(newPersona);

		}
		return lista;
	}
	/**
	 * Metodo utilizado para convertir un resultset a un ArrayList
	 * @param rs resultset de Area de trabajo
	 * @return retorna un arraylist con la informacion de Area de trabajo
	 * @throws SQLException
	 */
	public static ArrayList<AreaDeTrabajo> tCentroDeAtencion(ResultSet rs) throws SQLException {
		ArrayList<AreaDeTrabajo> lista = new ArrayList<AreaDeTrabajo>();

		while (rs.next()) {
			AreaDeTrabajo newArea = new AreaDeTrabajo(rs.getInt("id"), rs.getString("nombre"));
			lista.add(newArea);

		}
		return lista;
	}
	/**
	 * Metodo utilizado para convertir un resultset a un ArrayList
	 * @param rs resultset de cita
	 * @return retora un arraylist con la infomacion de la cita
	 * @throws SQLException
	 */
	public static ArrayList<Cita> tCita(ResultSet rs) throws SQLException {
		ArrayList<Cita> lista = new ArrayList<Cita>();

		while (rs.next()) {
			AreaDeTrabajo a = new AreaDeTrabajo(rs.getInt("areaDeTrabajo"), null);

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date fecha;
			try {
				fecha = sdf.parse(rs.getString("fecha"));
				Cita newCita = new Cita(rs.getInt("identificador"), rs.getInt("cedula"), rs.getString("estado"), a,
						rs.getString("observacion"), new Date(fecha.getTime()));
				lista.add(newCita);

			} catch (ParseException e) {
				System.out.println("Error al transformar la fecha");
			}

		}
		return lista;
	}
	/**
	 * Metodo utilizado para convertir un resulset a un ArrayList 
	 * @param rs resultset de Centro de Atencion
	 * @return retorna un arraylist con la informacion de centro de atencion
	 * @throws SQLException
	 */
	public static ArrayList<CentroDeAtencion> tCentro(ResultSet rs) throws SQLException {
		ArrayList<CentroDeAtencion> lista = new ArrayList<CentroDeAtencion>();

		while (rs.next()) {

			CentroDeAtencion newCentro = new CentroDeAtencion(rs.getInt("codigoCentro"), rs.getString("nombre"),
					rs.getString("ubicacion"), rs.getInt("capacidadMaxima"), rs.getString("tipoDeCentro"));
			lista.add(newCentro);

		}
		return lista;
	}

	public static ArrayList<Diagnostico> tDiagnostico(ResultSet rs) throws SQLException {
		ArrayList<Diagnostico> lista = new ArrayList<Diagnostico>();

		while (rs.next()) {

			Diagnostico newDiagnostico = new Diagnostico(rs.getString("nombre"));
			lista.add(newDiagnostico);

		}
		return lista;
	}

	public static ArrayList<Tratamiento> tTratamiento(ResultSet rs) throws SQLException {
		ArrayList<Tratamiento> lista = new ArrayList<Tratamiento>();

		while (rs.next()) {

			Tratamiento newTratamiento = new Tratamiento(rs.getString("nombre"),rs.getString("tipo"));
			lista.add(newTratamiento);

		}
		return lista;
	}

}
