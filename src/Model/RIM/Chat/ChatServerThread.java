package Model.RIM.Chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerThread implements Runnable
{
	//Globals
	Socket SOCK;
	ChatRoom room;
	private Scanner INPUT;
	@SuppressWarnings("unused")
	private PrintWriter OUT;
	String MESSAGE = "";
	
	public ChatServerThread(Socket X, ChatRoom room)
	{
		this.SOCK = X;
		this.room = room;
	}
	
	public void CheckConnection() throws IOException
	{
		if(!SOCK.isConnected())
		{
			for(int i = 1; i <= room.connectionArray.size(); i++)
			{
				if(room.connectionArray.get(i) == SOCK)
				{
					room.connectionArray.remove(i);
				}
			}
			
			for(int i=1; i<= room.connectionArray.size(); i++)
			{
				Socket TEMP_SOCK = (Socket) room.connectionArray.get(i-1);
				PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
				TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName() + "disconnected!");
				TEMP_OUT.flush();
				//show disconnection at SERVER
				System.out.println(TEMP_SOCK.getLocalAddress().getHostName() + " disconnected!");
			}
		}
	}

	@Override
	public void run()
	{
		try
		{
			try
			{
				INPUT = new Scanner(SOCK.getInputStream());
				OUT = new PrintWriter(SOCK.getOutputStream());
				
				while(true)
				{
					CheckConnection();
					
					if(!INPUT.hasNext())
					{
						return;
					}
					
					MESSAGE = INPUT.nextLine();
					
					System.out.println("Client said: "+MESSAGE);
					
					for(int i = 1; i<=room.connectionArray.size(); i++)
					{
						Socket TEMP_SOCK = (Socket) room.connectionArray.get(i-1);
						PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
						TEMP_OUT.println(MESSAGE);
						TEMP_OUT.flush();
						System.out.println("Sent to: " + TEMP_SOCK.getLocalAddress().getHostName());
					}
				}
			}
			finally
			{
				SOCK.close();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
