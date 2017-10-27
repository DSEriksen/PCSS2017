package group303_PCSSminiProject_Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client implements Runnable {

	private Socket connection = null;
	
	Client(Socket _connection) {
		this.connection = _connection;
	}
	
	public void run(){
		try {
			PrintWriter outputServer = new PrintWriter(connection.getOutputStream(), true);
			BufferedReader inputServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			outputServer.println("Hello, love");
			while (true) {
				String fromClient = inputServer.readLine();
				System.out.println("Client sent: " + fromClient);
				String msg = fromClient == null || fromClient.equals("") ? "null" : fromClient;
				outputServer.println(msg);
				
				if (fromClient.equals("/exit")) {
					break;
				}
			}
		} catch (Exception e) { System.out.println(e);
		
	}
	}
}
