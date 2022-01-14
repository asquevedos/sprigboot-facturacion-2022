package edu.ucacue.facturacion2.controller.persona;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
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
import javax.annotation.PostConstruct;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

@Controller
public class VentanaPersona extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtDireccion;
	private JButton btnEliminar;
	private JButton btnActualizar;

	public Persona persona;
	public Persona personaSeleccionada;
	public List<Persona> personas;

	@Autowired
	PersonaRepositorio personaRepositorio;

	// Interfaz gráfica con tabla
	PersonaItemModel personaModelo;
	private JTable tablePersona;

	public boolean bandera = true;
	private JComboBox cBOpcion;
	private JTextField txtCriterio;
	private JLabel lblNewLabel_2;
	private JButton btnBuscar;

	public VentanaPersona() {
		setBackground(new Color(240, 240, 240));

		this.setResizable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		interfazPersona();
		personas = new ArrayList<Persona>();
		setBounds(10, 10, 850, 386);
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
		txtApellido.setBounds(131, 87, 154, 20);
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
				if (bandera == true) {
					persona = new Persona();
					persona.setNombre(txtNombre.getText());
					persona.setApellido(txtApellido.getText());
					persona.setCedula(txtCedula.getText());
					persona.setDireccion(txtDireccion.getText());

					personaRepositorio.save(persona);
				} else {
					persona = new Persona();
					persona.setId(personaSeleccionada.getId());
					persona.setNombre(txtNombre.getText());
					persona.setApellido(txtApellido.getText());
					persona.setCedula(txtCedula.getText());
					persona.setDireccion(txtDireccion.getText());

					personaRepositorio.save(persona);
					bandera = true;
					txtCedula.setEnabled(true);
				}

				limpiarVentana();
				generarTabla();

			}
		});
		btnGrabarPersona.setBounds(10, 181, 96, 40);
		contentPane.add(btnGrabarPersona);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(315, 90, 451, 162);
		contentPane.add(scrollPane);

		tablePersona = new JTable();
		tablePersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(tablePersona.getSelectedRow());
				btnEliminar.setEnabled(true);
				btnActualizar.setEnabled(true);
				System.out.println(personaModelo.getPersonaAt(tablePersona.getSelectedRow()));

			}
		});
		scrollPane.setViewportView(tablePersona);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				personaRepositorio.delete(personaModelo.getPersonaAt(tablePersona.getSelectedRow()));
				generarTabla();
				btnEliminar.setEnabled(false);
				btnActualizar.setEnabled(false);
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(116, 180, 89, 41);
		contentPane.add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personaSeleccionada = personaModelo.getPersonaAt(tablePersona.getSelectedRow());
				txtNombre.setText(personaSeleccionada.getNombre());
				txtApellido.setText(personaSeleccionada.getApellido());
				txtCedula.setText(personaSeleccionada.getCedula());
				txtDireccion.setText(personaSeleccionada.getDireccion());
				txtCedula.setEnabled(false);
				bandera = false;

				btnEliminar.setEnabled(false);
				btnActualizar.setEnabled(false);

			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(215, 181, 89, 40);
		contentPane.add(btnActualizar);
		
		cBOpcion = new JComboBox();
		cBOpcion.setModel(new DefaultComboBoxModel(new String[] {"", "Nombre", "Apellido", "Cédula"}));
		cBOpcion.setBounds(315, 55, 77, 22);
		contentPane.add(cBOpcion);
		
		txtCriterio = new JTextField();
		txtCriterio.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtCriterio.setBounds(402, 37, 275, 39);
		contentPane.add(txtCriterio);
		txtCriterio.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Buscar por:");
		lblNewLabel_2.setBounds(315, 37, 77, 14);
		contentPane.add(lblNewLabel_2);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion= cBOpcion.getSelectedItem().toString();
				String criterio = txtCriterio.getText();
				List<Persona> resultadoBusqueda = new ArrayList<>();
				if(opcion.equals("Nombre"))
				{
					/// Tengo que tomar todas las personas que tengan el nombre por el criterio de busqueda
					resultadoBusqueda = personaRepositorio.buscarPorNombre("%"+criterio+"%");
					generarTablaBusqueda(resultadoBusqueda);
				}
				if(opcion.equals("Apellido"))
				{
					resultadoBusqueda = personaRepositorio.findByApellidoLike("%"+criterio+"%");
					generarTablaBusqueda(resultadoBusqueda);
					
				}
				if(opcion.equals("Cédula"))
				{
					resultadoBusqueda = personaRepositorio.findByCedulaLike("%"+criterio+"%");
					generarTablaBusqueda(resultadoBusqueda);
				}
			}
		});
		btnBuscar.setBounds(687, 37, 79, 34);
		contentPane.add(btnBuscar);
	}

	public void interfazPersona() {

	}

	public void limpiarVentana() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCedula.setText("");
		txtDireccion.setText("");

	}

	@PostConstruct
	public void generarTabla() {

		personas = personaRepositorio.findAll();

		personaModelo = new PersonaItemModel(personas);
		tablePersona.setModel(personaModelo);

	}
	
	public void generarTablaBusqueda(List<Persona> personas)
	{
		personaModelo = new PersonaItemModel(personas);
		tablePersona.setModel(personaModelo);
	}

}
