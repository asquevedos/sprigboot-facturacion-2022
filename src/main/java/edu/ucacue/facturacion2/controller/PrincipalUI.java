package edu.ucacue.facturacion2.controller;



import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.controller.cliente.VentanaCliente;
import edu.ucacue.facturacion2.controller.factura.VentanaFactura;
import edu.ucacue.facturacion2.controller.producto.VentanaProducto;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

@Controller
public class PrincipalUI extends JFrame {


	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	@Autowired
	VentanaCliente ventanaCliente;

	
	@Autowired
	VentanaFactura ventanaFactura;
	
	@Autowired
	VentanaProducto ventanaProducto;

	/**
	 * Create the frame.
	 */
	
	JDesktopPane desktopPane;
	public PrincipalUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 2, 1000, 600);
		

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Cliente");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Mantenimiento");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCliente.setVisible(true);
				ventanaCliente.generarTabla();
				desktopPane.add(ventanaCliente);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Reporte");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_3 = new JMenu("Producto");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Mantenimiento");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaProducto.setVisible(true);
				desktopPane.add(ventanaProducto);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
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
	
	
}
