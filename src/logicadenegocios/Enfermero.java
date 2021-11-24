package logicadenegocios;

public class Enfermero extends Funcionario {
  private boolean indicadorPersonasCargo;
  private boolean experienciaCapacitaciones;
  
  public Enfermero(String pNombre, String pPrimerApellido, String pSegundoApellido, int pCedula,int pIdentificación,String contrasenna, String pTipoFuncionario, int pAnno, int pMes, int pDia, boolean pIndicadorPersonasCargo, boolean pExperienciaCapacitaciones) {
	super(pNombre,pPrimerApellido,pSegundoApellido, pCedula,pIdentificación,contrasenna, pTipoFuncionario, pAnno, pMes, pDia);
	indicadorPersonasCargo=pIndicadorPersonasCargo;
	experienciaCapacitaciones=pExperienciaCapacitaciones;
  }

  public boolean isIndicadorPersonasCargo() {
	return indicadorPersonasCargo;
  }

  public void setIndicadorPersonasCargo(boolean pIndicadorPersonasCargo) {
	indicadorPersonasCargo = pIndicadorPersonasCargo;
  }

  public boolean isExperienciaCapacitaciones() {
	return experienciaCapacitaciones;
  }

  public void setExperienciaCapacitaciones(boolean pExperienciaCapacitaciones) {
	experienciaCapacitaciones = pExperienciaCapacitaciones;
  }
  
  public String toString(){
    String cadena="";
	cadena=super.toString()+ "/n"; 
	cadena+= "Indicador de personas a cargo: "+indicadorPersonasCargo+"/n";
	cadena+="Experiencia en capacitaciones"+ experienciaCapacitaciones+"/n";
	return cadena;
	}
  
}
