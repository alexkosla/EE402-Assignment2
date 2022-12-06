package Assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ClientGui extends JFrame implements ActionListener, WindowListener {

	private JButton buttonUp, buttonLeft, buttonRight, buttonDown, buttonClockwise, buttonCounterClockwise, buttonFaster, buttonSlower, buttonQuit;
	private JPanel mainPanel, leftPanel, midPanel, rightPanel;
	private JLabel status;
	private JLabel lastTime;
	private JLabel speed;
	protected Client client;
	
	public ClientGui(Client client) {
		// call the parent class constructor - sets the frame title
		super("Robot Client");
		this.client = client;
		this.addWindowListener(this);
		
		this.status = new JLabel("<html> Client initialized </html>");
		this.lastTime = new JLabel("<html>No message sent yet</html>");
		this.speed = new JLabel("<html>The robot's speed is " + this.client.robot.getVelocity() + " </html>");
		
		this.mainPanel = new JPanel(new GridLayout(1,3));
		this.leftPanel = new JPanel(new GridLayout(4,1));
		this.midPanel = new JPanel(new GridLayout(4,1));
		
		this.rightPanel = new JPanel(new GridLayout(4,1));
		this.rightPanel.setPreferredSize(new Dimension(150, 300));
		
		this.buttonUp = new JButton("Up");
		this.buttonLeft = new JButton("Left");
		this.buttonRight = new JButton("Right");
		this.buttonDown = new JButton("Down");
		this.buttonClockwise = new JButton("Rotate Right");
		this.buttonCounterClockwise = new JButton("Rotate Left");
		this.buttonFaster = new JButton("Go faster");
		this.buttonSlower = new JButton("Go slower");
		this.buttonQuit = new JButton("Quit");
		
		this.buttonUp.addActionListener(this);
		this.buttonLeft.addActionListener(this);
		this.buttonRight.addActionListener(this);
		this.buttonDown.addActionListener(this);
		this.buttonClockwise.addActionListener(this);
		this.buttonCounterClockwise.addActionListener(this);
		this.buttonFaster.addActionListener(this);
		this.buttonSlower.addActionListener(this);
		this.buttonQuit.addActionListener(this);
		
		// default layout is border layout for Frame/JFrame
		this.getContentPane().add(mainPanel);
		this.mainPanel.add(this.leftPanel);
		this.mainPanel.add(this.midPanel);
		this.mainPanel.add(this.rightPanel);
		
		this.leftPanel.add(this.buttonUp);
		this.leftPanel.add(this.buttonDown);
		this.leftPanel.add(this.buttonLeft);
		this.leftPanel.add(this.buttonRight);
		
		this.midPanel.add(this.buttonCounterClockwise);
		this.midPanel.add(this.buttonClockwise);
		this.midPanel.add(this.buttonFaster);
		this.midPanel.add(this.buttonSlower);
		
		this.rightPanel.add(this.status);
		this.rightPanel.add(this.lastTime);
		this.rightPanel.add(this.speed);
		this.rightPanel.add(this.buttonQuit);
		
		this.pack();
		this.setVisible(true);
	}
	
	public void setStatus(String newStatus) {
		this.status.setText("<html>" + newStatus + "</html>");
		System.out.println("status set to: "+newStatus);
	}
	
	public void messageSent(String time) {
		this.lastTime.setText("<html>Last message successfully sent at: " + time +"</html>");
	}
	// TODO Auto-generated method stub
	@Override
	public void actionPerformed(ActionEvent e) {
		
			switch(e.getActionCommand()) {
				case "Up":
					this.client.robot.move(DirectionEnum.NORTH);
					break;
				case "Left":
					this.client.robot.move(DirectionEnum.WEST);
					break;
				case "Down":
					this.client.robot.move(DirectionEnum.SOUTH);
					break;
				case "Right":
					this.client.robot.move(DirectionEnum.EAST);
					break;
				case "Rotate Right":
					this.client.robot.rotateRight();
					break;
				case "Rotate Left":
					this.client.robot.rotateLeft();
					break;
				case "Go faster":
					this.client.robot.setVelocity(this.client.robot.getVelocity() + 1);
					this.speed.setText("<html>The robot's speed is " + this.client.robot.getVelocity() + " </html>");
					break;
				case "Go slower":
					this.client.robot.setVelocity(this.client.robot.getVelocity() - 1);
					this.speed.setText("<html>The robot's speed is " + this.client.robot.getVelocity() + " </html>");
					break;
				case "Quit":
					System.out.println("Disconnecting from server");
					this.client.isActive = false;
					System.exit(0);
					break;
			}
			
		}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
