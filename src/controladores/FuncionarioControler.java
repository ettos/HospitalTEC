package controladores;

import java.sql.Date;
import java.sql.SQLException;

import excepciones.EmptyListException;
import utilidades.FuncionesDB;

public class FuncionarioControler {

	public static void agregarDiagnostico(String nombre) throws SQLException {

		FuncionesDB.agregarDiagnostico(nombre);
	}

	public static void eliminarDiagnostico(String nombre) throws SQLException {

		FuncionesDB.eliminarDiagnostico(nombre);
	}
	
	public static void actualizarDiagnostico(String nombre,String nNombre) throws SQLException {

		FuncionesDB.updateDiagnostico(nombre,nNombre);
	}

	public static void agregarTratamiento(String nombre, String tipo) throws SQLException {

		FuncionesDB.agregarTratamiento(nombre, tipo);
	}

	public static void eliminarTratamiento(String nombre) throws SQLException {

		FuncionesDB.eliminarTratamiento(nombre);
	}
	
	public static void actualizarTratamiento(String nombre,String nNombre,String nTipo) throws SQLException {

		FuncionesDB.updateTratamiento(nombre,nNombre,nTipo);
	}

	public static void asinarCita(int idFuncionario, int idCita) throws SQLException, EmptyListException {
		FuncionesDB.agregarCitaFuncionario(idFuncionario, idCita);
		FuncionesDB.updateEstadoCitas(idCita, "Asignada");

		agregarBitacora(idFuncionario, 0, idCita, "Asignacion de cita a funcionario");
	}

	public static void cancelarCita(int idFuncionario, int idCita) throws SQLException, EmptyListException {
		FuncionesDB.updateEstadoCitas(idCita, "Cancelada por centro médico");

		agregarBitacora(idFuncionario, 0, idCita, "Cancelacion por centro medico de cita");
	}

	public static void aplicarVacuna(int cedula, Date fechaAplicacion, String nombreVacuna, String farmaceutica,
			int numeroLote) throws SQLException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(fechaAplicacion);

		FuncionesDB.agregarVacuna(cedula, currentTime, nombreVacuna, farmaceutica, numeroLote);
	}

	public static void iniciarHospitalización(int codigoCentro, int cedula, String diagnostico, Date fechaInicio,
			Date fechaFinal, int idArea, int identificacion) throws SQLException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fecha1 = sdf.format(fechaInicio);
		String fecha2 = sdf.format(fechaFinal);

		FuncionesDB.agregarHospitalizacion(codigoCentro, cedula, diagnostico, fecha1, fecha2, idArea, identificacion);
	}

	private static void agregarBitacora(int idFuncionario, int idPaciente, int idCita, String descripcion)
			throws SQLException {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(fecha);

		FuncionesDB.agregarBitacora(idFuncionario, idPaciente, idCita, currentTime, descripcion);
	}

}
