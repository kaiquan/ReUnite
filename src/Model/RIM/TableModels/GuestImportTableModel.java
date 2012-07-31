package Model.RIM.TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Images.RIM.ImageHelper;
import Model.Membership.Guest;

public class GuestImportTableModel extends AbstractTableModel
{
	
	private static final long serialVersionUID = 1L;
	private TableSorter tableSorter;
	private int rowCount, colCount;
	private String[] columnNames = { "", "First name", "Last name", "School", "Date of birth", "Email", "Contact", "Address" };
	ArrayList<Object[]> al;


	public GuestImportTableModel(ArrayList<Guest> guestList)
	{	
		rowCount = guestList.size();
		al = new ArrayList<Object[]>(rowCount);
		colCount = columnNames.length;
		
		Object[][] data = new Object[rowCount][colCount];
		
		for (int i = 0; i < rowCount; i++)
		{
			Guest guest = guestList.get(i);
			data[i][0] = ImageHelper.loadImageIcon(guest.getProfilePicture(), "");
			data[i][1] = guest.getFirstName();
			data[i][2] = guest.getLastName();
			data[i][3] = guest.getSchool();
			data[i][4] = guest.getDateOfBirth();
			data[i][5] = guest.getEmail();
			data[i][6] = guest.getHandphoneNo();
			data[i][7] = guest.getAddress();
			
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

	
	public void addRows(ArrayList<Guest> guestList) 
	{
		for(int i = 0; i<guestList.size(); i++)
		{
			addRow(guestList.get(i));
		}
	}
	
	public void addRow(Guest guest) 
	{
		colCount = columnNames.length;
		Object[] data = new Object[colCount];

		data[0] = guest.getProfilePicture()!= null?ImageHelper.loadImageIcon(guest.getProfilePicture(), ""):ImageHelper.loadImageIcon("userIcon.png", "");
		data[1] = guest.getFirstName();
		data[2] = guest.getLastName();
		data[3] = guest.getSchool();
		data[4] = guest.getDateOfBirth();
		data[5] = guest.getEmail();
		data[6] = guest.getHandphoneNo();
		data[7] = guest.getAddress();
			
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

	public void setTableSorter(TableSorter tableSorter) {
		this.tableSorter = tableSorter;
	}

}


