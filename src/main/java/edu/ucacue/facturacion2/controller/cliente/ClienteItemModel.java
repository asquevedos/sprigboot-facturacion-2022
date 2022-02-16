package edu.ucacue.facturacion2.controller.cliente;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.ucacue.facturacion2.modelo.Cliente;

public class ClienteItemModel extends AbstractTableModel {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private List<Cliente> personas;
	private static final String[] COLUMN_NAMES = { "Nombre", "Apellido", "Cédula", "Dirección" };

	public ClienteItemModel(List<Cliente> personas) {

		this.personas = personas;

	}

	@Override
	public int getRowCount() {
		return personas.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Cliente persona = personas.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = persona.getNombre();
			break;
		case 1:
			value = persona.getApellido();
			break;
		case 2:
			value = persona.getCedula();
			break;
		case 3:
			value = persona.getDireccion();
			break;
		}

		return value;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Cliente.class;
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

	public Cliente getPersonaAt(int row) {
		return personas.get(row);
	}

}
