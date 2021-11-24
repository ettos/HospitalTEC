package logicadenegocios;

public class CentroDeAtencion {
  private int codigoCentro;
  private String nombre;
  private String ubicacion;
  private int capacidadMaxima;
  private String tipoDeCentro;

 
  public CentroDeAtencion(String pNombre, String pUbicacion, String pTipoDeCentro) {
    setNombre(pNombre);
    setUbicacion(pUbicacion);
    setTipoDeCentro(pTipoDeCentro);  
  }
  
  public CentroDeAtencion(int codigoCentro,String pNombre, String pUbicacion, int capacidadMaxima,String pTipoDeCentro) {
    setNombre(pNombre);
    setUbicacion(pUbicacion);
    setTipoDeCentro(pTipoDeCentro);  
  }

  public int getCodigoCentro() {
	return codigoCentro;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String pNombre) {
    nombre = pNombre;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String pUbicacion) {
	this.ubicacion = pUbicacion;
  }

  public String getTipoDeCentro() {
	return tipoDeCentro;
  }

  public void setTipoDeCentro(String pTipoDeCentro) {
	tipoDeCentro = pTipoDeCentro;
 }
  
  public String toString() {
	    String cadena = "";
	    cadena += "Código: " + codigoCentro + "\n";
		cadena += "Nombre: " + nombre + "\n";
		cadena += "Ubicación: " + ubicacion + "\n";
		cadena += "Tipo: " + tipoDeCentro + "\n";
		return cadena;
}
  
  
  
}
