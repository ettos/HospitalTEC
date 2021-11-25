package utilidades;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;
import javax.swing.text.StyledEditorKit.AlignmentAction;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;

/**
 * CLase utilizada para generar PDFs del Plan de Estudio
 * 
 * @author Josseline, Manuel, Nazaret
 *
 */
public class PDF implements Exportable {
	/**
	 * Metodo estatico que genera los planes de estudio en PDF
	 * 
	 * @param lista Objeto de la logica de negocios
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void generarPDF(ArrayList lista)
			throws FileNotFoundException, DocumentException {
		Document documento = new Document();
		FileOutputStream ficheroPDF = new FileOutputStream("reporte.pdf");
		PdfWriter.getInstance(documento, ficheroPDF);

		documento.open();

		Paragraph tituloDocumento = new Paragraph("Reporte de consulta realizada\n\n",
				FontFactory.getFont("Arial", 18, Font.BOLDITALIC, BaseColor.GRAY));
		tituloDocumento.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(tituloDocumento);
		tituloDocumento = new Paragraph("Información:\n",
				FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK));
		documento.add(tituloDocumento);

		for (Object obj : lista) {
			Paragraph CuerpoDocumento = new Paragraph("________________________________ \n",
					FontFactory.getFont("Arial", 10, Font.ITALIC, BaseColor.BLUE));
			documento.add(CuerpoDocumento);

			CuerpoDocumento = new Paragraph(obj.toString() + "\n",
					FontFactory.getFont("Arial", 10, Font.ITALIC, BaseColor.BLACK));
			documento.add(CuerpoDocumento);
		}

		documento.close();
	}
	
	public void generarPDFMensaje(ArrayList lista)
			throws FileNotFoundException, DocumentException {
		Document documento = new Document();
		FileOutputStream ficheroPDF = new FileOutputStream("reporte.pdf");
		PdfWriter.getInstance(documento, ficheroPDF);
		documento.open();

		Paragraph tituloDocumento = new Paragraph("Reporte de consulta realizada\n\n",
				FontFactory.getFont("Arial", 18, Font.BOLDITALIC, BaseColor.GRAY));
		tituloDocumento.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(tituloDocumento);
		tituloDocumento = new Paragraph("Información:\n",
				FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK));
		documento.add(tituloDocumento);

		Paragraph CuerpoDocumento = new Paragraph("________________________________ \n",
				FontFactory.getFont("Arial", 10, Font.ITALIC, BaseColor.BLUE));
		documento.add(CuerpoDocumento);

		CuerpoDocumento = new Paragraph(lista.get(0).toString() + "\n",
				FontFactory.getFont("Arial", 10, Font.ITALIC, BaseColor.BLACK));
		documento.add(CuerpoDocumento);
	

		documento.close();
	}

	@Override
	public void exportar(String obj, ArrayList pLista) throws FileNotFoundException, DocumentException {
		generarPDF(pLista);
	}
	
	public void exportar(ArrayList pLista) throws FileNotFoundException, DocumentException {
		generarPDFMensaje(pLista);
	}

}