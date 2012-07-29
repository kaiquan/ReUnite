package Model.RIM.TableModels;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Images.RIM.ImageHelper;
import Model.Membership.Guest;
import Model.RIM.GuestCollection;

public class GuestImportTableModel extends AbstractTableModel
{
	private static TableSorter tableSorter;
	private static final long serialVersionUID = 1L;
	private int rowCount, colCount;
	private String[] columnNames = { "", "First name", "Last name", "School", "Date of birth", "Email", "Telephone number", "Handphone number", "Address" };
	ArrayList<Object[]> al;


	public GuestImportTableModel(GuestCollection guestList)
	{	
		rowCount = guestList.getProfiles().size();
		al = new ArrayList<Object[]>(rowCount);
		colCount = columnNames.length;
		
		Object[][] data = new Object[rowCount][colCount];
		
		for (int i = 0; i < rowCount; i++)
		{
			Guest guest = guestList.getProfiles().get(i);
			data[i][0] = ImageHelper.loadImageIcon(guest.getProfilePicture(), "");
			data[i][1] = guest.getFirstName();
			data[i][2] = guest.getLastName();
			data[i][3] = guest.getSchool();
			data[i][4] = guest.getDateOfBirth();
			data[i][5] = guest.getEmail();
			data[i][6] = Color.blue;
			data[i][7] = guest.getHandphoneNo();
			data[i][8] = guest.getAddress();
			
			al.add(data[i]);
		}
		tableSorter = new TableSorter(this);
	}

	public boolean isCellEditable(int row, int column)
	{
		return (true);
	}

	
	public Class<?> getColumnClass(int column)
	{  
		Object value=this.getValueAt(0,column);  
		return (value==null?Object.class:value.getClass());  
	} 


	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return al.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		 return al.get(rowIndex)[columnIndex];
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	
	@Override
	public void setValueAt(Object value, int row, int column) {
		al.get(row)[column] = value;
	}

	@Override
	public int findColumn(String arg0) {
		return super.findColumn(arg0);
	}

	@Override
	public void fireTableCellUpdated(int arg0, int arg1) {
		super.fireTableCellUpdated(arg0, arg1);
	}

	@Override
	public void fireTableRowsDeleted(int arg0, int arg1) {
		super.fireTableRowsDeleted(arg0, arg1);
	}

	@Override
	public void fireTableRowsInserted(int arg0, int arg1) {
		super.fireTableRowsInserted(arg0, arg1);
	}

	@Override
	public void fireTableRowsUpdated(int arg0, int arg1) {
		super.fireTableRowsUpdated(arg0, arg1);
	}
	
	public void addRow(ArrayList<Guest> guestList) 
	{

	}
	
	public void addRow(Guest guest) 
	{
		colCount = columnNames.length;
		Object[] data = new Object[colCount];

		data[0] = guest.getProfilePicture();
		data[1] = guest.getFirstName();
		data[2] = guest.getLastName();
		data[3] = guest.getSchool();
		data[4] = Color.blue;
		data[5] = guest.getEmail();
		data[6] = guest.getTelephoneNo();
		data[7] = guest.getHandphoneNo();
		data[8] = guest.getAddress();
			
		al.add(data);
		
		fireTableDataChanged();
	}

	public void deleteRow(int[] index)
	{
		for(int i=0; i<index.length; i++)
		{
			al.remove(index[i]-i);
		}
		
		fireTableDataChanged();
		
	}
	
	public TableSorter getTableSorter() {
		return tableSorter;
	}

	public static void setTableSorter(TableSorter tableSorter) {
		GuestImportTableModel.tableSorter = tableSorter;
	}

}


