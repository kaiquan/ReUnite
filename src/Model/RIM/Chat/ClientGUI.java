package Model.RIM.Chat;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class ClientGUI extends JPanel implements ActionListener
{

	private int eventID;
	private String userName;
	private static final long serialVersionUID = 1L;
	// to hold the Username and later on the messages
	private JTextField messageField;
	// to Logout and get the list of the users
	private JButton logout, whoIsIn;
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
		
		new Client(this.userName, this, this.eventID);
		if (!client.start()) return;
		messageField.setText("");
		connected = true;
		
		JPanel mainPanel = new JPanel();

		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mainPanel.setLayout(new MigLayout("", "[217px]", "[19.00px][][]"));


		logout = new JButton("Logout");
		logout.addActionListener(this);
		logout.setEnabled(false); // you have to login before being able to logout
		whoIsIn = new JButton("Who is in");
		whoIsIn.addActionListener(this);
		whoIsIn.setEnabled(false); // you have to login before being able to Who is in

		JPanel southPanel = new JPanel();
		southPanel.add(logout);
		southPanel.add(whoIsIn);
		mainPanel.add(southPanel, "cell 0 0,growx,aligny top");

		add(mainPanel);
								
		scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, "cell 0 1,grow");

		// The CenterPanel which is the chat room
		messageArea = new JTextArea("Welcome to the Chat room\n", 12, 20);
		messageArea.setLineWrap(true);
		scrollPane.setViewportView(messageArea);
		messageArea.setEditable(false);
		messageField = new JTextField(userName);
		messageField.addActionListener(this);
		mainPanel.add(messageField, "cell 0 2,growx");
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
	}
	
	public void logout()
	{
		client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
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
