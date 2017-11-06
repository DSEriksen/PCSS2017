package pcss_client;

import java.io.*;
import java.net.*;

public class Client {

	//Attributes
	final int PORT = 3000;
	final String defaultHost = "localhost";
	private Socket socket;
	private String username;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private BufferedReader stdIn = null;
	private boolean partnerConnected = false;
	
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
		}catch(Exception e){}
	}

	
	public String getUsername(){return username;}

	public void communicate() throws IOException {
		readServer();
		String msg;
		boolean done = false;
		
		(new Thread() {
			public void run() {
				while(true) {
					try {
						readServer();
					} catch (IOException e) {
						break;					
					}
				}
			}
		}).start();
		
		while(!done){
			msg = stdIn.readLine();
			if(msg.equals("/exit") || msg.equals("/close")){
				done = true;
			}
			
			if(msg.length() < 25) {
				out.println(msg);
				
			} else if(msg.length() > 25) {
				System.out.println("Message too long");
			}
		}
	}
	
	
	public void readServer() throws IOException{
		String fromServer;
		fromServer = in.readLine();
		
		if(fromServer.contains("Now chatting with")) {
			partnerConnected = true;
		}
		
		if(partnerConnected == true) {
			System.out.println("Partner says: " +fromServer);
		} else{
			System.out.println("Server says: " + fromServer);
		}
	}

	public void tellServer()throws IOException{
		String toServer = "Hello back";
		out.println(toServer);
		System.out.println("Sent: " + toServer);
	}
}

