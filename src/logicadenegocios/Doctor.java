package logicadenegocios;

public class Doctor extends Funcionario{
  private int codigoMedico;
  private String especialidad;
  
  public Doctor(String pNombre, String pPrimerApellido, String pSegundoApellido, int pCedula,int pIdentificación,String contrasenna, String pTipoFuncionario, int pAnno, int pMes, int pDia, int pCodigoMedico, String pEspecialidad) {
    super(pNombre,pPrimerApellido,pSegundoApellido, pCedula,pIdentificación,contrasenna, pTipoFuncionario, pAnno, pMes, pDia);
    codigoMedico=pCodigoMedico;
    especialidad=pEspecialidad;
  }

  public int getCodigoMedico() {
	return codigoMedico;
  }

  public void setCodigoMedico(int pCodigoMedico) {
	codigoMedico = pCodigoMedico;
  }

  public String getEspecialidad() {
	return especialidad;
  }

  public void setEspecialidad(String pEspecialidad) {
	especialidad = pEspecialidad;
  }

  public String toString(){
	String cadena="";
	cadena=super.toString()+ "/n";
	cadena+= "Código Méddico: "+codigoMedico+"/n";
	cadena+="Especialidad: "+ especialidad+"/n";
	return cadena;
  }
  
}
