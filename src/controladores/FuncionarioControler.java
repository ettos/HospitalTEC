package controladores;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.EmptyListException;
import logicadenegocios.Cita;
import logicadenegocios.Diagnostico;
import logicadenegocios.Tratamiento;
import utilidades.FuncionesDB;

public class FuncionarioControler {

	public static ArrayList<Tratamiento> consultarDiagnosticoTratamiento(String nombreDiagnostico) throws SQLException, EmptyListException{
		ArrayList<Tratamiento> lista=FuncionesDB.consultarDiagnosticoTratamiento(nombreDiagnostico);
		return lista;
	}
	
	public static ArrayList<Cita> consultarCitas(String nombre,int area,String estado,Date fecha1,Date fecha2) throws SQLException, EmptyListException{
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date1 = sdf.format(fecha1);
    sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date2 = sdf.format(fecha2);
		ArrayList<Cita> lista=FuncionesDB.consultarCitas(nombre, area, estado, date1, date2);
		return lista;
	}
	
	public static ArrayList<Cita> consultarCitas() throws SQLException, EmptyListException{

		ArrayList<Cita> lista=FuncionesDB.consultarCitas();
		return lista;
	}
	public static ArrayList<Cita> consultarCitasArea(int area) throws SQLException, EmptyListException{

		ArrayList<Cita> lista=FuncionesDB.consultarCitasArea(area);
		return lista;
	}
	
	public static ArrayList<Diagnostico> consultarDiagnostico(String nombre, String diagnostico, String nivel, Date fecha1,
			Date fecha2) throws SQLException, EmptyListException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date1 = sdf.format(fecha1);
    sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date2 = sdf.format(fecha2);
		ArrayList<Diagnostico> lista=FuncionesDB.consultarDiagnostico(nombre, diagnostico, nivel, date1, date2);
		return lista;
	}
	
	public static ArrayList<Diagnostico> consultarDiagnostico() throws SQLException, EmptyListException {
		
		ArrayList<Diagnostico> lista=FuncionesDB.consultarDiagnostico();
		return lista;
	}

public static ArrayList<Diagnostico> consultarDiagnostico(String nombre) throws SQLException, EmptyListException {
		
		ArrayList<Diagnostico> lista=FuncionesDB.consultarDiagnostico(nombre);
		return lista;
	}
	public static void agregarDiagnostico(String nombre) throws SQLException {

		FuncionesDB.agregarDiagnostico(nombre);
	}

	public static void eliminarDiagnostico(String nombre) throws SQLException {

		FuncionesDB.eliminarDiagnostico(nombre);
	}
	
	public static void actualizarDiagnostico(String nombre,String nNombre) throws SQLException {

		FuncionesDB.updateDiagnostico(nombre,nNombre);
	}
	
	public static void agregarDiagnosticoTratamiento(String nombreDiagnostico,String nombreTratamiento) throws SQLException {

		FuncionesDB.agregarDiagnosticoTratamiento(nombreDiagnostico, nombreTratamiento);
	}

	public static void agregarTratamiento(String nombre, String tipo) throws SQLException {

		FuncionesDB.agregarTratamiento(nombre, tipo);
	}
	
	public static void agregarTratamientoPaciente(int cedula,String nombre, String dosis) throws SQLException {

		FuncionesDB.agregarTratamientoPaciente(cedula,nombre, dosis);
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
	
	public static void agregarDiagnosticoPaciente(int cedula, String nombre,String nivel,String observaciones) throws SQLException {
		FuncionesDB.agregarDiagnosticoPaciente(cedula, nombre, nivel, observaciones);
	}
	public static void agregarDiagnosticoCita(String nombre, int id) throws SQLException {
		FuncionesDB.agregarDiagnosticoCita(nombre, id);
	}
	

}
