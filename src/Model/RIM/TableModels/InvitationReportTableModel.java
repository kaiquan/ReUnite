package Model.RIM.TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Model.Event;

public class InvitationReportTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private TableSorter tableSorter;
	private int rowCount, colCount;
	private String[] columnNames = { "ID", "Name", "Initiator", "Date", "Status", "Facility", "Invitation sent", "Response" };
	private ArrayList<Event> eventList;
	private ArrayList<Object[]> al = new ArrayList<Object[]>();

	public InvitationReportTableModel(ArrayList<Event> eventList)
	{
		this.eventList = eventList;
		rowCount = eventList.size();
		colCount = columnNames.length;
		al = new ArrayList<Object[]>(rowCount);
		
		Object[][] data = new Object[rowCount][colCount];
		for (int i = 0; i < rowCount; i++)
		{
			Event event = eventList.get(i);
			
			data[i][0] = event.getID();
			data[i][1] = event.getEventName();
			data[i][2] = event.getEventInitiator();
			data[i][3] = event.getEventDate() + " at " + event.getEventTime();
			data[i][4] = event.getEventStatus();
			data[i][5] = event.getEventPackage().getBallroom().getFacility().getFacilityName();
			data[i][6] = (event.getEventInvitation().getInvitationID() != 0) ? true : false;
 			data[i][7] = (event.getEventInvitation().getInvitationID() != 0)? event.getEventInvitation().GET_INVITATION_RESPONSE():0;

			al.add(data[i]);
		}
		tableSorter = new TableSorter(this);
	}

	public boolean isCellEditable(int row, int column)
	{
		return (false);
	}

	
	public Class<?> getColumnClass(int column)
	{  
		Object value=this.getValueAt(0,column);  
		return (value==null?Object.class:value.getClass());  
	} 

	public Event getEvent(int index)
	{
		return eventList.get(index);
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
	
	
	public void addRows(ArrayList<Event> eventList) 
	{
		for(int i = 0; i<eventList.size(); i++)
		{
			addRow(eventList.get(i));
		}
	}
	
	public void addRow(Event event) 
	{
		colCount = columnNames.length;
		Object[] data = new Object[colCount];

		data[0] = event.getID();
		data[1] = event.getEventName();
		data[2] = event.getEventInitiator().getUserName();
		data[3] = event.getEventDate() + " at " + event.getEventTime();
		data[4] = event.getEventStatus();
		data[5] = event.getEventPackage().getBallroom().getFacility().getFacilityName();
		data[6] = new Model.Invitation(event.getEventInvitation().getInvitationID()).GET_GUEST_COUNT();
			
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
