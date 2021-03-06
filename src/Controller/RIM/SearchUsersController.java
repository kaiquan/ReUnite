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

import Model.Membership.Guest;
import Model.RIM.GuestCollection;
import Model.RIM.TableModels.*;

public class SearchUsersController
{
	
	@SuppressWarnings("unused")
	private DateHelper dateHelper = new DateHelper();
	
	
	
	private static GuestCollection guests;
	
	private static UserDirectoryTableModel tableModel;
	
	
	@SuppressWarnings("serial")
	public SearchUsersController()
	{
		guests = new GuestCollection(new Guest().GET_ALL_GUESTS());
		tableModel = new UserDirectoryTableModel(guests.getProfiles())
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return (false);
			}
		};
	}


	//Table methods
	public UserDirectoryTableModel getTableModel()
	{
		return tableModel;
	}

	public ArrayList<Object[]> getSelectedRows(int[] index)
	{
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		for(Integer i : index)
		{
			rows.add(tableModel.getRow(i));
		}
		return rows;
	}


	public void addToList(int[] rows)
	{
		CreateInvitationController controller = new CreateInvitationController();
		controller.getTableModel().addRecords(getSelectedRows(rows));
	}

	
}

