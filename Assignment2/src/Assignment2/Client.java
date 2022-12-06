/* The Client Class - Written by Derek Molloy for the EE402 Module
 * See: ee402.eeng.dcu.ie
 * 
 * 
 */

package Assignment2;

import java.net.*;
import java.time.Instant;
import java.io.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Client implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int portNumber = 5050;
	private ClientGui gui;
    private Socket socket = null;
    private ObjectOutputStream os = null;
    private ObjectInputStream is = null;
    protected Robot robot;
    protected Instant lastMessageSent;
    protected Instant lastSuccesfulMessage;
    protected boolean isActive;

	// the constructor expects the IP address of the server - the port is fixed
    public Client(String serverIP, String robotName) {
		this.robot = new Robot(robotName, this);
		this.gui = new ClientGui(this);
		this.isActive = true;
    	if (!connectToServer(serverIP)) {
    		System.out.println("XX. Failed to open socket connection to: " + serverIP);   
    		gui.setStatus("XX. Failed to open socket connection to: " + serverIP);
    	}
    	else {
    		while(isActive) {
    			try {
	    		    System.out.println("02. -> Sending an object...");
	        		this.robot.display();
	    		    os.writeObject(gui.client.robot);
	    		    os.flush();
	    		    lastMessageSent = Instant.now();
	    		    this.robot.setLastUpdated(lastMessageSent);
	    		} 
	    	    catch (Exception e) {
	    		    System.out.println("XX. Exception Occurred on Sending: " +  e.toString());
	    		}
	    		if(lastMessageSent != null)
	    			gui.messageSent(lastMessageSent.toString());
	    		lastSuccesfulMessage = lastMessageSent;
	    		
	    		try {
	    			System.out.println("Waiting to send next robot");
					Thread.sleep(10*1000);
					if (!connectToServer(serverIP)) {
			    		System.out.println("XX. Failed to open socket connection to: " + serverIP);   
			    		gui.setStatus("XX. Failed to open socket connection to: " + serverIP);
			    	}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Thread.currentThread().interrupt();
					isActive = false;
					gui.setStatus("Disconnected from Server");
				}
    			
    		}
    	}
    }

    private boolean connectToServer(String serverIP) {
    	try { // open a new socket to the server 
    		this.socket = new Socket(serverIP,portNumber);
    		this.os = new ObjectOutputStream(this.socket.getOutputStream());
    		this.is = new ObjectInputStream(this.socket.getInputStream());
    		System.out.println("00. -> Connected to Server:" + this.socket.getInetAddress() 
    				+ " on port: " + this.socket.getPort());
    		System.out.println("    -> from local address: " + this.socket.getLocalAddress() 
    				+ " and port: " + this.socket.getLocalPort());
    		gui.setStatus("Connected to Server");
    	} 
        catch (Exception e) {
        	System.out.println("XX. Failed to Connect to the Server at port: " + portNumber);
        	System.out.println("    Exception: " + e.toString());	
    		gui.setStatus("Failed to connect to server");
        	return false;
        }
		return true;
    }

    // method to receive a generic object.
    private Object receive() 
    {
		Object o = null;
		try {
			System.out.println("03. -- About to receive an object...");
    		gui.setStatus("About to receive object");
		    o = is.readObject();
		    System.out.println("04. <- Object received...");
    		gui.setStatus("Object received");
		} 
	    catch (Exception e) {
		    System.out.println("XX. Exception Occurred on Receiving:" + e.toString());
    		gui.setStatus("Object reception failed");
		}
		return o;
    }

    public static void main(String args[]) 
    {
    	System.out.println("**. Java Client Application - EE402 OOP Module, DCU");
    	if(args.length>=1){
    		String nameToSend = "Garfield";
    		if(args.length != 1)
    			nameToSend = args[1];
    		
    		Client theApp = new Client(args[0], nameToSend);
		    while(theApp.isActive) {
		    	// wait for input
		    }
		}
    	else
    	{
    		System.out.println("Error: you must provide the address of the server");
    		System.out.println("Usage is:  java Client x.x.x.x  (e.g. java Client 192.168.7.2)");
    		System.out.println("      or:  java Client hostname (e.g. java Client localhost)");
    	}    
    	System.out.println("**. End of Application.");
    }
}