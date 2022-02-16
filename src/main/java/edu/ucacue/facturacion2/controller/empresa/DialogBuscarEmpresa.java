package edu.ucacue.facturacion2.controller.empresa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.infraestructura.repositorio.EmpresaRepository;
import edu.ucacue.facturacion2.modelo.Empresa;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

@Controller
public class DialogBuscarEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCriterio;
	public JButton okButton;

	EmpresaItemModel empresaModelo;
	private JTable tableEmpresa;

	public Empresa empresa;
	public Empresa empresaSeleccionada;
	public List<Empresa> empresas;
	JComboBox cBCriterio = new JComboBox();
	@Autowired
	EmpresaRepository empresaRepositorio;

	public DialogBuscarEmpresa() {
		setTitle("Buscar Empresa");
		setBounds(100, 100, 563, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Buscar por:");
			lblNewLabel.setBounds(10, 11, 83, 14);
			contentPanel.add(lblNewLabel);
		}
		{

			cBCriterio.setModel(
					new DefaultComboBoxModel(new String[] { "", "Razon social", "Teléfono", "Ruc", "Dirección" }));
			cBCriterio.setBounds(95, 7, 125, 22);
			contentPanel.add(cBCriterio);
		}
		{
			txtCriterio = new JTextField();
			txtCriterio.setBounds(242, 8, 160, 20);
			contentPanel.add(txtCriterio);
			txtCriterio.setColumns(10);
		}
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String opcion = cBCriterio.getSelectedItem().toString();
					String criterio = "%" + txtCriterio.getText() + "%";
					List<Empresa> resultado = new ArrayList<>();
					if (opcion.equals("Razon social")) {
						resultado = empresaRepositorio.findByRazonSocialLike(criterio);
						generarTablaBusqueda(resultado);
					}
					if (opcion.equals("Teléfono")) {
						resultado = empresaRepositorio.findByTelefonoLike(criterio);
						generarTablaBusqueda(resultado);
					}
					if (opcion.equals("Ruc")) {
						resultado = empresaRepositorio.findByRucLike(criterio);
						generarTablaBusqueda(resultado);
					}

				}
			});
			btnBuscar.setBounds(412, 7, 89, 23);
			contentPanel.add(btnBuscar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);

			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(50, 55, 451, 162);
		contentPanel.add(scrollPane);

		tableEmpresa = new JTable();
		tableEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(tablePersona.getSelectedRow());
				// btnEliminar.setEnabled(true);
				// btnActualizar.setEnabled(true);
				// System.out.println(personaModelo.getPersonaAt(tablePersona.getSelectedRow()));

			}
		});
		scrollPane.setViewportView(tableEmpresa);
	}

	@PostConstruct
	public void generarTabla() {

		empresas = empresaRepositorio.findAll();

		empresaModelo = new EmpresaItemModel(empresas);
		tableEmpresa.setModel(empresaModelo);

	}

	public void generarTablaBusqueda(List<Empresa> empresas) {
		empresaModelo = new EmpresaItemModel(empresas);
		tableEmpresa.setModel(empresaModelo);
	}

	public Empresa empresaSeleccionada() {

		Empresa empresaSeleccionada = empresaModelo.getEmpresaAt(tableEmpresa.getSelectedRow());
		return empresaSeleccionada;
	}

}
