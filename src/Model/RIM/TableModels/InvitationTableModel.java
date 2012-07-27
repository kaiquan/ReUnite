package Model.RIM.TableModels;

import Images.RIM.ImageHelper;
import Model.Event;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class InvitationTableModel extends DefaultTableModel
{
	private static final long serialVersionUID = 1L;
	private int rowCount, colCount;
	private String[] columnNames = { "ID", "Name", "Initiator", "Date", "Status", "Facility", "Response" };
	private Object[][] data;

	public InvitationTableModel(ArrayList<Event> eventList)
	{
		rowCount = eventList.size();
		colCount = columnNames.length;
		data = new Object[rowCount][colCount];
		for (int i = 0; i < rowCount; i++)
		{
			Event event = eventList.get(i);
			data[i][0] = event.getID();
			data[i][1] = event.getEventName();
			data[i][2] = event.getEventInitiator().getUserName();
			data[i][3] = event.getEventDate() + " at " + event.getEventTime();
			data[i][4] = event.getEventStatus();
			data[i][5] = event.getEventPackage().getBallroom().getFacility().getFacilityName();
			data[i][6] = 40;

			setDataVector(data, columnNames);
		}
	}

	public boolean isCellEditable(int row, int column)
	{
		return (false);
	}

	public Class getColumnClass(int col)
	{
		return (getValueAt(0, col).getClass());
	}

	@Override
	public void setDataVector(Object[][] arg0, Object[] arg1)
	{
		super.setDataVector(arg0, arg1);
	}

	@Override
	public int getColumnCount()
	{

		return colCount;
	}

	@Override
	public int getRowCount()
	{

		return rowCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{

		return data[rowIndex][columnIndex];
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}

}
