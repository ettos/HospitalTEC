package utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

import excepciones.EmptyListException;
import logicadenegocios.Cita;
import logicadenegocios.Diagnostico;
import logicadenegocios.Hospitalizacion;
import logicadenegocios.Tratamiento;

public class CSV implements Exportable {

	public void exportar(String obj, ArrayList pLista) {
		enruter(obj, pLista);
	};
	
	@Override
	public void exportar(ArrayList pLista) throws FileNotFoundException, DocumentException, IOException {
		enruter("Mensaje", pLista);
	}

	public void enruter(String nombreObj, ArrayList lista) {

		switch (nombreObj) {
		case "Cita":
			csvCita(lista);
			break;
		case "Diagnostico":
			csvDiagnostico(lista);
			break;
		case "Tratamiento":
			csvTratamiento(lista);
			break;
		case "Hospitalizacion":
			csvHospitalizacion(lista);
			break;
		case "Mensaje":
			csvMensaje(lista);
			break;
		default:
			System.out.println("No hay caso");
			break;
		}

	}

	private void csvMensaje(ArrayList lista) {
		try (PrintWriter writer = new PrintWriter(new File("Reporte.csv"))) {

			StringBuilder sb = new StringBuilder();

			for (Object obj : lista) {
				sb.append(obj.toString());
				sb.append('\n');
			}
			writer.write(sb.toString());
			writer.close();
			System.out.println("done!");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void csvCita(ArrayList<Cita> lista) {
		try (PrintWriter writer = new PrintWriter(new File("Reporte.csv"))) {

			StringBuilder sb = new StringBuilder();
			sb.append(lista.get(0).getEstructura());
			sb.append('\n');

			for (Cita cita : lista) {
				sb.append(cita.getCSV());
				sb.append('\n');
			}
			writer.write(sb.toString());
			writer.close();
			System.out.println("done!");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void csvDiagnostico(ArrayList<Diagnostico> lista) {
		try (PrintWriter writer = new PrintWriter(new File("Reporte.csv"))) {

			StringBuilder sb = new StringBuilder();
			sb.append(lista.get(0).getEstructura());
			sb.append('\n');

			for (Diagnostico diagnostico : lista) {
				sb.append(diagnostico.getCSV());
				sb.append('\n');
			}
			writer.write(sb.toString());
			writer.close();
			System.out.println("done!");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void csvTratamiento(ArrayList<Tratamiento> lista) {
		try (PrintWriter writer = new PrintWriter(new File("Reporte.csv"))) {

			StringBuilder sb = new StringBuilder();
			sb.append(lista.get(0).getEstructura());
			sb.append('\n');

			for (Tratamiento tratamiento : lista) {
				sb.append(tratamiento.getCSV());
				sb.append('\n');
			}
			writer.write(sb.toString());
			writer.close();
			System.out.println("done!");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void csvHospitalizacion(ArrayList<Hospitalizacion> lista) {
		try (PrintWriter writer = new PrintWriter(new File("Reporte.csv"))) {

			StringBuilder sb = new StringBuilder();
			sb.append(lista.get(0).getEstructura());
			sb.append('\n');

			for (Hospitalizacion hospitalizacion : lista) {
				sb.append(hospitalizacion.getCSV());
				sb.append('\n');
			}
			writer.write(sb.toString());
			writer.close();
			System.out.println("done!");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}



}
