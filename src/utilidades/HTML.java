package utilidades;

import java.awt.Desktop;
import java.io.*;



public class HTML {
	
	public static void createHTML(String pNombreArchivo) throws IOException {
		File f = new File(pNombreArchivo+".htm");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><h1>Reporte Generado </h1>");
        bw.write("<textarea cols=75 rows=10>");
        bw.write("Paciente: \n");
        bw.write("Paciente: \n");
        bw.write("Paciente: \n");
        bw.write("Paciente: \n");
        bw.write("Paciente: \n");
        bw.write("Paciente: \n");
        
        bw.write("</textarea>");
        bw.write("</body></html>");
        bw.close();

        Desktop.getDesktop().browse(f.toURI());
		
  
	}
	
}
