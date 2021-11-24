package controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import logicadenegocios.Persona;
import utilidades.FuncionesDB;
import utilidades.Utilidad;
import utilidades.UsuarioLogueado;

public class LoginControler {
	
	public static boolean login(Persona pPersona,Persona pPersonaVerificada) throws SQLException {
		//Verificar si es Funcionario
		if(pPersona.getClass().getSimpleName().equals("Funcionario")) {
			if(Utilidad.validar(pPersona.getIdentificacion(), pPersonaVerificada.getIdentificacion()) &&
					 Utilidad.validar(pPersona.getContrasenna(), pPersonaVerificada.getContrasenna())) {
					UsuarioLogueado.setLog(pPersonaVerificada);
					return true;
				}
		//Verificar si es Paciente	
		}else if (pPersona.getClass().getSimpleName().equals("Paciente")) {
			if(Utilidad.validar(pPersona.getCedula(), pPersonaVerificada.getCedula()) &&
					 Utilidad.validar(pPersona.getContrasenna(), pPersonaVerificada.getContrasenna())) {
					UsuarioLogueado.setLog(pPersonaVerificada);
					return true;
				}
				
		}
		System.out.println("Usuario incorrecto");
		return false;
		
		
	}
	
	
	public static boolean logout(Persona pPersona) {
		return false;
	}
	

}
