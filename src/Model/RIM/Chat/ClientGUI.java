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

public class ClientGUI extends JPanel implements ActionListener
{

	private int eventID;
	private String userName;
	private static final long serialVersionUID = 1L;
	// will first hold "Username:", later on "Enter message"
	private JLabel label;
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

	// Constructor connection receiving a socket number
	public ClientGUI(String userName, int eventID)
	{
		this.userName = userName;
		this.eventID = eventID;
		// The NorthPanel with:
		JPanel northPanel = new JPanel();

		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		northPanel.setLayout(null);

		// the Label and the TextField
		label = new JLabel("Enter your username below", SwingConstants.CENTER);
		label.setBounds(0, 0, 0, 0);
		northPanel.add(label);
		messageField = new JTextField(userName);
		messageField.setBounds(0, 0, 0, 0);
		messageField.setBackground(Color.WHITE);
		northPanel.add(messageField);
		add(northPanel);

		// The CenterPanel which is the chat room
		messageArea = new JTextArea("Welcome to the Chat room\n", 80, 80);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(messageArea);
		scrollPane.setBounds(-73, 280, 366, -280);
		centerPanel.add(scrollPane);
		messageArea.setEditable(false);
		add(centerPanel);

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
		add(southPanel);

		setSize(385, 307);
		messageField.requestFocus();

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
		label.setText("Enter your username below");
		messageField.setText("Anonymous");
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
			label.setText("Enter your message below");
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
	}

}
