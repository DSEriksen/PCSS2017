package group303_PCSSminiProject_Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	public static void main(String[] args) {
		int port = 3002;
		
		try {
			
			Server server = new Server(port);
			server.run();
		
		} catch (Exception e) { System.out.println(e); }
	}
	
	private int port;
	private ServerSocket serverSocket;
	

	// collection of clients class 'implements Runnable' void run() {}
	private LinkedList<Client> listOfClients; 
	
	Server(int _port) throws Exception {
		this.port = _port;
		initialise();
	}
	
	private void initialise() throws Exception {
		//Socket client = socket.accept();
		//BufferedReader inputServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
		//socket.close();
		serverSocket = new ServerSocket(port);
		System.out.println("Server started on port " + port);
	}
	
	public void run() throws Exception {
		boolean shouldContinue = true;
		
		while(shouldContinue == true) {
			
			Client newUser = new Client(serverSocket.accept());
			listOfClients.add(newUser);
		
			Thread user = new Thread(newUser);
			
			user.run();
			
			shouldContinue = false;
			
		}
		
		serverSocket.close();		
		
	}
	
	public void shutDown() {
		// shutdown
	}
}
