package group303_PCSSminiProject_Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client implements Runnable {

	private Socket connection = null;
	private Server server;
	private PrintWriter outputServer;
	private BufferedReader inputServer;
	private String userName;
	
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
			
			// "send username"
			//username = read()
			//username = username;
			
			outputServer = new PrintWriter(connection.getOutputStream(), true);
			inputServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			//input = name; 
			//name = inputServer.readLine();
			sendMsg("Hello, love, please enter your username");
			userName = recieveMsg();
			sendMsg("Your username is: " + userName);
			server.getUserList();
			sendMsg(server.getUserList());
			

			while (true) {
				String message = recieveMsg();
				sendMsg(message);

				

				
				
			}
		} catch (Exception e) { System.out.println(e);}
		
		
	}
	
	public void sendMsg (String msg) {
		outputServer.println(msg);
	}
	
	public String recieveMsg() throws Exception{
		String fromClient = inputServer.readLine();
		System.out.println("Client sent: " + fromClient);
		String msg = fromClient == null || fromClient.equals("") ? "null" : fromClient;
		
		if (fromClient.equals("/exit")) {
			//break;
		}
		if (fromClient.equals("/list")){
			outputServer.println(server.getUserList());
		}
		
		return msg;
	}

}
