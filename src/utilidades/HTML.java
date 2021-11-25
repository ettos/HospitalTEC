package utilidades;

import java.awt.Desktop;
import java.io.*;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;



public class HTML implements Exportable{
	
	public void createHTML(ArrayList pLista) throws IOException {
		File f = new File("reporte.htm");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><h1>Reporte Generado </h1>");
        bw.write("<textarea cols=75 rows=10>");
        
        for(Object obj:pLista) {
          bw.write(obj.toString()+"\n");
          bw.write("___________________________________________________\n");
        }
        
        bw.write("</textarea>");
        bw.write("</body></html>");
        bw.close();

        Desktop.getDesktop().browse(f.toURI());
		
  
	}

	public void createHTMLMensaje(ArrayList pLista) throws IOException {
		File f = new File("reporte.htm");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><h1>Reporte Generado </h1>");
        bw.write("<textarea cols=75 rows=10>");
        
        
        bw.write(pLista.get(0).toString()+"\n");
        bw.write("___________________________________________________\n");
        
        
        bw.write("</textarea>");
        bw.write("</body></html>");
        bw.close();

        Desktop.getDesktop().browse(f.toURI());
		
  
	}
	
	@Override
	public void exportar(String obj, ArrayList pLista) throws DocumentException, IOException {
		createHTML(pLista);
	}
	
	public void exportar(ArrayList pLista) throws IOException {
		createHTMLMensaje(pLista);
	}
	
}
