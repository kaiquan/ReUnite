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
	
	public boolean sendInvitation(int eventID, String subject, String text)
	{
		Invitation invitation = new Invitation();
		invitation.setGuestList(tableModel.getAllGuests());
		invitation.setDateCreated(dateHelper.getCurrentDate());
		invitation.setEvent(new Event(eventID));
		invitation.setExpiryDate(dateHelper.addDaysToDate(3, invitation.getDateCreated()));

		if(invitationModel.CREATE_INVITATION(invitation, eventID, subject, text))
		{
			JOptionPane.showMessageDialog(null, "Your invitation has been successfully sent!");
			return true;
		}
		
		return false;
	}
	
	public static void main(String args[])
	{
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

