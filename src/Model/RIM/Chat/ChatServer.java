package Model.RIM.Chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer
{
	public static ArrayList<Socket> connectionArray = new ArrayList<Socket>();
	public static ArrayList<String> currentUsers = new ArrayList<String>();
	
	public static void main(String args[]) throws IOException
	{
		try
		{
			final int PORT = 6667;
			ServerSocket SERVER = new ServerSocket(PORT);
			System.out.println("Waiting for clients....");
			
			while(true)
			{
				Socket SOCK = SERVER.accept();
				connectionArray.add(SOCK);
				
				System.out.println("Client connected from: "+ SOCK.getLocalAddress().getHostName());
				
				addUserName(SOCK);
				
				ChatServerThread CHAT = new ChatServerThread(SOCK);
				
				Thread X = new Thread(CHAT);
				X.start();
			}
		}
		catch(BindException e)
		{
			System.out.println("Server is already running...");
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Closing things up....");
		}
	}
	
	public static void addUserName(Socket X) throws IOException
	{
		Scanner input = new Scanner(X.getInputStream());
		String userName = input.nextLine();
		currentUsers.add(userName);
		
		//To inform all other users about the new user
		for(int i = 1; i<=ChatServer.connectionArray.size(); i++)
		{
			Socket temp_sock = (Socket) ChatServer.connectionArray.get(i-1);
			PrintWriter OUT = new PrintWriter(temp_sock.getOutputStream());
			OUT.println("#?!" + currentUsers);//Send the whole ArrayList object
			OUT.flush();
		}
	}
}
