package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

import controladores.PacienteControler;
import excepciones.IsDigitNotExistException;
import excepciones.ValidarRangoNotExistException;
import utilidades.Utilidad;
import utilidades.tse;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PantallaAnnadirUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido1;
	private JTextField textApellido2;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField textAnno;
	private JTextField txtTipoSangre;
	private JLabel lblNacionalidad;
	private JTextField txtNacionalidad;
	private JLabel lblLugarResidencia;
	private JTextField textField;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JTextField txtContrasenna;

	

	/**
	 * Create the frame.
	 */
	public PantallaAnnadirUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#0a1944"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("A\u00F1adir Usuario");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTitulo.setBounds(152, 25, 139, 26);
		contentPane.add(lblTitulo);
		
		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setForeground(Color.WHITE);
		lblCedula.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCedula.setBounds(28, 73, 60, 21);
		contentPane.add(lblCedula);
		
		JLabel lblNombre = new JLabel("Nombre Completo: ");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombre.setBounds(28, 125, 139, 21);
		contentPane.add(lblNombre);
		
		
		JLabel lblFecha = new JLabel("Fecha Nacimiento: ");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFecha.setBounds(28, 346, 154, 24);
		contentPane.add(lblFecha);
		
		JLabel lblDaMes = new JLabel("D\u00EDa   -   Mes   -   A\u00F1o");
		lblDaMes.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaMes.setForeground(Color.WHITE);
		lblDaMes.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblDaMes.setBounds(38, 399, 129, 14);
		contentPane.add(lblDaMes);
		
		JLabel lblTipoSangre = new JLabel("Tipo de Sangre:");
		lblTipoSangre.setForeground(Color.WHITE);
		lblTipoSangre.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTipoSangre.setBounds(28, 424, 154, 21);
		contentPane.add(lblTipoSangre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(38, 150, 118, 20);
		contentPane.add(txtNombre);
		
		txtApellido1 = new JTextField();
		txtApellido1.setEditable(false);
		txtApellido1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtApellido1.setColumns(10);
		txtApellido1.setBounds(166, 150, 118, 20);
		contentPane.add(txtApellido1);
		
		textApellido2 = new JTextField();
		textApellido2.setEditable(false);
		textApellido2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textApellido2.setColumns(10);
		textApellido2.setBounds(294, 150, 118, 20);
		contentPane.add(textApellido2);
		
		
		txtCedula = new JTextField();
		txtCedula.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent event) {
		  	
		    try {
					String[] datos=tse.tse(Utilidad.cadenaAEntero(txtCedula.getText()));
					txtNombre.setText(datos[0]);
					txtApellido1.setText(datos[1]);
					textApellido2.setText(datos[2]);
					
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "¡Error de busqueda!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "¡Error de busqueda!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (IsDigitNotExistException e) {
					JOptionPane.showMessageDialog(null, "¡La cedula es incorrecta!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				}
		    
		  }
		});
		txtCedula.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCedula.setBounds(38, 96, 118, 20);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblContrasenna = new JLabel("Contrase\u00F1a:");
    lblContrasenna.setForeground(Color.WHITE);
    lblContrasenna.setFont(new Font("SansSerif", Font.PLAIN, 16));
    lblContrasenna.setBounds(226, 291, 154, 21);
    contentPane.add(lblContrasenna);

    txtContrasenna = new JTextField();
    txtContrasenna.setFont(new Font("SansSerif", Font.PLAIN, 12));
    txtContrasenna.setColumns(10);
    txtContrasenna.setBounds(236, 315, 154, 20);
    contentPane.add(txtContrasenna);
		
		txtDia = new JTextField();
		txtDia.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDia.setColumns(10);
		txtDia.setBounds(38, 371, 30, 20);
		contentPane.add(txtDia);
		
		txtMes = new JTextField();
		txtMes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMes.setColumns(10);
		txtMes.setBounds(85, 371, 30, 20);
		contentPane.add(txtMes);
		
		textAnno = new JTextField();
		textAnno.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textAnno.setColumns(10);
		textAnno.setBounds(127, 371, 40, 20);
		contentPane.add(textAnno);
		
		
		
		txtTipoSangre = new JTextField();
		txtTipoSangre.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtTipoSangre.setBounds(38, 448, 86, 20);
		contentPane.add(txtTipoSangre);
		txtTipoSangre.setColumns(10);
		
		lblNacionalidad = new JLabel("Nacionalidad: ");
		lblNacionalidad.setForeground(Color.WHITE);
		lblNacionalidad.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNacionalidad.setBounds(28, 181, 154, 21);
		contentPane.add(lblNacionalidad);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBounds(38, 205, 267, 20);
		contentPane.add(txtNacionalidad);
		
		lblLugarResidencia = new JLabel("Lugar de Residencia: ");
		lblLugarResidencia.setForeground(Color.WHITE);
		lblLugarResidencia.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblLugarResidencia.setBounds(28, 236, 154, 21);
		contentPane.add(lblLugarResidencia);
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(38, 260, 267, 20);
		contentPane.add(textField);
		
		lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTelefono.setBounds(28, 291, 154, 21);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(38, 315, 154, 20);
		contentPane.add(txtTelefono);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					validarVacios();
					
					int cedula=Utilidad.cadenaAEntero(txtCedula.getText());
					String nombre=txtNombre.getText();
					String apellido1=txtApellido1.getText();
					String apellido2=textApellido2.getText();
					String contraseña=txtContrasenna.getText();
					String tipoSangre=txtTipoSangre.getText();
					String nacionalidad=txtNacionalidad.getText();
					String residencia=textField.getText();
					int telefono=Utilidad.cadenaAEntero(txtTelefono.getText());
					
					int anno=Utilidad.cadenaAEntero(textAnno.getText());
					int mes=Utilidad.cadenaAEntero(txtMes.getText());
					int dia=Utilidad.cadenaAEntero(txtDia.getText());
					
					java.util.Date f=new java.util.Date(anno-1900, mes-1, dia);
					Date sqlDate = new Date(f.getTime());
					
					PacienteControler.agregarPaciente(cedula,nombre,apellido1,apellido2,contraseña,sqlDate,tipoSangre,nacionalidad,residencia,telefono);
					JOptionPane.showMessageDialog(null, "¡Usuario agregado con exito!", "¡Paciente agregado!", JOptionPane.INFORMATION_MESSAGE);
					limpiar();

				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡La cedula no es un digito!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡Usuario no agregado\nRevise los datos ingresados!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡Debe llenar todos los espacios!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnRegistrar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnRegistrar.setBounds(294, 446, 118, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnDevolver = new JButton("");
		btnDevolver.setBackground(Color.decode("#f6f7f2"));
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnDevolver.setForeground(Color.decode("#f6f7f2"));
		btnDevolver.setIcon(new ImageIcon("img\\logoSalir.png"));
		btnDevolver.setBounds(394, 11, 40, 40);
		contentPane.add(btnDevolver);
	}
	
	
	private void validarVacios() throws ValidarRangoNotExistException {
		Utilidad.validarRangoMinimo(txtCedula.getText(),0);
		Utilidad.validarRangoMinimo(txtNombre.getText(),0);
		Utilidad.validarRangoMinimo(txtApellido1.getText(),0);
		Utilidad.validarRangoMinimo(textApellido2.getText(),0);
		Utilidad.validarRangoMinimo(txtContrasenna.getText(),0);
		Utilidad.validarRangoMinimo(txtTipoSangre.getText(),0);
		Utilidad.validarRangoMinimo(txtNacionalidad.getText(),0);
		Utilidad.validarRangoMinimo(lblLugarResidencia.getText(),0);
		Utilidad.validarRangoMinimo(txtTelefono.getText(),0);
		
		Utilidad.validarRangoMinimo(textAnno.getText(),0);
		Utilidad.validarRangoMinimo(txtMes.getText(),0);
		Utilidad.validarRangoMinimo(txtDia.getText(),0);
	}
	
	private void limpiar() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellido1.setText("");
		textApellido2.setText("");
		txtTipoSangre.setText("");
		txtNacionalidad.setText("");
		textField.setText("");
		txtTelefono.setText("");
		textAnno.setText("");
		txtMes.setText("");
		txtDia.setText("");
		
	}
}
