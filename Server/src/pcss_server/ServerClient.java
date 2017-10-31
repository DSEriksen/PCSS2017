package pcss_server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class ServerClient implements Runnable {

	private Socket connection = null;
	private Server server;
	private PrintWriter outputServer;
	private BufferedReader inputServer;
	private String username = "";
	private int sUser;
	private ServerClient cUser = null;
	
	ServerClient(Socket _connection, Server _server) {
		this.connection = _connection;
		this.server = _server;
	}
	
	public void run(){
		try {
			outputServer = new PrintWriter(connection.getOutputStream(), true);
			inputServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			sendMsg("Enter username");
			username = recieveMsg();
			
			// handle invalid username here
			while(!checkUsername(username)) {
				sendMsg("Please enter a different name: ");
				username =  recieveMsg();
			}
			
			// at this point username is ok
			//outputServer.println("Welcome " + username);
			outputServer.println( server.getUserList() + " please select a user to chat with");
			sUser = Integer.parseInt(recieveMsg());
			
			selectUser(sUser);
			
			recieveMsg();
			outputServer.println("Now chatting");
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
			return "exiting...";
		}
		if (fromClient.equals("/list")){
			outputServer.println(server.getUserList());
		}
		
		return msg;
	}
	
	public void selectUser(int sUser) throws Exception{
		ServerClient cUser = new ServerClient(connection, server);
		cUser.username = server.getlistOfClients().get(sUser).getUsername();
		cUser.cUser = this;
		System.out.println("the result was: " + sUser);
		
		System.out.println(cUser.username);
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
		char specChar[] = {'?','!', '#', '%', '&', '/', '(', ')', '-', ' ', '^', '*', '.', ',', '@', '$', '{', '}', '[', ']', 'æ', 'ø', 'å'};
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
}
