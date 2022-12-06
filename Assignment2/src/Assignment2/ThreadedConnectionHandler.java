/* The Connection Handler Class - Written by Derek Molloy for the EE402 Module
 * See: ee402.eeng.dcu.ie
 */

package Assignment2;

import java.net.*;
import java.io.*;

public class ThreadedConnectionHandler extends Thread
{
    private Socket clientSocket = null;				// Client socket object
    private ObjectInputStream is = null;			// Input stream
    private ObjectOutputStream os = null;			// Output stream
    private DateTimeService theDateService;
    private ServerGui gui;
    protected RobotList robotList;
    
	// The constructor for the connection handler
    public ThreadedConnectionHandler(Socket clientSocket, ServerGui gui, RobotList robotList) {
    	System.out.println("15: creating a new connectionHandler");
    	this.robotList = robotList;
        this.gui = gui;
        this.clientSocket = clientSocket;
        //Set up a service object to get the current date and time
        theDateService = new DateTimeService();
    }

    // Will eventually be the thread execution method - can't pass the exception back
    public void run() {
         try {
            this.is = new ObjectInputStream(clientSocket.getInputStream());
            this.os = new ObjectOutputStream(clientSocket.getOutputStream());
            while (this.readCommand()) {}
         } 
         catch (IOException e) 
         {
        	System.out.println("XX. There was a problem with the Input/Output Communication:");
            e.printStackTrace();
         }
    }

    // Receive and process incoming objects from client socket 
    private boolean readCommand() {
        Robot robot = null;
        try {
    		robot = (Robot) is.readObject();
        	System.out.println("10: received an object");
    		is.close();
    		this.robotList.addRobot(robot);
    		System.out.println("11: added robot to robotList");
    		this.gui.robotDisplay.repaint();
    		
        } 
        catch (Exception e){    // catch a general exception
        	System.out.println("Failed to read an object. 55: " + e.getMessage());
        	this.closeSocket();
            return false;
        }
        return true;
    }

    // Use our custom DateTimeService Class to get the date and time
    private void getDate() {	// use the date service to get the date
        String currentDateTimeText = theDateService.getDateAndTime();
        this.send(currentDateTimeText);
    }

    // Send a generic object back to the client 
    private void send(Object o) {
        try {
            System.out.println("02. -> Sending (" + o +") to the client.");
            this.os.writeObject(o);
            this.os.flush();
        } 
        catch (Exception e) {
            System.out.println("XX." + e.getStackTrace());
        }
    }
    
    // Send a pre-formatted error message to the client 
    public void sendError(String message) { 
        this.send("Error:" + message);	//remember a String IS-A Object!
    }
    
    // Close the client socket 
    public void closeSocket() { //gracefully close the socket connection
        try {
        	System.out.println("Closing the socket");
            this.os.close();
            this.is.close();
            this.clientSocket.close();
        } 
        catch (Exception e) {
            System.out.println("XX. " + e.getStackTrace());
        }
    }
}