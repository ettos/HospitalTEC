package logicadenegocios;

import java.time.LocalDate;

public class Vacuna {
  public LocalDate fechaAplicacion;
  public String nombreVacuna;
  public String farmaceutica;
  public int numeroLote;
  
  public Vacuna(int pAnoo, int pMes, int pDia, String pNombreVacuna, String pFarmaceutica, int pNumeroLote) {
	fechaAplicacion=LocalDate.of(pAnoo, pMes, pDia);
	nombreVacuna=pNombreVacuna;
	farmaceutica=pFarmaceutica;
	numeroLote=pNumeroLote;
  }

  public LocalDate getFechaAplicacion() {
	return fechaAplicacion;
  }

  public void setFechaAplicacion(int pAnno, int pMes, int pDia) {
	fechaAplicacion=LocalDate.of(pAnno, pMes, pDia);	
  }

  public String getNombreVacuna() {
	return nombreVacuna;
  }

  public void setNombreVacuna(String pNombreVacuna) {
    nombreVacuna = pNombreVacuna;
  }

  public String getFarmaceutica() {
	return farmaceutica;
  }

  public void setFarmaceutica(String pFarmaceutica) {
	farmaceutica = pFarmaceutica;
  }

  public int getNumeroLote() {
	return numeroLote;
  }

  public void setNumeroLote(int pNumeroLote) {
	numeroLote = pNumeroLote;
  }
 
  public String toString() {
    String cadena = "";
    cadena += "Fecha de aplicación: " + fechaAplicacion + "\n";
    cadena += "Nombre: " + nombreVacuna + "\n";
    cadena += "Farmaceutica: " + farmaceutica + "\n";
    cadena += "Numero Lote: " + numeroLote + "\n";
    return cadena;
  }
 
}
