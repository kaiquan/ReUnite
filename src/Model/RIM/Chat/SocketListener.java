package Model.RIM.Chat;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketListener implements Runnable{

	ServerSocket SERVER;
	public SocketListener(ServerSocket SERVER)
	{
		this.SERVER = SERVER;
	}

	@Override
	public void run() {
	
		try {
			SERVER.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
