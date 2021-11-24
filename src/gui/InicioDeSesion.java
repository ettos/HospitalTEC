package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import controladores.LoginControler;
import controladores.PersonaControler;
import excepciones.IsDigitNotExistException;
import excepciones.EmptyListException;
import excepciones.ValidarRangoNotExistException;
import logicadenegocios.Persona;
import utilidades.Utilidad;
import utilidades.UsuarioLogueado;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class InicioDeSesion {

	protected JFrame frmInicioDeSesion;
	private JTextField txtUsuario;
	private JTextField txtContrasenna;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioDeSesion window = new InicioDeSesion();
					window.frmInicioDeSesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InicioDeSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInicioDeSesion = new JFrame();
		frmInicioDeSesion.setTitle("Inicio de Sesi\u00F3n");
		frmInicioDeSesion.setResizable(false);
		frmInicioDeSesion.getContentPane().setBackground(Color.decode("#0a1944"));
		frmInicioDeSesion.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Hospital TEC");
		lblTitulo.setForeground(Color.decode("#f6f7f2"));
		lblTitulo.setBounds(80, 25, 167, 37);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 28));
		frmInicioDeSesion.getContentPane().add(lblTitulo);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("img\\logoHospital.png"));
		lblLogo.setBounds(25, 11, 50, 50);
		frmInicioDeSesion.getContentPane().add(lblLogo);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setForeground(Color.decode("#f6f7f2"));
		lblUsuario.setBounds(67, 94, 150, 26);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 18));
		frmInicioDeSesion.getContentPane().add(lblUsuario);
		
		JLabel lblContrasenna = new JLabel("Contrase\u00F1a: ");
		lblContrasenna.setForeground(Color.decode("#f6f7f2"));
		lblContrasenna.setBounds(67, 176, 150, 23);
		lblContrasenna.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenna.setFont(new Font("SansSerif", Font.PLAIN, 18));
		frmInicioDeSesion.getContentPane().add(lblContrasenna);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtUsuario.setBounds(67, 131, 150, 20);
		frmInicioDeSesion.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasenna = new JPasswordField ();
		txtContrasenna.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtContrasenna.setBounds(67, 210, 150, 20);
		frmInicioDeSesion.getContentPane().add(txtContrasenna);
		txtContrasenna.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(Color.decode("#f1f1f1"));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
							
				int cedula;
				try {
					
					boolean estado=Utilidad.validarRangoMaximo(txtUsuario.getText(), 12) ;
					
					
					
					cedula = Utilidad.cadenaAEntero(txtUsuario.getText());
					if (txtUsuario.getText().length()== 4) {
						PersonaControler.personaLogin(cedula,txtContrasenna.getText(),"Funcionario");
						try {
							PantallaFuncionario frame = new PantallaFuncionario();
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}else {
						PersonaControler.personaLogin(cedula,txtContrasenna.getText(),"Paciente");
						try {
							frmInicioDeSesion.dispose();
							PantallaPaciente frame = new PantallaPaciente();
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					Persona persona=UsuarioLogueado.getUsuarioLogueado();
					JOptionPane.showMessageDialog(null, persona.getClass().getSimpleName()+" Logueado!\n Bienvenido "+persona.getNombre(), "¡Bienvenido!", JOptionPane.INFORMATION_MESSAGE);

					
				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡El usuario no corresponde a un digito!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, "¡Usuario o Contraseña incorrectos!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡Usuario o Contraseña incorrectos!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡Error generado en SQL!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} 
			}
		});
		
		btnEntrar.setForeground(Color.decode("#0a1944"));
		btnEntrar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnEntrar.setBounds(97, 254, 89, 23);
		frmInicioDeSesion.getContentPane().add(btnEntrar);
		
		JButton btnAnnadir = new JButton("");
		btnAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PantallaAnnadirUsuario frame = new PantallaAnnadirUsuario();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAnnadir.setIcon(new ImageIcon("img\\btnA\u00F1adir.png"));
		btnAnnadir.setBackground(Color.decode("#0a1944"));
		btnAnnadir.setBounds(242, 268, 30, 30);
		frmInicioDeSesion.getContentPane().add(btnAnnadir);
		frmInicioDeSesion.setBounds(100, 100, 300, 350);
		frmInicioDeSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
