package Model.RIM.Chat;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

public class ClientGUI extends JPanel implements ActionListener
{

	private int eventID;
	private String userName;
	private static final long serialVersionUID = 1L;
	// to hold the Username and later on the messages
	private JTextField messageField;
	// to Logout and get the list of the users
	private JButton login, logout, whoIsIn;
	// for the chat room
	private JTextArea messageArea;
	// if it is for connection
	private boolean connected;
	// the Client object
	private Client client;
	private JScrollPane scrollPane;

	// Constructor connection receiving a socket number
	public ClientGUI(String userName, int eventID)
	{
		this.userName = userName;
		this.eventID = eventID;
		JPanel mainPanel = new JPanel();

		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mainPanel.setLayout(new MigLayout("", "[217px][32.00px]", "[33px][][]"));

		// the 3 buttons
		login = new JButton("Login");
		login.addActionListener(this);
		logout = new JButton("Logout");
		logout.addActionListener(this);
		logout.setEnabled(false); // you have to login before being able to logout
		whoIsIn = new JButton("Who is in");
		whoIsIn.addActionListener(this);
		whoIsIn.setEnabled(false); // you have to login before being able to Who is in

		JPanel southPanel = new JPanel();
		southPanel.add(login);
		southPanel.add(logout);
		southPanel.add(whoIsIn);
		mainPanel.add(southPanel, "cell 0 0 2 1,growx,aligny top");

		add(mainPanel);
								
								scrollPane = new JScrollPane();
								mainPanel.add(scrollPane, "cell 0 1 2 1,grow");
						
								// The CenterPanel which is the chat room
								messageArea = new JTextArea("Welcome to the Chat room\n", 12, 20);
								scrollPane.setViewportView(messageArea);
								messageArea.setEditable(false);
								messageField = new JTextField(userName);
								mainPanel.add(messageField, "cell 0 2 2 1,growx");
								messageField.setBackground(Color.WHITE);
						messageField.requestFocus();
		setSize(385, 307);

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
		login.setEnabled(true);
		logout.setEnabled(false);
		whoIsIn.setEnabled(false);
		// let the user change them
		// don't react to a <CR> after the username
		messageField.removeActionListener(this);
		connected = false;
	}

	/*
	 * Button or JTextField clicked
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		// if it is the Logout button
		if (o == logout)
		{
			client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
			return;
		}
		// if it the who is in button
		if (o == whoIsIn)
		{
			client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, ""));
			return;
		}

		// ok it is coming from the JTextField
		if (connected)
		{
			// just have to send the message
			client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, messageField.getText()));
			messageField.setText("");
			return;
		}

		if (o == login)
		{
			// try creating a new Client with GUI
			client = new Client(this.userName, this, this.eventID);
			// test if we can start the Client
			if (!client.start()) return;
			messageField.setText("");
			connected = true;

			// disable login button
			login.setEnabled(false);
			// enable the 2 buttons
			logout.setEnabled(true);
			whoIsIn.setEnabled(true);
			// disable the Server and Port JTextField
			// Action listener for when the user enter a message
			messageField.addActionListener(this);
		}
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
