package pcss_client;

import java.io.*;
import java.net.*;

public class Client {
 //Yet another test
	//Attributes
	final int PORT = 3000;
	final String defaultHost = "localhost";
	private Socket socket;
	private String username;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private BufferedReader stdIn = null;
	
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
			//read user input
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			//messaging method
			communicate();
		}catch(Exception e){
			//todo handle exception
		}
	}
	
	

	
	public void choosePartner(int user){
		
	}
	
	public void addUserToList(){
		
	}
	
	public boolean respondPrompt(boolean ans){
		return ans;
	}	
	
	public void closeChat(){
		
	}
	
	public void exit(){
		
	}
	
	public void getUsers(){
		
	}
	
	
	public String getUsername(){return username;}

	public void communicate() throws IOException {
		readServer();
		String msg;
		boolean done = false;
		while(!done){
			msg = stdIn.readLine();
			if(msg.equals("/exit") || msg.equals("/close")){
				done = true;
			}
			out.println(msg);
			readServer();
		}
	}

	
	public void readServer() throws IOException{
		String fromServer;
		fromServer = in.readLine();
		System.out.println("Server says: " + fromServer);
	}

	public void tellServer()throws IOException{
		String toServer = "Hello back";
		out.println(toServer);
		System.out.println("Sent: " + toServer);
	}
}

