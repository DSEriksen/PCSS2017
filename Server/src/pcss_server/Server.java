package pcss_server;

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
	private LinkedList<ServerClient> listOfClients;
	private int userIndex = 0;
	
	Server(int _port) throws Exception {
		this.port = _port;
		listOfClients = new LinkedList<ServerClient>();
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
			ServerClient newUser = new ServerClient(serverSocket.accept(), this, userIndex);
			listOfClients.add(newUser);
			userIndex++;
			
			getUserList();

			Thread userThread = new Thread(newUser);
			userThread.start();
		}
		serverSocket.close();
	}
	

	
	public String getUserList() {
		StringBuffer list = new StringBuffer();
		for(int i = 0; i < listOfClients.size(); i++) {
			System.out.println(i + " " + listOfClients.get(i).getUsername());
			list.append(i + ": " + listOfClients.get(i).getUsername()+",").append(" ");
		}
		return list.toString();
	}
	
	
	public LinkedList<ServerClient> getlistOfClients() {
		return listOfClients;
	}

	public void shutDown() {
		// shutdown
	}
	
	public LinkedList<ServerClient> getUsers() {
		return listOfClients;
	}
}
