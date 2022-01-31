package edu.ucacue.facturacion2.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_facturas")
public class DetalleFactura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalleFactura;

	private int cantidad;
	private double total;

	@ManyToOne
	@JoinColumn(name = "producto_fk")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "cabecera_factura_fk")
	private CabeceraFactura cabeceraFactura;

	public DetalleFactura() {

	}

	public DetalleFactura(int id, int cantidad, double total, Producto producto, CabeceraFactura cabeceraFactura) {
		super();
		this.idDetalleFactura = id;
		this.cantidad = cantidad;
		this.total = total;
		this.producto = producto;
		this.cabeceraFactura = cabeceraFactura;
	}

	public int getIdDetalleFactura() {
		return idDetalleFactura;
	}

	public void setIdDetalleFactura(int idDetalleFactura) {
		this.idDetalleFactura = idDetalleFactura;
	}



	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public CabeceraFactura getCabeceraFactura() {
		return cabeceraFactura;
	}

	public void setCabeceraFactura(CabeceraFactura cabeceraFactura) {
		this.cabeceraFactura = cabeceraFactura;
	}

	public double calcularTotal() {
		double total = 0;
		total = this.producto.getPrecioUnitario() * this.cantidad;
		this.total = total;
		return total;

	}

}
