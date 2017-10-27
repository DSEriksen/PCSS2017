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
	
	private String name = "David Sebastian, Architect of Destruction"; //placeholder name
	
	public String getName() {
		return name;
	}

	public void run(){
		try {
			PrintWriter outputServer = new PrintWriter(connection.getOutputStream(), true);
			BufferedReader inputServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
<<<<<<< HEAD
			
			//input = name; 
			//name = inputServer.readLine();
			
=======
			outputServer.println("Hello, love");
>>>>>>> 1d0fabd2bdae806126aa05a2f5a62245e75f41e0
			while (true) {
				String fromClient = inputServer.readLine();
				System.out.println("Client sent: " + fromClient);
				String msg = fromClient == null || fromClient.equals("") ? "null" : fromClient;
				outputServer.println(msg);
				
				if (fromClient.equals("/exit")) {
					break;
				}
			}
		} catch (Exception e) { System.out.println(e);}
		
	}

  
}
