package Model.RIM.Chat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import Model.Invitation;
import Model.Membership.Guest;
import View.RIM.Components.JListGuestListRenderer;

public class ClientGUI extends JPanel implements ActionListener
{

	private int eventID;
	private String userName;
	private static final long serialVersionUID = 1L;
	// to hold the Username and later on the messages
	private JTextField messageField;
	// for the chat room
	private JTextArea messageArea;
	// if it is for connection
	private boolean connected;
	// the Client object
	private Client client;
	private JScrollPane chatBoxScrollPane;
	private JScrollPane scrollPane;
	JList<Guest> list;
	DefaultListModel<Guest> listModel;

	// Constructor connection receiving a socket number
	public ClientGUI(String userName, int eventID)
	{
		this.userName = userName;
		this.eventID = eventID;

		// The online users grid
		listModel = new DefaultListModel<Guest>();
		for(Guest guest : new Invitation().GET_ALL_GUESTS(eventID))
		{
			listModel.addElement(guest);
		}
		JPanel onlineUserPanel = new JPanel(new MigLayout("", "[1060.00]", "[353px]"));
		scrollPane = new JScrollPane();
		list = new JList<Guest>(listModel);
		list.setFixedCellWidth(100);
		list.setFixedCellHeight(100);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setCellRenderer(new JListGuestListRenderer());
		scrollPane.setViewportView(list);
		onlineUserPanel.add(scrollPane, "cell 0 0,grow");
		add(onlineUserPanel, "cell 0 0");

		JPanel chatBoxPanel = new JPanel();
		chatBoxPanel.setLayout(new MigLayout("", "[217px]", "[][]"));
		chatBoxScrollPane = new JScrollPane();
		chatBoxPanel.add(chatBoxScrollPane, "cell 0 0,grow");
		messageArea = new JTextArea("Welcome to the Chat room\n", 17, 22);
		messageArea.setLineWrap(true);
		chatBoxScrollPane.setViewportView(messageArea);
		messageArea.setEditable(false);
		messageField = new JTextField(userName);
		messageField.addActionListener(this);
		chatBoxPanel.add(messageField, "cell 0 1,grow");
		messageField.setBackground(Color.WHITE);
		messageField.requestFocus();
		add(chatBoxPanel, "grow");

		setLayout(new MigLayout("", "[535.00px,left][left]", "[367px]"));
		setSize(779, 372);

		login();
	}

	// called by the Client to append text in the TextArea
	void append(String str)
	{
		messageArea.append(str);
		messageArea.setCaretPosition(messageArea.getText().length() - 1);
	}

	// called by the GUI is the connection failed
	// we reset our buttons, label, textfield
	void connectionFailed()
	{
		messageField.removeActionListener(this);
		connected = false;
	}

	/*
	 * Button or JTextField clicked
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (connected)
		{
			// just have to send the message
			client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, messageField.getText()));
			messageField.setText("");
			return;
		}
	}

	public void login()
	{
		client = new Client(this.userName, this, this.eventID);

		if (!client.start())
		{
			return;
		}

		messageField.setText("");
		connected = true;
		client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, ""));
	}

	public void logout()
	{
		client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
	}

	public void refreshOnlineUsers(HashSet<String> onlineUsers)
	{
		Iterator<String> iterator = onlineUsers.iterator();
		while (iterator.hasNext())
		{
			String onlineUser = iterator.next();
			System.out.println(onlineUser);
			for (int i = 0; i < list.getModel().getSize(); i++)
			{
				if(list.getModel().getElementAt(i).getUserName()!=null){
					if (list.getModel().getElementAt(i).getUserName().equals(onlineUser))
					{
						Guest updatedGuest = list.getModel().getElementAt(i);
						updatedGuest.setOnlineStatus(true);
						listModel.set(i, updatedGuest);
						list.setModel(listModel);
						list.updateUI();
					}
				}
			}
		}
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	// to start the whole thing the server
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		final ClientGUI client = new ClientGUI("ameenvzn93@gmail.com", 55);
		frame.setContentPane(client);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowListener()
		{

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				client.logout();
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
