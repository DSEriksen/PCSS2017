package group303_PCSSminiProject_Server;

import java.net.*;

public class Client implements Runnable {

	private Socket connection = null;
	
	Client(Socket _connection) {
		this.connection = _connection;
	}
	
	public void run(){
		
	}
}
