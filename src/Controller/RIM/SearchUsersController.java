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
import Model.RIM.TableModels.GuestImportTableModel;

public class SearchUsersController
{
	
	@SuppressWarnings("unused")
	private DateHelper dateHelper = new DateHelper();
	
	
	
	private static GuestCollection guests = new GuestCollection(new Guest().GET_ALL_GUESTS());
	
	private static GuestImportTableModel tableModel = new GuestImportTableModel(guests.getProfiles());
	
	
	public SearchUsersController()
	{
		
	}

	
	//Table methods
	public GuestImportTableModel getTableModel()
	{
		return tableModel;
	}

	public ArrayList<Object[]> getSelectedRows(int[] index)
	{
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		for(int i=0; i<index.length; i++)
		{
			rows.add(tableModel.getRow(i));
		}
		return rows;
	}


	public void addToList()
	{
		CreateInvitationController controller = new CreateInvitationController();
		controller.getTableModel().addRecords(getSelectedRows(new int[]{4, 3 ,6}));
		
	}

	
}

