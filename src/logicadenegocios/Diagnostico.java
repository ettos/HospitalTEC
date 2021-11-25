package logicadenegocios;

import java.util.ArrayList;

public class Diagnostico {
	private int cedula;
  private String nombre;
  private String nivel;
  private String observaciones;
  private ArrayList<Tratamiento> tratamientos;
  
  public Diagnostico(int pCedula,String pNombre, String pNivel, String pObservaciones) {
  setCedula(pCedula);
	nombre=pNombre;
	nivel=pNivel;
	observaciones=pObservaciones;
	tratamientos=new ArrayList <Tratamiento>();
  }
  
  public Diagnostico(String pNombre) {
  	nombre=pNombre;
    }
  
  public void añadirTratamiento(Tratamiento tratamiento) {
	tratamientos.add(tratamiento); 
  }

  public String getNombre() {
	return nombre;
  }

  public void setNombre(String pNombre) {
	nombre = pNombre;
  }

  public String getNivel() {
	return nivel;
  }

  public void setNivel(String pNivel) {
	nivel = pNivel;
  }

  public String getObservaciones() {
	return observaciones;
  }

  public void setObservaciones(String pObservaciones) {
	observaciones = pObservaciones;
  }
  
  public String toString() {
    String cadena = "";
	cadena += "Nombre: " + nombre + "\n";
    cadena += "Nivel: " + nivel + "\n";
	cadena += "Observaciones: " +observaciones + "\n";

	return cadena;
 }
  
  public String getEstructura() {
  	return "cedula,nombre,nivel,observaciones";
  }
  public String getCSV() {
  	return cedula+","+nombre+","+nivel+","+observaciones;
  }

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

}
