package utilidades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;

public interface Exportable {
	
	public void exportar(String obj, ArrayList pLista) throws FileNotFoundException, DocumentException, IOException;

	public void exportar(ArrayList pLista) throws FileNotFoundException, DocumentException, IOException;
	
	

}
