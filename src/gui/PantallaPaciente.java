package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controladores.FuncionarioControler;
import controladores.PacienteControler;
import excepciones.EmptyListException;
import excepciones.IsDigitNotExistException;
import excepciones.RangoFechaException;
import excepciones.ValidarRangoNotExistException;
import logicadenegocios.AreaDeTrabajo;
import logicadenegocios.Cita;
import logicadenegocios.Persona;
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
import java.awt.event.ActionEvent;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;

public class PantallaPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAnno;
	private JTextField txtObservaciones;
	private JTextField txtDiaConsulta;
	private JTextField txtDiaConsultaDiagnostico;
	private JTextField txtDiaConsultaTratamiento;
	private JTextField txtMesConsulta;
	private JTextField txtMesConsultaDiagnostico;
	private JTextField txtMesConsultaTratamiento;
	private JTextField txtAnnoConsulta;
	private JTextField txtAnnoConsultaDiagnostico;
	private JTextField txtAnnoConsultaTratamiento;
	private JTextField txtDiaConsulta2;
	private JTextField txtDiaConsultaDiagnostico2;
	private JTextField txtDiaConsultaTratamiento2;
	private JTextField txtMesConsulta2;
	private JTextField txtMesConsultaDiagnostico2;
	private JTextField txtMesConsultaTratamiento2;
	private JTextField txtAnnoConsulta2;
	private JTextField txtAnnoConsultaDiagnostico2;
	private JTextField txtAnnoConsultaTratamiento2;
	private JTextField txtNombreDiagnostico;
	private JTextField txtNombreTratamiento;
	private JComboBox cbEspecialidad, cbHora, cnMinutos, cbEspecialidad_1, cbEstado, cbNivelDiagnostico,
			cbTipoTratamiento;
	private JTable tableDiagnostico;
	private JTable tableCitas;
	private JTable tableTratamientos;
	private JTable tableHospitalizaciones;

	private DefaultTableModel modeloCitas;
	private DefaultTableModel modeloDiagnostico;
	private DefaultTableModel modeloTratamientos;
	private DefaultTableModel modeloHospitalizaciones;

	private Persona personaLog;
	private ArrayList<AreaDeTrabajo> listaAreas;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private ArrayList<Cita> listaCitas;

	/**
	 * Create the frame.
	 */
	public PantallaPaciente() {
		personaLog = UsuarioLogueado.getUsuarioLogueado();

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#f1f1f1"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("\\img\\logoPaciente.png"));
		lblLogo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblLogo.setBounds(163, 11, 50, 50);
		contentPane.add(lblLogo);

		JLabel lblTitulo = new JLabel("\u00A1Bienvenido!");
		lblTitulo.setForeground(Color.decode("#0a1944"));
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblTitulo.setBounds(218, 15, 173, 31);
		contentPane.add(lblTitulo);

		JButton btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon("\\img\\logoSalir.png"));
		btnSalir.setBackground(Color.decode("#f6f7f2"));
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
		btnSalir.setBounds(10, 3, 39, 39);
		contentPane.add(btnSalir);

		JTabbedPane tpOpciones = new JTabbedPane(JTabbedPane.TOP);
		tpOpciones.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tpOpciones.setBounds(0, 98, 594, 423);
		contentPane.add(tpOpciones);

		JPanel pnlCitas = new JPanel();
		pnlCitas.setBackground(Color.decode("#0a1944"));
		pnlCitas.setToolTipText("");
		pnlCitas.setForeground(Color.GRAY);
		tpOpciones.addTab("Añadir Cita", null, pnlCitas, null);
		pnlCitas.setLayout(null);

		JLabel lblAnnadirCita = new JLabel("A\u00F1adir Cita");
		lblAnnadirCita.setForeground(Color.decode("#f1f1f1"));
		lblAnnadirCita.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadirCita.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblAnnadirCita.setBounds(227, 21, 170, 27);
		pnlCitas.add(lblAnnadirCita);

		JLabel lblEspecialidadCita = new JLabel("Especialidad:");
		lblEspecialidadCita.setForeground(Color.decode("#f1f1f1"));
		lblEspecialidadCita.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEspecialidadCita.setBounds(35, 81, 118, 19);
		pnlCitas.add(lblEspecialidadCita);

		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setForeground(Color.decode("#f1f1f1"));
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFecha.setBounds(352, 81, 60, 14);
		pnlCitas.add(lblFecha);

		JLabel lblGuiaFecha = new JLabel("D\u00EDa     -     Mes     -     A\u00F1o");
		lblGuiaFecha.setForeground(Color.decode("#f1f1f1"));
		lblGuiaFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFecha.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFecha.setBounds(352, 106, 190, 14);
		pnlCitas.add(lblGuiaFecha);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setForeground(Color.decode("#f1f1f1"));
		lblHora.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblHora.setBounds(35, 175, 46, 14);
		pnlCitas.add(lblHora);

		JLabel lblGuiaHora = new JLabel("         Hora      -      Minutos");
		lblGuiaHora.setForeground(Color.decode("#f1f1f1"));
		lblGuiaHora.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaHora.setBounds(35, 199, 190, 14);
		pnlCitas.add(lblGuiaHora);

		JLabel lblObervaciones = new JLabel("Observaciones:");
		lblObervaciones.setForeground(Color.decode("#f1f1f1"));
		lblObervaciones.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblObervaciones.setBounds(45, 258, 118, 14);
		pnlCitas.add(lblObervaciones);

		txtDia = new JTextField();
		txtDia.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDia.setBounds(367, 130, 30, 20);
		pnlCitas.add(txtDia);
		txtDia.setColumns(10);

		txtMes = new JTextField();
		txtMes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMes.setColumns(10);
		txtMes.setBounds(429, 130, 30, 20);
		pnlCitas.add(txtMes);

		txtAnno = new JTextField();
		txtAnno.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnno.setColumns(10);
		txtAnno.setBounds(492, 130, 40, 20);
		pnlCitas.add(txtAnno);

		txtObservaciones = new JTextField();
		txtObservaciones.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtObservaciones.setBounds(45, 279, 220, 20);
		pnlCitas.add(txtObservaciones);
		txtObservaciones.setColumns(10);

		cbEspecialidad = new JComboBox();
		getAreas(cbEspecialidad);
		cbEspecialidad.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbEspecialidad.setBounds(35, 131, 220, 22);
		pnlCitas.add(cbEspecialidad);

		cbHora = new JComboBox();
		cbHora.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbHora.setBounds(66, 215, 46, 22);
		pnlCitas.add(cbHora);

		cnMinutos = new JComboBox();
		cnMinutos.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cnMinutos.setBounds(148, 215, 56, 22);
		pnlCitas.add(cnMinutos);

		setHoras(cbHora, cnMinutos);

		JButton btnRegistrarCita = new JButton("Registrar");
		btnRegistrarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int dia = Utilidad.cadenaAEntero(txtDia.getText());
					int mes = Utilidad.cadenaAEntero(txtMes.getText());
					int anno = Utilidad.cadenaAEntero(txtAnno.getText());
					;
					int hora = cbHora.getSelectedIndex();
					int min = cnMinutos.getSelectedIndex();
					java.util.Date f = new java.util.Date(anno - 1900, mes - 1, dia, hora, min);
					Date sqlDate = new Date(f.getTime());

					Utilidad.compararFechas(sqlDate, new Date(new java.util.Date().getTime()));

					PacienteControler.agregarCita(personaLog.getCedula(), cbEspecialidad.getSelectedIndex() + 1,
							cbEspecialidad.getSelectedItem().toString(), txtObservaciones.getText(), sqlDate);
					JOptionPane.showMessageDialog(null, "La cita ha sido agregada satisfactoriamente", "¡CITA AGREGADA!",
							JOptionPane.INFORMATION_MESSAGE);

					actualizarTablas();

					clearcb(cbHora);
					clearcb(cnMinutos);
					cleartxt(txtDia);
					cleartxt(txtDia);
					cleartxt(txtMes);
					cleartxt(txtAnno);
					cleartxt(txtObservaciones);

				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "La fecha debe digitarse en números", "¡Error!",
							JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error interno\nSQL", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (MessagingException e1) {
					JOptionPane.showMessageDialog(null, "Error interno\nCorreo no enviado", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (RangoFechaException e1) {
					JOptionPane.showMessageDialog(null, "¡Fecha invalida!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnRegistrarCita.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnRegistrarCita.setBounds(254, 359, 89, 23);
		pnlCitas.add(btnRegistrarCita);

		JLabel lblLogoAnnadirCita = new JLabel("");
		lblLogoAnnadirCita.setIcon(new ImageIcon("img\\a\u00F1adirCita.png"));
		lblLogoAnnadirCita.setBounds(205, 11, 50, 50);
		pnlCitas.add(lblLogoAnnadirCita);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setForeground(new Color(241, 241, 241));
		lblTelefono.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTelefono.setBounds(352, 173, 118, 14);
		pnlCitas.add(lblTelefono);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setForeground(new Color(241, 241, 241));
		lblCorreo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCorreo.setBounds(352, 254, 118, 14);
		pnlCitas.add(lblCorreo);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(352, 216, 170, 20);
		pnlCitas.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(352, 276, 170, 20);
		pnlCitas.add(txtCorreo);

		JPanel pnlConsultarCitas = new JPanel();
		pnlConsultarCitas.setBackground(Color.decode("#3c4f6d"));
		tpOpciones.addTab("Consultar Citas", null, pnlConsultarCitas, null);
		pnlConsultarCitas.setLayout(null);

		JLabel lblConsultarCitas = new JLabel("Consulta de Citas");
		lblConsultarCitas.setForeground(new Color(255, 255, 255));
		lblConsultarCitas.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblConsultarCitas.setBounds(220, 20, 159, 24);
		pnlConsultarCitas.add(lblConsultarCitas);

		JLabel lblFechaConsulta = new JLabel("Fecha: ");
		lblFechaConsulta.setForeground(new Color(255, 255, 255));
		lblFechaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsulta.setBounds(15, 170, 60, 14);
		pnlConsultarCitas.add(lblFechaConsulta);

		JLabel lblGuiaFechaConsulta = new JLabel("D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsulta.setForeground(new Color(255, 255, 255));
		lblGuiaFechaConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsulta.setBounds(15, 220, 159, 14);
		pnlConsultarCitas.add(lblGuiaFechaConsulta);

		JLabel lblFechaConsulta2 = new JLabel("Fecha: ");
		lblFechaConsulta2.setForeground(new Color(255, 255, 255));
		lblFechaConsulta2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsulta2.setBounds(15, 245, 60, 14);
		pnlConsultarCitas.add(lblFechaConsulta2);

		JLabel lblGuiaFechaConsulta2 = new JLabel("D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsulta2.setForeground(new Color(255, 255, 255));
		lblGuiaFechaConsulta2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsulta2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsulta2.setBounds(15, 300, 159, 14);
		pnlConsultarCitas.add(lblGuiaFechaConsulta2);

		JLabel lblEspecialidadCitaConsulta = new JLabel("Especialidad:");
		lblEspecialidadCitaConsulta.setForeground(new Color(255, 255, 255));
		lblEspecialidadCitaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEspecialidadCitaConsulta.setBounds(15, 60, 96, 20);
		pnlConsultarCitas.add(lblEspecialidadCitaConsulta);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(new Color(255, 255, 255));
		lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblEstado.setBounds(15, 120, 95, 14);
		pnlConsultarCitas.add(lblEstado);

		txtDiaConsulta = new JTextField();
		txtDiaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsulta.setColumns(10);
		txtDiaConsulta.setBounds(25, 190, 30, 20);
		pnlConsultarCitas.add(txtDiaConsulta);

		txtMesConsulta = new JTextField();
		txtMesConsulta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsulta.setColumns(10);
		txtMesConsulta.setBounds(75, 190, 30, 20);
		pnlConsultarCitas.add(txtMesConsulta);

		txtAnnoConsulta = new JTextField();
		txtAnnoConsulta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsulta.setColumns(10);
		txtAnnoConsulta.setBounds(125, 190, 40, 20);
		pnlConsultarCitas.add(txtAnnoConsulta);

		txtDiaConsulta2 = new JTextField();
		txtDiaConsulta2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsulta2.setColumns(10);
		txtDiaConsulta2.setBounds(25, 270, 30, 20);
		pnlConsultarCitas.add(txtDiaConsulta2);

		txtMesConsulta2 = new JTextField();
		txtMesConsulta2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsulta2.setColumns(10);
		txtMesConsulta2.setBounds(75, 270, 30, 20);
		pnlConsultarCitas.add(txtMesConsulta2);

		txtAnnoConsulta2 = new JTextField();
		txtAnnoConsulta2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsulta2.setColumns(10);
		txtAnnoConsulta2.setBounds(125, 270, 40, 20);
		pnlConsultarCitas.add(txtAnnoConsulta2);

		cbEspecialidad_1 = new JComboBox();
		getAreas(cbEspecialidad_1);
		cbEspecialidad_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbEspecialidad_1.setBounds(20, 90, 149, 22);
		pnlConsultarCitas.add(cbEspecialidad_1);

		String[] estados = { "Registrada", "Cancelada por paciente", "Cancelada por centro medico", "Asignada",
				"Realizada" };
		cbEstado = new JComboBox(estados);
		cbEstado.setBounds(20, 140, 149, 22);
		pnlConsultarCitas.add(cbEstado);

		JScrollPane spConsultarCitas = new JScrollPane();
		spConsultarCitas.setBounds(216, 75, 336, 266);
		pnlConsultarCitas.add(spConsultarCitas);

		String[] colCitas = new String[] { "Identificador", "Especialidad", "Estado", "Fecha" };
		modeloCitas = new DefaultTableModel(colCitas, 0);

		tableCitas = new JTable(modeloCitas);

		spConsultarCitas.setViewportView(tableCitas);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setForeground(Color.BLACK);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				try {
					if(txtAnnoConsulta.getText().length()== 0) {
						actualizarTablas();
						limpiarFechasConsulta();
						return;
					}
					
					int cedula = UsuarioLogueado.getUsuarioLogueado().getCedula();
					int areaDeTrabajo = cbEspecialidad_1.getSelectedIndex()+1;
					String estado = cbEstado.getSelectedItem().toString();
					
					int anno1 = Utilidad.cadenaAEntero(txtAnnoConsulta.getText());
					int mes1 = Utilidad.cadenaAEntero(txtMesConsulta.getText());
					int dia1 = Utilidad.cadenaAEntero(txtDiaConsulta.getText());

					int anno2 = Utilidad.cadenaAEntero(txtAnnoConsulta2.getText());
					int mes2 = Utilidad.cadenaAEntero(txtMesConsulta2.getText());
					int dia2 = Utilidad.cadenaAEntero(txtDiaConsulta2.getText());

					java.util.Date f1 = new java.util.Date(anno1 - 1900, mes1 - 1, dia1);
					Date sqlDate1 = new Date(f1.getTime());

					java.util.Date f2 = new java.util.Date(anno2 - 1900, mes2 - 1, dia2);
					Date sqlDate2 = new Date(f2.getTime());
					
					listaCitas = PacienteControler.consultarCitaRangoFechas(cedula, areaDeTrabajo, estado, sqlDate1, sqlDate2);
					limpiarFechasConsulta();
					setModeloTablaCitas(listaCitas);
					
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "En la fecha solamente se pueden ingresar digitos", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Problemas internos, No se han encontrado citas", "¡ERROR!",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "No se han encontrado citas", "¡ERROR!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnConsultar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnConsultar.setBounds(45, 325, 89, 23);
		pnlConsultarCitas.add(btnConsultar);

		JButton btnEliminarCita = new JButton("Eliminar Cita");
		btnEliminarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tableCitas.getSelectedRow();
				int id;
				try {
					id = Utilidad.cadenaAEntero(tableCitas.getModel().getValueAt(i, 0).toString());

					// Verificacion de fechas
					String f = tableCitas.getModel().getValueAt(i, 3).toString();
					java.util.Date javaDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(f);
					Date fecha1 = new Date(javaDate.getTime());
					Date fecha2 = Utilidad.getMannana();

					Utilidad.compararFechas(fecha1, fecha2);

					PacienteControler.cancelarCita(UsuarioLogueado.getUsuarioLogueado().getIdentificacion(), id);
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
		btnEliminarCita.setForeground(Color.BLACK);
		btnEliminarCita.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEliminarCita.setBounds(329, 352, 120, 23);
		pnlConsultarCitas.add(btnEliminarCita);

		JLabel lblLogoConsultarCitas = new JLabel("");
		lblLogoConsultarCitas.setIcon(new ImageIcon("img\\ConsultaCitas.png"));
		lblLogoConsultarCitas.setBounds(165, 11, 50, 50);
		pnlConsultarCitas.add(lblLogoConsultarCitas);

		JPanel pnlDiagnosticos = new JPanel();
		pnlDiagnosticos.setBackground(Color.decode("#a5b5c4"));
		tpOpciones.addTab("Diagnósticos", null, pnlDiagnosticos, null);
		pnlDiagnosticos.setLayout(null);

		JLabel lblDiagnosticos = new JLabel("Diagn\u00F3sticos");
		lblDiagnosticos.setForeground(Color.decode("#0a1944"));
		lblDiagnosticos.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDiagnosticos.setBounds(231, 38, 112, 24);
		pnlDiagnosticos.add(lblDiagnosticos);

		JLabel lblFechaConsultaDiagnostico = new JLabel("Fecha: ");
		lblFechaConsultaDiagnostico.setForeground(Color.decode("#0a1944"));
		lblFechaConsultaDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsultaDiagnostico.setBounds(15, 180, 60, 14);
		pnlDiagnosticos.add(lblFechaConsultaDiagnostico);

		JLabel lblGuiaFechaConsultaDiagnostico = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsultaDiagnostico.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsultaDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsultaDiagnostico.setBounds(20, 225, 159, 14);
		pnlDiagnosticos.add(lblGuiaFechaConsultaDiagnostico);

		JLabel lblFechaConsultaDiagnostico2 = new JLabel("Fecha: ");
		lblFechaConsultaDiagnostico2.setForeground(Color.decode("#0a1944"));
		lblFechaConsultaDiagnostico2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsultaDiagnostico2.setBounds(10, 245, 60, 14);
		pnlDiagnosticos.add(lblFechaConsultaDiagnostico2);

		JLabel lblGuiaFechaConsultaDiagnostico2 = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsultaDiagnostico2.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsultaDiagnostico2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsultaDiagnostico2.setBounds(20, 295, 159, 14);
		pnlDiagnosticos.add(lblGuiaFechaConsultaDiagnostico2);

		JLabel lblNombreDiagnostico = new JLabel("Nombre de Diagn\u00F3stico: ");
		lblNombreDiagnostico.setForeground(Color.decode("#0a1944"));
		lblNombreDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombreDiagnostico.setBounds(10, 75, 180, 24);
		pnlDiagnosticos.add(lblNombreDiagnostico);

		JLabel lblNivelDiagnostico = new JLabel("Nivel Diagn\u00F3stico: ");
		lblNivelDiagnostico.setForeground(Color.decode("#0a1944"));
		lblNivelDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNivelDiagnostico.setBounds(15, 125, 149, 24);
		pnlDiagnosticos.add(lblNivelDiagnostico);

		txtDiaConsultaDiagnostico = new JTextField();
		txtDiaConsultaDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsultaDiagnostico.setColumns(10);
		txtDiaConsultaDiagnostico.setBounds(25, 200, 30, 20);
		pnlDiagnosticos.add(txtDiaConsultaDiagnostico);

		txtMesConsultaDiagnostico = new JTextField();
		txtMesConsultaDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsultaDiagnostico.setColumns(10);
		txtMesConsultaDiagnostico.setBounds(75, 200, 30, 20);
		pnlDiagnosticos.add(txtMesConsultaDiagnostico);

		txtAnnoConsultaDiagnostico = new JTextField();
		txtAnnoConsultaDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsultaDiagnostico.setColumns(10);
		txtAnnoConsultaDiagnostico.setBounds(125, 200, 40, 20);
		pnlDiagnosticos.add(txtAnnoConsultaDiagnostico);

		txtDiaConsultaDiagnostico2 = new JTextField();
		txtDiaConsultaDiagnostico2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsultaDiagnostico2.setColumns(10);
		txtDiaConsultaDiagnostico2.setBounds(25, 270, 30, 20);
		pnlDiagnosticos.add(txtDiaConsultaDiagnostico2);

		txtMesConsultaDiagnostico2 = new JTextField();
		txtMesConsultaDiagnostico2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsultaDiagnostico2.setColumns(10);
		txtMesConsultaDiagnostico2.setBounds(75, 270, 30, 20);
		pnlDiagnosticos.add(txtMesConsultaDiagnostico2);

		txtAnnoConsultaDiagnostico2 = new JTextField();
		txtAnnoConsultaDiagnostico2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsultaDiagnostico2.setColumns(10);
		txtAnnoConsultaDiagnostico2.setBounds(125, 270, 40, 20);
		pnlDiagnosticos.add(txtAnnoConsultaDiagnostico2);

		txtNombreDiagnostico = new JTextField();
		txtNombreDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombreDiagnostico.setBounds(30, 101, 149, 20);
		pnlDiagnosticos.add(txtNombreDiagnostico);
		txtNombreDiagnostico.setColumns(10);

		cbNivelDiagnostico = new JComboBox();
		cbNivelDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbNivelDiagnostico.setBounds(30, 150, 149, 22);
		pnlDiagnosticos.add(cbNivelDiagnostico);

		JScrollPane spDiagnosticos = new JScrollPane();
		spDiagnosticos.setBounds(207, 73, 357, 290);
		pnlDiagnosticos.add(spDiagnosticos);

		String[] colDiagnostico = new String[] { "Nombre", "Nivel", "Fecha" };
		modeloDiagnostico = new DefaultTableModel(colDiagnostico, 0);
		tableDiagnostico = new JTable(modeloDiagnostico);

		spDiagnosticos.setViewportView(tableDiagnostico);

		JButton btnConsultarDiagnostico = new JButton("Consultar");
		btnConsultarDiagnostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarDiagnostico.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnConsultarDiagnostico.setBounds(50, 320, 89, 23);
		pnlDiagnosticos.add(btnConsultarDiagnostico);

		JLabel lblLogoDiagnostico = new JLabel("");
		lblLogoDiagnostico.setIcon(new ImageIcon("img\\logoDiagnostico.png"));
		lblLogoDiagnostico.setBounds(189, 11, 50, 50);
		pnlDiagnosticos.add(lblLogoDiagnostico);

		JPanel pnlTratamientos = new JPanel();
		pnlTratamientos.setBackground(Color.decode("#3c4f6d"));
		tpOpciones.addTab("Tratamientos", null, pnlTratamientos, null);
		pnlTratamientos.setLayout(null);

		JLabel lblTratamientos = new JLabel("Tratamientos");
		lblTratamientos.setForeground(new Color(255, 255, 255));
		lblTratamientos.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTratamientos.setBounds(264, 30, 122, 26);
		pnlTratamientos.add(lblTratamientos);

		JLabel lblNombreTratamiento = new JLabel("Nombre del Tratamiento:");
		lblNombreTratamiento.setForeground(new Color(255, 255, 255));
		lblNombreTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombreTratamiento.setBounds(10, 80, 183, 14);
		pnlTratamientos.add(lblNombreTratamiento);

		JLabel lblTipoTratamiento = new JLabel("Tipo de Tratamiento:");
		lblTipoTratamiento.setForeground(new Color(255, 255, 255));
		lblTipoTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTipoTratamiento.setBounds(10, 136, 183, 20);
		pnlTratamientos.add(lblTipoTratamiento);

		JLabel lblFechaConsultaTratamiento = new JLabel("Fecha: ");
		lblFechaConsultaTratamiento.setForeground(new Color(255, 255, 255));
		lblFechaConsultaTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsultaTratamiento.setBounds(10, 195, 60, 14);
		pnlTratamientos.add(lblFechaConsultaTratamiento);

		JLabel lblGuiaFechaConsultaTratamiento = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsultaTratamiento.setForeground(new Color(255, 255, 255));
		lblGuiaFechaConsultaTratamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsultaTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsultaTratamiento.setBounds(7, 240, 159, 14);
		pnlTratamientos.add(lblGuiaFechaConsultaTratamiento);

		JLabel lblFechaConsultaTratamiento2 = new JLabel("Fecha: ");
		lblFechaConsultaTratamiento2.setForeground(new Color(255, 255, 255));
		lblFechaConsultaTratamiento2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsultaTratamiento2.setBounds(10, 260, 60, 14);
		pnlTratamientos.add(lblFechaConsultaTratamiento2);

		JLabel lblGuiaFechaConsultaTratamiento2 = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsultaTratamiento2.setForeground(new Color(255, 255, 255));
		lblGuiaFechaConsultaTratamiento2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsultaTratamiento2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsultaTratamiento2.setBounds(7, 305, 159, 14);
		pnlTratamientos.add(lblGuiaFechaConsultaTratamiento2);

		txtNombreTratamiento = new JTextField();
		txtNombreTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombreTratamiento.setBounds(20, 105, 159, 20);
		pnlTratamientos.add(txtNombreTratamiento);
		txtNombreTratamiento.setColumns(10);

		txtDiaConsultaTratamiento = new JTextField();
		txtDiaConsultaTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsultaTratamiento.setColumns(10);
		txtDiaConsultaTratamiento.setBounds(25, 215, 30, 20);
		pnlTratamientos.add(txtDiaConsultaTratamiento);

		txtMesConsultaTratamiento = new JTextField();
		txtMesConsultaTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsultaTratamiento.setColumns(10);
		txtMesConsultaTratamiento.setBounds(75, 215, 30, 20);
		pnlTratamientos.add(txtMesConsultaTratamiento);

		txtAnnoConsultaTratamiento = new JTextField();
		txtAnnoConsultaTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsultaTratamiento.setColumns(10);
		txtAnnoConsultaTratamiento.setBounds(125, 215, 40, 20);
		pnlTratamientos.add(txtAnnoConsultaTratamiento);

		txtDiaConsultaTratamiento2 = new JTextField();
		txtDiaConsultaTratamiento2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDiaConsultaTratamiento2.setColumns(10);
		txtDiaConsultaTratamiento2.setBounds(25, 280, 30, 20);
		pnlTratamientos.add(txtDiaConsultaTratamiento2);

		txtMesConsultaTratamiento2 = new JTextField();
		txtMesConsultaTratamiento2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMesConsultaTratamiento2.setColumns(10);
		txtMesConsultaTratamiento2.setBounds(75, 280, 30, 20);
		pnlTratamientos.add(txtMesConsultaTratamiento2);

		txtAnnoConsultaTratamiento2 = new JTextField();
		txtAnnoConsultaTratamiento2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnnoConsultaTratamiento2.setColumns(10);
		txtAnnoConsultaTratamiento2.setBounds(125, 280, 40, 20);
		pnlTratamientos.add(txtAnnoConsultaTratamiento2);

		cbTipoTratamiento = new JComboBox();
		cbTipoTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbTipoTratamiento.setBounds(20, 165, 159, 22);
		pnlTratamientos.add(cbTipoTratamiento);

		JButton btnConsultarTratamientos = new JButton("Consultar");
		btnConsultarTratamientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarTratamientos.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnConsultarTratamientos.setBounds(45, 330, 89, 23);
		pnlTratamientos.add(btnConsultarTratamientos);

		JScrollPane spTratamientos = new JScrollPane();
		spTratamientos.setBounds(204, 76, 351, 295);
		pnlTratamientos.add(spTratamientos);

		String[] colTratamientos = new String[] { "Nombre", "Tipo de Tratamiento", "Fecha" };
		modeloTratamientos = new DefaultTableModel(colTratamientos, 0);
		tableTratamientos = new JTable(modeloTratamientos);

		tableTratamientos.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spTratamientos.setViewportView(tableTratamientos);

		JLabel lblLogoTratamiento = new JLabel("");
		lblLogoTratamiento.setIcon(new ImageIcon("img\\logoTratamiento.png"));
		lblLogoTratamiento.setBounds(204, 11, 50, 50);
		pnlTratamientos.add(lblLogoTratamiento);

		JPanel pnlHospitalizaciones = new JPanel();
		pnlHospitalizaciones.setBackground(Color.decode("#0a1944"));
		tpOpciones.addTab("Hospitalizaciones", null, pnlHospitalizaciones, null);
		pnlHospitalizaciones.setLayout(null);

		JLabel lblHospitalizaciones = new JLabel("Hospitalizaciones");
		lblHospitalizaciones.setForeground(new Color(255, 255, 255));
		lblHospitalizaciones.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblHospitalizaciones.setBounds(225, 30, 200, 24);
		pnlHospitalizaciones.add(lblHospitalizaciones);

		JScrollPane spHospitalizaciones = new JScrollPane();
		spHospitalizaciones.setBounds(10, 77, 559, 235);
		pnlHospitalizaciones.add(spHospitalizaciones);

		String[] colHospitalizaciones = new String[] { "Fecha de Inicio", "Fecha de Finalización", "Especialidad",
				"Funcionario Encargado" };
		modeloHospitalizaciones = new DefaultTableModel(colTratamientos, 0);
		tableHospitalizaciones = new JTable(modeloHospitalizaciones);

		tableHospitalizaciones.setFont(new Font("SansSerif", Font.PLAIN, 12));
		spHospitalizaciones.setViewportView(tableHospitalizaciones);

		JLabel lblLogoHospitalizaciones = new JLabel("");
		lblLogoHospitalizaciones.setIcon(new ImageIcon("img\\LogoHospitalizacionClaro.png"));
		lblLogoHospitalizaciones.setBounds(165, 22, 50, 50);
		pnlHospitalizaciones.add(lblLogoHospitalizaciones);

		JButton btnConsultarHospitalizaciones = new JButton("Buscar");
		btnConsultarHospitalizaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarHospitalizaciones.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnConsultarHospitalizaciones.setBounds(239, 335, 89, 23);
		pnlHospitalizaciones.add(btnConsultarHospitalizaciones);

		actualizarTablas();

	}

	private void getAreas(JComboBox cb) {
		try {
			listaAreas = FuncionesDB.consultarAreas();
			for (AreaDeTrabajo area : listaAreas) {
				cb.addItem(area.getNombre());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error Interno SQL", "¡Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(null, "No existen Areas de Trabajo", "¡Advertencia!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cleartxt(JTextField txt) {
		txt.setText("");
	}

	private void clearcb(JComboBox cb) {
		cb.setSelectedIndex(0);
	}

	private void setHoras(JComboBox hora, JComboBox min) {
		for (int i = 0; i < 24; i++) {
			hora.addItem(i);
		}
		for (int i = 0; i < 60; i++) {
			min.addItem(i);
		}
	}

	private void actualizarTablas() {
		setModeloTablaCitas();
	}

	private void setModeloTablaCitas() {
		limpiarTabla(modeloCitas);
		try {
			listaCitas = FuncionesDB.consultarCitas(UsuarioLogueado.getUsuarioLogueado().getCedula());
			for (Cita cita : listaCitas) {
				Object[] objs = { cita.getIdentificador(), cita.getAreaTrabajo().getId(), cita.getEstado(),
						cita.getFechaCompleta() };
				modeloCitas.addRow(objs);

			}
		} catch (SQLException | EmptyListException e) {
			JOptionPane.showMessageDialog(null, "Actualmente no hay citas en el sistema", "¡Advertencia!",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void setModeloTablaCitas(ArrayList<Cita> listaCitas) {
		limpiarTabla(modeloCitas);
		for (Cita cita : listaCitas) {
			Object[] objs = { cita.getIdentificador(), cita.getAreaTrabajo().getId(), cita.getEstado(),
					cita.getFechaCompleta() };
			modeloCitas.addRow(objs);

		}
	}

	private void limpiarTabla(DefaultTableModel modelo) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
	}
	
	private void limpiarFechasConsulta() {
		txtAnnoConsulta.setText("");
		txtMesConsulta.setText("");
		txtDiaConsulta.setText("");
		txtAnnoConsulta2.setText("");
		txtMesConsulta2.setText("");
		txtDiaConsulta2.setText("");
	}

}
