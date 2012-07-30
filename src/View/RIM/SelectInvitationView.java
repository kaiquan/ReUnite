//package View.RIM;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.util.ArrayList;
//import javax.swing.*;
//
//import Controller.RIM.LookAndFeelController;
//import Model.Event;
//import Model.RIM.TableModels.InvitationTableModel;
//
//@SuppressWarnings("serial")
//public class SelectInvitationView extends JFrame
//{
//	public SelectInvitationView()
//	{
//		JPanel panel = new JPanel();
//		setTitle("Select invitation");
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
//		setContentPane(panel);
//		setSize(new Dimension(500, 250));
//		
//		Event eventModel = new Event();
//		
//		ArrayList<Event> invitationList = eventModel.GET_ALL_EVENTS();
//		
//		JTable invitationTable = new JTable(new InvitationTableModel(invitationList));
//		invitationTable.setBackground(Color.GREEN);
//		invitationTable.setSize(super.getSize());
//		
//		
//		panel.add(invitationTable);
//	
//		
//		LookAndFeelController.setGlobalLookAndFeel();
//		setVisible(true);
//	}
//	
//	public static void main(String args[])
//	{
//		new SelectInvitationView();
//	}
//}
