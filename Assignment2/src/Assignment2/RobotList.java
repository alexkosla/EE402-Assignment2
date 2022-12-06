package Assignment2;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;
import java.util.Vector;

public class RobotList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Robot> robots;
	private Vector<UUID> robotIds;
	protected int margin;
	
	public RobotList(){
		robots = new Vector<Robot>(10);
		robotIds = new Vector<UUID>(3);
		setMargin(25);
	}
	
	public void addRobot(Robot robot){
		System.out.println("30: adding robot");
		robots.addElement(robot);
		if(!robotIds.contains(robot.getRobotId()))
			robotIds.add(robot.getRobotId());
		
		// get the list of all robots with the same uuid as the one you want to add
		Vector<Robot> matchingIdRobots = getRobotsWithMatchingId(robot.getRobotId());
		System.out.println("39: just got the matchingIdRobots. Printing elemAt(0) then the last");
		
		// keep 3 robots of each id in the list max
		if(matchingIdRobots.size() > 3) {
			System.out.println("40: " + matchingIdRobots.size() + " is the size");
			robots.remove(matchingIdRobots.elementAt(0));
			
		}
	}
	
	public boolean getCollisionChance() {
		Vector<Robot> currentPositions = new Vector<Robot>();
		for(UUID id : robotIds) {
			currentPositions.add(getRobotsWithMatchingId(id).lastElement());
		}
		int robotCount = currentPositions.size();
		int robotDistance = 0;
		
		if(robotCount <= 1) {
			return false;
		}
		
		if(robotCount == 2) {
			robotDistance = getDistance(currentPositions.firstElement(), currentPositions.lastElement());
			if(robotDistance <  (margin + currentPositions.firstElement().getDimension() 
					+ currentPositions.lastElement().getDimension()))
				return true;
		}
		
		else {
			robotDistance = getDistance(currentPositions.firstElement(), currentPositions.lastElement());
			if(robotDistance <  (margin + currentPositions.firstElement().getDimension() + currentPositions.lastElement().getDimension()))
				return true;
			
			robotDistance = getDistance(currentPositions.firstElement(), currentPositions.elementAt(1));
			if(robotDistance <  (margin + currentPositions.firstElement().getDimension() + currentPositions.elementAt(1).getDimension()))
				return true;
			
			robotDistance = getDistance(currentPositions.elementAt(1), currentPositions.lastElement());
			if(robotDistance <  (margin + currentPositions.elementAt(1).getDimension() + currentPositions.lastElement().getDimension()))
				return true;
		}
		return false;
		
		
	}
	
	public int getDistance(Robot a, Robot b) {
		int x1 = a.getXpos();
		int y1 = a.getYpos();
		int x2 = b.getXpos();
		int y2 = b.getYpos();
		
		return (int) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
		
	}
    
    public class RobotTimeComparator implements Comparator<Robot>
    {
        public int compare(Robot a, Robot b) {
          return a.getLastUpdated().compareTo(b.getLastUpdated());
        }
    }
	
	public Vector<Robot> getRobotsWithMatchingId(UUID robotId){
		Vector<Robot> toReturn = new Vector<Robot>();
    	Comparator<Robot> byTime = new RobotTimeComparator();
		if(robotIds.contains(robotId)) {
			for(Robot r : robots)
			{
				if(r.robotId.equals(robotId))
					toReturn.add(r);
			}
		}
		// oldest robot comes first in the list
		toReturn.sort(byTime);
		return toReturn;
	}

    public Vector<UUID> getUniqueIds(){
    	return this.robotIds;
    }
	
	public Robot getRobot(int i) {
		return robots.elementAt(i);
	}
	
	public int getSize() {
		return robots.size();
	}
	
	public boolean getCollision(int x, int y) {
		for(Robot r : robots) {
			int dimension = r.getDimension();
			int robot_x_low = r.getXpos();
			int robot_x_high = robot_x_low + dimension;
			int robot_y_low = r.getYpos();
			int robot_y_high = robot_y_low + dimension;
			if(robot_x_low <= x && x <= robot_x_high) {
				if(robot_y_low <= y && y <= robot_y_high)
					return true;
			}
		}
		return false;
	}
	
	public Robot getCollisionRobot(int x, int y) {
		Robot toReturn = null;
		for(Robot r : robots) {
			int dimension = r.getDimension();
			int robot_x_low = r.getXpos();
			int robot_x_high = robot_x_low + dimension;
			int robot_y_low = r.getYpos();
			int robot_y_high = robot_y_low + dimension;
			if(robot_x_low <= x && x <= robot_x_high) {
				if(robot_y_low <= y && y <= robot_y_high)
					toReturn = r;
			}
		}
		return toReturn;
	}
	
	public void display() {
		System.out.println("19: There are "+ robots.size() + " robots in the vector");
		for (Robot robot : robots) {
			robot.display();
		}
	}
	
	public void display(Vector<Robot> moreRobots) {
		System.out.println("20: There are "+ moreRobots.size() + " robots in the vector");
		for (Robot robot : moreRobots) {
			robot.display();
		}
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

}
