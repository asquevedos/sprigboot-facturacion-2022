package edu.ucacue.facturacion2.controller.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.infraestructura.repositorio.ClienteRepositorio;
import edu.ucacue.facturacion2.modelo.Cliente;

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
public class VentanaCliente extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtDireccion;
	private JButton btnEliminar;
	private JButton btnActualizar;

	public Cliente cliente;
	public Cliente clienteSeleccionada;
	public List<Cliente> clientes;

	@Autowired
	ClienteRepositorio clienteRepositorio;

	// Interfaz gráfica con tabla
	ClienteItemModel clienteModelo;
	private JTable tableCliente;

	public boolean bandera = true;
	private JComboBox cBOpcion;
	private JTextField txtCriterio;
	private JLabel lblNewLabel_2;
	private JButton btnBuscar;
	private JTextField txtTelefono;

	public VentanaCliente() {
		setBackground(new Color(240, 240, 240));

		this.setResizable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		interfazPersona();
		clientes = new ArrayList<Cliente>();
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
					cliente = new Cliente();
					cliente.setNombre(txtNombre.getText());
					cliente.setApellido(txtApellido.getText());
					cliente.setCedula(txtCedula.getText());
					cliente.setDireccion(txtDireccion.getText());
					cliente.setTelefono(txtTelefono.getText());

					clienteRepositorio.save(cliente);
				} else {
					cliente = new Cliente();
					cliente.setId(clienteSeleccionada.getId());
					cliente.setNombre(txtNombre.getText());
					cliente.setApellido(txtApellido.getText());
					cliente.setCedula(txtCedula.getText());
					cliente.setDireccion(txtDireccion.getText());
					cliente.setTelefono(txtTelefono.getText());

					clienteRepositorio.save(cliente);
					bandera = true;
					txtCedula.setEnabled(true);
				}

				limpiarVentana();
				generarTabla();

			}
		});
		btnGrabarPersona.setBounds(11, 212, 96, 40);
		contentPane.add(btnGrabarPersona);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(315, 90, 451, 162);
		contentPane.add(scrollPane);

		tableCliente = new JTable();
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(tablePersona.getSelectedRow());
				btnEliminar.setEnabled(true);
				btnActualizar.setEnabled(true);
				System.out.println(clienteModelo.getPersonaAt(tableCliente.getSelectedRow()));

			}
		});
		scrollPane.setViewportView(tableCliente);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clienteRepositorio.delete(clienteModelo.getPersonaAt(tableCliente.getSelectedRow()));
				generarTabla();
				btnEliminar.setEnabled(false);
				btnActualizar.setEnabled(false);
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(117, 211, 89, 41);
		contentPane.add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clienteSeleccionada = clienteModelo.getPersonaAt(tableCliente.getSelectedRow());
				txtNombre.setText(clienteSeleccionada.getNombre());
				txtApellido.setText(clienteSeleccionada.getApellido());
				txtCedula.setText(clienteSeleccionada.getCedula());
				txtDireccion.setText(clienteSeleccionada.getDireccion());
				txtTelefono.setText(clienteSeleccionada.getTelefono());
				txtCedula.setEnabled(false);
				bandera = false;

				btnEliminar.setEnabled(false);
				btnActualizar.setEnabled(false);

			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(216, 212, 89, 40);
		contentPane.add(btnActualizar);

		cBOpcion = new JComboBox();
		cBOpcion.setModel(new DefaultComboBoxModel(new String[] { "", "Nombre", "Apellido", "Cédula" }));
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
				String opcion = cBOpcion.getSelectedItem().toString();
				String criterio = txtCriterio.getText();
				List<Cliente> resultadoBusqueda = new ArrayList<>();
				if (opcion.equals("Nombre")) {
					/// Tengo que tomar todas las personas que tengan el nombre por el criterio de
					/// busqueda
					resultadoBusqueda = clienteRepositorio.buscarPorNombre("%" + criterio + "%");
					generarTablaBusqueda(resultadoBusqueda);
				}
				if (opcion.equals("Apellido")) {
					resultadoBusqueda = clienteRepositorio.findByApellidoLike("%" + criterio + "%");
					generarTablaBusqueda(resultadoBusqueda);

				}
				if (opcion.equals("Cédula")) {
					resultadoBusqueda = clienteRepositorio.findByCedulaLike("%" + criterio + "%");
					generarTablaBusqueda(resultadoBusqueda);
				}
			}
		});
		btnBuscar.setBounds(687, 37, 79, 34);
		contentPane.add(btnBuscar);

		JLabel lblNewLabel_1_3_1 = new JLabel("Teléfono:");
		lblNewLabel_1_3_1.setBounds(46, 181, 60, 21);
		contentPane.add(lblNewLabel_1_3_1);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(131, 181, 154, 20);
		contentPane.add(txtTelefono);
	}

	public void interfazPersona() {

	}

	public void limpiarVentana() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCedula.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");

	}

	@PostConstruct
	public void generarTabla() {

		clientes = clienteRepositorio.findAll();

		clienteModelo = new ClienteItemModel(clientes);
		tableCliente.setModel(clienteModelo);

	}

	public void generarTablaBusqueda(List<Cliente> personas) {
		clienteModelo = new ClienteItemModel(personas);
		tableCliente.setModel(clienteModelo);
	}
}
