package Model.RIM.Chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;

public class Client
{
	private int eventID;
	// for I/O
	private ObjectInputStream sInput; // to read from the socket
	private ObjectOutputStream sOutput; // to write on the socket
	private Socket socket;

	private ClientGUI cg;

	// the server, the port and the username
	private String server, userName;
	private int port;

	Client(String userName, ClientGUI cg, int eventID)
	{
		this.server = "localhost";
		this.port = 1500;
		this.userName = userName;
		this.eventID = eventID;
		// save if we are in GUI mode or not
		this.cg = cg;
	}

	/* To start the dialog */
	public boolean start()
	{
		// try to connect to the server
		try
		{
			socket = new Socket(server, port);
			socket.getOutputStream().write(eventID);
			socket.getOutputStream().flush();

		}
		catch (Exception ec)
		{
			display("Error connectiong to server:" + ec);
			return false;
		}

		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		System.out.println(msg);

		/* Creating both Data Stream */
		try
		{
			sInput = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO)
		{
			display("Exception creating new Input/output Streams: " + eIO);
			return false;
		}

		// creates the Thread to listen from the server
		new ListenFromServer().start();
		// Send our username to the server this is the only message that we
		// will send as a String. All other messages will be ChatMessage objects
		try
		{
			sOutput.writeObject(userName);
		}
		catch (IOException eIO)
		{
			display("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		// success we inform the caller that it worked
		return true;
	}

	/* To send a message to the console or the GUI */
	private void display(String msg)
	{
		System.out.println(msg);
		cg.append(msg + "\n"); // append to the ClientGUI JTextArea (or whatever)
	}

	/* To send a message to the server */
	void sendMessage(ChatMessage msg)
	{
		try
		{
			if(sOutput!=null)
			{
				sOutput.writeObject(msg);
			}
		}
		catch (IOException e)
		{
			display("Exception writing to server: " + e);
		}
	}

	/* When something goes wrong Close the Input/Output streams and disconnect not much to do in the catch clause */
	private void disconnect()
	{
		try
		{
			if (sInput != null) sInput.close();
		}
		catch (Exception e)
		{
		} // not much else I can do
		try
		{
			if (sOutput != null) sOutput.close();
		}
		catch (Exception e)
		{
		} // not much else I can do
		try
		{
			if (socket != null) socket.close();
		}
		catch (Exception e)
		{
		} // not much else I can do

		// inform the GUI
		if (cg != null) cg.connectionFailed();

	}

	public static void main(String[] args)
	{
	}

	class ListenFromServer extends Thread
	{
		public void run()
		{
			while (true)
			{
				try
				{
					Object msg = (Object) sInput.readObject();
					if (msg instanceof HashSet<?>)
					{
						@SuppressWarnings("unchecked")
						HashSet<String> onlineUsers = (HashSet<String>) msg;
						cg.refreshOnlineUsers(onlineUsers);
					}
					else
					{
						String text = (String) msg;
						System.out.println(text);
						System.out.print("> ");
						cg.append(text);
					}

				}
				catch (IOException e)
				{
					display("Server has closed the connection: " + e);
					if (cg != null) cg.connectionFailed();
					break;
				}
				// can't happen with a String object but need the catch anyhow
				catch (ClassNotFoundException e2)
				{
				}
			}
		}
	}
}
