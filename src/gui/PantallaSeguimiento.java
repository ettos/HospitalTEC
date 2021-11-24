package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaSeguimiento extends JFrame {

	private JPanel contentPane;
	private JTextField txtDosis;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAnno;
	private JTextField txtObservaciones;
	private JTextField txtFuncionario;


	/**
	 * Create the frame.
	 */
	public PantallaSeguimiento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#d9d8dd"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAsignarTratamiento = new JLabel("Asignar Tratamiento");
		lblAsignarTratamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignarTratamiento.setForeground(Color.decode("#0a1944"));
		lblAsignarTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAsignarTratamiento.setBounds(429, 94, 170, 28);
		contentPane.add(lblAsignarTratamiento);
		
		JLabel lblNombreTratamiento = new JLabel("Nombre del Tratamiento: ");
		lblNombreTratamiento.setForeground(Color.decode("#0a1944"));
		lblNombreTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreTratamiento.setBounds(348, 133, 160, 29);
		contentPane.add(lblNombreTratamiento);
		
		JLabel lblDosis = new JLabel("Dosis:");
		lblDosis.setForeground(Color.decode("#0a1944"));
		lblDosis.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDosis.setBounds(348, 173, 160, 24);
		contentPane.add(lblDosis);
		
		JLabel lblTipoTratamiento = new JLabel("Tipo de Tratamiento:");
		lblTipoTratamiento.setForeground(Color.decode("#0a1944"));
		lblTipoTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTipoTratamiento.setBounds(348, 208, 160, 28);
		contentPane.add(lblTipoTratamiento);
		
		JButton btnAsignarTratamiento = new JButton("Asignar Tratamiento");
		btnAsignarTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAsignarTratamiento.setBounds(447, 252, 154, 23);
		contentPane.add(btnAsignarTratamiento);
		
		JLabel lblNewLabel = new JLabel("Seguimiento de la Hospitalizaci\u00F3n");
		lblNewLabel.setForeground(Color.decode("#0a1944"));
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel.setBounds(224, 23, 295, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblFechaConsulta = new JLabel("Fecha: ");
		lblFechaConsulta.setForeground(new Color(10, 25, 68));
		lblFechaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFechaConsulta.setBounds(29, 204, 60, 14);
		
		JLabel lblGuiaFechaConsulta = new JLabel("   D\u00EDa   -   Mes   -   A\u00F1o");
		lblGuiaFechaConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuiaFechaConsulta.setForeground(new Color(10, 25, 68));
		lblGuiaFechaConsulta.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblGuiaFechaConsulta.setBounds(29, 256, 220, 14);
		contentPane.add(lblGuiaFechaConsulta);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setForeground(new Color(10, 25, 68));
		lblObservaciones.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblObservaciones.setBounds(29, 101, 109, 14);
		contentPane.add(lblObservaciones);
		
		JLabel lblFuncionarioACargo = new JLabel("Funcionario a Cargo:");
		lblFuncionarioACargo.setForeground(new Color(10, 25, 68));
		lblFuncionarioACargo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblFuncionarioACargo.setBounds(29, 297, 235, 26);
		contentPane.add(lblFuncionarioACargo);
		
		txtObservaciones = new JTextField();
		txtObservaciones.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtObservaciones.setColumns(10);
		txtObservaciones.setBounds(29, 138, 235, 20);
		contentPane.add(txtObservaciones);
		
		contentPane.add(lblFechaConsulta);
		txtDosis = new JTextField();
		txtDosis.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDosis.setColumns(10);
		txtDosis.setBounds(515, 176, 170, 20);
		contentPane.add(txtDosis);
		
		txtDia = new JTextField();
		txtDia.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDia.setColumns(10);
		txtDia.setBounds(79, 228, 30, 20);
		contentPane.add(txtDia);
		
		txtMes = new JTextField();
		txtMes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMes.setColumns(10);
		txtMes.setBounds(129, 228, 30, 20);
		contentPane.add(txtMes);
		
		txtAnno = new JTextField();
		txtAnno.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAnno.setColumns(10);
		txtAnno.setBounds(179, 228, 40, 20);
		contentPane.add(txtAnno);
		
		txtFuncionario = new JTextField();
		txtFuncionario.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFuncionario.setColumns(10);
		txtFuncionario.setBounds(29, 334, 235, 20);
		contentPane.add(txtFuncionario);
		
		JComboBox cbNombreTratamiento = new JComboBox();
		cbNombreTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbNombreTratamiento.setBounds(515, 137, 170, 22);
		contentPane.add(cbNombreTratamiento);
		
		JComboBox cbTipoTratamiento = new JComboBox();
		cbTipoTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbTipoTratamiento.setBounds(515, 212, 170, 22);
		contentPane.add(cbTipoTratamiento);
		
		
		JButton btnRegistrarSeguimiento = new JButton("Registrar Seguimiento");
		btnRegistrarSeguimiento.setFont(new Font("SansSerif", Font.PLAIN, 13));
		btnRegistrarSeguimiento.setBounds(278, 384, 170, 23);
		contentPane.add(btnRegistrarSeguimiento);
		
		JButton btnVolver = new JButton("");
		btnVolver.setBackground(Color.decode("#d9d8dd"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setIcon(new ImageIcon("img\\logoSalir.png"));
		btnVolver.setBounds(10, 11, 40, 40);
		contentPane.add(btnVolver);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("img\\logoHospitalizacionOscuro.png"));
		lblLogo.setBounds(164, 11, 50, 50);
		contentPane.add(lblLogo);
	}

}
