package edu.ucacue.facturacion2.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "cabecera_facturas")
public class CabeceraFactura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// @Column(columnDefinition = "integer auto_increment")
	// Cambiar esto pasa Mysql
	@Generated(GenerationTime.INSERT)
	@Column(columnDefinition = "serial ")
	private int numeroFactura;

	@ManyToOne
	@JoinColumn(name = "cliente_fk")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "empresa_fk")
	private Empresa empresa;

	private double subTotal;
	private double iva;
	private double total;

	private Date fechaCompra;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleFactura")
	private List<DetalleFactura> detallesFacturas;

	@Column(columnDefinition = "boolean default true")
	private boolean estado;
	private Date fechaCancelacion;

	public CabeceraFactura() {
		this.detallesFacturas = new ArrayList<>();
		this.estado = true;
	}

	public CabeceraFactura(int id, int numeroFactura, Cliente cliente, Empresa empresa, Date fechaCompra,
			List<DetalleFactura> detallesFacturas) {
		super();
		detallesFacturas = new ArrayList<DetalleFactura>();
		this.id = id;
		this.numeroFactura = numeroFactura;
		this.cliente = cliente;
		this.empresa = empresa;
		this.fechaCompra = fechaCompra;
		this.detallesFacturas = detallesFacturas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public List<DetalleFactura> getDetallesFacturas() {
		return detallesFacturas;
	}

	public void setDetallesFacturas(List<DetalleFactura> detallesFacturas) {
		this.detallesFacturas = detallesFacturas;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
		if (this.estado == false) {
			setFechaCancelacion(new Date());
		} else {
			setFechaCancelacion(null);
		}
	}

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public void calcularTotal() {
		double subTot = 0;
		for (DetalleFactura detalles : this.detallesFacturas) {
			subTot = subTot + detalles.getTotal();
		}
		this.subTotal = subTot;
		this.iva = subTot * 0.12;
		this.total = subTot + iva;
	}

}
