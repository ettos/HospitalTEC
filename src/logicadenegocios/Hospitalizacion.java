package logicadenegocios;

import java.sql.Date;
import java.time.LocalDate;

public class Hospitalizacion {
	
	private int codigoCentro;
	private int cedula;
	private String diagnostico;
	private Date fechaInicio;
	private Date fechaFinal;
	private int idArea;
	private int identificacion;
	
	public Hospitalizacion(int pCodigoCentro,int pCedula,String pDiagnostico,Date fechaInicio, Date fechaFinal,int pIdArea, int pIdentifiacion) {
		setCedula(pCodigoCentro);
		setCedula(pCedula);
		setDiagnostico(pDiagnostico);
		setFechaInicio(fechaInicio);
		setFechaFinal(fechaFinal);
		setIdArea(pIdArea);
		setIdentificacion(pIdentifiacion);
	}

	public int getCodigoCentro() {
		return codigoCentro;
	}

	public void setCodigoCentro(int codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}
	
	@Override
	public String toString() {
		String str="";
		str+="\n Codigo Centro: " + codigoCentro;
		str+="\n Cedula: " + cedula;
		str+="\n Diagnostico: " + diagnostico;
		str+= "\n Fecha Inicio: " + fechaInicio;
		str+= "\n Fecha Final: " + fechaFinal;
		str+="\n Id Area: " + idArea;
		str+="\n Identificacion Funcionario: " +identificacion;
		return str;
	}
	
	public String getEstructura() {
		return "codigoCentro,cedula,diagnostico,fechaInicio,fechaFinal,idArea,identificacion";
	}
	
	public String getCSV() {
		return codigoCentro+","+cedula+","+diagnostico+","+fechaInicio+","+fechaFinal+","+idArea+","+identificacion;
	}

}
