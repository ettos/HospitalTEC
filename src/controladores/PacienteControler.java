package controladores;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import excepciones.EmptyListException;
import logicadenegocios.AreaDeTrabajo;
import logicadenegocios.Cita;
import utilidades.EnvioCorreo;
import utilidades.FuncionesDB;
import utilidades.ReportesDB;

public class PacienteControler {
	
	public static void agregarCita(int pCedula, int pArea,String pAreaN,String pObservacion,Date fecha,String correo,String numeroT) throws SQLException, MessagingException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(fecha);
		
		FuncionesDB.agregarCita(pCedula, pArea,pObservacion, currentTime);
		
		Cita newCita=new Cita("Registrada", new AreaDeTrabajo(pArea, pAreaN), pObservacion, fecha);
		String str="";
		
		str+="Se ha agregado una cita nueva\n\nDetalles de la cita\n";
		str+=newCita.toString();
		
		EnvioCorreo.enviarCorreo(correo, str);
		
		java.util.Date hoy= new java.util.Date();
		fecha=new Date(hoy.getTime());
		sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    currentTime = sdf.format(fecha);
		agregarBitacora(0, pCedula, 0, currentTime, "Agrego una nueva Cita para el "+newCita.getFecha());

	}
	
	private static void agregarBitacora(int idFuncionario, int idPaciente,int idCita,String fecha,String descripcion) throws SQLException {
		FuncionesDB.agregarBitacora(idFuncionario, idPaciente, idCita, fecha, descripcion);
	}
	
	public static void agregarPaciente(int cedula, String nombre,String apellido1,String apellido2,String contrasenna,Date fechaNacimiento,String tipoDeSangre,String nacionalidad, String lugarDeResidencia,int telefono) throws SQLException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(fechaNacimiento);
		FuncionesDB.agregarPersona(cedula,nombre,apellido1,apellido2);
		FuncionesDB.agregarPaciente(cedula,contrasenna,currentTime,tipoDeSangre,nacionalidad,lugarDeResidencia,telefono);
	}
	
	public static void cancelarCita(int cedula, int idCita) throws SQLException, EmptyListException {
		FuncionesDB.updateEstadoCitas(idCita, "Cancelada por centro paciente");

		agregarBitacora(0, cedula, idCita, "Cancelacion por paciente de cita");
	}
	
	private static void agregarBitacora(int idFuncionario, int idPaciente, int idCita, String descripcion)
			throws SQLException {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(fecha);

		FuncionesDB.agregarBitacora(idFuncionario, idPaciente, idCita, currentTime, descripcion);
	}

	
	public static ArrayList<Cita> consultarCitaRangoFechas(int cedula,int areaDeTrabajo,String estado,Date fecha1,Date fecha2)
			throws SQLException, EmptyListException {
		
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date1 = sdf.format(fecha1);
    sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date2 = sdf.format(fecha2);
    

		System.out.println(cedula);
		System.out.println(areaDeTrabajo);
		System.out.println(estado);
		System.out.println(date1);
		System.out.println(date2);

    ArrayList<Cita> lista=ReportesDB.consultarCitaRangoFechas(cedula, areaDeTrabajo, estado, date1, date2);
    return lista;
	}
	
}
