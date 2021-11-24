package logicadenegocios;

import java.util.ArrayList;

public class Persona {
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private int cedula;
	private ArrayList<Cita> citas;

	public Persona(int pCedula, String pNombre, String pPrimerApellido, String pSegundoApellido) {
		nombre = pNombre;
		primerApellido = pPrimerApellido;
		segundoApellido = pSegundoApellido;
		cedula = pCedula;
		citas = new ArrayList<Cita>();
	}

	public void registrarCita(Cita cita) {
		citas.add(cita);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombreCompleto(String pNombre) {
		nombre = pNombre;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int pCedula) {
		cedula = pCedula;
	}

	public String getContrasenna() {
		return null;
	}
	
	public int getIdentificacion() {
		return 0;
	}
	
	public String toString() {
		String cadena = "";
		cadena += "Nombre Completo: " + nombre +" "+primerApellido+" "+segundoApellido+ "\n";
		cadena += "Cédula: " + cedula + "\n";
		cadena += "\nCITAS\n";
		cadena += "      Identificador " + "     Estado " + "      Fecha " + "       Área de Trabajo " + "\n";
		for (int i = 0; i < citas.size(); i++) {
			Cita citaActual = (Cita) citas.get(i);
			cadena += citaActual.toString() + "\n";
		}
		return cadena;
	}
}
