package edu.ucacue.facturacion2.controller.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.infraestructura.repositorio.ClienteRepositorio;
import edu.ucacue.facturacion2.modelo.Cliente;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Controller
public class DialogBuscarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCriterio;
	public JButton okButton;

	ClienteItemModel personaModelo;
	private JTable tablePersona;

	public Cliente persona;
	public Cliente personaSeleccionada;
	public List<Cliente> personas;

	@Autowired
	ClienteRepositorio personaRepositorio;

	public DialogBuscarCliente() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Buscar por:");
			lblNewLabel.setBounds(10, 11, 66, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JComboBox cBCriterio = new JComboBox();
			cBCriterio.setModel(new DefaultComboBoxModel(new String[] { "Nombre", "Apellido", "Cédula" }));
			cBCriterio.setBounds(72, 7, 72, 22);
			contentPanel.add(cBCriterio);
		}
		{
			txtCriterio = new JTextField();
			txtCriterio.setBounds(154, 8, 160, 20);
			contentPanel.add(txtCriterio);
			txtCriterio.setColumns(10);
		}
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(324, 7, 89, 23);
			contentPanel.add(btnBuscar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
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

		tablePersona = new JTable();
		tablePersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(tablePersona.getSelectedRow());
				// btnEliminar.setEnabled(true);
				// btnActualizar.setEnabled(true);
				// System.out.println(personaModelo.getPersonaAt(tablePersona.getSelectedRow()));

			}
		});
		scrollPane.setViewportView(tablePersona);
	}

	@PostConstruct
	public void generarTabla() {

		personas = personaRepositorio.findAll();

		personaModelo = new ClienteItemModel(personas);
		tablePersona.setModel(personaModelo);

	}

	public void generarTablaBusqueda(List<Cliente> personas) {
		personaModelo = new ClienteItemModel(personas);
		tablePersona.setModel(personaModelo);
	}

	public Cliente personaSeleccionada() {

		Cliente personaSeleccionada = personaModelo.getPersonaAt(tablePersona.getSelectedRow());
		return personaSeleccionada;
	}

}
