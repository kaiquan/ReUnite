package Model.RIM.Chat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import Model.Membership.Guest;
import Model.RIM.GuestCollection;
import Model.RIM.GuestListModel;
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

	// Constructor connection receiving a socket number
	public ClientGUI(String userName, int eventID)
	{
		this.userName = userName;
		this.eventID = eventID;

		// The online users grid
		GuestCollection collection = new GuestCollection();
		ListModel<Guest> listModel = new GuestListModel(collection);
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
		System.out.println("Refreshing JList");
		Iterator<String> iterator = onlineUsers.iterator();
		while (iterator.hasNext())
		{
			String onlineUser = iterator.next();

			for (int i = 0; i < list.getModel().getSize(); i++)
			{
				System.out.println(onlineUser);
				if(list.getModel().getElementAt(i).getUserName()!=null){
					if (list.getModel().getElementAt(i).getUserName().equals(onlineUser))
					{
						list.getModel().getElementAt(i).setOnlineStatus(true);
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
		frame.setContentPane(new ClientGUI("Adeel", 3));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
