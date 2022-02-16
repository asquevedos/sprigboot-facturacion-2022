package edu.ucacue.facturacion2.controller;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.facturacion2.controller.cliente.VentanaCliente;
import edu.ucacue.facturacion2.controller.factura.VentanaFactura;
import edu.ucacue.facturacion2.controller.producto.VentanaProducto;
import edu.ucacue.facturacion2.infraestructura.servicios.ClienteReporteService;
import net.sf.jasperreports.engine.JRException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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

	@Autowired
	ClienteReporteService clienteReporteService;

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
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clienteReporteService.generarReporte("html");
				} catch (JRException jre) {
					// TODO: handle exception
				} catch (FileNotFoundException fnfe) {
					// TODO: handle exception
				}

			}
		});

		JMenu mnNewMenu_4 = new JMenu("Reporte");
		mnNewMenu.add(mnNewMenu_4);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("pdf");
		mnNewMenu_4.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("html");
		mnNewMenu_4.add(mntmNewMenuItem_5);
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
				ventanaFactura.limpiarVentana();
				desktopPane.add(ventanaFactura);

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		contentPane = new JPanel();

		// ventanaFactura.

		desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane);
	}
}
