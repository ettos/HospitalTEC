package logicadenegocios;

import java.time.LocalDate;

public class Hospitalizacion {
	
	private int codigoCentro;
	private int cedula;
	private String diagnostico;
	private LocalDate fechaInicio;
	private LocalDate fechaFinal;
	private int idArea;
	private int identificacion;
	
	public Hospitalizacion(int pCodigoCentro,int pCedula,String pDiagnostico,LocalDate fechaInicio, LocalDate fechaFinal,int pIdArea, int pIdentifiacion) {
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

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal) {
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
		String str="Hospitalizacion";
		str+="codigoCentro=" + codigoCentro;
		str+="cedula=" + cedula;
		str+="diagnostico=" + diagnostico;
		str+= "fechaInicio=" + fechaInicio;
		str+=", fechaFinal=" + fechaFinal;
		str+="idArea=" + idArea;
		str+="identificacion=" +identificacion;
		return str;
	}

}
