package Assignment2;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ServerGui extends JFrame implements ActionListener, WindowListener {
	private JButton buttonToggleMargin, buttonQuit;
	private JPanel leftPanel, rightPanel, selectedRobotPanel, alertPanel, marginPanel;
	protected JLabel selectedInfo;
	protected JLabel alert;
	protected JLabel marginValue;
	protected RobotDisplay robotDisplay;
	protected ThreadedServer server;
	protected RobotList robotList; // maybe needs to be static?
	
	public ServerGui(RobotList robotList) {
		// call the parent class constructor - sets the frame title
		super("Simple Swing Example");
		System.out.println("opened a new ServerGui");
		this.robotList = robotList;
		this.robotDisplay = new RobotDisplay(this, 300, 300);
		
		this.addWindowListener(this);
		
		this.buttonToggleMargin = new JButton("Toggle Margin");
		this.buttonToggleMargin.setPreferredSize(new Dimension(150, 50));
		this.buttonQuit = new JButton("Quit");
		this.buttonQuit.setPreferredSize(new Dimension(150, 50));
		
		this.selectedInfo = new JLabel("<html>Robot Name: N/A <br> Robot Dimension: N/A </html>");
		this.alert = new JLabel("<html> No alerts </html>");
		this.marginValue = new JLabel("<html> Margin is set to: " + robotList.margin + " </html>");
		
		this.leftPanel = new JPanel();
		this.rightPanel = new JPanel();
		this.leftPanel.setPreferredSize(new Dimension(300, 300));
		this.rightPanel.setPreferredSize(new Dimension(150, 300));
		this.leftPanel.add(robotDisplay);
		this.getContentPane().add("West", this.leftPanel);
		this.getContentPane().add("East", this.rightPanel);
		
		leftPanel.setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.DARK_GRAY));
		rightPanel.setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.DARK_GRAY));
		
		this.selectedRobotPanel = new JPanel();
		this.selectedRobotPanel.setPreferredSize(new Dimension(150, 50));
		this.selectedRobotPanel.add(selectedInfo);
		this.rightPanel.add(this.selectedRobotPanel);
		
		this.alertPanel = new JPanel();
		this.alertPanel.setPreferredSize(new Dimension(150, 75));
		alertPanel.setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.GREEN));
		
		this.marginPanel = new JPanel();
		this.marginPanel.setPreferredSize(new Dimension(150, 50));
		marginPanel.setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.GREEN));
		
		this.alertPanel.add(alert);
		this.marginPanel.add(marginValue);
		this.rightPanel.add(this.alertPanel);
		this.rightPanel.add(this.marginPanel);
		this.rightPanel.add(this.buttonToggleMargin);
		this.rightPanel.add(this.buttonQuit);
		
		
		this.buttonToggleMargin.addActionListener(this);
		this.buttonQuit.addActionListener(this);
		
		this.pack();
		this.setVisible(true);
	}
	
	public void updateAlert(boolean isNotAlert) {
		if(isNotAlert) {
			this.alert.setText("<html> POTENTIAL <br> COLLISION <br> POSSIBLE </html>");
			alertPanel.setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.RED));
			marginPanel.setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.RED));
		}
		else {
			this.alert.setText("<html> No alerts </html>");
			alertPanel.setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.GREEN));
			marginPanel.setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.GREEN));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "Toggle Margin":
				System.out.println("current margin is: " + robotList.margin);
				robotList.margin = (robotList.margin == 25) ? 0 : 25;
				System.out.println("new margin is: " + robotList.margin);
				this.marginValue.setText("<html> Margin is set to: " + robotList.margin + " </html>");
				updateAlert(robotList.getCollisionChance());
				break;
			case "Quit":
				System.out.println("Turning off Server");
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
