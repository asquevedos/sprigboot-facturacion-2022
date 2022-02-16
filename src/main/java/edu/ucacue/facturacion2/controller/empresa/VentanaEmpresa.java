package edu.ucacue.facturacion2.controller.empresa;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.infraestructura.repositorio.EmpresaRepository;
import edu.ucacue.facturacion2.modelo.Empresa;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@Controller
public class VentanaEmpresa extends JInternalFrame {
	private JPanel contentPane;
	private JTextField txtRazonSocial;
	private JTextField txtTelefono;
	private JTextField txtRuc;
	private JTextField txtDireccion;
	private JButton btnEliminar;
	private JButton btnActualizar;

	public Empresa empresa;
	public Empresa empresaSeleccionada;
	public List<Empresa> empresas;

	@Autowired
	EmpresaRepository empresaRepositorio;

	// Interfaz gráfica con tabla
	EmpresaItemModel empresaModelo;
	private JTable tableEmpresa;

	public boolean bandera = true;
	private JTextField txtBusqueda;

	public VentanaEmpresa() {
		setTitle("Mantenimiento de Personas");
		setClosable(true);

		this.setResizable(true);
		this.setMaximizable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		interfazPersona();
		empresas = new ArrayList<Empresa>();
		setBounds(100, 100, 800, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mantenimiento de Empresas");
		lblNewLabel.setBounds(10, 11, 760, 21);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 17));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Razon social:");
		lblNewLabel_1.setBounds(10, 56, 96, 21);
		contentPane.add(lblNewLabel_1);

		txtRazonSocial = new JTextField();
		txtRazonSocial.setBounds(131, 56, 154, 20);
		contentPane.add(txtRazonSocial);
		txtRazonSocial.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Teléfono:");
		lblNewLabel_1_1.setBounds(10, 88, 96, 21);
		contentPane.add(lblNewLabel_1_1);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(131, 87, 154, 20);
		contentPane.add(txtTelefono);

		JLabel lblNewLabel_1_2 = new JLabel("RUC:");
		lblNewLabel_1_2.setBounds(10, 120, 96, 21);
		contentPane.add(lblNewLabel_1_2);

		txtRuc = new JTextField();
		txtRuc.setColumns(10);
		txtRuc.setBounds(131, 120, 154, 20);
		contentPane.add(txtRuc);

		JLabel lblNewLabel_1_3 = new JLabel("Dirección:");
		lblNewLabel_1_3.setBounds(10, 149, 96, 21);
		contentPane.add(lblNewLabel_1_3);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(131, 149, 154, 20);
		contentPane.add(txtDireccion);

		JButton btnGrabarPersona = new JButton("Grabar");

		btnGrabarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// persona = new Persona(txtNombre.getText(), txtApellido.getText(),
				// txtCedula.getText(), txtDireccion.getText() );
				if (bandera == true) {
					empresa = new Empresa();
					empresa.setRazonSocial(txtRazonSocial.getText());
					empresa.setTelefono(txtTelefono.getText());
					empresa.setRuc(txtRuc.getText());
					empresa.setDireccion(txtDireccion.getText());

					empresaRepositorio.save(empresa);
				} else {
					empresa = new Empresa();
					empresa.setId(empresaSeleccionada.getId());
					empresa.setRazonSocial(txtRazonSocial.getText());
					empresa.setTelefono(txtTelefono.getText());
					empresa.setRuc(txtRuc.getText());
					empresa.setDireccion(txtDireccion.getText());

					empresaRepositorio.save(empresa);
					bandera = true;
					txtRuc.setEnabled(true);
				}

				limpiarVentana();
				generarTabla();

			}
		});
		btnGrabarPersona.setBounds(10, 181, 96, 40);
		contentPane.add(btnGrabarPersona);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(130, 180, 640, 160);
		contentPane.add(scrollPane);

		tableEmpresa = new JTable();
		tableEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(tablePersona.getSelectedRow());
				btnEliminar.setEnabled(true);
				btnActualizar.setEnabled(true);
				System.out.println(empresaModelo.getEmpresaAt(tableEmpresa.getSelectedRow()));

			}
		});
		scrollPane.setViewportView(tableEmpresa);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				empresaRepositorio.delete(empresaModelo.getEmpresaAt(tableEmpresa.getSelectedRow()));
				generarTabla();
				btnEliminar.setEnabled(false);
				btnActualizar.setEnabled(false);
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(10, 232, 96, 41);
		contentPane.add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empresaSeleccionada = empresaModelo.getEmpresaAt(tableEmpresa.getSelectedRow());
				txtRazonSocial.setText(empresaSeleccionada.getRazonSocial());
				txtTelefono.setText(empresaSeleccionada.getTelefono());
				txtRuc.setText(empresaSeleccionada.getRuc());
				txtDireccion.setText(empresaSeleccionada.getDireccion());
				txtRuc.setEnabled(false);
				bandera = false;

				btnEliminar.setEnabled(false);
				btnActualizar.setEnabled(false);

			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(10, 284, 96, 40);
		contentPane.add(btnActualizar);

		JLabel lblNewLabel_2 = new JLabel("Buscar");
		lblNewLabel_2.setBounds(342, 60, 89, 14);
		contentPane.add(lblNewLabel_2);

		JComboBox cbCriteriodeBusqueda = new JComboBox();
		cbCriteriodeBusqueda.setModel(
				new DefaultComboBoxModel(new String[] { "", "Razon social", "Teléfono", "Ruc", "Dirección" }));
		cbCriteriodeBusqueda.setBounds(441, 56, 154, 22);
		contentPane.add(cbCriteriodeBusqueda);

		JButton btnBuscar = new JButton("Buscar");

		btnBuscar.setBounds(681, 101, 89, 35);
		contentPane.add(btnBuscar);

		txtBusqueda = new JTextField();
		txtBusqueda.setColumns(10);
		txtBusqueda.setBounds(342, 94, 304, 48);
		contentPane.add(txtBusqueda);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion = cbCriteriodeBusqueda.getSelectedItem().toString();
				String criterio = "%" + txtBusqueda.getText() + "%";
				List<Empresa> resultado = new ArrayList<>();
				if (opcion.equals("Razon social")) {
					resultado = empresaRepositorio.findByRazonSocialLike(criterio);
					generarTablaPorBusqueda(resultado);
				}
				if (opcion.equals("Teléfono")) {
					resultado = empresaRepositorio.findByTelefonoLike(criterio);
					generarTablaPorBusqueda(resultado);
				}
				if (opcion.equals("Ruc")) {
					resultado = empresaRepositorio.findByRucLike(criterio);
					generarTablaPorBusqueda(resultado);
				}

			}

		});
	}

	public void interfazPersona() {

	}

	public void limpiarVentana() {
		txtRazonSocial.setText("");
		txtTelefono.setText("");
		txtRuc.setText("");
		txtDireccion.setText("");
	}

	public void generarTablaPorBusqueda(List<Empresa> empresas) {
		empresaModelo = new EmpresaItemModel(empresas);
		tableEmpresa.setModel(empresaModelo);
	}

	@PostConstruct
	public void generarTabla() {
		List<Empresa> empresas = empresaRepositorio.findAll();
		empresaModelo = new EmpresaItemModel(empresas);
		tableEmpresa.setModel(empresaModelo);

	}
}
