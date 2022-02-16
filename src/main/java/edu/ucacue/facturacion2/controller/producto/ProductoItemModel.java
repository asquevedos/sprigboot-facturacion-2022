package edu.ucacue.facturacion2.controller.producto;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.ucacue.facturacion2.modelo.Producto;

public class ProductoItemModel extends AbstractTableModel {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private List<Producto> productos;
	private static final String[] COLUMN_NAMES = { "Nombre", "Precio Unitario", "Existencias" };

	public ProductoItemModel(List<Producto> productos) {

		this.productos = productos;

	}

	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Producto producto = productos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = producto.getNombre();
			break;
		case 1:
			value = producto.getPrecioUnitario();
			break;
		case 2:
			value = producto.getCantidad();
			break;

		}

		return value;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Producto.class;
	}

	// the column header
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	/*
	 * Override this if you want the values to be editable...
	 * 
	 * @Override public void setValueAt(Object aValue, int rowIndex, int
	 * columnIndex) { //.... }
	 */

	/**
	 * This will return the user at the specified row...
	 * 
	 * @param row
	 * @return
	 */

	public Producto getPersonaAt(int row) {
		return productos.get(row);
	}

}
