package Assignment2;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class Robot implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int dimension;
	private int xpos;
	private int ypos;
	private int velocity;
	protected DirectionEnum orientation;
	protected Instant lastUpdated;
	protected UUID robotId;

	public Robot(String name, Client client) {
		setName(name);
		setDimension(15);
		setXpos(150);
		setYpos(150);
		this.setVelocity(10);
		this.robotId = UUID.randomUUID();
		setOrientation(DirectionEnum.NORTH);
		setLastUpdated(Instant.now());
	}
	
	public void display() {
		System.out.println("<<<");
		System.out.println("\tname is: " + this.getName());
		System.out.println("\trobotId is: " + this.robotId.toString());
		System.out.println("\tlastTime is: " + this.getLastUpdated().toString());
		System.out.println("\txpos is: " + this.getXpos());
		System.out.println("\typos is: " + this.getYpos());
		System.out.println("\tdimension is: " + this.getDimension());
		System.out.println("\tvelocity is: " + this.getVelocity());
		System.out.println(">>>");
	}
	
	public void move(DirectionEnum newDirection) {
		this.orientation = newDirection;
		switch(this.orientation) {
			case NORTH:
				this.setYpos(getYpos() - getVelocity()); //minus because north and canvas is upside down?
				break;
			case SOUTH:
				this.setYpos(getYpos() + getVelocity());
				break;
			case WEST:
				this.setXpos(getXpos() - getVelocity());
				break;
			case EAST:
				this.setXpos(getXpos() + getVelocity());
				break;
		}
//		display();
	}
	
	public void rotateLeft() {
		switch(this.orientation) {
			case NORTH:
				this.orientation = DirectionEnum.WEST;
				break;
			case SOUTH:
				this.orientation = DirectionEnum.EAST;
				break;
			case WEST:
				this.orientation = DirectionEnum.SOUTH;
				break;
			case EAST:
				this.orientation = DirectionEnum.NORTH;
				break;
		}
	}
	
	public void rotateRight() {
		switch(this.orientation) {
			case NORTH:
				this.orientation = DirectionEnum.EAST;
				break;
			case SOUTH:
				this.orientation = DirectionEnum.WEST;
				break;
			case WEST:
				this.orientation = DirectionEnum.NORTH;
				break;
			case EAST:
				this.orientation = DirectionEnum.SOUTH;
				break;
		}
	}

	public UUID getRobotId() {
		return robotId;
	}

	public void setRobotId(UUID robotId) {
		this.robotId = robotId;
	}

	public Instant getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Instant lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DirectionEnum getOrientation() {
		return orientation;
	}

	public void setOrientation(DirectionEnum orientation) {
		this.orientation = orientation;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
}
