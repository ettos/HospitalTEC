package logicadenegocios;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cita {
	private int identificador=0;
	private String estado;
	private int cedula;
	private Date fecha;
	private AreaDeTrabajo areaTrabajo;
	private String observacion;
	private ArrayList<Diagnostico> diagnosticos;


	public Cita(String pEstado, AreaDeTrabajo pAreaTrabajo, String pObservacion, Date pFecha) {
		identificador ++;
		estado = pEstado;
		fecha = pFecha;
		areaTrabajo = pAreaTrabajo;
		setObservacion(pObservacion);
		diagnosticos = new ArrayList<Diagnostico>();
	}

	public Cita(int pIdentificador,int pCedula, String pEstado, AreaDeTrabajo pAreaTrabajo, String pObservacion,Date pFecha) {
		identificador =pIdentificador;
		estado = pEstado;
		setCedula(pCedula);
		fecha = pFecha;
		areaTrabajo = pAreaTrabajo;
		setObservacion(pObservacion);
		diagnosticos = new ArrayList<Diagnostico>();
	}

	public void agregarDiagnostico(Diagnostico diagnostico) {
		diagnosticos.add(diagnostico);
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int pIdentificador) {
		identificador = pIdentificador;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String pEstado) {
		estado = pEstado;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public String getFechaCompleta() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(fecha);
		return currentTime;
	}

	public void setFecha(Date pFecha) {
		fecha = pFecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public AreaDeTrabajo getAreaTrabajo() {
		return areaTrabajo;
	}

	public void setAreaTrabajo(AreaDeTrabajo areaTrabajo) {
		this.areaTrabajo = areaTrabajo;
	}

	public ArrayList<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(ArrayList<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	
	public String toString() {
		String cadena = "";
		cadena += "Identificador: " + identificador + "\n";
		cadena += "Estado: " + estado + "\n";
		cadena += "Fecha: " + fecha + "\n";
		cadena += "Area de Trabajo:\n " + areaTrabajo.toString();
		cadena += "Observaciones: " + observacion + "\n";
		
		return cadena;
	}
	
	public String getEstructura() {
		return "identificador,estado,cedula,fecha,areaTrabajo,observacion";
	}
	
	public String getCSV() {
		return identificador+","+estado+","+cedula+","+fecha+","+areaTrabajo.getId()+","+observacion;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
}
