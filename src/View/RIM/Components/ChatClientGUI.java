package View.RIM.Components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.net.Socket;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Images.RIM.ImageHelper;
import Model.RIM.Chat.ChatClientThread;
import Model.RIM.Chat.ChatServer;

@SuppressWarnings("serial")
public class ChatClientGUI extends JPanel
{
	private static ChatClientThread chatClient;
	public static String userName = "Anonymous";
	

	private static JButton B_ABOUT = new JButton();
	private static JButton B_CONNECT = new JButton();
	private static JButton B_DISCONNECT = new JButton();
	private static JButton B_HELP = new JButton();
	private static JButton B_SEND = new JButton();
	public static JLabel L_MESSAGE = new JLabel("Message: ");
	public static JTextField TF_MESSAGE = new JTextField(20);
	private static JLabel L_CONVERSATION = new JLabel();
	public static JTextArea TA_CONVERSATION = new JTextArea();
	private static JScrollPane SP_CONVERSATION= new JScrollPane();
	private static JLabel L_ONLINE = new JLabel();
	public static JList<String> JL_Online = new JList<String>();
	private static JScrollPane SP_ONLINE = new JScrollPane();
	private static JLabel L_LoggedInAs = new JLabel();
	private static JLabel L_LoggedInAsBox = new JLabel();
	
	//Login window
	public static JFrame loginWindow = new JFrame();
	public static JTextField TF_UserNameBox = new JTextField(20);
	private static JButton B_ENTER = new JButton("ENTER");
	private static JLabel L_EnterUserName = new JLabel("Enter username: ");
	private static JPanel P_Login = new JPanel();
	
	public ChatClientGUI()
	{
		buildMainWindow();
		initialize();
	}
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(new ChatClientGUI());
		frame.setVisible(true);
		
	}
	
	public static void connect()
	{
		try
		{
			final int PORT = 6667;
			final String HOST = "localhost";
			Socket SOCK = new Socket(HOST, PORT);
			System.out.println("You are connected to: " + HOST);
			
			chatClient = new ChatClientThread(SOCK);
			
			//Send name to add to 'online' list
			PrintWriter OUT = new PrintWriter(SOCK.getOutputStream());
			OUT.println(userName + "&*&*& 3");
			OUT.flush();
			
			Thread X = new Thread(chatClient);
			X.start();
		}
		catch(Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Server not responding.");
			System.exit(0);
		}
	}
	
	public static void initialize()
	{
		B_SEND.setEnabled(true);
		B_DISCONNECT.setEnabled(false);
		B_CONNECT.setEnabled(true);
	}
	
	public static void buildLoginWindow()
	{
		loginWindow.setTitle("What's your name?");
		loginWindow.setSize(400, 100);
		loginWindow.setLocation(250, 200);
		loginWindow.setResizable(false);
		P_Login = new JPanel();
		P_Login.add(L_EnterUserName);
		P_Login.add(TF_UserNameBox);
		P_Login.add(B_ENTER);
		loginWindow.getContentPane().add(P_Login);
		
		loginAction();
		loginWindow.setVisible(true);
		
	}
	
	public void buildMainWindow()
	{
		configureMainWindow();
		mainWindowAction();
	}
	
	public void configureMainWindow()
	{

		setLayout(null);
		B_SEND.setIcon(ImageHelper.loadImageIcon("send.png", "Send message", 16, -1, 5));
		B_SEND.setText("SEND");
		add(B_SEND);
		B_SEND.setBounds(270, 40, 100, 25);
		
		B_DISCONNECT.setIcon(ImageHelper.loadImageIcon("disconnect.png", "disconnect", 16, -1, 5));
		B_DISCONNECT.setText("DISCONNECT");
		add(B_DISCONNECT);
		B_DISCONNECT.setBounds(10, 40, 130, 25);
		
		B_CONNECT.setIcon(ImageHelper.loadImageIcon("connect.png", "connect", 16, -1, 5));
		B_CONNECT.setText("CONNECT");
		B_CONNECT.setToolTipText("");
		add(B_CONNECT);
		B_CONNECT.setBounds(150, 40, 110, 25);
		
		B_HELP.setBackground(new java.awt.Color(0,0,255));
		B_HELP.setForeground(new java.awt.Color(255, 255, 255));
		B_HELP.setText("HELP");
		B_HELP.setToolTipText("");
		add(B_HELP);
		
		B_ABOUT.setText("NUDGE!");
		B_ABOUT.setToolTipText("");
		add(B_ABOUT);
		B_ABOUT.setBounds(380, 42, 100, 20);
		
		L_MESSAGE.setText("Message");
		add(L_MESSAGE);
		L_MESSAGE.setBounds(10, 10, 60, 20);
		
		TF_MESSAGE.setForeground(new java.awt.Color(0, 0, 255));
		TF_MESSAGE.requestFocus();
		add(TF_MESSAGE);
		TF_MESSAGE.setBounds(70, 4, 260, 30);
		
		L_CONVERSATION.setHorizontalAlignment(SwingConstants.CENTER);
		L_CONVERSATION.setText("Your messages:");
		add(L_CONVERSATION);
		L_CONVERSATION.setBounds(100, 70, 140, 16);
		
		SP_CONVERSATION.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_CONVERSATION.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(SP_CONVERSATION);
		TA_CONVERSATION.setSize(new Dimension(200, 200));
		TA_CONVERSATION.setPreferredSize(new Dimension(200, 200));
		TA_CONVERSATION.setBackground(java.awt.Color.lightGray);
		TA_CONVERSATION.setColumns(20);
		TA_CONVERSATION.setFont(new java.awt.Font("Tahoma", 0, 12));
		TA_CONVERSATION.setForeground(new java.awt.Color(0, 0, 255));
		TA_CONVERSATION.setLineWrap(true);
		TA_CONVERSATION.setEditable(false);
		add(TA_CONVERSATION);
		TA_CONVERSATION.setBounds(10, 114, 321, 156);
		
		L_ONLINE.setHorizontalAlignment(SwingConstants.CENTER);
		L_ONLINE.setText("Currently Online");
		L_ONLINE.setToolTipText("");
		add(L_ONLINE);
		L_ONLINE.setBounds(350, 70, 130, 16);
		
//		String[] TestNames = {"Bob", "Sue", "Jenny", "Anna"};
		JL_Online.setForeground(new java.awt.Color(0, 0, 255));
//		JL_Online.setListData(TestNames);
		
		SP_ONLINE.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_ONLINE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SP_ONLINE.setViewportView(JL_Online);
		add(SP_ONLINE);
		SP_ONLINE.setBounds(350, 90, 130, 180);
		
		L_LoggedInAs.setFont(new java.awt.Font("Tahoma",0, 12));
		L_LoggedInAs.setText("Currently logged in as");
		add(L_LoggedInAs);
		L_LoggedInAs.setBounds(348, 0, 140, 15);
		
		L_LoggedInAsBox.setFont(new java.awt.Font("Tahoma",java.awt.Font.BOLD, 12));
		L_LoggedInAsBox.setText(userName);
		add(L_LoggedInAsBox);
		L_LoggedInAsBox.setBounds(375, 15, 140, 15);
	}
	
	public static void loginAction()
	{
		B_ENTER.addActionListener(
			new java.awt.event.ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					enterButtonClicked();
					
				}
			}
		);
	}

	
	public static void mainWindowAction()
	{
		B_SEND.addActionListener(
			new java.awt.event.ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					sendButtonClicked();
					
				}
				
			}
		);
		
		B_DISCONNECT.addActionListener(
				new java.awt.event.ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						disconnectButtonClicked();
						
					}
					
				}
		);
		
		B_CONNECT.addActionListener(
				new java.awt.event.ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						buildLoginWindow();
						
					}
					
				}
		);

		B_HELP.addActionListener(
				new java.awt.event.ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						helpButtonClicked();
						
					}
					
				}
		);	
		
		B_ABOUT.addActionListener(
				new java.awt.event.ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						aboutButtonClicked();
						
					}
					
				}
		);
	}
	
	 public static synchronized void playSound(final String url) {
		    new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
		      public void run() {
		        try {
		          Clip clip = AudioSystem.getClip();
		          AudioInputStream inputStream = AudioSystem.getAudioInputStream(ImageHelper.class.getResourceAsStream("/Images/" + url));
		          clip.open(inputStream);
		          clip.start(); 
		        } catch (Exception e) {
		          System.err.println(e.getMessage());
		        }
		      }
		    }).start();
		  }

	public static void sendButtonClicked()
	{
		if(!TF_MESSAGE.getText().equals(""))
		{
			chatClient.send(TF_MESSAGE.getText());
			TF_MESSAGE.requestFocus();
		}
	}
	
	public static void enterButtonClicked()
	{
		if(!TF_UserNameBox.getText().equals(""))
		{
			userName = TF_UserNameBox.getText().trim();
			L_LoggedInAsBox.setText(userName);
//			ChatServer.currentUsers.add(userName);
			loginWindow.setVisible(false);
			B_SEND.setEnabled(true);
			B_DISCONNECT.setEnabled(true);
			B_CONNECT.setEnabled(false);
			connect();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please enter a name!");
		}
	}
	
	public static void disconnectButtonClicked()
	{
		try
		{
			chatClient.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void helpButtonClicked()
	{
		playSound("nudge.wav");
	}
	
	public static void aboutButtonClicked()
	{
		playSound("nudge.wav");
	}
	
	
	
}
