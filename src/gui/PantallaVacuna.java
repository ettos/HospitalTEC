package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.FuncionarioControler;
import excepciones.IsDigitNotExistException;
import excepciones.RangoFechaException;
import excepciones.ValidarRangoNotExistException;
import utilidades.Utilidad;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PantallaVacuna extends JFrame {

	private JPanel contentPane;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAnno;
	private JTextField txtNombre;
	private JTextField txtFarmaceutica;
	private JTextField txtLote;

	private int cedula;

	/**
	 * Create the frame.
	 */
	public PantallaVacuna(int cedula) {
		this.cedula=cedula;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("");
		btnVolver.setBackground(Color.decode("#f6f7f2"));
		btnVolver.setIcon(new ImageIcon("img\\logoSalir.png"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		btnVolver.setBounds(10, 11, 40, 40);
		contentPane.add(btnVolver);
		
		JLabel lblTitulo = new JLabel("Aplicar Vacuna");
		lblTitulo.setForeground(Color.decode("#0a1944"));
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTitulo.setBounds(188, 24, 131, 26);
		contentPane.add(lblTitulo);
		
		JLabel lblFecha = new JLabel("Fecha de Aplicaci\u00F3n: ");
		lblFecha.setForeground(Color.decode("#0a1944"));
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFecha.setBounds(270, 181, 151, 26);
		contentPane.add(lblFecha);
		
		
		JLabel lblGuiaFechaConsulta = new JLabel("D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsulta.setForeground(Color.decode("#0a1944"));
		lblGuiaFechaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsulta.setBounds(270, 243, 159, 14);
		contentPane.add(lblGuiaFechaConsulta);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la Vacuna: ");
		lblNombreDeLa.setForeground(Color.decode("#0a1944"));
		lblNombreDeLa.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombreDeLa.setBounds(36, 94, 159, 26);
		contentPane.add(lblNombreDeLa);
		
		JLabel lblFarmaceutica = new JLabel("Farmac\u00E9utica: ");
		lblFarmaceutica.setForeground(Color.decode("#0a1944"));
		lblFarmaceutica.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFarmaceutica.setBounds(270, 94, 151, 26);
		contentPane.add(lblFarmaceutica);
		
		JLabel lblLote = new JLabel("Lote:");
		lblLote.setForeground(Color.decode("#0a1944"));
		lblLote.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblLote.setBounds(36, 181, 151, 26);
		contentPane.add(lblLote);
		
		txtDia = new JTextField();
		txtDia.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDia.setColumns(10);
		txtDia.setBounds(280, 212, 30, 20);
		contentPane.add(txtDia);
		txtMes = new JTextField();
		txtMes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMes.setColumns(10);
		txtMes.setBounds(330, 212, 30, 20);
		contentPane.add(txtMes);
		
		txtAnno = new JTextField();
		txtAnno.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnno.setColumns(10);
		txtAnno.setBounds(380, 212, 40, 20);
		contentPane.add(txtAnno);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombre.setBounds(46, 129, 159, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtFarmaceutica = new JTextField();
		txtFarmaceutica.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFarmaceutica.setColumns(10);
		txtFarmaceutica.setBounds(280, 129, 159, 20);
		contentPane.add(txtFarmaceutica);
		
		txtLote = new JTextField();
		txtLote.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLote.setColumns(10);
		txtLote.setBounds(46, 212, 159, 20);
		contentPane.add(txtLote);
		
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int lote;
				try {
					
					Utilidad.validarRangoMinimo(txtLote.getText(), 0);
					Utilidad.validarRangoMinimo(txtAnno.getText(), 0);
					Utilidad.validarRangoMinimo(txtMes.getText(), 0);
					Utilidad.validarRangoMinimo(txtDia.getText(), 0);
					Utilidad.validarRangoMinimo(txtFarmaceutica.getText(), 0);
					Utilidad.validarRangoMinimo(txtNombre.getText(), 0);
					
					lote = Utilidad.cadenaAEntero(txtLote.getText());
					int anno=Utilidad.cadenaAEntero(txtAnno.getText());
					int mes=Utilidad.cadenaAEntero(txtMes.getText());
					int dia=Utilidad.cadenaAEntero(txtDia.getText());
					Date fecha=new Date(new java.util.Date(anno-1900,mes-1,dia).getTime());
					
					Utilidad.compararFechas(fecha, new Date(new java.util.Date().getTime()));
					
					FuncionarioControler.aplicarVacuna(cedula, fecha, txtNombre.getText(), txtFarmaceutica.getText(), lote);
					JOptionPane.showMessageDialog(null, "¡La Vacuna ha sido aplicada con exito!", "¡Vacuna Aplicada!", JOptionPane.INFORMATION_MESSAGE);

				} catch (IsDigitNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡El Lote, el año, el mes y el dia deben ser digitos!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡Error interno\nSQL!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡Deben llenar todos los espacios!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (RangoFechaException e1) {
					JOptionPane.showMessageDialog(null, "¡Fecha invalida!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnAplicar.setFont(new Font("SansSerif", Font.PLAIN, 13));
		btnAplicar.setBounds(198, 291, 89, 23);
		contentPane.add(btnAplicar);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("img\\logoVacuna.png"));
		lblLogo.setBounds(132, 11, 50, 50);
		contentPane.add(lblLogo);
	}
}
