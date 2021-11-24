package logicadenegocios;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Persona {
  private java.util.Date fechaNacimiento;
  private String contrasenna;
  private String tipoDeSangre;
  private String nacionalidad;
  private String lugarDeResidencia;
  private int telefono;
  private ArrayList<Vacuna> vacunas;
  
  public Paciente(String pNombre, String pPrimerApellido, String pSegundoApellido, int pCedula,String pContrasenna, int pAnno, int pMes, int pDia, String pTipoDeSangre, String pNacionalidad, String pLugarDeResidencia, int pTelefono) {
    super (pCedula,pNombre,pPrimerApellido,pSegundoApellido);
    fechaNacimiento=new java.util.Date(pAnno, pMes, pDia);
    contrasenna=pContrasenna;
    tipoDeSangre=pTipoDeSangre;
    nacionalidad=pNacionalidad;
    lugarDeResidencia=pLugarDeResidencia;
    telefono=pTelefono;
    vacunas=new ArrayList<Vacuna>();
  }
  
  public void agregarVacuna(Vacuna vacuna) {
	vacunas.add(vacuna);
  }
  
  public String getContrasenna() {
  	return contrasenna;
  }

  public java.util.Date getFechaNacimiento() {
	return fechaNacimiento;
  }

  public void setFechaNacimiento(int pAnno, int pMes, int pDia) {
	fechaNacimiento = new java.util.Date(pAnno, pMes, pDia);
  }

  public String getTipoDeSangre() {
	return tipoDeSangre;
  }

  public void setTipoDeSangre(String pTipoDeSangre) {
	tipoDeSangre = pTipoDeSangre;
  }

  public String getNacionalidad() {
	return nacionalidad;
  }

  public void setNacionalidad(String pNacionalidad) {
	nacionalidad = pNacionalidad;
  }

  public String getLugarDeResidencia() {
	return lugarDeResidencia;
  }

  public void setLugarDeResidencia(String pLugarDeResidencia) {
	lugarDeResidencia = pLugarDeResidencia;
  }

  public int getTelefono() {
	return telefono;
  }

  public void setTelefono(int pTelefono) {
	telefono = pTelefono;
  }
  
  public String toString(){
    String cadena="";
	cadena=super.toString()+ "\n"; 
	cadena+= "Fecha de Nacimiento: "+ fechaNacimiento+ "\n";
	cadena+="Tipo de Sangre: "+ tipoDeSangre+"\n";
	cadena+="Nacionalidad: "+nacionalidad+"\n";
	cadena+="Lugar de Residencia "+lugarDeResidencia+"\n";
	cadena+="\nVACUNAS\n";
	cadena += "      Fecha " + "     Nombre " + "      Farmaceútica " + "       Número de lote " + "\n";
	for (int i = 0; i < vacunas.size(); i++) {
		Vacuna vacunaActual = (Vacuna) vacunas.get(i);
		cadena +=vacunaActual.toString() + "\n";
	}
	return cadena;
}

}
