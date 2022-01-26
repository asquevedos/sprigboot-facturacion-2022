package edu.ucacue.facturacion2.controller;



import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.controller.factura.VentanaFactura;
import edu.ucacue.facturacion2.controller.persona.VentanaPersona;
import edu.ucacue.facturacion2.infraestructura.repositorio.CabeceraFacturaRepository;
import edu.ucacue.facturacion2.infraestructura.repositorio.ClienteRepository;
import edu.ucacue.facturacion2.infraestructura.repositorio.EmpresaRepository;
import edu.ucacue.facturacion2.infraestructura.repositorio.ProductoRepository;
import edu.ucacue.facturacion2.modelo.CabeceraFactura;
import edu.ucacue.facturacion2.modelo.Cliente;
import edu.ucacue.facturacion2.modelo.DetalleFactura;
import edu.ucacue.facturacion2.modelo.Empresa;
import edu.ucacue.facturacion2.modelo.Producto;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

@Controller
public class PrincipalUI extends JFrame {


	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	@Autowired
	VentanaPersona ventanaPersona;
	
	@Autowired
	CabeceraFacturaRepository cabeceraFacturaRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	VentanaFactura ventanaFactura;
	

	/**
	 * Create the frame.
	 */
	
	JDesktopPane desktopPane;
	public PrincipalUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 10, 1000, 500);
		

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Persona");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Mantenimiento");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPersona.setVisible(true);
				ventanaPersona.generarTabla();
				desktopPane.add(ventanaPersona);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Reporte");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Empresa");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Facturación");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Nueva Factura ");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaFactura.setVisible(true);
				desktopPane.add(ventanaFactura);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		contentPane = new JPanel();

		
		//ventanaFactura.
		
		desktopPane= new JDesktopPane();
		getContentPane().add(desktopPane);
	}
	
	public void insertarCFEjemplo() {
		
		
	Cliente cliente = 	clienteRepository.findById(1).get();
	Empresa empresa = empresaRepository.findById(1).get();
	
	CabeceraFactura cF = new CabeceraFactura();	
	
	cF.setCliente(cliente);
	cF.setEmpresa(empresa);
	cF.setNumeroFactura(1000);
	cF.setFechaCompra(new Date());
	
	
	//Empiezo a generar los detalles de cada Factura
	
	List<DetalleFactura> detalles = new ArrayList<>(); 
	
	DetalleFactura dF1 = new DetalleFactura();
	Producto producto1 = productoRepository.findById(1).get();
	dF1.setCantidad(8);
	dF1.setCabeceraFactura(cF);
	dF1.setProducto(producto1);
	dF1.calcularTotal();
	
	DetalleFactura dF2 = new DetalleFactura();
	Producto producto2 = productoRepository.findById(2).get();
	dF2.setCantidad(7);
	dF2.setCabeceraFactura(cF);
	dF2.setProducto(producto2);
	dF2.calcularTotal();
	

	
	detalles.add(dF1);
	detalles.add(dF2);

	
	cF.setDetallesFacturas(detalles);
	
	
	cabeceraFacturaRepository.save(cF);
	
	
	}
}
