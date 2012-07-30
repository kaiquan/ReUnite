package Controller.RIM;
/*******************************************************************************************
 * Project: ReUnite
 *
 * Class: CreateInvitationViewController
 *
 * Author: Adeel M. Ateeque (112013Z)
 * 
 * Lecturer: Ms Lim Ai Hua
 * 
 * Purpose:  A controller class to handle all actions on CreateInvitationView and to handle
 * and fulfill requests from the view.
 * 
 * Honor Code: I pledge that this program represents my own program code. 
 * I received help from no one in designing, coding and debugging my program.
 *******************************************************************************************/
import java.util.ArrayList;

import Controller.RIM.Utils.DateHelper;
import Model.Event;
import Model.RIM.TableModels.InvitationReportTableModel;

public class InvitationResponseController
{
	@SuppressWarnings("unused")
	private DateHelper dateHelper = new DateHelper();
	
	private final Event eventModel = new Event();
	
	private InvitationReportTableModel tableModel = new InvitationReportTableModel(eventModel.GET_ALL_EVENTS());;
	
	
	public InvitationResponseController()
	{

	}
	
	//Table methods
	public InvitationReportTableModel getTableModel()
	{
		return tableModel;
	}
	
	public void addRow()
	{
		tableModel.addRow(new Event());
	}
	
	public void addRows(ArrayList<Event> event)
	{
		tableModel.addRows(event);
	}
	
	public void deleteRow(int row[])
	{
		tableModel.deleteRow(row);
	}
	
	public static void main(String args[])
	{

	}
}

