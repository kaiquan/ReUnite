package Model.RIM.Chat;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;

import View.RIM.Components.ChatClientGUI;

public class ChatClientThread implements Runnable
{
	Calendar calendar = new GregorianCalendar();
	DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	
	Socket SOCK;
	Scanner INPUT;
	Scanner SEND = new Scanner(System.in);
	PrintWriter OUT;
	
	public ChatClientThread(Socket x)
	{
		this.SOCK = x;
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
				OUT.flush();
				checkStream();
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
	
	public void disconnect() throws IOException
	{
		OUT.println(ChatClientGUI.userName + " has disconnected.");
		OUT.flush();
		SOCK.close();
		JOptionPane.showMessageDialog(null, "You are disconnected");
		System.exit(0);
	}
	
	public void checkStream()
	{
		while(true)
		{
			receive();
		}
	}
	
	public void receive()
	{
		if(INPUT.hasNext())
		{
			String MESSAGE = INPUT.nextLine();
			
			if(MESSAGE.contains("#?!"))
			{
				String TEMP1 = MESSAGE.substring(3);
				TEMP1 = TEMP1.replace("[", "");
				TEMP1 = TEMP1.replace("]", "");
				
				String[] currentUsers = TEMP1.split(",  ");
				
				ChatClientGUI.JL_Online.setListData(currentUsers);
			}
			else
			{
				ChatClientGUI.TA_CONVERSATION.append(MESSAGE +"\t at " +dateFormat.format(calendar.getTime())+ "\n");
			}
		}
	}
	
	public void send(String x)
	{	
		OUT.println(ChatClientGUI.userName + ": " + x);
		OUT.flush();
		
		ChatClientGUI.TF_MESSAGE.setText("");
	}
}
