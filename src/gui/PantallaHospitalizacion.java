package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.FuncionarioControler;
import excepciones.EmptyListException;
import excepciones.IsDigitNotExistException;
import excepciones.RangoFechaException;
import excepciones.ValidarRangoNotExistException;
import logicadenegocios.AreaDeTrabajo;
import logicadenegocios.CentroDeAtencion;
import logicadenegocios.Diagnostico;
import logicadenegocios.Tratamiento;
import utilidades.FuncionesDB;
import utilidades.UsuarioLogueado;
import utilidades.Utilidad;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PantallaHospitalizacion extends JFrame {

	private JPanel contentPane;
	private JTextField txtDiaI;
	private JTextField txtMesI;
	private JTextField txtAnnoI;
	private JTextField txtDiaF;
	private JTextField txtMesF;
	private JTextField txtAnnoF;
	private JTextField txtIdentificacionPaciente;
	private JTextField txtFuncionario;
	
	private JComboBox cbCentroDeAtencion,cbDiagnostico;
	private JComboBox cbEspecializacion;
	
	private ArrayList<CentroDeAtencion> listaCentros;
	private ArrayList<Diagnostico> listaDiagnosticos;
	private ArrayList<AreaDeTrabajo> listaAreas;
	

	/**
	 * Create the frame.
	 */
	public PantallaHospitalizacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#f6f7f2"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBackground(Color.decode("#f6f7f2"));
		btnVolver.setIcon(new ImageIcon("\\img\\logoSalir.png"));
		btnVolver.setBounds(10, 11, 35, 35);
		contentPane.add(btnVolver);
		
		JLabel lblTitulo = new JLabel("Registrar Hospitalizaci\u00F3n");
		lblTitulo.setForeground(Color.decode("#0a1944"));
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitulo.setBounds(178, 34, 267, 30);
		contentPane.add(lblTitulo);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("img\\logoHospitalizacionOscuro.png"));
		lblLogo.setBounds(118, 21, 50, 50);
		contentPane.add(lblLogo);
		
		JLabel lblCentroDeAtencion = new JLabel("Centro de Atenci\u00F3n: ");
		lblCentroDeAtencion.setForeground(Color.decode("#0a1944"));
		lblCentroDeAtencion.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCentroDeAtencion.setBounds(40, 95, 170, 21);
		contentPane.add(lblCentroDeAtencion);
		
		JLabel lblCedulaPaciente = new JLabel("Identificaci\u00F3n del Paciente:");
		lblCedulaPaciente.setForeground(Color.decode("#0a1944"));
		lblCedulaPaciente.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCedulaPaciente.setBounds(40, 152, 191, 21);
		contentPane.add(lblCedulaPaciente);
		
		JLabel lblDiagnostico = new JLabel("Diagn\u00F3stico: ");
		lblDiagnostico.setForeground(Color.decode("#0a1944"));
		lblDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblDiagnostico.setBounds(40, 222, 191, 21);
		contentPane.add(lblDiagnostico);
		
		JLabel lblFechaConsulta = new JLabel("Fecha de Inicio: ");
		lblFechaConsulta.setForeground(Color.decode("#0a1944"));
		lblFechaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsulta.setBounds(40, 284, 191, 14);
		contentPane.add(lblFechaConsulta);
		
		
		
		JLabel lblGuiaFechaConsulta = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsulta.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsulta.setBounds(263, 314, 220, 14);
		contentPane.add(lblGuiaFechaConsulta);
		
		JLabel lblFechaConsulta2 = new JLabel("Fecha de Finalizaci\u00F3n: ");
		lblFechaConsulta2.setForeground(Color.decode("#0a1944"));
		lblFechaConsulta2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsulta2.setBounds(40, 356, 220, 14);
		contentPane.add(lblFechaConsulta2);
		
		JLabel lblGuiaFechaConsulta2 = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsulta2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsulta2.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsulta2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsulta2.setBounds(263, 386, 220, 14);
		contentPane.add(lblGuiaFechaConsulta2);
		
		
		txtDiaI = new JTextField();
		txtDiaI.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaI.setColumns(10);
		txtDiaI.setBounds(311, 283, 30, 20);
		contentPane.add(txtDiaI);
		
		txtMesI = new JTextField();
		txtMesI.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesI.setColumns(10);
		txtMesI.setBounds(361, 283, 30, 20);
		contentPane.add(txtMesI);
		
		txtAnnoI = new JTextField();
		txtAnnoI.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoI.setColumns(10);
		txtAnnoI.setBounds(411, 283, 40, 20);
		contentPane.add(txtAnnoI);
		txtDiaF = new JTextField();
		txtDiaF.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaF.setColumns(10);
		txtDiaF.setBounds(313, 355, 30, 20);
		contentPane.add(txtDiaF);
		
		txtMesF = new JTextField();
		txtMesF.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesF.setColumns(10);
		txtMesF.setBounds(363, 355, 30, 20);
		contentPane.add(txtMesF);
		
		txtAnnoF = new JTextField();
		txtAnnoF.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoF.setColumns(10);
		txtAnnoF.setBounds(413, 355, 40, 20);
		contentPane.add(txtAnnoF);
		
		cbCentroDeAtencion = new JComboBox();
		cbCentroDeAtencion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbCentroDeAtencion.setBounds(300, 97, 170, 22);
		contentPane.add(cbCentroDeAtencion);
		
		txtIdentificacionPaciente = new JTextField();
		txtIdentificacionPaciente.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtIdentificacionPaciente.setBounds(300, 153, 170, 20);
		contentPane.add(txtIdentificacionPaciente);
		txtIdentificacionPaciente.setColumns(10);
		
		cbDiagnostico = new JComboBox();
		cbDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbDiagnostico.setBounds(300, 223, 170, 22);
		contentPane.add(cbDiagnostico);
		
		JLabel lblEspecializacin = new JLabel("Especializaci\u00F3n: ");
		lblEspecializacin.setForeground(new Color(10, 25, 68));
		lblEspecializacin.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEspecializacin.setBounds(40, 426, 220, 21);
		contentPane.add(lblEspecializacin);
		
		JLabel lblFuncionario = new JLabel("Funcionario: ");
		lblFuncionario.setForeground(new Color(10, 25, 68));
		lblFuncionario.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFuncionario.setBounds(40, 471, 220, 14);
		contentPane.add(lblFuncionario);
		
		cbEspecializacion = new JComboBox();
		cbEspecializacion.setBounds(300, 425, 170, 22);
		contentPane.add(cbEspecializacion);
		
		txtFuncionario = new JTextField();
		txtFuncionario.setText(UsuarioLogueado.getUsuarioLogueado().getIdentificacion()+"");
		txtFuncionario.setEditable(false);
		txtFuncionario.setBounds(300, 471, 170, 20);
		contentPane.add(txtFuncionario);
		txtFuncionario.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				try {
					
					Utilidad.validarRango(txtIdentificacionPaciente.getText(),9);
					Utilidad.validarRango(txtFuncionario.getText(),4);
					Utilidad.validarRangoMinimo(txtAnnoI.getText(),0);
					Utilidad.validarRangoMinimo(txtMesI.getText(),0);
					Utilidad.validarRangoMinimo(txtDiaI.getText(),0);
					Utilidad.validarRangoMinimo(txtAnnoF.getText(),0);
					Utilidad.validarRangoMinimo(txtMesF.getText(),0);
					Utilidad.validarRangoMinimo(txtDiaF.getText(),0);
					Utilidad.validarRangoMinimo(txtFuncionario.getText(),0);
					
					
					int centro=cbCentroDeAtencion.getSelectedIndex()+1;
					int cedula=Utilidad.cadenaAEntero(txtIdentificacionPaciente.getText());
					String diagnostico=cbDiagnostico.getSelectedItem().toString();
					int anno1=Utilidad.cadenaAEntero(txtAnnoI.getText());
					int mes1=Utilidad.cadenaAEntero(txtMesI.getText());
					int dia1=Utilidad.cadenaAEntero(txtDiaI.getText());
					
					int anno2 = Utilidad.cadenaAEntero(txtAnnoF.getText());
					int mes2=Utilidad.cadenaAEntero(txtMesF.getText());
					int dia2=Utilidad.cadenaAEntero(txtDiaF.getText());
					
					int especializacion=cbEspecializacion.getSelectedIndex()+1;
					int funcionario=Utilidad.cadenaAEntero(txtFuncionario.getText());
					
					java.util.Date f1=new java.util.Date(anno1-1900, mes1-1, dia1);
					Date sqlDate1 = new Date(f1.getTime());
					
					java.util.Date f2=new java.util.Date(anno2-1900, mes2-1, dia2);
					Date sqlDate2 = new Date(f2.getTime());
					
					Utilidad.compararFecha1AntesFecha2(sqlDate1,sqlDate2);
					
					
					FuncionarioControler.iniciarHospitalización(centro, cedula, diagnostico, sqlDate1, sqlDate2, especializacion, funcionario);
					JOptionPane.showMessageDialog(null, "¡La hospitalizacion ha sido realizada!", "¡Hospitalizacion realizada!", JOptionPane.INFORMATION_MESSAGE);
					limpiarTextos();

				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡En la cedula, identificacion y fechas solamente puede ingresar numeros!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (RangoFechaException e1) {
					JOptionPane.showMessageDialog(null, "¡Fechas seleccionada incorrectas!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡Error en la extension de los parametros\nCedula 9 caracteres\nFuncionario 4 caracteres\nNo dejar espacios vacios!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡No se ha agregado la hospitalizaciones!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
				
			}
		});
		btnRegistrar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnRegistrar.setBounds(254, 527, 89, 23);
		contentPane.add(btnRegistrar);
		
		actualizarcb();
		
	}
	
	private void actualizarcb() {
		getCentros();
		getDiagnosticos();
		getAreas();		
	}
	
	private void getCentros() {
		try {
			listaCentros=FuncionesDB.consultarCentros();
			for(CentroDeAtencion centro:listaCentros) {
				cbCentroDeAtencion.addItem(centro.getNombre());
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error Interno SQL", "¡Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(null,"No existen Areas de Trabajo", "¡Advertencia!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void getDiagnosticos() {
		cbDiagnostico.removeAllItems();
		try {
			listaDiagnosticos=FuncionesDB.consultarDiagnostico();
			for(Diagnostico diagnostico:listaDiagnosticos) {
				cbDiagnostico.addItem(diagnostico.getNombre());
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error Interno SQL", "¡Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(null,"No existen Diagnosticos", "¡Advertencia!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	private void getAreas() {
		cbEspecializacion.removeAllItems();
		try {
			listaAreas = FuncionesDB.consultarAreas();
			for (AreaDeTrabajo area : listaAreas) {
				cbEspecializacion.addItem(area.getNombre());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error Interno SQL", "¡Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(null, "No existen Areas de Trabajo", "¡Advertencia!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiarTextos() {
		txtIdentificacionPaciente.setText("");
		txtFuncionario.setText("");
		txtAnnoI.setText("");
		txtMesI.setText("");
		txtDiaI.setText("");
		txtAnnoF.setText("");
		txtMesF.setText("");
		txtDiaF.setText("");
		txtFuncionario.setText("");
	}
	
}
