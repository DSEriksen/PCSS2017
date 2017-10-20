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
	private InputStream input = null;
	
	//Constructor
	public Client(){
		username = null;
		//create socket
		try{
		socket = new Socket(defaultHost, PORT);
		}catch(IOException e){
			//todo handle exception
		}
		//create input stream
		try{
			input = socket.getInputStream();
		}catch(IOException e){
			//todo handle exception
		}
	}
	
	
	public void setUsername(String _username){
		
		//check length
		if (_username.length() < 5 || _username.length() > 20){
			System.out.println("Username is either too short or too long (from 5 to 20)");
		}
		
		//special character check
		else if (userCharCheck(_username)){
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
	public boolean userCharCheck(String _username){
		char specChar[] = {'!', '#', '%', '&', '/', '(', ')', '-', ' '};
		
		for (int i = 0; i < _username.length(); i++){
			for (int j = 0; j < specChar.length; j++){
				if (_username.charAt(i) == specChar[j]){
					return false;
				}
			}
		}
		return true;
	}
}
