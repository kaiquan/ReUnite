package Model.RIM.Chat;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server
{
	// a unique ID for each connection
	private static int uniqueId;

	// a HashMap to keep track of the list of chatRooms and the list of Client inside them
	private HashMap<Integer, ArrayList<ClientThread>> chatRooms;

	private ServerGUI sg;

	private SimpleDateFormat sdf;

	// the port number to listen for connection
	private int port;

	private boolean keepGoing;

	public Server(int port, ServerGUI sg)
	{
		this.sg = sg;
		this.port = port;
		sdf = new SimpleDateFormat("HH:mm:ss");
		// ArrayList for the Client list
		chatRooms = new HashMap<Integer, ArrayList<ClientThread>>();
	}

	public void start()
	{
		keepGoing = true;
		/* wait for connection requests */
		try
		{
			// the socket used by the server
			ServerSocket serverSocket = new ServerSocket(port);

			// infinite loop to wait for connections
			while (keepGoing)
			{
				// format message saying we are waiting
				display("Server waiting for Clients on port " + port + ".");

				Socket socket = serverSocket.accept(); // accept connections
				int eventID = socket.getInputStream().read();// get the user's event id

				if (!keepGoing)
					break;

				ClientThread t = new ClientThread(socket, eventID);

				if (chatRooms.containsKey(eventID))
				{
					chatRooms.get(eventID).add(t);
					System.out.println("Added user to chat room" + eventID);
				}
				else
				{
					ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
					clients.add(t);
					chatRooms.put(eventID, clients);
					System.out.println("Created chat room" + eventID);
				}
				t.start();
			}
			// To stop the server
			try
			{
				Set<Integer> rooms = chatRooms.keySet();
				for (Integer i : rooms)
				{
					for (ClientThread client : chatRooms.get(i))
					{
						ClientThread tc = client;
						try
						{
							tc.sInput.close();
							tc.sOutput.close();
							tc.socket.close();
						}
						catch (IOException ioE)
						{

						}
					}
				}
			}
			catch (Exception e)
			{
				display("Exception closing the server and clients: " + e);
			}
		}

		catch (IOException e)
		{
			String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
			display(msg);
		}
	}

	/* For the GUI to stop the server */
	protected void stop()
	{
		keepGoing = false;
		// connect to myself as Client to exit statement
		// Socket socket = serverSocket.accept();
		try
		{
			new Socket("localhost", port);
		}
		catch (Exception e)
		{
			// nothing I can really do
		}
	}

	private void display(String msg)
	{
		String time = sdf.format(new Date()) + " " + msg;
		if (sg == null)
			System.out.println(time);
		else
			sg.appendToEventLog(time + "\n");
	}

	/* to broadcast a message to all Clients */
	private synchronized void broadcast(String message, int eventID)
	{
		// add HH:mm:ss and \n to the message
		String time = sdf.format(new Date());
		String messageLf = time + " " + message + "\n";
		// display message on console or GUI
		if (sg == null)
			System.out.print(messageLf);
		else
			sg.appendToRoom(messageLf); // append in the room window

		// we loop in reverse order in case we would have to remove a Client
		// because it has disconnected
		ArrayList<ClientThread> al = chatRooms.get(eventID);
		for (int i = al.size(); --i >= 0;)
		{
			ClientThread ct = al.get(i);
			// try to write to the Client if it fails remove it from the list
			if (!ct.writeMsg(messageLf))
			{
				al.remove(i);
				display("Disconnected Client " + ct.username + " removed from list.");
			}
		}
	}

	// for a client who logoff using the LOGOUT message
	synchronized void remove(int id, int eventID)
	{
		Set<Integer> rooms = chatRooms.keySet();
		for (Integer i : rooms)
		{
			ArrayList<ClientThread> clients = chatRooms.get(i);
			for (ClientThread client : clients)
			{
				ClientThread ct = client;
				// found it
				if (ct.id == id)
				{
					chatRooms.get(i).remove(ct);
					return;
				}
			}
		}
	}

	public static void main(String[] args)
	{
		// // start server on port 1500 unless a portNumber is specified
		// int portNumber = 1500;
		//
		// // create a server object and start it
		// Server server = new Server(portNumber);
		// server.start();
	}

	/** One instance of this thread will run for each client */
	class ClientThread extends Thread
	{
		// the socket where to listen/talk
		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;
		// my unique id (easier for deconnection)
		int id;
		int eventID;
		// the Username of the Client
		String username;
		// the only type of message a will receive
		ChatMessage cm;
		// the date I connect
		String date;

		ClientThread(Socket socket, int eventID)
		{
			// a unique id
			id = ++uniqueId;
			this.eventID = eventID;
			this.socket = socket;
			/* Creating both Data Stream */
			System.out.println("Thread trying to create Object Input/Output Streams");
			try
			{
				// create output first
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				sInput = new ObjectInputStream(socket.getInputStream());
				// read the username
				username = (String) sInput.readObject();
				display(username + " just connected.");
			}
			catch (IOException e)
			{
				display("Exception creating new Input/output Streams: " + e);
				return;
			}
			// have to catch ClassNotFoundException
			// but I read a String, I am sure it will work
			catch (ClassNotFoundException e)
			{
			}
			date = new Date().toString() + "\n";
		}

		// what will run forever
		public void run()
		{
			// to loop until LOGOUT
			boolean keepGoing = true;
			while (keepGoing)
			{
				// read a String (which is an object)
				try
				{
					cm = (ChatMessage) sInput.readObject();
				}
				catch (IOException e)
				{
					display(username + " Exception reading Streams: " + e);
					break;
				}
				catch (ClassNotFoundException e2)
				{
					break;
				}
				// the messaage part of the ChatMessage
				String message = cm.getMessage();

				// Switch on the type of message receive
				switch (cm.getType())
				{

					case ChatMessage.MESSAGE:
						broadcast(username + ": " + message, eventID);
						break;
					case ChatMessage.LOGOUT:
						display(username + " disconnected with a LOGOUT message.");
						keepGoing = false;
						break;
					case ChatMessage.WHOISIN:
						writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");
						// scan al the users connected in the chatroom
						ArrayList<ClientThread> al = chatRooms.get(eventID);
						for (int i = 0; i < al.size(); ++i)
						{
							ClientThread ct = al.get(i);
							writeMsg((i + 1) + ") " + ct.username + " since " + ct.date);
						}
						break;
				}
			}
			// remove myself from the arrayList containing the list of the
			// connected Clients
			remove(id, eventID);
			close();
		}

		// try to close everything
		private void close()
		{
			// try to close the connection
			try
			{
				if (sOutput != null)
					sOutput.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				if (sInput != null)
					sInput.close();
			}
			catch (Exception e)
			{
			}
			;
			try
			{
				if (socket != null)
					socket.close();
			}
			catch (Exception e)
			{
			}
		}

		/* Write a String to the Client output stream */
		private boolean writeMsg(String msg)
		{
			// if Client is still connected send the message to it
			if (!socket.isConnected())
			{
				close();
				return false;
			}
			// write the message to the stream
			try
			{
				sOutput.writeObject(msg);
			}
			// if an error occurs, do not abort just inform the user
			catch (IOException e)
			{
				display("Error sending message to " + username);
				display(e.toString());
			}
			return true;
		}
	}
}
