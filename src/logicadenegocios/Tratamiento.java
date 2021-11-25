package logicadenegocios;

public class Tratamiento {
	private int cedula;
	private String nombre;
	private String dosis; /// Esto no entendí bien si es String o integer
	private String tipo;

	public Tratamiento(int pCedula, String pNombre, String pDosis, String pTipo) {
		setCedula(pCedula);
		nombre = pNombre;
		dosis = pDosis;
		tipo = pTipo;
	}

	public Tratamiento(String pNombre, String pTipo) {
		nombre = pNombre;
		tipo = pTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String pDosis) {
		dosis = pDosis;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String pTipo) {
		tipo = pTipo;
	}

	public String toString() {
		String cadena = "";
		cadena += "Nombre: " + nombre + "\n";
		cadena += "Dosis: " + dosis + "\n";
		cadena += "Tipo: " + tipo + "\n";
		return cadena;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	public String getEstructura() {
		return "cedula,nombre,dosis,tipo";
	}

	public String getCSV() {
		return cedula+","+nombre+","+dosis+","+tipo;
	}
}
