package edu.ucacue.facturacion2.controller.factura;


import java.util.List;



import javax.swing.table.AbstractTableModel;
import edu.ucacue.facturacion2.modelo.DetalleFactura;



public class DetalleFacturaItemModel extends AbstractTableModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<DetalleFactura> detallesFactura;
	private static final String[] COLUMN_NAMES = {"Producto","Pre. Uni.", "Cantidad", "Total"};
    private final Class<?>[] columnClass = new Class<?>[] {
            String.class, Double.class, Integer.class, Double.class
        };

	public DetalleFacturaItemModel(List<DetalleFactura> detallesFactura) {

		this.detallesFactura = detallesFactura;

	}
	
    //the column header
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
	@Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return detallesFactura.size();
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
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
       DetalleFactura row = detallesFactura.get(rowIndex);
        if(2 == columnIndex) {
            row.setCantidad((Integer) aValue);
            row.calcularTotal();
            this.fireTableCellUpdated(rowIndex, 3);
        }

    }
    
	public DetalleFactura getDetalleFacturaAt(int row) {
		return detallesFactura.get(row);
	}
	
	public void removeRow(int row) {
	    // remove a row from your internal data structure
		detallesFactura.remove(row);
	    fireTableRowsDeleted(row, row);
	}


}
