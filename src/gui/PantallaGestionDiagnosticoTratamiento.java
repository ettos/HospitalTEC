package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.FuncionarioControler;
import excepciones.EmptyListException;
import excepciones.ValidarRangoNotExistException;
import logicadenegocios.AreaDeTrabajo;
import logicadenegocios.Diagnostico;
import logicadenegocios.Tratamiento;
import utilidades.FuncionesDB;
import utilidades.Utilidad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PantallaGestionDiagnosticoTratamiento extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreD;
	private JTextField txtNombreT;

	private JComboBox cbTratamiento,cbTipoTratamiento,cbDiagnostico;
	private ArrayList<Diagnostico> listaDiagnosticos;
	private ArrayList<Tratamiento> listaTratamientos;
	/**
	 * Create the frame.
	 */
	public PantallaGestionDiagnosticoTratamiento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#0a1944"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Gesti\u00F3n de Diagn\u00F3stico y Tratamiento");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTitulo.setBounds(152, 35, 331, 26);
		contentPane.add(lblTitulo);
		
		JLabel lblNombreD = new JLabel("Nombre del Diagn\u00F3stico:");
		lblNombreD.setForeground(Color.WHITE);
		lblNombreD.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombreD.setBounds(10, 96, 173, 26);
		contentPane.add(lblNombreD);
		
		txtNombreD = new JTextField();
		txtNombreD.setBounds(20, 152, 196, 20);
		contentPane.add(txtNombreD);
		txtNombreD.setColumns(10);
		
		JButton btnRegistrarD = new JButton("Registrar");
		btnRegistrarD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Utilidad.validarRangoMinimo(txtNombreD.getText(), 0);
					
					FuncionarioControler.agregarDiagnostico(txtNombreD.getText());
					JOptionPane.showMessageDialog(null, "¡El diagnostico ha sido agregado!", "¡Diagnostico Agregado!", JOptionPane.INFORMATION_MESSAGE);
					txtNombreD.setText("");
					actualizarcb();

				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡Debe escribir el nombre del diagnostico!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡No se ha podido agregar el diagnostico!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnRegistrarD.setBounds(266, 151, 89, 23);
		contentPane.add(btnRegistrarD);
		
		JLabel lblNombreT = new JLabel("Nombre del Tratamiento:");
		lblNombreT.setForeground(Color.WHITE);
		lblNombreT.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombreT.setBounds(10, 225, 173, 26);
		contentPane.add(lblNombreT);
		
		txtNombreT = new JTextField();
		txtNombreT.setColumns(10);
		txtNombreT.setBounds(20, 262, 196, 20);
		contentPane.add(txtNombreT);
		
		JButton btnRegistrarT = new JButton("Registrar");
		btnRegistrarT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Utilidad.validarRangoMinimo(txtNombreT.getText(), 0);
					String tipo=cbTipoTratamiento.getSelectedItem().toString();
					
					FuncionarioControler.agregarTratamiento(txtNombreT.getText(),tipo);
					JOptionPane.showMessageDialog(null, "¡El tratamiento ha sido agregado!", "¡Tratamiento Agregado!", JOptionPane.INFORMATION_MESSAGE);
					txtNombreT.setText("");
					actualizarcb();

				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡Debe escribir el nombre del tratamiento!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡No se ha podido agregar el tratamiento!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnRegistrarT.setBounds(266, 304, 89, 23);
		contentPane.add(btnRegistrarT);
		
		JButton btnModificarD = new JButton("Modificar");
		btnModificarD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Utilidad.validarRangoMinimo(txtNombreD.getText(), 0);
					String nombre=cbDiagnostico.getSelectedItem().toString();
					String nNombre=txtNombreD.getText();
					
					FuncionarioControler.actualizarDiagnostico(nombre, nNombre);
					JOptionPane.showMessageDialog(null, "¡El diagnostico ha sido actualizado!", "¡Diagnostico Actualizado!", JOptionPane.INFORMATION_MESSAGE);
					txtNombreT.setText("");
					actualizarcb();

				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡Debe escribir el nombre del diagnostico!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡No se ha podido actualizar el diagnostico!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnModificarD.setBounds(379, 151, 89, 23);
		contentPane.add(btnModificarD);
		
		JButton btnEliminarD = new JButton("Eliminar");
		btnEliminarD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nombre=cbDiagnostico.getSelectedItem().toString();
					FuncionarioControler.eliminarDiagnostico(nombre);
					JOptionPane.showMessageDialog(null, "¡El diagnostico ha sido eliminado!", "¡Diagnostico Eliminado!", JOptionPane.INFORMATION_MESSAGE);
					actualizarcb();
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡No se ha podido eliminar el diagnostico!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnEliminarD.setBounds(489, 151, 89, 23);
		contentPane.add(btnEliminarD);
		
		cbDiagnostico = new JComboBox();
		cbDiagnostico.setBounds(266, 101, 312, 22);
		contentPane.add(cbDiagnostico);
		
		JButton btnModificarT = new JButton("Modificar");
		btnModificarT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Utilidad.validarRangoMinimo(txtNombreT.getText(), 0);
					String nombre=cbTratamiento.getSelectedItem().toString();
					String nNombre=txtNombreT.getText();
					String nTipo=cbTipoTratamiento.getSelectedItem().toString();
					
					FuncionarioControler.actualizarTratamiento(nombre, nNombre, nTipo);
					JOptionPane.showMessageDialog(null, "¡El tratamiento ha sido actualizado!", "¡Tratamiento Actualizado!", JOptionPane.INFORMATION_MESSAGE);
					txtNombreT.setText("");
					actualizarcb();

				} catch (ValidarRangoNotExistException e1) {
					JOptionPane.showMessageDialog(null, "¡Debe escribir el nombre del tratamiento!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡No se ha podido actualizar el tratamiento!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		});
		btnModificarT.setBounds(379, 304, 89, 23);
		contentPane.add(btnModificarT);
		
		JButton btnEliminarT = new JButton("Eliminar");
		btnEliminarT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nombre=cbTratamiento.getSelectedItem().toString();
					FuncionarioControler.eliminarTratamiento(nombre);
					JOptionPane.showMessageDialog(null, "¡El tratamiento ha sido eliminado!", "¡Tratamiento Eliminado!", JOptionPane.INFORMATION_MESSAGE);
					actualizarcb();
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "¡No se ha podido eliminar el tratamiento!", "¡Error!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		});
		btnEliminarT.setBounds(489, 304, 89, 23);
		contentPane.add(btnEliminarT);
		
		cbTratamiento = new JComboBox();
		cbTratamiento.setBounds(266, 262, 312, 22);
		contentPane.add(cbTratamiento);
		
		JLabel lblNombreT_1 = new JLabel("Tipo del Tratamiento:");
		lblNombreT_1.setForeground(Color.WHITE);
		lblNombreT_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNombreT_1.setBounds(10, 293, 173, 26);
		contentPane.add(lblNombreT_1);
		
		String[] tiposTratamientos= {"Medicamento", "Cirugía", "Curación", "Sutura", "Radiografía", "Ultrasonido", "Otros"};
		cbTipoTratamiento = new JComboBox(tiposTratamientos);
		cbTipoTratamiento.setBounds(20, 330, 196, 22);
		contentPane.add(cbTipoTratamiento);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(Color.decode("#f6f7f2"));
		btnNewButton.setIcon(new ImageIcon("img\\logoSalir.png"));
		btnNewButton.setBounds(10, 11, 40, 40);
		contentPane.add(btnNewButton);
		
		
		actualizarcb();
	}
	
	private void actualizarcb() {
		getDiagnosticos();
		getTratamientos();
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
	
	private void getTratamientos() {
		cbTratamiento.removeAllItems();
		try {
			listaTratamientos=FuncionesDB.consultarTratamiento();
			for(Tratamiento tratamiento:listaTratamientos) {
				cbTratamiento.addItem(tratamiento.getNombre());
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error Interno SQL", "¡Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (EmptyListException e) {
			JOptionPane.showMessageDialog(null,"No existen Tratamientos", "¡Advertencia!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
