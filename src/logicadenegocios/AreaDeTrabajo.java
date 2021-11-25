package logicadenegocios;

public class AreaDeTrabajo {
  private int id;
  private String nombre;
  
  public AreaDeTrabajo(int pId,String pNombre) {
  	setId(pId);
  	setNombre(pNombre);
  }

  public int getId() {
	return id;
  }
  public void setId(int pId) {
  	id=pId;
    }

  public String getNombre() {
	return nombre;
  }

  public void setNombre(String pNombre) {
	nombre = pNombre;
  }
  
  public String toString() {
    String cadena = "";
    cadena += "Identificador: " + id + "\n";
	cadena += "Nombre: " + nombre + "\n";
	return cadena;
 }
  
  public String getStructureCSV() {
  	return "id,nombre";
  }
  
  public String toStringCSV() {
  	String cadena = id+","+nombre+"\n";
  	return cadena;
  }
}