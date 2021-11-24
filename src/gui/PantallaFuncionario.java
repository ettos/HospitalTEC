package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import controladores.FuncionarioControler;
import excepciones.EmptyListException;
import excepciones.IsDigitNotExistException;
import excepciones.RangoFechaException;
import excepciones.ValidarRangoNotExistException;
import logicadenegocios.AreaDeTrabajo;
import logicadenegocios.Cita;
import logicadenegocios.Diagnostico;
import logicadenegocios.Funcionario;
import logicadenegocios.Hospitalizacion;
import logicadenegocios.Tratamiento;
import utilidades.FuncionesDB;
import utilidades.ReportesDB;
import utilidades.UsuarioLogueado;
import utilidades.Utilidad;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;

public class PantallaFuncionario extends JFrame {

	private JPanel contentPane;
	private JPanel pnlAtenderCita;
	private JTabbedPane tOpcionesFuncionario;

	private JTable tableGestionCitas;
	private JTable tableMisPacientes;
	private JTextField txtObservaciones;
	private JTextField txtDosis;
	private JTextField txtCedulaPaciente;
	private JTable tableDiagnosticos;
	private JTable tableCitasConsulta;
	private JTextField txtDiaConsulta;
	private JTextField txtMesConsulta;
	private JTextField txtAnnoConsulta;
	private JTextField txtDiaConsulta2;
	private JTextField txtMesConsulta2;
	private JTextField txtAnnoConsulta2;
	private JTextField txtNombrePacienteConsulta;
	private JTable tableDiagnosticoConsulta;
	private JTextField txtNombrePacienteC;
	private JTable tableTratamientoConsulta;
	private JTextField txtNombrePaciente;
	private JTable tableHospitalizaciones;
	private JButton btnSeguimiento;

	private DefaultTableModel modeloGestionCitas;
	private DefaultTableModel modeloMisPacientes;
	private DefaultTableModel modeloDiagnosticoConsulta;
	private DefaultTableModel modeloTratamientoConsulta;
	private DefaultTableModel modeloHospitalizaciones;
	private DefaultTableModel modeloCitasConsulta;
	private DefaultTableModel modeloDiagnosticos;
	private DefaultTableModel modeloPlan;

	private ArrayList<Cita> listaCitas;
	private ArrayList<Diagnostico> listaDiagnosticos;
	private ArrayList<Tratamiento> listaTratamientos;
	private ArrayList<Hospitalizacion> listaHospitalizaciones;
	private ArrayList<Diagnostico> setModeloTablamodeloDiagnosticoPacientes;
	private JComboBox cbEspecialidad;
	private JComboBox cbEstado;
	private JComboBox cbNombreDiagnosticoConsulta;
	private JComboBox cbNivelDiagnosticoConsulta;
	private JComboBox cbNombreTratamientoC;
	private JComboBox cbTipoTratamientoC;
	private JScrollPane spDiagnosticos;
	private JComboBox cbNombreDiagnostico;
	private JComboBox cbNombreTratamiento;
	private JComboBox cbTipoTratamiento;
	private ArrayList<Diagnostico> listaDiagnosticosPaciente;
	private JTextField txtDiaConsultaC;
	private JTextField txtMesConsultaC;
	private JTextField txtAnnoConsultaC;
	private JTextField txtDiaConsulta2C;
	private JTextField txtMesConsulta2C;
	private JTextField txtAnnoConsulta2C;
	private ArrayList<AreaDeTrabajo> listaAreas;
	private JTextField txtNombrePacienteConsultaC;
	private JTextField txtDiaConsultaD;
	private JTextField txtMesConsultaD;
	private JTextField txtAnnoConsultaD;
	private JTextField txtDiaConsulta2D;
	private JTextField txtMesConsulta2D;
	private JTextField txtAnnoConsulta2D;
	private JTextField txtNombrePacienteConsultaD;
	private JTextField txtNombrePacienteCT;
	private JTextField txtDiaConsultaT;
	private JTextField txtMesConsultaT;
	private JTextField txtAnnoConsultaT;
	private JTextField txtDiaConsulta2T;
	private JTextField txtMesConsulta2T;
	private JTextField txtAnnoConsulta2T;
	private JTextField txtNombrePacienteH;

	/**
	 * Create the frame.
	 */
	public PantallaFuncionario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 639);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#f6f7f2"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("\u00A1Bienvenido!");
		lblTitulo.setForeground(Color.decode("#0a1944"));
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 34));
		lblTitulo.setBounds(330, 42, 186, 26);
		contentPane.add(lblTitulo);

		JLabel lblLogo = new JLabel("\r\n");
		lblLogo.setIcon(new ImageIcon("img\\logoDoctor.png"));
		lblLogo.setBounds(270, 27, 50, 50);
		contentPane.add(lblLogo);

		JButton btnSalir = new JButton("");
		btnSalir.setBackground(Color.decode("#f6f7f2"));
		btnSalir.setIcon(new ImageIcon("img\\logoSalir.png"));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					InicioDeSesion window = new InicioDeSesion();
					window.frmInicioDeSesion.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSalir.setBounds(723, 16, 40, 40);
		contentPane.add(btnSalir);

		// JTabbed Opciones

		tOpcionesFuncionario = new JTabbedPane(JTabbedPane.TOP);
		tOpcionesFuncionario.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tOpcionesFuncionario.setBounds(0, 107, 794, 504);
		contentPane.add(tOpcionesFuncionario);

		JPanel pnlAsignarCitas = new JPanel();
		pnlAsignarCitas.setBackground(Color.decode("#0a1944"));
		tOpcionesFuncionario.addTab("Asignar Cita", null, pnlAsignarCitas, null);
		pnlAsignarCitas.setLayout(null);

		JLabel lblAsignacionCita = new JLabel("Asignaci\u00F3n de Cita");
		lblAsignacionCita.setForeground(Color.WHITE);
		lblAsignacionCita.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblAsignacionCita.setBounds(340, 22, 169, 23);
		pnlAsignarCitas.add(lblAsignacionCita);

		JLabel lblPacientes = new JLabel("Citas de Pacientes");
		lblPacientes.setForeground(Color.WHITE);
		lblPacientes.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblPacientes.setBounds(27, 85, 133, 14);
		pnlAsignarCitas.add(lblPacientes);

		JLabel lblMisPacientes = new JLabel("Mis Citas");
		lblMisPacientes.setForeground(Color.WHITE);
		lblMisPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMisPacientes.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblMisPacientes.setBounds(68, 345, 67, 14);
		pnlAsignarCitas.add(lblMisPacientes);

		JScrollPane spGestionCitas = new JScrollPane();
		spGestionCitas.setBounds(186, 81, 570, 170);
		pnlAsignarCitas.add(spGestionCitas);

		String[] colGCitas = new String[] { "Identificador", "Paciente", "Especialidad", "Fecha", "Estado",
				"Observaciones" };
		modeloGestionCitas = new DefaultTableModel(colGCitas, 0);

		tableGestionCitas = new JTable(modeloGestionCitas);
		spGestionCitas.setViewportView(tableGestionCitas);

		JScrollPane spMisPacientes = new JScrollPane();
		spMisPacientes.setBounds(186, 288, 570, 170);
		pnlAsignarCitas.add(spMisPacientes);

		String[] colMPCitas = new String[] { "Identificador", "Paciente", "Especialidad", "Fecha", "Estado",
				"Observaciones" };
		modeloMisPacientes = new DefaultTableModel(colMPCitas, 0);

		tableMisPacientes = new JTable(modeloMisPacientes);
		spMisPacientes.setViewportView(tableMisPacientes);

		JButton btnAsignarCita = new JButton("Asignar Cita");
		btnAsignarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tableGestionCitas.getSelectedRow();
				int id;
				try {
					id = Utilidad.cadenaAEntero(tableGestionCitas.getModel().getValueAt(i, 0).toString());

					System.out.println(UsuarioLogueado.getUsuarioLogueado().getIdentificacion() + "  " + id);

					FuncionarioControler.asinarCita(UsuarioLogueado.getUsuarioLogueado().getIdentificacion(), id);
					JOptionPane.showMessageDialog(null, "Cita asignada", "¡Exito en la transaccion!",
							JOptionPane.INFORMATION_MESSAGE);
					actualizarTablas();

				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "El id no corresponde a un numero", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error Interno \nSQL", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "Lista no encontrada", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una casilla primero.", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAsignarCita.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAsignarCita.setBounds(42, 125, 120, 23);
		pnlAsignarCitas.add(btnAsignarCita);

		JButton btnEliminarCita = new JButton("Eliminar Cita");
		btnEliminarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tableGestionCitas.getSelectedRow();
				int id;
				try {
					id = Utilidad.cadenaAEntero(tableGestionCitas.getModel().getValueAt(i, 0).toString());

					// Verificacion de fechas
					String f = tableGestionCitas.getModel().getValueAt(i, 3).toString();
					java.util.Date javaDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(f);
					Date fecha1 = new Date(javaDate.getTime());
					Date fecha2 = Utilidad.getMannana();

					Utilidad.compararFechas(fecha1, fecha2);

					FuncionarioControler.cancelarCita(UsuarioLogueado.getUsuarioLogueado().getIdentificacion(), id);
					JOptionPane.showMessageDialog(null, "Cita cancelada", "¡Exito en la transaccion!",
							JOptionPane.INFORMATION_MESSAGE);

					actualizarTablas();
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "El id no corresponde a un numero", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (RangoFechaException e1) {
					JOptionPane.showMessageDialog(null,
							"No se puede cancelar la cita\nTiempo limite para eliminarla sobrepasado.", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,
							"No se ha podido agregar la cita\nPor favor verifique que los datos esten correctos.", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "No se ha encontrado la lista.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una casilla primero.", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEliminarCita.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEliminarCita.setBounds(42, 173, 120, 23);
		pnlAsignarCitas.add(btnEliminarCita);

		JButton btnConcluirCita = new JButton("Concluir Cita");
    btnConcluirCita.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	
  				try {
          	int i = tableMisPacientes.getSelectedRow();
  					int id = Utilidad.cadenaAEntero(tableMisPacientes.getModel().getValueAt(i, 0).toString());

  					// Verificacion de fechas
  					String f = tableMisPacientes.getModel().getValueAt(i, 3).toString();
  					java.util.Date javaDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(f);
  					Date fecha1 = new Date(javaDate.getTime());
  					Date fecha2 = Utilidad.getMannana();

  					FuncionarioControler.concluirCita(UsuarioLogueado.getUsuarioLogueado().getIdentificacion(), id);
  					JOptionPane.showMessageDialog(null, "Cita Concluida", "¡Exito en la transaccion!",
  							JOptionPane.INFORMATION_MESSAGE);

  					actualizarTablas();
  				} catch (IsDigitNotExistException e1) {
  					JOptionPane.showMessageDialog(null, "El id no corresponde a un numero", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
  				} catch (ParseException e1) {
  					e1.printStackTrace();
  				} catch (SQLException e1) {
  					JOptionPane.showMessageDialog(null,
  							"No se ha podido concluir la cita\nPor favor verifique que los datos esten correctos.", "¡ERROR!",
  							JOptionPane.ERROR_MESSAGE);
  					e1.printStackTrace();
  				} catch (EmptyListException e1) {
  					JOptionPane.showMessageDialog(null, "No se ha encontrado la lista.", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
  				} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
  					JOptionPane.showMessageDialog(null, "Debe seleccionar una casilla primero.", "¡ERROR!",
  							JOptionPane.ERROR_MESSAGE);
  				}

        }
    });
    btnConcluirCita.setFont(new Font("SansSerif", Font.PLAIN, 12));
    btnConcluirCita.setBounds(42, 382, 106, 23);
    pnlAsignarCitas.add(btnConcluirCita);
		
		JLabel lblLogo_4 = new JLabel("\r\n");
		lblLogo_4.setIcon(new ImageIcon("img\\ConsultaCitas.png"));
		lblLogo_4.setBounds(280, 12, 50, 50);
		pnlAsignarCitas.add(lblLogo_4);

		// Panel de Atender Cita

		pnlAtenderCita = new JPanel();
		pnlAtenderCita.setBackground(Color.decode("#3c4f6d"));
		tOpcionesFuncionario.addTab("Atender Cita", null, pnlAtenderCita, null);
		pnlAtenderCita.setLayout(null);

		JLabel lblAtencionCita = new JLabel("Atenci\u00F3n de Cita");
		lblAtencionCita.setForeground(Color.WHITE);
		lblAtencionCita.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblAtencionCita.setBounds(100, 24, 154, 19);
		pnlAtenderCita.add(lblAtencionCita);

		JLabel lblRegistrarDiagnostico = new JLabel("Registrar Diagn\u00F3sticos");
		lblRegistrarDiagnostico.setForeground(Color.WHITE);
		lblRegistrarDiagnostico.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblRegistrarDiagnostico.setBounds(128, 79, 170, 19);
		pnlAtenderCita.add(lblRegistrarDiagnostico);

		JLabel lblNombreDiagnostico = new JLabel("Nombre del Diagn\u00F3stico:");
		lblNombreDiagnostico.setForeground(Color.WHITE);
		lblNombreDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreDiagnostico.setBounds(40, 109, 160, 28);
		pnlAtenderCita.add(lblNombreDiagnostico);

		JLabel lblNivelDiagnostico = new JLabel("Nivel del Diagn\u00F3stico:");
		lblNivelDiagnostico.setForeground(Color.WHITE);
		lblNivelDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNivelDiagnostico.setBounds(40, 148, 160, 27);
		pnlAtenderCita.add(lblNivelDiagnostico);

		JLabel lblObservaciones = new JLabel("Observaciones: ");
		lblObservaciones.setForeground(Color.WHITE);
		lblObservaciones.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblObservaciones.setBounds(40, 186, 160, 28);
		pnlAtenderCita.add(lblObservaciones);

		JLabel lblAsignarTratamiento = new JLabel("Asignar Tratamiento");
		lblAsignarTratamiento.setForeground(Color.WHITE);
		lblAsignarTratamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignarTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAsignarTratamiento.setBounds(121, 275, 170, 28);
		pnlAtenderCita.add(lblAsignarTratamiento);

		JLabel lblNombreTratamiento = new JLabel("Nombre del Tratamiento: ");
		lblNombreTratamiento.setForeground(Color.WHITE);
		lblNombreTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreTratamiento.setBounds(40, 314, 160, 29);
		pnlAtenderCita.add(lblNombreTratamiento);

		JLabel lblDosis = new JLabel("Dosis:");
		lblDosis.setForeground(Color.WHITE);
		lblDosis.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDosis.setBounds(40, 354, 160, 24);
		pnlAtenderCita.add(lblDosis);
		/*
		 * JLabel lblTipoTratamiento = new JLabel("Tipo de Tratamiento:");
		 * lblTipoTratamiento.setForeground(Color.WHITE); lblTipoTratamiento.setFont(new
		 * Font("SansSerif", Font.PLAIN, 14)); lblTipoTratamiento.setBounds(40, 389,
		 * 160, 28); pnlAtenderCita.add(lblTipoTratamiento);
		 */
		JLabel lblCedulaPaciente = new JLabel("C\u00E9dula de Paciente a Atender : ");
		lblCedulaPaciente.setForeground(Color.WHITE);
		lblCedulaPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCedulaPaciente.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCedulaPaciente.setBounds(509, 82, 210, 14);
		pnlAtenderCita.add(lblCedulaPaciente);

		JLabel lblDiagnosticoPaciente = new JLabel("Diagn\u00F3sticos del Paciente");
		lblDiagnosticoPaciente.setForeground(Color.WHITE);
		lblDiagnosticoPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiagnosticoPaciente.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDiagnosticoPaciente.setBounds(479, 147, 253, 28);
		pnlAtenderCita.add(lblDiagnosticoPaciente);

		cbNombreDiagnostico = new JComboBox();
		cbNombreDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbNombreDiagnostico.setBounds(207, 113, 170, 22);
		pnlAtenderCita.add(cbNombreDiagnostico);

		String[] nivelesD = { "leve", "grave", "muy grave" };
		JComboBox cbNivelDiagnostico = new JComboBox(nivelesD);
		cbNivelDiagnostico.setBounds(207, 152, 170, 22);
		pnlAtenderCita.add(cbNivelDiagnostico);

		cbNombreTratamiento = new JComboBox();
		cbNombreTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbNombreTratamiento.setBounds(207, 318, 170, 22);
		pnlAtenderCita.add(cbNombreTratamiento);
		/*
		 * cbTipoTratamiento = new JComboBox(); cbTipoTratamiento.setFont(new
		 * Font("SansSerif", Font.PLAIN, 12)); cbTipoTratamiento.setBounds(207, 393,
		 * 170, 22); pnlAtenderCita.add(cbTipoTratamiento);
		 */
		txtObservaciones = new JTextField();
		txtObservaciones.setBounds(207, 192, 170, 20);
		pnlAtenderCita.add(txtObservaciones);
		txtObservaciones.setColumns(10);

		txtDosis = new JTextField();
		txtDosis.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDosis.setBounds(210, 357, 170, 20);
		pnlAtenderCita.add(txtDosis);
		txtDosis.setColumns(10);

		txtCedulaPaciente = new JTextField();
		txtCedulaPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int cedula;
				try {
					cedula = Utilidad.cadenaAEntero(txtCedulaPaciente.getText());
					setModeloTablamodeloDiagnosticoPacientes(cedula);
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡La cedula debe ser un digito!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		txtCedulaPaciente.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCedulaPaciente.setBounds(519, 109, 170, 20);
		pnlAtenderCita.add(txtCedulaPaciente);
		txtCedulaPaciente.setColumns(10);

		JButton btnRegistrarDiagnostico = new JButton("Registrar Diagn\u00F3stico");
		btnRegistrarDiagnostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Utilidad.validarRangoMinimo(txtCedulaPaciente.getText(), 0);
					int cedula = Utilidad.cadenaAEntero(txtCedulaPaciente.getText());
					String diagnostico = cbNombreDiagnostico.getSelectedItem().toString();
					String nivel = cbNivelDiagnostico.getSelectedItem().toString();
					String observaciones = txtObservaciones.getText();
					
					int i=tableMisPacientes.getSelectedRow();
					int id=Utilidad.cadenaAEntero(modeloMisPacientes.getValueAt(i, 0).toString());
					FuncionarioControler.agregarDiagnosticoCita(diagnostico,id);

					FuncionarioControler.agregarDiagnosticoPaciente(cedula, diagnostico, nivel, observaciones);
					
					
					setModeloTablamodeloDiagnosticoPacientes(cedula);
					JOptionPane.showMessageDialog(null, "¡El diagnostico ha sido registrado!", "¡Diagnostico Registrado!",
							JOptionPane.INFORMATION_MESSAGE);
					txtObservaciones.setText("");

				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡Debe ingresar la cedula del paciente!", "¡Error!",
							JOptionPane.ERROR_MESSAGE);
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡La cedula debe ser un digito!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡No se ha registrado el diagnostico!", "¡Error!",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}catch (java.lang.ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar la cita que esta atendiendo en la seccion 'Asignar Cita'.", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnRegistrarDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnRegistrarDiagnostico.setBounds(121, 225, 154, 23);
		pnlAtenderCita.add(btnRegistrarDiagnostico);

		JButton btnAsignarTratamiento = new JButton("Asignar Tratamiento");
		btnAsignarTratamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int cedula = Utilidad.cadenaAEntero(txtCedulaPaciente.getText());
					String nombre = cbNombreTratamiento.getSelectedItem().toString();
					String dosis = txtDosis.getText();
					
					int i=tableMisPacientes.getSelectedRow();
					int id=Utilidad.cadenaAEntero(modeloMisPacientes.getValueAt(i, 0).toString());
					FuncionarioControler.agregarTratamientoCita(nombre,id);

					FuncionarioControler.agregarTratamientoPaciente(cedula, nombre, dosis);
					JOptionPane.showMessageDialog(null, "¡El diagnostico ha sido registrado!", "¡Diagnostico Registrado!",
							JOptionPane.INFORMATION_MESSAGE);
					txtDosis.setText("");
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡La cedula debe ser un digito!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡No se ha agregado el tratamiento!", "¡Error!",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}catch (java.lang.ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar la cita que esta atendiendo en la seccion 'Asignar Cita'.", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAsignarTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAsignarTratamiento.setBounds(121, 428, 154, 23);
		pnlAtenderCita.add(btnAsignarTratamiento);

		JButton btnSeleccionarDiagnostico = new JButton("Seleccionar Diagn\u00F3stico");
		btnSeleccionarDiagnostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int i = tableDiagnosticos.getSelectedRow();
					String diagnostico = modeloDiagnosticos.getValueAt(i, 0).toString();
					listaTratamientos = FuncionarioControler.consultarDiagnosticoTratamiento(diagnostico);
					getTratamientos();
				} catch (SQLException | EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "¡No se han cargado los tratamientos recomendados!", "¡Error!",
							JOptionPane.ERROR_MESSAGE);
				} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "¡Debe seleccionar un diagnostico primero!", "¡Error!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSeleccionarDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnSeleccionarDiagnostico.setBounds(519, 428, 175, 23);
		pnlAtenderCita.add(btnSeleccionarDiagnostico);

		spDiagnosticos = new JScrollPane();
		spDiagnosticos.setBounds(479, 188, 253, 230);
		pnlAtenderCita.add(spDiagnosticos);

		String[] colDiagnosticos = new String[] { "Nombre", "Nivel", "Observación" };
		modeloDiagnosticos = new DefaultTableModel(colDiagnosticos, 0);

		tableDiagnosticos = new JTable(modeloDiagnosticos);

		spDiagnosticos.setViewportView(tableDiagnosticos);

		JLabel lblLogo_1 = new JLabel("\r\n");
		lblLogo_1.setIcon(new ImageIcon("img\\logoAtenderCitas.png"));
		lblLogo_1.setBounds(40, 12, 50, 50);
		pnlAtenderCita.add(lblLogo_1);

		JButton btnRegistrarHospitalizaciones = new JButton("");
		btnRegistrarHospitalizaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PantallaHospitalizacion frame = new PantallaHospitalizacion();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRegistrarHospitalizaciones.setBackground(Color.decode("#f6f7f2"));
		btnRegistrarHospitalizaciones.setIcon(new ImageIcon("img\\HospitalizacionPeque\u00F1o.png"));
		btnRegistrarHospitalizaciones.setBounds(698, 24, 42, 42);
		pnlAtenderCita.add(btnRegistrarHospitalizaciones);

		JButton btnRegistrarVacuna = new JButton("");
		btnRegistrarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cedula = 0;
				try {
					Utilidad.validarRango(txtCedulaPaciente.getText(), 9);
					cedula = Utilidad.cadenaAEntero(txtCedulaPaciente.getText());

					if (FuncionesDB.consultarExistenciaPaciente(cedula) == false) {
						JOptionPane.showMessageDialog(null, "Paciente con la cedula " + cedula + " no encontrado.", "¡Advertencia!",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					PantallaVacuna frame = new PantallaVacuna(cedula);
					frame.setVisible(true);
				} catch (IsDigitNotExistException e2) {
					JOptionPane.showMessageDialog(null, "Solo puede insertar digitos en la cedula del paciente", "¡Advertencia!",
							JOptionPane.WARNING_MESSAGE);
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "Debe digitar la cedula del paciente para realizar la vacina",
							"¡Advertencia!", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "Paciente con la cedula " + cedula + " no encontrado.", "¡Advertencia!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnRegistrarVacuna.setIcon(new ImageIcon("img\\logoVacunaPeque\u00F1o.png"));
		btnRegistrarVacuna.setBackground(Color.decode("#f6f7f2"));
		btnRegistrarVacuna.setBounds(647, 24, 42, 42);
		pnlAtenderCita.add(btnRegistrarVacuna);

		// Reporte de citas

		JPanel pnlCitasConsultas = new JPanel();
		pnlCitasConsultas.setBackground(Color.decode("#a5b5c4"));
		tOpcionesFuncionario.addTab("Consultas de Citas", null, pnlCitasConsultas, null);
		pnlCitasConsultas.setLayout(null);

		JLabel lblNombrePacienteConsulta = new JLabel("Nombre de Paciente:");
		lblNombrePacienteConsulta.setForeground(Color.decode("#0a1944"));
		lblNombrePacienteConsulta.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombrePacienteConsulta.setBounds(510, 62, 220, 14);
		pnlCitasConsultas.add(lblNombrePacienteConsulta);

		JLabel lblConsultaCitas = new JLabel("Consultas de Citas");
		lblConsultaCitas.setForeground(Color.decode("#0a1944"));
		lblConsultaCitas.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblConsultaCitas.setBounds(301, 26, 172, 24);
		pnlCitasConsultas.add(lblConsultaCitas);

		JLabel lblFechaConsulta = new JLabel("Fecha: ");
		lblFechaConsulta.setForeground(Color.decode("#0a1944"));
		lblFechaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsulta.setBounds(510, 231, 60, 14);
		pnlCitasConsultas.add(lblFechaConsulta);

		JLabel lblGuiaFechaConsulta = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsulta.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsulta.setBounds(510, 275, 220, 14);
		pnlCitasConsultas.add(lblGuiaFechaConsulta);

		JLabel lblFechaConsulta2 = new JLabel("Fecha: ");
		lblFechaConsulta2.setForeground(Color.decode("#0a1944"));
		lblFechaConsulta2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsulta2.setBounds(510, 300, 220, 14);
		pnlCitasConsultas.add(lblFechaConsulta2);

		JLabel lblGuiaFechaConsulta2 = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsulta2.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsulta2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsulta2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsulta2.setBounds(510, 356, 220, 14);
		pnlCitasConsultas.add(lblGuiaFechaConsulta2);

		JLabel lblEspecialidadCitaConsulta = new JLabel("Especialidad:");
		lblEspecialidadCitaConsulta.setForeground(Color.decode("#0a1944"));
		lblEspecialidadCitaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEspecialidadCitaConsulta.setBounds(510, 113, 220, 20);
		pnlCitasConsultas.add(lblEspecialidadCitaConsulta);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(Color.decode("#0a1944"));
		lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEstado.setBounds(510, 175, 218, 14);
		pnlCitasConsultas.add(lblEstado);

		txtNombrePacienteConsultaC = new JTextField();
		txtNombrePacienteConsultaC.setBounds(510, 82, 220, 20);
		pnlCitasConsultas.add(txtNombrePacienteConsultaC);
		txtNombrePacienteConsultaC.setColumns(10);

		txtDiaConsultaC = new JTextField();
		txtDiaConsultaC.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsultaC.setColumns(10);
		txtDiaConsultaC.setBounds(560, 247, 30, 20);
		pnlCitasConsultas.add(txtDiaConsultaC);

		txtMesConsultaC = new JTextField();
		txtMesConsultaC.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsultaC.setColumns(10);
		txtMesConsultaC.setBounds(610, 247, 30, 20);
		pnlCitasConsultas.add(txtMesConsultaC);

		txtAnnoConsultaC = new JTextField();
		txtAnnoConsultaC.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsultaC.setColumns(10);
		txtAnnoConsultaC.setBounds(660, 247, 40, 20);
		pnlCitasConsultas.add(txtAnnoConsultaC);

		txtDiaConsulta2C = new JTextField();
		txtDiaConsulta2C.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsulta2C.setColumns(10);
		txtDiaConsulta2C.setBounds(560, 325, 30, 20);
		pnlCitasConsultas.add(txtDiaConsulta2C);

		txtMesConsulta2C = new JTextField();
		txtMesConsulta2C.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsulta2C.setColumns(10);
		txtMesConsulta2C.setBounds(610, 325, 30, 20);
		pnlCitasConsultas.add(txtMesConsulta2C);

		txtAnnoConsulta2C = new JTextField();
		txtAnnoConsulta2C.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsulta2C.setColumns(10);
		txtAnnoConsulta2C.setBounds(660, 325, 40, 20);
		pnlCitasConsultas.add(txtAnnoConsulta2C);

		cbEspecialidad = new JComboBox();
		cbEspecialidad.setBounds(510, 142, 220, 22);
		pnlCitasConsultas.add(cbEspecialidad);

		String[] estados = { "Registrada", "Cancelada por paciente", "Cancelada por centro medico", "Asignada",
		"Realizada" };
		cbEstado = new JComboBox(estados);
		cbEstado.setBounds(510, 198, 220, 22);
		pnlCitasConsultas.add(cbEstado);

		JButton btnCitasRegistradas = new JButton("Citas Registradas");
		btnCitasRegistradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				try {
					String nombre=txtNombrePacienteConsultaC.getText();
					int area= cbEspecialidad.getSelectedIndex()+1;
					String estado= cbEstado.getSelectedItem().toString();
				
					int anno1 = Utilidad.cadenaAEntero(txtAnnoConsultaC.getText());
					int mes1 = Utilidad.cadenaAEntero(txtMesConsultaC.getText());
					int dia1 = Utilidad.cadenaAEntero(txtDiaConsultaC.getText());

					int anno2 = Utilidad.cadenaAEntero(txtAnnoConsulta2C.getText());
					int mes2 = Utilidad.cadenaAEntero(txtMesConsulta2C.getText());
					int dia2 = Utilidad.cadenaAEntero(txtDiaConsulta2C.getText());

					java.util.Date f1 = new java.util.Date(anno1 - 1900, mes1 - 1, dia1);
					Date sqlDate1 = new Date(f1.getTime());

					java.util.Date f2 = new java.util.Date(anno2 - 1900, mes2 - 1, dia2);
					Date sqlDate2 = new Date(f2.getTime());
					
					listaCitas=FuncionarioControler.consultarCitas(nombre, area, estado, sqlDate1, sqlDate2);
					setModeloTablaCitas(listaCitas);
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error en la busqueda", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "No se han encontrado citas", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "En la fecha solamente se pueden ingresar digitos", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCitasRegistradas.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCitasRegistradas.setBounds(550, 381, 172, 23);
		pnlCitasConsultas.add(btnCitasRegistradas);

		JButton bntCantidadCitasConsulta = new JButton("Cantidad de Citas");
		bntCantidadCitasConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int area= cbEspecialidad.getSelectedIndex()+1;

				try {
					
					String nombre=txtNombrePacienteConsultaC.getText();
					String estado= cbEstado.getSelectedItem().toString();
					Utilidad.validarRangoMinimo(nombre,0);

					int anno1 = Utilidad.cadenaAEntero(txtAnnoConsultaC.getText());
					int mes1 = Utilidad.cadenaAEntero(txtMesConsultaC.getText());
					int dia1 = Utilidad.cadenaAEntero(txtDiaConsultaC.getText());

					int anno2 = Utilidad.cadenaAEntero(txtAnnoConsulta2C.getText());
					int mes2 = Utilidad.cadenaAEntero(txtMesConsulta2C.getText());
					int dia2 = Utilidad.cadenaAEntero(txtDiaConsulta2C.getText());

					java.util.Date f1 = new java.util.Date(anno1 - 1900, mes1 - 1, dia1);
					Date sqlDate1 = new Date(f1.getTime());

					java.util.Date f2 = new java.util.Date(anno2 - 1900, mes2 - 1, dia2);
					Date sqlDate2 = new Date(f2.getTime());
					
					listaCitas=FuncionarioControler.consultarCitas();
					int total=listaCitas.size();
					listaCitas=FuncionarioControler.consultarCitasArea(area);
					int citasAreas=listaCitas.size();
					listaCitas=FuncionarioControler.consultarCitas(nombre, area, estado, sqlDate1, sqlDate2);
					
					String mensaje="En el sistema existe un total de "+total+" citas.\n"+
							"Aplicando el area se han encontrado "+citasAreas+" Citas.\n"+
							"Aplicando el filtro se han encontrado "+listaCitas.size()+" Citas.";
					
					
					JOptionPane.showMessageDialog(null, mensaje,"Reporte de Cantidad de Citas", JOptionPane.INFORMATION_MESSAGE);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error en la busqueda", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "No se han encontrado citas", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "En la fecha solamente se pueden ingresar digitos", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del paciente", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		bntCantidadCitasConsulta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		bntCantidadCitasConsulta.setBounds(160, 415, 172, 23);
		pnlCitasConsultas.add(bntCantidadCitasConsulta);

		JScrollPane spCitas = new JScrollPane();
		spCitas.setBounds(22, 78, 436, 326);
		pnlCitasConsultas.add(spCitas);

		String[] colCitasConsultas = new String[] { "Identificador", "Paciente", "Especialidad", "Estado", "Fecha" };
		modeloCitasConsulta = new DefaultTableModel(colCitasConsultas, 0);

		tableCitasConsulta = new JTable(modeloCitasConsulta);

		tableCitasConsulta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spCitas.setViewportView(tableCitasConsulta);

		JLabel lblLogo_2 = new JLabel("\r\n");
		lblLogo_2.setIcon(new ImageIcon("\\img\\ConsultasCitasOscura.png"));
		lblLogo_2.setBounds(241, 17, 50, 50);
		pnlCitasConsultas.add(lblLogo_2);

		JPanel pnlDiagnosticoConsulta = new JPanel();
		pnlDiagnosticoConsulta.setBackground(Color.decode("#d9d8dd"));
		tOpcionesFuncionario.addTab("Consulta de Diagnóstico", null, pnlDiagnosticoConsulta, null);
		pnlDiagnosticoConsulta.setLayout(null);

		JLabel lblConsultaDiagnostico = new JLabel("Consultas de Diagn\u00F3stico");
		lblConsultaDiagnostico.setForeground(Color.decode("#0a1944"));
		lblConsultaDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblConsultaDiagnostico.setBounds(287, 37, 204, 24);
		pnlDiagnosticoConsulta.add(lblConsultaDiagnostico);

		JLabel lblNombrePacienteConsultaD = new JLabel("Nombre de Paciente:");
		lblNombrePacienteConsultaD.setForeground(Color.decode("#0a1944"));
		lblNombrePacienteConsultaD.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombrePacienteConsultaD.setBounds(540, 90, 220, 14);
		pnlDiagnosticoConsulta.add(lblNombrePacienteConsultaD);

		JLabel lblNombreDiagnosticoConsulta = new JLabel("Nombre del Diagn\u00F3stico:");
		lblNombreDiagnosticoConsulta.setForeground(Color.decode("#0a1944"));
		lblNombreDiagnosticoConsulta.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreDiagnosticoConsulta.setBounds(540, 140, 220, 24);
		pnlDiagnosticoConsulta.add(lblNombreDiagnosticoConsulta);

		JLabel lblNivelDiagnosticoConsulta = new JLabel("Nivel del Diagn\u00F3stico:");
		lblNivelDiagnosticoConsulta.setForeground(Color.decode("#0a1944"));
		lblNivelDiagnosticoConsulta.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNivelDiagnosticoConsulta.setBounds(540, 200, 220, 24);
		pnlDiagnosticoConsulta.add(lblNivelDiagnosticoConsulta);

		JLabel lblFechaConsultaD = new JLabel("Fecha: ");
		lblFechaConsultaD.setForeground(Color.decode("#0a1944"));
		lblFechaConsultaD.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFechaConsultaD.setBounds(540, 270, 60, 14);
		pnlDiagnosticoConsulta.add(lblFechaConsultaD);

		JLabel lblGuiaFechaConsultaD = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsultaD.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsultaD.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsultaD.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsultaD.setBounds(540, 320, 220, 14);
		pnlDiagnosticoConsulta.add(lblGuiaFechaConsultaD);

		JLabel lblFechaConsultaD2 = new JLabel("Fecha: ");
		lblFechaConsultaD2.setForeground(Color.decode("#0a1944"));
		lblFechaConsultaD2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFechaConsultaD2.setBounds(540, 340, 220, 14);
		pnlDiagnosticoConsulta.add(lblFechaConsultaD2);

		JLabel lblGuiaFechaConsultaD2 = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsultaD2.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsultaD2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsultaD2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsultaD2.setBounds(540, 390, 220, 14);
		pnlDiagnosticoConsulta.add(lblGuiaFechaConsultaD2);

		txtNombrePacienteConsultaD = new JTextField();
		txtNombrePacienteConsultaD.setBounds(540, 110, 220, 20);
		pnlDiagnosticoConsulta.add(txtNombrePacienteConsultaD);
		txtNombrePacienteConsultaD.setColumns(10);

		txtDiaConsultaD = new JTextField();
		txtDiaConsultaD.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsultaD.setColumns(10);
		txtDiaConsultaD.setBounds(590, 290, 30, 20);
		pnlDiagnosticoConsulta.add(txtDiaConsultaD);

		txtMesConsultaD = new JTextField();
		txtMesConsultaD.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsultaD.setColumns(10);
		txtMesConsultaD.setBounds(640, 290, 30, 20);
		pnlDiagnosticoConsulta.add(txtMesConsultaD);

		txtAnnoConsultaD = new JTextField();
		txtAnnoConsultaD.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsultaD.setColumns(10);
		txtAnnoConsultaD.setBounds(690, 290, 40, 20);
		pnlDiagnosticoConsulta.add(txtAnnoConsultaD);

		txtDiaConsulta2D = new JTextField();
		txtDiaConsulta2D.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsulta2D.setColumns(10);
		txtDiaConsulta2D.setBounds(590, 360, 30, 20);
		pnlDiagnosticoConsulta.add(txtDiaConsulta2D);

		txtMesConsulta2D = new JTextField();
		txtMesConsulta2D.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsulta2D.setColumns(10);
		txtMesConsulta2D.setBounds(640, 360, 30, 20);
		pnlDiagnosticoConsulta.add(txtMesConsulta2D);

		txtAnnoConsulta2D = new JTextField();
		txtAnnoConsulta2D.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsulta2D.setColumns(10);
		txtAnnoConsulta2D.setBounds(690, 360, 40, 20);
		pnlDiagnosticoConsulta.add(txtAnnoConsulta2D);

		cbNombreDiagnosticoConsulta = new JComboBox();
		cbNombreDiagnosticoConsulta.setBounds(540, 170, 220, 22);
		pnlDiagnosticoConsulta.add(cbNombreDiagnosticoConsulta);

		cbNivelDiagnosticoConsulta = new JComboBox(nivelesD);
		cbNivelDiagnosticoConsulta.setBounds(540, 230, 220, 22);
		pnlDiagnosticoConsulta.add(cbNivelDiagnosticoConsulta);

		JScrollPane spDiagnosticoConsulta = new JScrollPane();
		spDiagnosticoConsulta.setBounds(10, 85, 481, 330);
		pnlDiagnosticoConsulta.add(spDiagnosticoConsulta);

		String[] colDiagnosticoConsulta = new String[] { "Paciente", "Nombre", "Nivel","Observaciones" };
		modeloDiagnosticoConsulta = new DefaultTableModel(colDiagnosticoConsulta, 0);

		tableDiagnosticoConsulta = new JTable(modeloDiagnosticoConsulta);
		tableDiagnosticoConsulta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spDiagnosticoConsulta.setViewportView(tableDiagnosticoConsulta);

		JButton btnConsultarDiagnostico = new JButton("Consultar Diagn\u00F3stico");
		btnConsultarDiagnostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nombre=txtNombrePacienteConsultaD.getText();
					String diagnostico= cbNombreDiagnosticoConsulta.getSelectedItem().toString();
					String nivel= cbNivelDiagnosticoConsulta.getSelectedItem().toString();
					Utilidad.validarRangoMinimo(nombre,0);

					int anno1 = Utilidad.cadenaAEntero(txtAnnoConsultaD.getText());
					int mes1 = Utilidad.cadenaAEntero(txtMesConsultaD.getText());
					int dia1 = Utilidad.cadenaAEntero(txtDiaConsultaD.getText());

					int anno2 = Utilidad.cadenaAEntero(txtAnnoConsulta2D.getText());
					int mes2 = Utilidad.cadenaAEntero(txtMesConsulta2D.getText());
					int dia2 = Utilidad.cadenaAEntero(txtDiaConsulta2D.getText());

					java.util.Date f1 = new java.util.Date(anno1 - 1900, mes1 - 1, dia1);
					Date sqlDate1 = new Date(f1.getTime());

					java.util.Date f2 = new java.util.Date(anno2 - 1900, mes2 - 1, dia2);
					Date sqlDate2 = new Date(f2.getTime());
					
					listaDiagnosticos=FuncionarioControler.consultarDiagnostico(nombre, diagnostico, nivel, sqlDate1, sqlDate2);
					setModeloTablaDiagnosticos(listaDiagnosticos);
								
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error en la busqueda", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "No se han encontrado diagnosticos", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "En la fecha solamente se pueden ingresar digitos", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "Debe escribir el nombre del paciente", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConsultarDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnConsultarDiagnostico.setBounds(550, 425, 163, 23);
		pnlDiagnosticoConsulta.add(btnConsultarDiagnostico);

		JButton btnCantidadDiagnostico = new JButton("Consultar Cantidad Diagn\u00F3stico");
		btnCantidadDiagnostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nombre=txtNombrePacienteConsultaD.getText();
					String diagnostico= cbNombreDiagnosticoConsulta.getSelectedItem().toString();
					String nivel= cbNivelDiagnosticoConsulta.getSelectedItem().toString();
					Utilidad.validarRangoMinimo(nombre,0);

					int anno1 = Utilidad.cadenaAEntero(txtAnnoConsultaD.getText());
					int mes1 = Utilidad.cadenaAEntero(txtMesConsultaD.getText());
					int dia1 = Utilidad.cadenaAEntero(txtDiaConsultaD.getText());

					int anno2 = Utilidad.cadenaAEntero(txtAnnoConsulta2D.getText());
					int mes2 = Utilidad.cadenaAEntero(txtMesConsulta2D.getText());
					int dia2 = Utilidad.cadenaAEntero(txtDiaConsulta2D.getText());

					java.util.Date f1 = new java.util.Date(anno1 - 1900, mes1 - 1, dia1);
					Date sqlDate1 = new Date(f1.getTime());

					java.util.Date f2 = new java.util.Date(anno2 - 1900, mes2 - 1, dia2);
					Date sqlDate2 = new Date(f2.getTime());
					
					listaDiagnosticos=FuncionarioControler.consultarDiagnostico();
					int total=listaDiagnosticos.size();
					listaDiagnosticos=FuncionarioControler.consultarDiagnostico(diagnostico);
					int diagnosticosNombre=listaDiagnosticos.size();
					listaDiagnosticos=FuncionarioControler.consultarDiagnostico(nombre, diagnostico, nivel, sqlDate1, sqlDate2);
					
					String mensaje="En el sistema existe un total de "+total+" diagnosticos.\n"+
							"Aplicando el nombre se han encontrado "+diagnosticosNombre+" diagnosticos.\n"+
							"Aplicando el filtro se han encontrado "+listaDiagnosticos.size()+" diagnosticos.";
					
					
					JOptionPane.showMessageDialog(null, mensaje,"Reporte de Cantidad de Citas", JOptionPane.INFORMATION_MESSAGE);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error en la busqueda", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "No se han encontrado diagnosticos", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "En la fecha solamente se pueden ingresar digitos", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del usuario", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnCantidadDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCantidadDiagnostico.setBounds(136, 423, 220, 23);
		pnlDiagnosticoConsulta.add(btnCantidadDiagnostico);

		JLabel lblLogo_3 = new JLabel("\r\n");
		lblLogo_3.setIcon(new ImageIcon("img\\logoDiagnostico.png"));
		lblLogo_3.setBounds(237, 11, 50, 50);
		pnlDiagnosticoConsulta.add(lblLogo_3);

		JPanel pnlTratamientoConsulta = new JPanel();
		pnlTratamientoConsulta.setBackground(Color.decode("#f3f2f5"));
		tOpcionesFuncionario.addTab("Consultas de Tratamiento", null, pnlTratamientoConsulta, null);
		pnlTratamientoConsulta.setLayout(null);

		JLabel lblTratamiendoConsulta = new JLabel("Consultas de Tratamiento");
		lblTratamiendoConsulta.setForeground(Color.decode("#0a1944"));
		lblTratamiendoConsulta.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTratamiendoConsulta.setBounds(300, 20, 225, 24);
		pnlTratamientoConsulta.add(lblTratamiendoConsulta);

		JLabel lblNombrePacienteConsultaT = new JLabel("Nombre de Paciente:");
		lblNombrePacienteConsultaT.setForeground(Color.decode("#0a1944"));
		lblNombrePacienteConsultaT.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombrePacienteConsultaT.setBounds(500, 100, 220, 14);
		pnlTratamientoConsulta.add(lblNombrePacienteConsultaT);

		JLabel lblNombreTratamientoC = new JLabel("Nombre del Tratamiento");
		lblNombreTratamientoC.setForeground(Color.decode("#0a1944"));
		lblNombreTratamientoC.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombreTratamientoC.setBounds(500, 150, 220, 24);
		pnlTratamientoConsulta.add(lblNombreTratamientoC);

		JLabel lblTipoTratamientoConsulta = new JLabel("Tipo de Tratamiento:");
		lblTipoTratamientoConsulta.setForeground(Color.decode("#0a1944"));
		lblTipoTratamientoConsulta.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTipoTratamientoConsulta.setBounds(500, 210, 220, 24);
		pnlTratamientoConsulta.add(lblTipoTratamientoConsulta);

		JLabel lblFechaConsultaT = new JLabel("Fecha: ");
		lblFechaConsultaT.setForeground(Color.decode("#0a1944"));
		lblFechaConsultaT.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsultaT.setBounds(500, 270, 60, 14);
		pnlTratamientoConsulta.add(lblFechaConsultaT);

		JLabel lblGuiaFechaConsultaT = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsultaT.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsultaT.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsultaT.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsultaT.setBounds(500, 320, 220, 14);
		pnlTratamientoConsulta.add(lblGuiaFechaConsultaT);

		JLabel lblFechaConsultaT2 = new JLabel("Fecha: ");
		lblFechaConsultaT2.setForeground(Color.decode("#0a1944"));
		lblFechaConsultaT2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsultaT2.setBounds(500, 340, 220, 14);
		pnlTratamientoConsulta.add(lblFechaConsultaT2);

		JLabel lblGuiaFechaConsultaT2 = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsultaT2.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsultaT2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsultaT2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsultaT2.setBounds(500, 390, 220, 14);
		pnlTratamientoConsulta.add(lblGuiaFechaConsultaT2);

		txtNombrePacienteCT = new JTextField();
		txtNombrePacienteCT.setBounds(500, 120, 220, 20);
		pnlTratamientoConsulta.add(txtNombrePacienteCT);
		txtNombrePacienteCT.setColumns(10);

		txtDiaConsultaT = new JTextField();
		txtDiaConsultaT.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsultaT.setColumns(10);
		txtDiaConsultaT.setBounds(550, 290, 30, 20);
		pnlTratamientoConsulta.add(txtDiaConsultaT);

		txtMesConsultaT = new JTextField();
		txtMesConsultaT.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsultaT.setColumns(10);
		txtMesConsultaT.setBounds(600, 290, 30, 20);
		pnlTratamientoConsulta.add(txtMesConsultaT);

		txtAnnoConsultaT = new JTextField();
		txtAnnoConsultaT.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsultaT.setColumns(10);
		txtAnnoConsultaT.setBounds(650, 290, 40, 20);
		pnlTratamientoConsulta.add(txtAnnoConsultaT);

		txtDiaConsulta2T = new JTextField();
		txtDiaConsulta2T.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsulta2T.setColumns(10);
		txtDiaConsulta2T.setBounds(550, 360, 30, 20);
		pnlTratamientoConsulta.add(txtDiaConsulta2T);

		txtMesConsulta2T = new JTextField();
		txtMesConsulta2T.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsulta2T.setColumns(10);
		txtMesConsulta2T.setBounds(600, 360, 30, 20);
		pnlTratamientoConsulta.add(txtMesConsulta2T);

		txtAnnoConsulta2T = new JTextField();
		txtAnnoConsulta2T.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsulta2T.setColumns(10);
		txtAnnoConsulta2T.setBounds(650, 360, 40, 20);
		pnlTratamientoConsulta.add(txtAnnoConsulta2T);

		cbNombreTratamientoC = new JComboBox();
		cbNombreTratamientoC.setBounds(500, 180, 220, 22);
		cbNombreTratamientoC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					cbTipoTratamientoC.removeAllItems();
					cbTipoTratamientoC.addItem(listaTratamientos.get(cbNombreTratamientoC.getSelectedIndex()).getTipo());
				
				
			}
		});
		pnlTratamientoConsulta.add(cbNombreTratamientoC);

		cbTipoTratamientoC = new JComboBox();
		cbTipoTratamientoC.setBounds(500, 240, 220, 22);
		cbTipoTratamientoC.setEnabled(false);
		pnlTratamientoConsulta.add(cbTipoTratamientoC);

		JScrollPane spTratamientoConsulta = new JScrollPane();
		spTratamientoConsulta.setBounds(10, 82, 451, 330);
		pnlTratamientoConsulta.add(spTratamientoConsulta);

		String[] colTratamientoConsulta = new String[] { "Paciente", "Diagnóstico", "Tipo", "Dosis" };
		modeloTratamientoConsulta = new DefaultTableModel(colTratamientoConsulta, 0);

		tableTratamientoConsulta = new JTable(modeloTratamientoConsulta);
		tableTratamientoConsulta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spTratamientoConsulta.setViewportView(tableTratamientoConsulta);

		JButton btnConsultarTratamiendo = new JButton("Consultar Tratamiendo Asociados");
		btnConsultarTratamiendo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nombre=txtNombrePacienteCT.getText();
					String tratamiento= cbNombreTratamientoC.getSelectedItem().toString();
					String tipo= cbTipoTratamientoC.getSelectedItem().toString();
					Utilidad.validarRangoMinimo(nombre,0);

					int anno1 = Utilidad.cadenaAEntero(txtAnnoConsultaT.getText());
					int mes1 = Utilidad.cadenaAEntero(txtMesConsultaT.getText());
					int dia1 = Utilidad.cadenaAEntero(txtDiaConsultaT.getText());

					int anno2 = Utilidad.cadenaAEntero(txtAnnoConsulta2T.getText());
					int mes2 = Utilidad.cadenaAEntero(txtMesConsulta2T.getText());
					int dia2 = Utilidad.cadenaAEntero(txtDiaConsulta2T.getText());

					java.util.Date f1 = new java.util.Date(anno1 - 1900, mes1 - 1, dia1);
					Date sqlDate1 = new Date(f1.getTime());

					java.util.Date f2 = new java.util.Date(anno2 - 1900, mes2 - 1, dia2);
					Date sqlDate2 = new Date(f2.getTime());
					
					listaTratamientos=FuncionarioControler.consultarTratamiento(nombre, tratamiento, tipo, sqlDate1, sqlDate2);
					setModeloTablaTratamiento(listaTratamientos);
								
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error en la busqueda", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "No se han encontrado tratamientos", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "En la fecha solamente se pueden ingresar digitos", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del ususario", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);				
				}
				
			}
		});
		btnConsultarTratamiendo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnConsultarTratamiendo.setBounds(500, 423, 245, 23);
		pnlTratamientoConsulta.add(btnConsultarTratamiendo);

		JButton btnConsultarTratamientoCantidad = new JButton("Consultar Cantidad de Tratamiento");
		btnConsultarTratamientoCantidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nombre=txtNombrePacienteCT.getText();
					String tratamiento= cbNombreTratamientoC.getSelectedItem().toString();
					String tipo= cbTipoTratamientoC.getSelectedItem().toString();
					Utilidad.validarRangoMinimo(nombre,0);

					int anno1 = Utilidad.cadenaAEntero(txtAnnoConsultaT.getText());
					int mes1 = Utilidad.cadenaAEntero(txtMesConsultaT.getText());
					int dia1 = Utilidad.cadenaAEntero(txtDiaConsultaT.getText());

					int anno2 = Utilidad.cadenaAEntero(txtAnnoConsulta2T.getText());
					int mes2 = Utilidad.cadenaAEntero(txtMesConsulta2T.getText());
					int dia2 = Utilidad.cadenaAEntero(txtDiaConsulta2T.getText());

					java.util.Date f1 = new java.util.Date(anno1 - 1900, mes1 - 1, dia1);
					Date sqlDate1 = new Date(f1.getTime());

					java.util.Date f2 = new java.util.Date(anno2 - 1900, mes2 - 1, dia2);
					Date sqlDate2 = new Date(f2.getTime());
					
					
					listaTratamientos=FuncionarioControler.consultarTratamientos();
					int total=listaTratamientos.size();
					listaTratamientos=FuncionarioControler.consultarTratamientos(tratamiento);
					int tratamientosNombre=listaTratamientos.size();
					listaTratamientos=FuncionarioControler.consultarTratamiento(nombre, tratamiento, tipo, sqlDate1, sqlDate2);
					
					String mensaje="En el sistema existe un total de "+total+" tratamientos.\n"+
							"Aplicando el nombre se han encontrado "+tratamientosNombre+" diagnosticos.\n"+
							"Aplicando el filtro se han encontrado "+listaTratamientos.size()+" diagnosticos.";
					
					
					JOptionPane.showMessageDialog(null, mensaje,"Reporte de Cantidad de Citas", JOptionPane.INFORMATION_MESSAGE);
					
								
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error en la busqueda", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "No se han encontrado tratamientos", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "En la fecha solamente se pueden ingresar digitos", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}catch (java.lang.IndexOutOfBoundsException e1) {
					try {
						actualizarcb();
					} catch (SQLException | EmptyListException e2) {
						JOptionPane.showMessageDialog(null, "Error interno", "¡ERROR!",JOptionPane.ERROR_MESSAGE);
					}
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del paciente", "¡ERROR!",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnConsultarTratamientoCantidad.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnConsultarTratamientoCantidad.setBounds(108, 423, 245, 23);
		pnlTratamientoConsulta.add(btnConsultarTratamientoCantidad);

		JLabel lblLogo_5 = new JLabel("\r\n");
		lblLogo_5.setIcon(new ImageIcon("img\\logoTratamientoOscuro.png"));
		lblLogo_5.setBounds(240, 11, 50, 50);
		pnlTratamientoConsulta.add(lblLogo_5);

		JPanel pnlHospitalizacion = new JPanel();
		pnlHospitalizacion.setBackground(Color.decode("#3c4f6d"));
		tOpcionesFuncionario.addTab("Hospitalizaciones", null, pnlHospitalizacion, null);
		pnlHospitalizacion.setLayout(null);

		JLabel lblHospitalizaciones = new JLabel("Hospitalizaciones");
		lblHospitalizaciones.setForeground(Color.WHITE);
		lblHospitalizaciones.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblHospitalizaciones.setBounds(356, 22, 159, 26);
		pnlHospitalizacion.add(lblHospitalizaciones);

		JLabel lblNombrePaciente = new JLabel("Nombre del paciente:");
		lblNombrePaciente.setForeground(Color.WHITE);
		lblNombrePaciente.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombrePaciente.setBounds(86, 86, 159, 26);
		pnlHospitalizacion.add(lblNombrePaciente);

		txtNombrePacienteH = new JTextField();
		txtNombrePacienteH.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombrePacienteH.setBounds(87, 123, 210, 20);
		pnlHospitalizacion.add(txtNombrePacienteH);
		txtNombrePacienteH.setColumns(10);

		JScrollPane spHospitalizaciones = new JScrollPane();
		spHospitalizaciones.setBounds(56, 172, 635, 265);
		pnlHospitalizacion.add(spHospitalizaciones);

		String[] colHospitalizacion = new String[] { "Identificación", "Diagnóstico", "Fecha Inicio",
				"Fecha Finalizacion", "Especialidad","Hospitalizado por" };
		modeloHospitalizaciones = new DefaultTableModel(colHospitalizacion, 0);
		tableHospitalizaciones = new JTable(modeloHospitalizaciones);

		tableHospitalizaciones.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spHospitalizaciones.setViewportView(tableHospitalizaciones);

		JButton btnConsultarHospitalizaciones = new JButton("Buscar");
		btnConsultarHospitalizaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nombre=txtNombrePacienteH.getText();
					Utilidad.validarRangoMinimo(nombre,0);
					
					listaHospitalizaciones=ReportesDB.consultarHospitalización(nombre);
					setModeloTablaHospitalizaciones(listaHospitalizaciones);
								
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error en la busqueda", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "No se han encontrado tratamientos", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "Debe escribir el nombre del paciente", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnConsultarHospitalizaciones.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnConsultarHospitalizaciones.setBounds(307, 123, 89, 20);
		pnlHospitalizacion.add(btnConsultarHospitalizaciones);

		JLabel lblLogoHospitalizaciones = new JLabel("");
		lblLogoHospitalizaciones.setIcon(new ImageIcon("img\\LogoHospitalizacionClaro.png"));
		lblLogoHospitalizaciones.setBounds(296, 11, 50, 50);
		pnlHospitalizacion.add(lblLogoHospitalizaciones);

		btnSeguimiento = new JButton("Dar Seguimiento");
		btnSeguimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = tableHospitalizaciones.getSelectedRow();
					
					if(i==-1) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar la hospitalizacion primero", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int cedula = Utilidad.cadenaAEntero(tableHospitalizaciones.getModel().getValueAt(i, 0).toString());
					PantallaSeguimiento frame = new PantallaSeguimiento(cedula);
					frame.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error en la busqueda", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnSeguimiento.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnSeguimiento.setBounds(532, 121, 159, 23);
		pnlHospitalizacion.add(btnSeguimiento);

		JButton btnGestionDyT = new JButton("");
		btnGestionDyT.setBackground(Color.decode("#f6f7f2"));
		btnGestionDyT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PantallaGestionDiagnosticoTratamiento frame = new PantallaGestionDiagnosticoTratamiento();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGestionDyT.setIcon(new ImageIcon("img\\logoVacunaPeque\u00F1o.png"));
		btnGestionDyT.setBounds(10, 11, 42, 42);
		contentPane.add(btnGestionDyT);

		
		try {
			actualizarcb();
		} catch (SQLException | EmptyListException e1) {
			JOptionPane.showMessageDialog(null, "Actualmente no hay tratamientos registrados", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		asignarPrivilegios();
		actualizarTablas();

	}

	private void actualizarcb() throws SQLException, EmptyListException {
		getDiagnosticos();
		getAllTratamientos();
		getAreas();
	}

	private void asignarPrivilegios() {
		Funcionario nFuncionario = (Funcionario) UsuarioLogueado.getUsuarioLogueado();
		System.out.println(nFuncionario.getTipoFuncionario());
		if (nFuncionario.getTipoFuncionario().equals("Enfermero")) {
			tOpcionesFuncionario.setEnabledAt(1, false);
		} else if (nFuncionario.getTipoFuncionario().equals("Secretario")) {
			tOpcionesFuncionario.setEnabledAt(1, false);
			tOpcionesFuncionario.setEnabledAt(3, false);
			tOpcionesFuncionario.setEnabledAt(4, false);
			btnSeguimiento.setEnabled(false);
		}
	}

	private void actualizarTablas() {
		setModeloTablamodeloGestionCitas();
		setModeloTablamodeloMisPacientes();
	}

	private void setModeloTablamodeloGestionCitas() {
		limpiarTabla(modeloGestionCitas);
		try {
			listaCitas = FuncionesDB.consultarCitas("Registrada");
			for (Cita cita : listaCitas) {
				Object[] objs = { cita.getIdentificador(), cita.getCedula(), cita.getAreaTrabajo().getId(),
						cita.getFechaCompleta(), cita.getEstado(), cita.getObservacion() };
				modeloGestionCitas.addRow(objs);

			}
		} catch (SQLException | EmptyListException e) {
			JOptionPane.showMessageDialog(null, "Actualmente no hay citas en el sistema", "¡Advertencia!",
					JOptionPane.WARNING_MESSAGE);
		}

	}
	
	private void setModeloTablaCitas(ArrayList<Cita> listaCitas) {
		limpiarTabla(modeloCitasConsulta);
		for (Cita cita : listaCitas) {
			Object[] objs = { cita.getIdentificador(),cita.getCedula(), cita.getAreaTrabajo().getId(), cita.getEstado(),
					cita.getFechaCompleta() };
			modeloCitasConsulta.addRow(objs);

		}
	}
	
	private void setModeloTablaDiagnosticos(ArrayList<Diagnostico> lista) {
		limpiarTabla(modeloDiagnosticoConsulta);
		for (Diagnostico diagnostico : lista) {
			Object[] objs = { diagnostico.getCedula(),diagnostico.getNombre(),diagnostico.getNivel(),diagnostico.getObservaciones()};
			modeloDiagnosticoConsulta.addRow(objs);

		}
	}
	
	private void setModeloTablaHospitalizaciones(ArrayList<Hospitalizacion> lista) {
		limpiarTabla(modeloHospitalizaciones);
		for (Hospitalizacion hospitalizacion : lista) {
			Object[] objs = { hospitalizacion.getCedula(),hospitalizacion.getDiagnostico(),
					hospitalizacion.getFechaInicio(),hospitalizacion.getFechaFinal(),
					hospitalizacion.getIdArea(),hospitalizacion.getIdentificacion()};
			modeloHospitalizaciones.addRow(objs);

		}
	}
	
	private void setModeloTablaTratamiento(ArrayList<Tratamiento> lista) {
		limpiarTabla(modeloTratamientoConsulta);
		for (Tratamiento trat : lista) {
			Object[] objs = { trat.getCedula(),trat.getNombre(),trat.getTipo(),trat.getDosis()};
			modeloTratamientoConsulta.addRow(objs);
		}
	}

	private void setModeloTablamodeloMisPacientes() {
		limpiarTabla(modeloMisPacientes);
		try {
			listaCitas = FuncionesDB.consultarCitas(UsuarioLogueado.getUsuarioLogueado().getIdentificacion(), "Asignada");
			for (Cita cita : listaCitas) {
				Object[] objs = { cita.getIdentificador(), cita.getCedula(), cita.getAreaTrabajo().getId(), cita.getFechaCompleta(),
						cita.getEstado(), cita.getObservacion() };
				modeloMisPacientes.addRow(objs);

			}
		} catch (SQLException | EmptyListException e) {
			JOptionPane.showMessageDialog(null, "No posee citas en este momento", "¡Advertencia!",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void setModeloTablamodeloDiagnosticoPacientes(int cedula) {
		limpiarTabla(modeloDiagnosticos);
		try {
			listaDiagnosticosPaciente = FuncionesDB.consultarDiagnosticoPaciente(cedula);
			for (Diagnostico diagnostico : listaDiagnosticosPaciente) {
				Object[] objs = { diagnostico.getNombre(), diagnostico.getNivel(), diagnostico.getObservaciones() };
				modeloDiagnosticos.addRow(objs);

			}
		} catch (SQLException | EmptyListException e) {
			JOptionPane.showMessageDialog(null, "No posee Diagnosticos en este momento", "¡Advertencia!",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void limpiarTabla(DefaultTableModel modelo) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
	}

	private void getDiagnosticos() {
		cbNombreDiagnostico.removeAllItems();
		cbNombreDiagnosticoConsulta.removeAllItems();
		try {
			listaDiagnosticos = FuncionesDB.consultarDiagnostico();
			for (Diagnostico diagnostico : listaDiagnosticos) {
				cbNombreDiagnostico.addItem(diagnostico.getNombre());
				cbNombreDiagnosticoConsulta.addItem(diagnostico.getNombre());

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error Interno SQL", "¡Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(null, "No existen Diagnosticos", "¡Advertencia!", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void getTratamientos() {
		cbNombreTratamiento.removeAllItems();
		cbNombreTratamientoC.removeAllItems();
			for(Tratamiento tratamiento:listaTratamientos) {
				cbNombreTratamiento.addItem(tratamiento.getNombre());
				cbNombreTratamientoC.addItem(tratamiento.getNombre());
			}
	}
	
	private void getAllTratamientos() throws SQLException, EmptyListException {
		cbNombreTratamientoC.removeAllItems();
		listaTratamientos=FuncionesDB.consultarTratamiento();
			for(Tratamiento tratamiento:listaTratamientos) {
				cbNombreTratamientoC.addItem(tratamiento.getNombre());
			}
	}
	
	private void getAreas() {
		try {
			listaAreas = FuncionesDB.consultarAreas();
			for (AreaDeTrabajo area : listaAreas) {
				cbEspecialidad.addItem(area.getNombre());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error Interno SQL", "¡Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(null, "No existen Areas de Trabajo", "¡Advertencia!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
