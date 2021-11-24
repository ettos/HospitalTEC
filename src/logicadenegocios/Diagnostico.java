package logicadenegocios;

import java.util.ArrayList;

public class Diagnostico {
	private int cedula;
  private String nombre;
  private String nivel;
  private String observaciones;
  private ArrayList<Tratamiento> tratamientos;
  
  public Diagnostico(int pCedula,String pNombre, String pNivel, String pObservaciones) {
  cedula=pCedula;
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
	cadena += "      Nombre " + "     Dosis " + "     Tipo " + "\n";
	for (int i = 0; i < tratamientos.size(); i++) {
	  Tratamiento tratamientoActual = (Tratamiento) tratamientos.get(i);
	  cadena +=tratamientoActual.toString() + "\n";
	}
	return cadena;
 }

}
