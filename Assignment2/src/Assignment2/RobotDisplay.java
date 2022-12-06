package Assignment2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;
import java.util.Vector;

@SuppressWarnings("serial")
public class RobotDisplay extends Canvas implements MouseListener {
    protected ServerGui gui;
    protected RobotList robotList;
    
    public RobotDisplay(ServerGui gui, int width, int height){
    		this.gui = gui;
            this.setSize(width,height);
            this.robotList = gui.robotList;
            this.addMouseListener(this);
            this.update();
    }
    
    public void update() { this.repaint(); }
    
    public void paint(Graphics g){
    	int i = 0;
		gui.updateAlert(robotList.getCollisionChance());
		
    	for(UUID id : robotList.getUniqueIds())
    	{
    		Vector<Robot> robotsWithSameIds = robotList.getRobotsWithMatchingId(id);
			Color color = null;
    		int j = 95;
    		if(i == 0)
    			color = new Color(150, 50, 50, j);
    		else if(i == 1)
    			color = new Color(50, 150, 50, j);
    		else
    			color = new Color(50, 50, 150, j);
    		i++;
    		
    		for(Robot robot : robotsWithSameIds)
    		{
    			System.out.println("19: IN robotsWithSameIds FOR LOOP");
    			g.setColor(color);
    			g.drawOval(robot.getXpos(), robot.getYpos(), robot.getDimension(), robot.getDimension());
    			String directionToDraw = "";
    			
    			switch(robot.orientation) {
    				case NORTH:
    					directionToDraw = "N";
    					break;
    				case SOUTH:
    					directionToDraw = "S";
    					break;
    				case EAST:
    					directionToDraw = "E";
    					break;
    				case WEST:
    					directionToDraw = "W";
    					break;
    			}
    			g.drawString(Integer.toString(robot.getVelocity()) + ", "+directionToDraw, robot.getXpos()-robot.getDimension(), robot.getYpos());
    			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), j);
    			color.brighter();
    			j = j + 80;
    		}
    	}
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		Robot collidedRobot = robotList.getCollisionRobot(e.getX(), e.getY());
		if(collidedRobot != null)
			gui.selectedInfo.setText("<html>Robot Name: " + collidedRobot.getName() + "<br> Robot Dimension: " + collidedRobot.getDimension() +"</html>");
		else {
			gui.selectedInfo.setText("<html>Robot Name: N/A <br> Robot Dimension: N/A </html>");
		
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
