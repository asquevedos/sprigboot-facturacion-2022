package edu.ucacue.facturacion2.controller.empresa;



import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.ucacue.facturacion2.modelo.Empresa;

public class EmpresaItemModel extends AbstractTableModel{

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private List<Empresa> empresas;

	private static final String[] COLUMN_NAMES = {"Razon Social", "Ruc", "Telefono","Dirección"};

	public EmpresaItemModel(List<Empresa> empresas) {

		this.empresas = empresas;

	}

	@Override
	public int getRowCount() {
		return empresas.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Empresa empresa = empresas.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = empresa.getRazonSocial();
			break;
		case 1:
			value = empresa.getRuc();
			break;
		case 2:
			value = empresa.getTelefono();
			break;
		case 3:
			value = empresa.getDireccion();
			break;
		}
		//int id, String nombre, String ruc, String direccion

		return value;

	}

	@Override
    public Class<?> getColumnClass(int columnIndex) {
        return Empresa.class;
    }
	
    //the column header
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
    
	public Empresa getEmpresaAt(int row) {
		return empresas.get(row);
	}

}
