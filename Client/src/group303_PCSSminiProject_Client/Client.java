package group303_PCSSminiProject_Client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	
	//Attributes
	final int PORT = 3000;
	final String defaultHost = "localhost";
	private Socket socket;
	private String username;
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	//Constructor
	public Client(){
		username = null;
		try{
			//create socket
			socket = new Socket(defaultHost, PORT);
			//create writer
			out = new PrintWriter(socket.getOutputStream(), true);
			//create reader
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//	
		}catch(Exception e){
			//todo handle exception
		}
	}
	
	
	public void setUsername(String _username){
		
		//check length
		if (_username.length() < 5 || _username.length() > 20){
			handleInvalidUsername(_username);
		}
		
		//special character check
		else if (validateUsername(_username)){
			username = _username;
		}
	}
	
	public void choosePartner(int user){
		
	}
	
	public void addUserToList(){
		
	}
	
	public boolean respondPrompt(boolean ans){
		
		if(ans = true){
			return true;
		}
		else{
			return false;
		}	
	}	
	
	public void closeChat(){
		
	}
	
	public void exit(){
		
	}
	
	public void getUsers(){
		
	}
	
	//method for handling an invalid username. Can contain more actions if neccesary.
	public void handleInvalidUsername(String _username){
		System.out.println(_username + " is not a valid username. Username must be between 5 and 20 characters.");
	}
	
	public boolean validateUsername(String _username){
		//array of characters that are not allowed to be in username
		char specChar[] = {'!', '#', '%', '&', '/', '(', ')', '-', ' ', '^', '*', '.', ',', '@', '$', '{', '}', '[', ']'};
		//char userToChar[] = _username.toCharArray();
		
		//goes through the username and the special characters array, and checks for similarities
		for (int i = 0; i < _username.length(); i++){
			for (int j = 0; j < specChar.length; j++){
				if (_username.charAt(i) == specChar[j]){
					System.out.println("Username contains '" + specChar[j] + "' which is an illegal character.");
					return false;
				}
			}
		}
		
		
		/*// Alternative solution to going through the string and characters
		for (char c: userToChar)
			for (char z:specChar){
				if (_username.charAt(c) == specChar[z]){
					return false;
			}
		}
		*/
		
	
		
		return true;
	}

	public String getUsername(){return username;}

}

