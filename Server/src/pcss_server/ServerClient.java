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
	private boolean request = false;
	
	ServerClient(Socket _connection, Server _server) {
		this.connection = _connection;
		this.server = _server;
	}
	
	public void run(){
		try {
			outputServer = new PrintWriter(connection.getOutputStream(), true);
			inputServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			sendMsg("Enter username: ");
			username = recieveMsg();
			
			// handle invalid username here
			while(!checkUsername(username)) {
				outputServer.println("Please enter a different name: ");
				username =  recieveMsg();
			}

			// user is sent to lobby
			goToLobby();
			
			if (!request)
				outputServer.println("Now chatting with " + username+". Press ENTER to refresh chat");
			
			while (true) {
				String message = recieveMsg();
				sendMsgChat(message);
			}
		} catch (Exception e) { System.out.println(e);}
	}
	
	public void sendMsg (String msg) {
		outputServer.println(msg);
	}
	
	public void sendMsgChat(String msg) {
		cUser.sendMsg(msg);
	}
	
	public String recieveMsg() throws Exception{
		String fromClient = inputServer.readLine();
		System.out.println(username+ " sent: " + fromClient);
		String msg = fromClient == null || fromClient.equals("") ? "null" : fromClient;
		
		if (fromClient.equals("/exit")) {
			return "exiting...";
		}
		if (fromClient.equals("/list")){
			outputServer.println(server.getUserList());
		}
		
		return msg;
	}

	public void goToLobby()throws Exception{
		outputServer.println("Would you like to join the lobby or select a user to chat with? (join/select)");
		boolean choiceMade = false;
		
		// var for storing input
		String choice;
		
		while(!choiceMade && !request){
			choice = recieveMsg();
			
			if(choice.equals("select")) {
				outputServer.println(server.getUserList() + " please select a user to chat with");
				sUser = Integer.parseInt(recieveMsg());
				if (selectUser(sUser))
					choiceMade = true;
			} else {
				outputServer.println(server.getUserList() + " please select a user to chat with. ENTER to refresh or 'select' to pick someone");
			}
		}
	}
	
	// java pcss_client.Main
	
	boolean selectUser(int userIndex) throws Exception{
		ServerClient targetUser = server.getUsers().get(userIndex);
		System.out.println(this.getUsername() + " trying to connect to " + targetUser.getUsername());
		
		if (!this.username.equals(targetUser.getUsername())) {
			this.cUser = targetUser;
			cUser.sendRequest(this);
			return true;
		}

		System.out.println("user tried to connect to themself");
		sendMsg("You cannot chat with yourself. Press ENTER to return to lobby");
		return false;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String _username) {
		username = _username;
	}
	
	public void sendRequest(ServerClient sender) {
		request = true;
		this.cUser = sender;
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
	}
}
