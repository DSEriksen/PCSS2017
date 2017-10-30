package group303_PCSSminiProject_Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client implements Runnable {

	private Socket connection = null;
	private Server server;
	private String username;
	Client(Socket _connection, Server _server) {
		this.connection = _connection;
		this.server = _server;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String _username) {
		username = _username;
	}
	public boolean checkUsername(String _username){

		boolean validUser = true;
		//method for handling an invalid username. Can contain more actions if neccesary.
		//check length
		if (_username.length() < 5 || _username.length() > 20){
			System.out.println(_username + " is not a valid username. Username must be between 5 and 20 characters.");
			validUser = false;
		}

		//special character check
		//array of characters that are not allowed to be in username
		char specChar[] = {'?','!', '#', '%', '&', '/', '(', ')', '-', ' ', '^', '*', '.', ',', '@', '$', '{', '}', '[', ']'};
		//char userToChar[] = _username.toCharArray();

		//goes through the username and the special characters array, and checks for similarities
		for (int i = 0; i < _username.length(); i++){
			for (int j = 0; j < specChar.length; j++){
				if (_username.charAt(i) == specChar[j]){
					System.out.println("Username contains '" + specChar[j] + "' which is an illegal character.");
					validUser = false;
				}
			}
		}
		return validUser;
		
		/*// Alternative solution to going through the string and characters
		for (char c: userToChar)
			for (char z:specChar){
				if (_username.charAt(c) == specChar[z]){
					return false;
			}
		}
		*/
	}
	
	public void run(){
		try {
			PrintWriter outputServer = new PrintWriter(connection.getOutputStream(), true);
			BufferedReader inputServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			//input = name;
			outputServer.println("Hello! Please enter a username");
			username = inputServer.readLine();
			

			while(!checkUsername(username)) {
				outputServer.println("Please enter a different name: ");
				username = inputServer.readLine();
			}
			outputServer.println("Welcome " + username);
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
