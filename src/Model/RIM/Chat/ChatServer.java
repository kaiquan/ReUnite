package Model.RIM.Chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ChatServer  implements Runnable
{
	private static ChatRoomList chatRooms = new ChatRoomList();
	private static ServerSocket SERVER;
	private static final int PORT = 6667;
	private boolean isStopped = false;
	private Thread runningThread = null;
	
	@Override
	public void run() {
		synchronized(this){
            this.runningThread = Thread.currentThread();
        }
				openServerSocket();
				System.out.println("Waiting for clients....");
			
				while(!isStopped())
				{
					Socket SOCK = null;
					try
					{
						 System.out.println("WAITING ON THE SOCKET");
						 SOCK = SERVER.accept();
						 
						 System.out.println("Client connected from: "+ SOCK.getLocalAddress().getHostName());
					
						addChatRoom(SOCK);
					}
					catch(BindException e)
					{
						System.out.println("Server is already running...");
					}
					catch (IOException e)
					{
		                if(isStopped()) {
		                    System.out.println("Server Stopped.") ;
		                    return;
		                }
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
		}

	public ChatServer() 
	{
		
	}

	
	public static void main(String args[]) throws IOException
	{
		
	}
    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.SERVER.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.SERVER = new ServerSocket(this.PORT);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port "+PORT, e);
        }
    }
	
	public static void addChatRoom(Socket X) throws IOException
	{
		Scanner input = new Scanner(X.getInputStream());
		String text = input.nextLine();
		String[] userNameAndEvent = text.split("&*&*&");
		
		String userName = userNameAndEvent[0].trim();
		String room = userNameAndEvent[1].trim();

		ChatRoom chatRoom = new ChatRoom(room, "");
		chatRoom.addUser(X, userName);
		
		chatRooms.addRoom(chatRoom);
	}
}

