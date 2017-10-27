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

			
			while (true) {
				
				outputServer.println("Hello, love");
				
				String fromClient = inputServer.readLine();
				System.out.println("Client sent: " + fromClient);
				
				if (fromClient == "exit") {
					break;
				}
			}
		} catch (Exception e) { System.out.println(e);
		
	}
	}
}
