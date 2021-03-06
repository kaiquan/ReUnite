package Model.RIM.Chat;

import javax.swing.*;

import Controller.RIM.LookAndFeelController;

import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

public class ServerGUI extends JFrame implements ActionListener, WindowListener {
	
	private static final long serialVersionUID = 1L;
	// the stop and start buttons
	private JButton stopStart;
	// JTextArea for the chat room and the events
	private JTextArea chatBox, consoleLog;
	// The port number
	private JTextField tPortNumber;
	// my server
	private Server server;
	
	
	// Server constructor that receive the port to listen to for connection as parameter
	ServerGUI(int port) {
		super("Chat Server");
		setResizable(false);
		server = null;
		// In the NorthPanel the PortNumber the Start and Stop buttons
		JPanel north = new JPanel();
		north.add(new JLabel("Port number: "));
		tPortNumber = new JTextField("" + port);
		north.add(tPortNumber);
		// to stop or start the server, we start with "Start"
		stopStart = new JButton("Start");
		stopStart.addActionListener(this);
		north.add(stopStart);
		getContentPane().add(north, BorderLayout.NORTH);
		
		// The event and chat room
		JPanel center = new JPanel();
		chatBox = new JTextArea(80,80);
		chatBox.setEditable(false);
		appendToRoom("Chat room.\n");
		center.setLayout(new MigLayout("", "[646px,grow,fill][]", "[1446px,fill]"));
		center.add(new JScrollPane(chatBox), "cell 0 0,alignx left,aligny top");
		consoleLog = new JTextArea(80,80);
		center.add(new JScrollPane(consoleLog), "cell 1 0");
		consoleLog.setEditable(false);
		appendToEventLog("Events log.\n");
		getContentPane().add(center);
		
		addWindowListener(this);
		setSize(801, 604);
		setVisible(true);
	}		

	// append message to the two JTextArea
	// position at the end
	void appendToRoom(String str) {
		chatBox.append(str);
		chatBox.setCaretPosition(chatBox.getText().length() - 1);
	}
	void appendToEventLog(String str) {
		consoleLog.append(str);
		consoleLog.setCaretPosition(chatBox.getText().length() - 1);
		
	}
	
	// start or stop where clicked
	public void actionPerformed(ActionEvent e) {
		// if running we have to stop
		if(server != null) {
			server.stop();
			server = null;
			tPortNumber.setEditable(true);
			stopStart.setText("Start");
			return;
		}
      	// OK start the server	
		int port;
		try {
			port = Integer.parseInt(tPortNumber.getText().trim());
		}
		catch(Exception er) {
			appendToEventLog("Invalid port number");
			return;
		}
		//create a new Server
		server = new Server(port, this);
		// and start it as a thread
		new ServerRunning().start();
		stopStart.setText("Stop");
		tPortNumber.setEditable(false);
	}
	
	// entry point to start the Server
	public static void main(String[] arg) {
		LookAndFeelController.setGlobalLookAndFeel();
		// start server default port 1500
		new ServerGUI(1500);
	}

	
	public void windowClosing(WindowEvent e) {
		// if my Server exist
		if(server != null) {
			try {
				server.stop();			// ask the server to close the conection
			}
			catch(Exception eClose) {
			}
			server = null;
		}
		// dispose the frame
		dispose();
		System.exit(0);
	}

	public void windowClosed(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	/*
	 * A thread to run the Server
	 */
	public class ServerRunning extends Thread {
		public void run() {
			server.start();         // should execute until if fails
			// the server failed
			stopStart.setText("Start");
			tPortNumber.setEditable(true);
			appendToEventLog("Server crashed\n");
			server = null;
		}
	}

}
