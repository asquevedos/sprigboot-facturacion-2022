package edu.ucacue.facturacion2.controller.producto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.infraestructura.repositorio.ClienteRepositorio;
import edu.ucacue.facturacion2.infraestructura.repositorio.ProductoRepository;
import edu.ucacue.facturacion2.modelo.Cliente;
import edu.ucacue.facturacion2.modelo.Producto;

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
public class VentanaProducto extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPrecioUnitario;
	private JTextField txtCantidad;
	private JButton btnEliminar;
	private JButton btnActualizar;

	public Producto producto;
	public Producto productoSeleccionada;
	public List<Producto> productos;

	@Autowired
	ProductoRepository productoRepositorio;

	// Interfaz gráfica con tabla
	ProductoItemModel productoModelo;
	private JTable tableProducto;

	public boolean bandera = true;
	private JComboBox cBOpcion;
	private JTextField txtCriterio;
	private JLabel lblNewLabel_2;
	private JButton btnBuscar;

	public VentanaProducto() {
		setBackground(new Color(240, 240, 240));

		this.setResizable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		interfazPersona();
		productos = new ArrayList<Producto>();
		setBounds(10, 10, 850, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mantenimiento de Productos");
		lblNewLabel.setBounds(5, 5, 424, 21);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 17));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(33, 56, 60, 21);
		contentPane.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setBounds(131, 56, 154, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Precio Unitario:");
		lblNewLabel_1_1.setBounds(33, 88, 96, 21);
		contentPane.add(lblNewLabel_1_1);

		txtPrecioUnitario = new JTextField();
		txtPrecioUnitario.setColumns(10);
		txtPrecioUnitario.setBounds(131, 87, 154, 20);
		contentPane.add(txtPrecioUnitario);

		JLabel lblNewLabel_1_2 = new JLabel("Cantidad:");
		lblNewLabel_1_2.setBounds(33, 120, 73, 21);
		contentPane.add(lblNewLabel_1_2);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(131, 120, 154, 20);
		contentPane.add(txtCantidad);

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
					producto = new Producto();
					producto.setNombre(txtNombre.getText());
					producto.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText()));
					producto.setCantidad(Integer.parseInt(txtCantidad.getText()));


					productoRepositorio.save(producto);
				} else {
					producto = new Producto();
					producto.setId(productoSeleccionada.getId());
					producto.setNombre(txtNombre.getText());
					producto.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText()));
					producto.setCantidad(Integer.parseInt(txtCantidad.getText()));
				
					productoRepositorio.save(producto);
					bandera = true;
					txtCantidad.setEnabled(true);
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

		tableProducto = new JTable();
		tableProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(tablePersona.getSelectedRow());
				btnEliminar.setEnabled(true);
				btnActualizar.setEnabled(true);
				System.out.println(productoModelo.getPersonaAt(tableProducto.getSelectedRow()));

			}
		});
		scrollPane.setViewportView(tableProducto);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				productoRepositorio.delete(productoModelo.getPersonaAt(tableProducto.getSelectedRow()));
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
				productoSeleccionada = productoModelo.getPersonaAt(tableProducto.getSelectedRow());
				txtNombre.setText(productoSeleccionada.getNombre());
				txtPrecioUnitario.setText(productoSeleccionada.getPrecioUnitario()+"");
				txtCantidad.setText(productoSeleccionada.getCantidad()+"");

				txtCantidad.setEnabled(false);
				bandera = false;

				btnEliminar.setEnabled(false);
				btnActualizar.setEnabled(false);

			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(215, 181, 89, 40);
		contentPane.add(btnActualizar);
		
		cBOpcion = new JComboBox();
		cBOpcion.setModel(new DefaultComboBoxModel(new String[] {"", "Nombre"}));
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
				List<Producto> resultadoBusqueda = new ArrayList<>();
				if(opcion.equals("Nombre"))
				{
					/// Tengo que tomar todas las personas que tengan el nombre por el criterio de busqueda
					resultadoBusqueda = productoRepositorio.buscarPorNombre("%"+criterio+"%");
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
		txtPrecioUnitario.setText("");
		txtCantidad.setText("");
	}

	@PostConstruct
	public void generarTabla() {

		productos = productoRepositorio.findAll();

		productoModelo = new ProductoItemModel(productos);
		tableProducto.setModel(productoModelo);

	}
	
	public void generarTablaBusqueda(List<Producto> productos)
	{
		productoModelo = new ProductoItemModel(productos);
		tableProducto.setModel(productoModelo);
	}

}
