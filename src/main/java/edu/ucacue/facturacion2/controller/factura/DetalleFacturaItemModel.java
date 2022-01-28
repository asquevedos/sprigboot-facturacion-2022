package edu.ucacue.facturacion2.controller.factura;


import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.table.AbstractTableModel;

import edu.ucacue.facturacion2.modelo.CabeceraFactura;
import edu.ucacue.facturacion2.modelo.DetalleFactura;
import edu.ucacue.facturacion2.modelo.Producto;


public class DetalleFacturaItemModel extends AbstractTableModel {
	
	
	List<DetalleFactura> detallesFactura;
	
	private static final String[] COLUMN_NAMES = {"Producto","Pre. Uni.", "Cantidad", "Total"};
    private final Class[] columnClass = new Class[] {
            Producto.class, Double.class, Integer.class, Double.class
        };

	public DetalleFacturaItemModel(List<DetalleFactura> detallesFactura) {

		this.detallesFactura = detallesFactura;

	}

	@Override
	public int getRowCount() {
		return detallesFactura.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		DetalleFactura detalleFactura = detallesFactura.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = detalleFactura.getProducto().getNombre();
			break;
		case 1:
			value = detalleFactura.getProducto().getPrecioUnitario();
			break;
		case 2:
			value = detalleFactura.getCantidad();
			break;
		case 3:
			value = detalleFactura.calcularTotal();
			break;

		}

		return value;

	}

	@Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
	
    //the column header
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
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
    
	public DetalleFactura getDetalleFacturaAt(int row) {
		return detallesFactura.get(row);
	}


}
