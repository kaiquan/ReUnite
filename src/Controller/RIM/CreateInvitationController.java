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

import javax.swing.JOptionPane;

import Controller.RIM.Utils.DateHelper;
import Model.Event;
import Model.Invitation;
import Model.Membership.Guest;
import Model.RIM.GuestCollection;
import Model.RIM.TableModels.GuestImportTableModel;
import View.RIM.SearchUsersView;

public class CreateInvitationController
{
	private DateHelper dateHelper = new DateHelper();
	
	private final Invitation invitationModel = new Invitation();
	
	private static GuestCollection guests = new GuestCollection();
	
	private static GuestImportTableModel tableModel = new GuestImportTableModel(guests.getProfiles());
	
	
	public CreateInvitationController()
	{
		
	}

	/**
	 * Directs the appropriate method in the model to create a new Invitation
	 * record in the database
	 * 
	 * @return The success in boolean, true or false
	 * */
	public boolean createNewInvitation(ArrayList<Guest> guestList, int eventID)
	{
		Invitation newInvitation = new Invitation();
		
		newInvitation.setDateCreated(dateHelper.getCurrentDate());
		newInvitation.setEvent(new Event(eventID));
		newInvitation.setExpiryDate(dateHelper.addDaysToDate(3, newInvitation.getDateCreated()));

		if(invitationModel.CREATE_INVITATION(newInvitation))
		{
			JOptionPane.showMessageDialog(null, "New invitation successfully created!");
		}
		return false;
	}
	
	public GuestImportTableModel getTableModel()
	{
		return tableModel;
	}
	
	public void addRow()
	{
		tableModel.addRow(new Guest());
	}
	
	public void addRows(ArrayList<Guest> guests)
	{
		tableModel.addRows(guests);
	}
	
	public void deleteRow(int row[])
	{
		tableModel.deleteRow(row);
	}
	
	public boolean sendInvitation()
	{
		
		Invitation invitation = new Invitation();
		invitation.setGuestList(tableModel.getAllGuests());
		invitationModel.CREATE_INVITATION(invitation);
		return false;
	}
	
	public static void main(String args[])
	{
		CreateInvitationController controller = new CreateInvitationController();
		ArrayList<Guest> guestList = new ArrayList<Guest>();
		controller.createNewInvitation(guestList , 4);
	}

	public void searchUsers()
	{
		new SearchUsersView();
	}

	public ArrayList<Integer> validateData()
	{
		ArrayList<Integer> invalidRows = new ArrayList<Integer>();
		
		for(int i=0; i<tableModel.getRowCount(); i++)
		{
			if(!tableModel.validateRow(tableModel.getRow(i)))
			{
				invalidRows.add(i);
			}
		}
		
		return invalidRows;
	}
}

