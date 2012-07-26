package Model.RIM;

import javax.swing.*;
import javax.swing.event.*;

import Model.Membership.Guest;

public class GuestListModel implements ListModel<Guest>
{
	private GuestCollection collection;

	public GuestListModel(GuestCollection collection)
	{
		this.collection = collection;
	}

	public Guest getElementAt(int index)
	{
		return (collection.getProfiles().get(index));
	}

	public int getSize()
	{
		return (collection.getProfiles().size());
	}

	public void addListDataListener(ListDataListener l)
	{
	}

	public void removeListDataListener(ListDataListener l)
	{
	}
}