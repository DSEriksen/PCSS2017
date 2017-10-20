package group303_PCSSminiProject_Client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	
	//Attributes
	Socket socket;
	String username;
	
	//Constructor
	public Client(){
		
		try{
		socket = new Socket("localhost", 3000);
		}catch(Exception e){}
	}
	
	
	public void setUsername(String _username){
		
		//length check
		if (_username.length() < 5 || _username.length() > 20){
			System.out.println("Username either too short or too long (from 5 to 20)");
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
