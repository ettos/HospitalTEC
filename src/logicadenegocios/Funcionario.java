package logicadenegocios;

import java.time.LocalDate;

public class Funcionario extends Persona {
  private int identificacion;
  private String contrasenna;
  private String tipoFuncionario;
  private LocalDate fechaDeIngreso;
  
  public Funcionario(String pNombre, String pPrimerApellido, String pSegundoApellido, int pCedula,int pIdentificación,String pContrasenna,String pTipoFuncionario, int pAnno, int pMes, int pDia) {
    super(pCedula,pNombre,pPrimerApellido,pSegundoApellido);
    tipoFuncionario=pTipoFuncionario;
    identificacion=pIdentificación;
    contrasenna=pContrasenna;
    fechaDeIngreso=LocalDate.of(pAnno, pMes, pDia);  
  }
  public int getIdentificacion() {
  	return identificacion;
  }
  
  public void setIdentificacion(int pIdentificación) {
    identificacion=pIdentificación;
  }
  
  public String getContrasenna() {
  	return contrasenna;
  }

  public String getTipoFuncionario() {
	return tipoFuncionario;
  }

  public void setTipoFuncionario(String pTipoFuncionario) {
	tipoFuncionario = pTipoFuncionario;
  }

  public LocalDate getFechaDeIngreso() {
	return fechaDeIngreso;
  }

  public void setFechaDeIngreso(int pAnno, int pMes, int pDia) {
	fechaDeIngreso = LocalDate.of(pAnno, pMes, pDia);
  }
  
  public String toString(){
    String cadena="";
	cadena=super.toString()+ "/n"; 
	cadena+= "Tipo de Funcionario: "+ tipoFuncionario+ "/n";
	cadena+="Fecha de Ingreso: "+ fechaDeIngreso+ "/n";
	return cadena;
  }
  
}
