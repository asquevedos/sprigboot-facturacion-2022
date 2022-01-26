package edu.ucacue.facturacion2.controller.factura;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.controller.persona.DialogBuscarPersona;
import edu.ucacue.facturacion2.modelo.CabeceraFactura;
import edu.ucacue.facturacion2.modelo.Persona;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
@Controller
public class VentanaFactura extends JInternalFrame {

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	
	JLabel lblNombre;
	JLabel lblApellido;
	JLabel lblCedula;
	JLabel lblDireccion;
	
	@Autowired
	DialogBuscarPersona buscarPersona;
	
	
	Persona personaSeleccionada;

	CabeceraFactura cabeceraFactura;
	
	public VentanaFactura() {
		cabeceraFactura = new CabeceraFactura();
		setBounds(100, 100, 615, 300);
		getContentPane().setLayout(null);
		
		this.setResizable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setIconifiable(true);
		
		JLabel lblNewLabel_1 = new JLabel("Escoger la empresa que estás realizando la factura");
		lblNewLabel_1.setBounds(496, 11, 78, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("los detalles de las facturas");
		lblNewLabel_2.setBounds(69, 139, 291, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnBuscarPersona = new JButton("Buscar Persona");
		btnBuscarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buscarPersona.setVisible(true);
				
				buscarPersona.okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						personaSeleccionada = buscarPersona.personaSeleccionada();
						cabeceraFactura.setCliente(null);
						llenarDatosPersona();
						buscarPersona.setVisible(false);
					}
				});
			}
		});
		btnBuscarPersona.setBounds(10, 21, 107, 23);
		getContentPane().add(btnBuscarPersona);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(124, 11, 276, 96);
		getContentPane().add(separator);
		
		 lblNombre = new JLabel("");
		lblNombre.setBounds(127, 11, 93, 14);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("");
		lblApellido.setBounds(230, 11, 113, 14);
		getContentPane().add(lblApellido);
		
		 lblCedula = new JLabel("");
		lblCedula.setBounds(127, 30, 46, 14);
		getContentPane().add(lblCedula);
		
		lblDireccion = new JLabel("");
		lblDireccion.setBounds(124, 58, 46, 14);
		getContentPane().add(lblDireccion);

	}
	
	public void llenarDatosPersona() {
		
		lblNombre.setText(personaSeleccionada.getNombre());
		lblApellido.setText(personaSeleccionada.getApellido());
		lblCedula.setText(personaSeleccionada.getCedula());
		lblDireccion.setText(personaSeleccionada.getDireccion());
	}
}
