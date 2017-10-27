package group303_PCSSminiProject_Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client implements Runnable {

	private Socket connection = null;
	private Server server;
	
	Client(Socket _connection, Server _server) {
		this.connection = _connection;
		this.server = _server;
	}
	
	private String name = "David Sebastian, Architect of Destruction"; //placeholder name
	
	public String getName() {
		return name;
	}

	public void run(){
		try {
			PrintWriter outputServer = new PrintWriter(connection.getOutputStream(), true);
			BufferedReader inputServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			//input = name; 
			//name = inputServer.readLine();
			outputServer.println("Hello, love");

			while (true) {
				String fromClient = inputServer.readLine();
				System.out.println("Client sent: " + fromClient);
				String msg = fromClient == null || fromClient.equals("") ? "null" : fromClient;
				outputServer.println(msg);
				
				if (fromClient.equals("/exit")) {
					break;
				}
				if (fromClient.equals("/list")){
					outputServer.println(server.getUserList());
					
				}
			}
		} catch (Exception e) { System.out.println(e);}
		
	}

  
}
