package edu.ucacue.facturacion2.controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.infraestructura.repositorio.PersonaRepositorio;
import edu.ucacue.facturacion2.modelo.Persona;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JTextPane;

@Controller
public class VentanaPersona extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtDireccion;
	JTextArea textArea;

	public Persona persona;
	public List<Persona> personas;

	@Autowired
	PersonaRepositorio personaRepositorio;

	public VentanaPersona() {

		personas = new ArrayList<Persona>();
		//

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mantenimiento de Personas");
		lblNewLabel.setBounds(5, 5, 424, 21);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 17));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(46, 56, 60, 21);
		contentPane.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setBounds(131, 56, 154, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Apellido:");
		lblNewLabel_1_1.setBounds(46, 88, 60, 21);
		contentPane.add(lblNewLabel_1_1);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(131, 88, 154, 20);
		contentPane.add(txtApellido);

		JLabel lblNewLabel_1_2 = new JLabel("Cédula:");
		lblNewLabel_1_2.setBounds(46, 120, 60, 21);
		contentPane.add(lblNewLabel_1_2);

		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(131, 120, 154, 20);
		contentPane.add(txtCedula);

		JLabel lblNewLabel_1_3 = new JLabel("Dirección:");
		lblNewLabel_1_3.setBounds(46, 149, 60, 21);
		contentPane.add(lblNewLabel_1_3);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(131, 149, 154, 20);
		contentPane.add(txtDireccion);

		JButton btnGrabarPersona = new JButton("Grabar");
		btnGrabarPersona.setSelectedIcon(
				new ImageIcon("C:\\Users\\SEBASTIAN QUEVEDO\\Downloads\\Paomedia-Small-N-Flat-Floppy.ico"));
		btnGrabarPersona
				.setIcon(new ImageIcon("C:\\Users\\SEBASTIAN QUEVEDO\\Downloads\\Paomedia-Small-N-Flat-Floppy.ico"));

		btnGrabarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// persona = new Persona(txtNombre.getText(), txtApellido.getText(),
				// txtCedula.getText(), txtDireccion.getText() );
				persona = new Persona();
				persona.setNombre(txtNombre.getText());
				persona.setApellido(txtApellido.getText());
				persona.setCedula(txtCedula.getText());
				persona.setDireccion(txtDireccion.getText());

				personaRepositorio.save(persona);

				limpiarVentana();

			}
		});
		btnGrabarPersona.setBounds(165, 180, 96, 40);
		contentPane.add(btnGrabarPersona);


		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personas = personaRepositorio.findAll();
				llenarDatosPersona(personas);
			}
		});
		btnMostrar.setBounds(399, 231, 89, 23);
		contentPane.add(btnMostrar);
		
		textArea = new JTextArea();
		textArea.setRows(100);
		textArea.setBounds(322, 54, 481, 145);
		contentPane.add(textArea);

		//llenarDatosPersona(personas);

	}

	public void limpiarVentana() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCedula.setText("");
		txtDireccion.setText("");

	}

	public void llenarDatosPersona(List<Persona> personas) {
		String datos="";
		for (Persona p : personas) {
			//datos = datos + p + "\n";
			//tAPersonas.setText("\n");
			textArea.append(p.toString());
		}
		
		//textArea.setText(datos);
		// tAPersonas.setText("hasdiasodsa");

		// System.out.println(tAPersonas);
	}
}
