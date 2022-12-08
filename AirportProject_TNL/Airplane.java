/**
 * 
 * @author Truong Le
 *
 */
public class Airplane implements Comparable<Airplane> {
	
	private int ID; // Unique ID
	private int priorityLevel; // If needs priority
	private double maintenanceTime; private double takeOffTime; // time needed for maintenance, time needed for takeoff
	private double distance; private double speed; // Distance from airport and speed of travel to airport
	private String direction; // direction plane is coming from, direction plane is going after takeoff
	private int status; // status of the plane
	private String[] statusList = {"Approaching", "Maintenance", "Take Off", "Leaving"}; // different kinds of status for the plane
	
	
	/**
	 * Constructors for Airplane
	 */
	public Airplane() {ID = 0; priorityLevel = 1; maintenanceTime = 10; takeOffTime = 10; distance = 10; speed = 1; direction = "North"; status = 0;}
	public Airplane(int ID, int priorityLevel, double maintenanceTime, double takeOffTime, double distance, double speed, String direction, int status) {this.ID = ID; this.priorityLevel = priorityLevel; this.maintenanceTime = maintenanceTime; this.takeOffTime = takeOffTime; this.distance = distance; this.speed = speed; this.direction = direction; this.status = status;}
	
	
	/**
	 * Setter for ID
	 * @param ID new ID
	 */
	public void setID(int ID) {this.ID = ID;}
	/**
	 * Getter for ID
	 * @return ID
	 */
	public int getID() {return ID;}
	
	/**
	 * Setter for priorityLevel
	 * @param priorityLevel new priorityLevel
	 */
	public void setPriorityLevel(int priorityLevel) {this.priorityLevel = priorityLevel;}
	/**
	 * Getter for priorityLevel
	 * @return priorityLevel
	 */
	public int getPriorityLevel() {return priorityLevel;}
	
	/**
	 * Setter for maintenanceTime
	 * @param maintenanceTime new maintenanceTime
	 */
	public void setMaintenanceTime(double maintenanceTime) {this.maintenanceTime = maintenanceTime;}
	/**
	 * Getter for maintenanceTime
	 * @return maintenanceTime
	 */
	public double getMaintenanceTime() {return maintenanceTime;}
	
	/**
	 * Setter for takeOffTime
	 * @param takeOffTime new takeOffTime
	 */
	public void setTakeOffTime(double takeOffTime) {this.takeOffTime = takeOffTime;}
	/**
	 * Getter for takeOffTime
	 * @return takeOffTime
	 */
	public double getTakeOffTime() {return takeOffTime;}
	
	/**
	 * Setter for distance
	 * @param distance new distance
	 */
	public void setDistance(double distance) {this.distance = distance;}
	/**
	 * Getter for distance
	 * @return distance
	 */
	public double getDistance() {return distance;}
	
	/**
	 * Setter for speed
	 * @param speed new speed
	 */
	public void setSpeed(double speed) {this.speed = speed;}
	/**
	 * Getter for speed
	 * @return speed
	 */
	public double getSpeed() {return speed;}
	
	/**
	 * Setter for direction
	 * @param direction new Direction
	 */
	public void setDirection(String direction) {this.direction = direction;}
	/**
	 * Getter for direction
	 * @return direction
	 */
	public String getDirection() {return direction;}
	
	/**
	 * Setter for status
	 * @param status new Status
	 */
	public void setStatus(int status) {this.status = status;}
	/**
	 * Getter for status
	 * @return status
	 */
	public String getStatus() {return statusList[status];}
	/**
	 * Get next status
	 */
	public void getNextStatus() {if (status <= 4) status++;};
	
	
	/**
	 * Get Airspace Data
	 * @return Airspace Data
	 */
	public String airSpaceData() {
		String showPriority = ((getStatus().equals("Approaching")) ? Integer.toString(priorityLevel) : "");
		String str = "";
		str += String.format("|%4s|%10s|%10s|%12s|%10s|", ID, distance, direction, statusList[status], showPriority);
		
		return str;
	}
	
	/**
	 * Get Runway Data
	 * @return Runway Data
	 */
	public String getRunwayData() {
		String statusTime = ((getStatus().equals("Maintenance")) ? Double.toString(maintenanceTime)  : Double.toString(takeOffTime));
		//String str = "[ID:" + ID + statusTime + ", Status:" + statusList[status] + "] ";
		
		String str = "";
		str += String.format("[%4s|%6s|%12s]", ID, statusTime, statusList[status]);
		
		return str;
	}
	
	
	/**
	 * Comparable for Airplane
	 * @param plane plane being compared to
	 */
	public int compareTo(Airplane plane) {
		if (this.priorityLevel == plane.priorityLevel)
			return 0;
		else if (getPriorityLevel() > plane.getPriorityLevel())
			return 1;
		else
			return -1;
	}
	
}
