package group303_PCSSminiProject_Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	public static void main(String[] args) {
		int port = 3000;
		
		try {
			
			Server server = new Server(port);
			server.run();
		
		} catch (Exception e) { System.out.println(e); }
	}
	
	private int port;
	private ServerSocket serverSocket;
	//private BufferedReader inputServer = null;
	//private PrintWriter outputServer = null;

	// collection of clients class 'implements Runnable' void run() {}
	private LinkedList<Client> listOfClients; 
	
	Server(int _port) throws Exception {
		this.port = _port;
		listOfClients = new LinkedList<Client>();
		initialise();
	}
	
	private void initialise() throws Exception {
		serverSocket = new ServerSocket(port);
		Socket client = serverSocket.accept();

		PrintWriter outputServer = new PrintWriter(client.getOutputStream(), true);
		outputServer.println("Hello, love");
		
		//BufferedReader inputServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
		//socket.close();
		System.out.println("Server started on port " + port);
		
	}
	
	public void run() throws Exception {
		boolean shouldContinue = true;
		
		while(shouldContinue == true) {
			
			Client newUser = new Client(serverSocket.accept());
			
			listOfClients.add(newUser);
		
			Thread userThread = new Thread(newUser);
			
			userThread.start();
			
			shouldContinue = true;
			
		}
		
		serverSocket.close();		
		
	}
	
	public void shutDown() {
		// shutdown
	}
}
