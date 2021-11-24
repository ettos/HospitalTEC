package controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.EmptyListException;
import logicadenegocios.Funcionario;
import logicadenegocios.Paciente;
import logicadenegocios.Persona;
import utilidades.FuncionesDB;
import utilidades.ResultsetToArrayList;
import utilidades.Utilidad;

public class PersonaControler {

	public static void personaLogin(int pCedula, String pContrase�a, String pRol) throws SQLException, EmptyListException {
		Persona newPersona = null;
		Persona personaVerificada=null;
		ArrayList<Persona> lista=null;
		
		switch (pRol) {
		case "Paciente":
			newPersona = new Paciente(null, null, null, pCedula, pContrase�a, 10, 10, 10, null, null, null, 0);
			lista=FuncionesDB.consultarLogInPaciente(pCedula, pContrase�a);
			personaVerificada=lista.get(0);
			break;
		case "Funcionario":
			newPersona = new Funcionario(null, null, null, 0, pCedula, pContrase�a, null, 10, 10, 10);
			lista=FuncionesDB.consultarLogInFuncionario(pCedula, pContrase�a);
			personaVerificada=lista.get(0);
			break;
		case "s":
			System.out.println("n");
			break;

		default:
			System.out.println("No hay caso");
			return;
		}
		
		LoginControler.login(newPersona,personaVerificada);

	}
	
	

}
