package Model.RIM.TableModels;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import Images.RIM.ImageHelper;
import Model.Membership.Guest;

public class UserDirectoryTableModel extends AbstractTableModel
{

	private static final long serialVersionUID = 1L;
	private TableSorter tableSorter;
	private int rowCount, colCount;
	private String[] columnNames = { "", "First name*", "Last name*", "School", "Date of birth", "Email*", "Contact", "Address" };
	private static ArrayList<Object[]> al;

	public UserDirectoryTableModel(ArrayList<Guest> guestList)
	{
		rowCount = guestList.size();
		al = new ArrayList<Object[]>(rowCount);
		colCount = columnNames.length;

		Object[][] data = new Object[rowCount][colCount];

		for (int i = 0; i < rowCount; i++)
		{
			Guest guest = guestList.get(i);
			
			ImageIcon profilePicture = ImageHelper.loadImageIcon(guest.getProfilePicture()!=null?guest.getProfilePicture():"userIcon.png", "", 75, -1, 5);
			
			data[i][0] = profilePicture;
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
		Object value = this.getValueAt(0, column);
		return (value == null ? Object.class : value.getClass());
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
	public void setValueAt(Object value, int row, int column)
	{
		al.get(row)[column] = value;
	}

	public void addRecords(ArrayList<Object[]> g)
	{
		al.addAll(g);

		fireTableDataChanged();
	}

	public void addRows(ArrayList<Guest> guestList)
	{
		for (int i = 0; i < guestList.size(); i++)
		{
			addRow(guestList.get(i));
		}
	}

	public void addRow(Guest guest)
	{
		colCount = columnNames.length;
		Object[] data = new Object[colCount];

		data[0] = guest.getProfilePicture() != null ? ImageHelper.loadImageIcon(guest.getProfilePicture(), "") : ImageHelper.loadImageIcon("userIcon.png", "");
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

	public Guest getGuest(int index)
	{
		Object[] data = getRow(index);
		Guest guest = new Guest();
		guest.setFirstName(data[1] != null ? data[1].toString() : "");
		guest.setLastName(data[2] != null ? data[2].toString() : "");
		guest.setSchool(data[3] != null ? data[3].toString() : "");
		guest.setDateOfBirth(data[4] != null ? (Date)data[4] : null);
		guest.setEmail(data[5] != null ? data[5].toString() : "");
		guest.setHandphoneNo(data[6] != null ? data[6].toString() : "");
		guest.setAddress(data[7] != null ? data[7].toString() : "");
		return guest;
	}
	
	public boolean validateRow(Object[] row)
	{
		boolean valid = false;
		
		if(row[1] == null || row[2] == null || row[5] == null)
		{
			valid = false;
		}
		else
		{
			valid = true;
		}
		
		return valid;
	}

	public ArrayList<Guest> getAllGuests()
	{
		ArrayList<Guest> guests = new ArrayList<Guest>();
		for (int i = 0; i < getRowCount(); i++)
		{
			Guest guest = getGuest(i);
			guests.add(guest);
		}
		return guests;
	}

	public Object[] getRow(int row)
	{
		return al.get(row);
	}

	public void deleteRow(int[] index)
	{
		for (int i = 0; i < index.length; i++)
		{
			al.remove(index[i] - i);
		}

		fireTableDataChanged();
	}

	public TableSorter getTableSorter()
	{
		return tableSorter;
	}

	public void setTableSorter(TableSorter tableSorter)
	{
		this.tableSorter = tableSorter;
	}

}
