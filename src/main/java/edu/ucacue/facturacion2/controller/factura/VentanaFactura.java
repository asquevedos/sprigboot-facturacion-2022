package edu.ucacue.facturacion2.controller.factura;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.controller.cliente.ClienteItemModel;
import edu.ucacue.facturacion2.controller.cliente.DialogBuscarCliente;
import edu.ucacue.facturacion2.controller.empresa.DialogBuscarEmpresa;
import edu.ucacue.facturacion2.infraestructura.repositorio.CabeceraFacturaRepository;
import edu.ucacue.facturacion2.infraestructura.repositorio.ProductoRepository;
import edu.ucacue.facturacion2.modelo.CabeceraFactura;
import edu.ucacue.facturacion2.modelo.Cliente;
import edu.ucacue.facturacion2.modelo.DetalleFactura;
import edu.ucacue.facturacion2.modelo.Empresa;
import edu.ucacue.facturacion2.modelo.Producto;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@Controller
public class VentanaFactura extends JInternalFrame {
	JTextArea txtAreaNombre;
	JTextArea txtAreaApellido;
	JTextArea txtAreaCedula;
	JTextArea txtAreaDireccion;
	JTextArea txtAreaTelefono;

	JTextArea txtAreaNombreEmp;
	JTextArea txtAreaDireccionEmp;
	JTextArea txtAreaRUCEmp;
	JTextArea txtAreaRazonSocialEmp;
	JTextArea txtAreaTelefonoEmp;
	JPopupMenu popupMenu;
	JMenuItem deleteItem;
	JButton btnEliminar;
	JLabel lblSubTotal;
	JLabel lblIva;
	JLabel lblTotal;
	private JLabel lblTotal1;
	JLabel lblIVA;

	DetalleFacturaItemModel detalleFacturaModelo;
	private JTable tableDetalleFactura;
	List<DetalleFactura> detallesFactura;

	@Autowired
	DialogBuscarCliente buscarCliente;
	@Autowired
	DialogBuscarEmpresa buscarEmpresa;

	@Autowired
	ProductoRepository productoRepositorio;
	
	@Autowired
	CabeceraFacturaRepository cFRepository;

	Cliente clienteSeleccionada;
	Empresa empresaSeleccionada;
	public CabeceraFactura cabeceraFactura;
	private JTextField txtIdProducto;

	public VentanaFactura() {

		setTitle("Facturación");
		setBackground(Color.LIGHT_GRAY);

		detallesFactura = new ArrayList<>();

		cabeceraFactura = new CabeceraFactura();
		setBounds(90, 10, 815, 520);
		getContentPane().setLayout(null);

		this.setResizable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setIconifiable(true);

		JLabel lblNewLabel = new JLabel("Seleccionar Cliente:");
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(34, 198, 152, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Seleccionar Empresa gestora:");
		lblNewLabel_1.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setBounds(34, 35, 213, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Id del producto:");
		lblNewLabel_2.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(10, 289, 144, 13);
		getContentPane().add(lblNewLabel_2);

		JButton btnBuscarPersona = new JButton("Buscar");
		btnBuscarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buscarCliente.setVisible(true);
				
				buscarCliente.generarTabla();

				buscarCliente.okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						clienteSeleccionada = buscarCliente.personaSeleccionada();
						cabeceraFactura.setCliente(clienteSeleccionada);
						llenarDatosCliente();
						//buscarCliente.setVisible(false);
						buscarCliente.dispatchEvent(new WindowEvent(buscarCliente, WindowEvent.WINDOW_CLOSING));
					}
				});

			}
		});
		btnBuscarPersona.setFont(new Font("Courier New", Font.BOLD, 12));
		btnBuscarPersona.setBounds(196, 186, 117, 26);
		getContentPane().add(btnBuscarPersona);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 175, 783, 13);
		getContentPane().add(separator);

		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 228, 91, 13);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Apellido");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_4.setBounds(408, 228, 91, 13);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Cédula");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_5.setBounds(317, 191, 91, 13);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Dirección");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 257, 106, 13);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Teléfono");
		lblNewLabel_7.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(408, 261, 88, 13);
		getContentPane().add(lblNewLabel_7);

		JButton btnBuscarEmpresa = new JButton("Buscar");
		btnBuscarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buscarEmpresa.setVisible(true);

				buscarEmpresa.okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						empresaSeleccionada = buscarEmpresa.empresaSeleccionada();
						cabeceraFactura.setEmpresa(empresaSeleccionada);
						llenarDatosEmpresa();
						buscarEmpresa.dispatchEvent(new WindowEvent(buscarEmpresa, WindowEvent.WINDOW_CLOSING));
					}
				});

			}
		});
		btnBuscarEmpresa.setFont(new Font("Courier New", Font.BOLD, 12));
		btnBuscarEmpresa.setBounds(234, 24, 100, 35);
		getContentPane().add(btnBuscarEmpresa);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 0, 783, 14);
		getContentPane().add(separator_1);

		JLabel lblNewLabel_8 = new JLabel("Nombre");
		lblNewLabel_8.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_8.setBounds(34, 86, 69, 13);
		getContentPane().add(lblNewLabel_8);

		JLabel lblNewLabel_8_1 = new JLabel("Dirección");
		lblNewLabel_8_1.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_8_1.setBounds(34, 116, 69, 13);
		getContentPane().add(lblNewLabel_8_1);

		JLabel lblNewLabel_8_2 = new JLabel("RUC");
		lblNewLabel_8_2.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_8_2.setBounds(32, 140, 69, 13);
		getContentPane().add(lblNewLabel_8_2);

		JLabel lblNewLabel_8_2_1 = new JLabel("Razón Social");
		lblNewLabel_8_2_1.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_8_2_1.setBounds(408, 147, 91, 13);
		getContentPane().add(lblNewLabel_8_2_1);

		JLabel lblNewLabel_8_2_2 = new JLabel("Teléfono");
		lblNewLabel_8_2_2.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_8_2_2.setBounds(408, 116, 69, 13);
		getContentPane().add(lblNewLabel_8_2_2);

		txtAreaNombreEmp = new JTextArea();
		txtAreaNombreEmp.setEnabled(false);
		txtAreaNombreEmp.setEditable(false);
		txtAreaNombreEmp.setBounds(126, 80, 343, 22);
		getContentPane().add(txtAreaNombreEmp);

		txtAreaDireccionEmp = new JTextArea();
		txtAreaDireccionEmp.setEnabled(false);
		txtAreaDireccionEmp.setEditable(false);
		txtAreaDireccionEmp.setBounds(126, 110, 272, 22);
		getContentPane().add(txtAreaDireccionEmp);

		txtAreaRUCEmp = new JTextArea();
		txtAreaRUCEmp.setEnabled(false);
		txtAreaRUCEmp.setEditable(false);
		txtAreaRUCEmp.setBounds(126, 140, 272, 22);
		getContentPane().add(txtAreaRUCEmp);

		txtAreaRazonSocialEmp = new JTextArea();
		txtAreaRazonSocialEmp.setEnabled(false);
		txtAreaRazonSocialEmp.setEditable(false);
		txtAreaRazonSocialEmp.setBounds(509, 140, 196, 22);
		getContentPane().add(txtAreaRazonSocialEmp);

		txtAreaTelefonoEmp = new JTextArea();
		txtAreaTelefonoEmp.setEnabled(false);
		txtAreaTelefonoEmp.setEditable(false);
		txtAreaTelefonoEmp.setBounds(509, 109, 196, 22);
		getContentPane().add(txtAreaTelefonoEmp);

		txtAreaNombre = new JTextArea();
		txtAreaNombre.setEnabled(false);
		txtAreaNombre.setEditable(false);
		txtAreaNombre.setBounds(126, 223, 272, 22);
		getContentPane().add(txtAreaNombre);

		txtAreaApellido = new JTextArea();
		txtAreaApellido.setEnabled(false);
		txtAreaApellido.setEditable(false);
		txtAreaApellido.setBounds(487, 219, 196, 22);
		getContentPane().add(txtAreaApellido);

		txtAreaCedula = new JTextArea();
		txtAreaCedula.setEnabled(false);
		txtAreaCedula.setEditable(false);
		txtAreaCedula.setBounds(433, 186, 272, 22);
		getContentPane().add(txtAreaCedula);

		txtAreaDireccion = new JTextArea();
		txtAreaDireccion.setEnabled(false);
		txtAreaDireccion.setEditable(false);
		txtAreaDireccion.setBounds(126, 252, 272, 22);
		getContentPane().add(txtAreaDireccion);

		txtAreaTelefono = new JTextArea();
		txtAreaTelefono.setEnabled(false);
		txtAreaTelefono.setEditable(false);
		txtAreaTelefono.setBounds(487, 252, 196, 22);
		getContentPane().add(txtAreaTelefono);

		JLabel lblNewLabel_10 = new JLabel("FACTURA ELECTRÓNICA");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(529, 54, 190, 13);
		getContentPane().add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Nº folio no asignado");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(509, 77, 223, 13);
		getContentPane().add(lblNewLabel_11);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(204, 51, 51), 2));
		panel.setBounds(509, 13, 223, 86);
		getContentPane().add(panel);

		JLabel lblNewLabel_9 = new JLabel("Rut 44300251-0\r\n\r\n");
		panel.add(lblNewLabel_9);
		lblNewLabel_9.setForeground(new Color(204, 51, 51));
		lblNewLabel_9.setFont(new Font("Courier New", Font.BOLD, 15));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setToolTipText("");

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 281, 783, 3);
		getContentPane().add(separator_2);

		txtIdProducto = new JTextField();
		txtIdProducto.setBounds(164, 285, 40, 20);
		getContentPane().add(txtIdProducto);
		txtIdProducto.setColumns(10);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetalleFactura detalleFactura = new DetalleFactura();
				int idProducto = Integer.parseInt(txtIdProducto.getText());
				Producto producto = productoRepositorio.findById(idProducto).get();
				detalleFactura.setProducto(producto);
				detalleFactura.setCantidad(1);
				detalleFactura.setCabeceraFactura(cabeceraFactura);
				detalleFactura.calcularTotal();
				detallesFactura.add(detalleFactura);
				cabeceraFactura.setDetallesFacturas(detallesFactura);
				cabeceraFactura.calcularTotal();
				llenarDatosDetallFactura();

			}
		});
		btnNewButton.setBounds(210, 285, 81, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setBounds(291, 285, 81, 23);
		getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 317, 699, 111);
		getContentPane().add(scrollPane);
		


		tableDetalleFactura = new JTable();
		tableDetalleFactura.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cabeceraFactura.calcularTotal();
				llenarDatosDetallFactura();
			}
		});

		scrollPane.setViewportView(tableDetalleFactura);
		tableDetalleFactura.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				btnEliminar.setEnabled(true);
			}
			
		});
		
		detalleFacturaModelo = new DetalleFacturaItemModel(detallesFactura);
		

		tableDetalleFactura.setModel(detalleFacturaModelo);

		

		


		JLabel lblNewLabel_12 = new JLabel("I.V.A:");
		lblNewLabel_12.setBounds(564, 451, 62, 14);

		getContentPane().add(lblNewLabel_12);

		JLabel lblNewLabel_12_1 = new JLabel("Sub Total:");
		lblNewLabel_12_1.setBounds(564, 439, 62, 14);
		getContentPane().add(lblNewLabel_12_1);

		lblTotal1 = new JLabel("Total:");
		lblTotal1.setBounds(564, 464, 62, 14);
		getContentPane().add(lblTotal1);

		lblSubTotal = new JLabel("");
		lblSubTotal.setBounds(641, 439, 76, 14);
		getContentPane().add(lblSubTotal);

		lblTotal = new JLabel("");
		lblTotal.setBounds(641, 464, 73, 14);
		getContentPane().add(lblTotal);

		btnEliminar = new JButton("X");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detalleFacturaModelo.removeRow(tableDetalleFactura.getSelectedRow());
				cabeceraFactura.calcularTotal();
				llenarDatosDetallFactura();

			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(30, 435, 49, 23);
		getContentPane().add(btnEliminar);

		lblIVA = new JLabel("");
		lblIVA.setBounds(643, 451, 76, 14);
		getContentPane().add(lblIVA);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cabeceraFactura.setFechaCompra(new Date());
				cabeceraFactura.calcularTotal();
				cFRepository.save(cabeceraFactura);
				limpiarVentana();
			}
		});
		btnGuardar.setBounds(283, 439, 89, 23);
		getContentPane().add(btnGuardar);
		


	}

	public void llenarDatosCliente() {

		txtAreaNombre.setText(clienteSeleccionada.getNombre());
		txtAreaApellido.setText(clienteSeleccionada.getApellido());
		txtAreaCedula.setText(clienteSeleccionada.getCedula());
		txtAreaDireccion.setText(clienteSeleccionada.getDireccion());
		txtAreaTelefono.setText(clienteSeleccionada.getTelefono());
	}

	public void llenarDatosEmpresa() {

		txtAreaNombreEmp.setText(empresaSeleccionada.getNombre());
		txtAreaDireccionEmp.setText(empresaSeleccionada.getDireccion());
		txtAreaRUCEmp.setText(empresaSeleccionada.getRuc());
		txtAreaRazonSocialEmp.setText(empresaSeleccionada.getRazonSocial());
		txtAreaTelefonoEmp.setText(empresaSeleccionada.getTelefono());
	}

	public void llenarDatosDetallFactura() {
		detalleFacturaModelo = new DetalleFacturaItemModel(detallesFactura);
		tableDetalleFactura.setModel(detalleFacturaModelo);

		lblSubTotal.setText("$ " +  cabeceraFactura.getSubTotal() + "");
		lblIVA.setText("$ " + cabeceraFactura.getIva() + "");
		lblTotal.setText("$ " + cabeceraFactura.getTotal() + "");

	}
	
	public void limpiarVentana() {
		// datos clientes
		txtAreaNombre.setText("");
		txtAreaApellido.setText("");
		txtAreaCedula.setText("");
		txtAreaDireccion.setText("");
		txtAreaNombre.setText("");
		txtAreaTelefono.setText("");
		
		// datos Empresa
		txtAreaTelefonoEmp.setText("");
		txtAreaRazonSocialEmp.setText("");
		txtAreaNombreEmp.setText("");
		txtAreaRUCEmp.setText("");
		txtAreaDireccionEmp.setText("");
		txtAreaDireccionEmp.setText("");
		
		//Tabla
		
		detallesFactura = new ArrayList<>();
		llenarDatosDetallFactura();
	}
}
