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

	// collection of clients class 'implements Runnable' void run() {}
	private LinkedList<Client> listOfClients; 
	
	Server(int _port) throws Exception {
		this.port = _port;
		listOfClients = new LinkedList<Client>();
		initialise();
	}
	
	private void initialise() throws Exception {
		serverSocket = new ServerSocket(port);
		System.out.println("Server started on port " + port);
		//socket.close();
	}
	
	public void run() throws Exception {
		boolean shouldContinue = true;
		
		while(shouldContinue) {
			Client newUser = new Client(serverSocket.accept());
			listOfClients.add(newUser);
<<<<<<< HEAD
			
			getUserList();
		
=======
>>>>>>> 1d0fabd2bdae806126aa05a2f5a62245e75f41e0
			Thread userThread = new Thread(newUser);
			userThread.start();
		}
<<<<<<< HEAD
		
=======
>>>>>>> 1d0fabd2bdae806126aa05a2f5a62245e75f41e0
		serverSocket.close();
	}
	
	
	public void getUserList() {
		for(int i = 0; i < listOfClients.size(); i++) {
			System.out.println(listOfClients.get(i).getName());
		}
	}

	
	public void shutDown() {
		// shutdown
	}
	
	
}
