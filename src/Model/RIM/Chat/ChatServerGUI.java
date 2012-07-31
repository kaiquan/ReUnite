package Model.RIM.Chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ChatServerGUI extends JFrame {

	public ChatServerGUI()
	{
		final ChatServer server = new ChatServer();
		
		JButton btnStartServer = new JButton("Start server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(server).start();
			}
		});
		getContentPane().add(btnStartServer, BorderLayout.WEST);
		
		JButton btnStopServer = new JButton("Stop server");
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Stopping Server");
				server.stop();
			}
		});
		getContentPane().add(btnStopServer, BorderLayout.EAST);
		this.addWindowListener(new WindowListener()
		{
			@Override
			public void windowClosed(WindowEvent arg0) 
			{
				server.stop();
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
		});
		setSize(225, 150);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String args[])
	{
		new ChatServerGUI();
	}
}
