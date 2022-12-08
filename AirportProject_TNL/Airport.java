/**
 * 
 * @author Truong Le
 *
 */
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
public class Airport {
	
	public Queue<Airplane> approach = new LinkedList<Airplane>(); // planes entering airport, ready to land
	public Queue<Airplane> leaving = new LinkedList<Airplane>(); // planes leaving airport, just took off
	public PriorityQueue<Airplane> northRunway = new PriorityQueue<Airplane>(); // N, North Runway
	public PriorityQueue<Airplane> eastRunway = new PriorityQueue<Airplane>(); // E, North Runway
	public PriorityQueue<Airplane> southRunway = new PriorityQueue<Airplane>(); // S, North Runway
	public PriorityQueue<Airplane> westRunway = new PriorityQueue<Airplane>(); // W, North Runway

	
	/**
	 * Get which runway the plane is headed to
	 * @param plane the plane headed to the runway
	 * @return which runway the plane is headed to
	 */
	public Queue<Airplane> getRunwayDirection (Airplane plane) {
		if (plane.getDirection().equals("North")) return northRunway;
		else if (plane.getDirection().equals("East")) return eastRunway;
		else if (plane.getDirection().equals("South")) return southRunway;
		else if (plane.getDirection().equals("West")) return westRunway;
		return null;
	}
	
	/**
	 * Add a plane to the approaching Airspace
	 * @param plane plane approaching 
	 */
	public void addToAirspace(Airplane plane) {
		approach.add(plane);
	}
	
	/**
	 * Get current Airspace
	 * @return current Airspace
	 */
	public String getAirspace() {
		String str = "";
		if (!approach.isEmpty() || !leaving.isEmpty()) {str += String.format("|%4s|%10s|%10s|%12s|%10s|\n", "ID", "Distance", "Direction", "Status", "Priority");}
		if (!approach.isEmpty()) {for(Airplane plane:approach) {str += plane.airSpaceData() + "\n";}}
		if (!leaving.isEmpty()) {for(Airplane plane:leaving) {str += plane.airSpaceData() + "\n";}}
		else if (str.equals("")) {str = "Airspace is Empty"; return str;}
		return str;
	}
	
	/**
	 * Add a plane to the correct runway
	 * @param plane plane being added
	 * @return boolean to see if plane was added successfully or not
	 */
	public boolean addToRunway(Airplane plane) {
		return getRunwayDirection(plane).add(plane);
	}
	
	/**
	 * Get current runway
	 * @param runway runway to get
	 * @return current runway
	 */
	public String getRunway(Queue<Airplane> runway) {
		String str = "";
		for(Airplane elem:runway) {
			if (runway.peek().getID() != elem.getID())
				str += elem.getID() + " ";
		}
		return str;
	}
	
	/**
	 * See if any planes are on any runway
	 * @return boolean to see if any planes are on any runway or not
	 */
	public boolean planeOnRunway() {
		boolean onRunway = false;
		if (!northRunway.isEmpty()) onRunway = true; if (!eastRunway.isEmpty()) onRunway = true; if (!southRunway.isEmpty()) onRunway = true; if (!westRunway.isEmpty()) onRunway = true;
		return onRunway;
	}
	
	/**
	 * Get current of all runway
	 * @return current of all runway
	 */
	public String runwayData() {
		String str = "";
		if (planeOnRunway()) str += String.format("|%6s[%4s|%6s|%12s]%8s|\n", "Runway", "ID", "Time", "Status", "Waiting");
		if (!northRunway.isEmpty()) str += String.format("|%6s", "N") + northRunway.peek().getRunwayData() + "   " + getRunway(northRunway)+ "\n";
		if (!eastRunway.isEmpty()) str += String.format("|%6s", "E") + eastRunway.peek().getRunwayData() + "   " + getRunway(eastRunway)+ "\n";
		if (!southRunway.isEmpty()) str += String.format("|%6s", "S") + southRunway.peek().getRunwayData() + "   " + getRunway(southRunway)+ "\n";
		if (!westRunway.isEmpty()) str += String.format("|%6s", "W") + westRunway.peek().getRunwayData() + "   " + getRunway(westRunway)+ "\n";
		if (!planeOnRunway()) str = "Runway is Empty";
		
		return str;
	}
	
	/**
	 * Calculate the distance as the plane approach the runway
	 */
	public void planeApproachDistance() {
		for(Airplane plane:approach){
			plane.setDistance(plane.getDistance() - plane.getSpeed());
		}
		while(!approach.isEmpty() && approach.peek().getDistance() <= 0) {
			Airplane temp = approach.remove();
			addToRunway(temp); temp.getNextStatus();
		}
	}
	
	/**
	 * Calculate the distance as the plane leaves the runway
	 */
	public void planeLeavingDistance() {
		for(Airplane plane:leaving){
			plane.setDistance(plane.getDistance() + plane.getSpeed());
		}
		while(!leaving.isEmpty() && leaving.peek().getDistance() >= 10) {
			leaving.remove();
		}
	}
	
	/**
	 * Calculate the maintenance and takeoff times for the plane on the runway
	 * @param runway runway with plane to calculate maintenance and takeoff times
	 */
	public void maintenanceAndTakeOff(PriorityQueue<Airplane> runway) {
		if (!runway.isEmpty()) {
			if (runway.peek().getStatus().equals("Maintenance")) {
				runway.peek().setMaintenanceTime(runway.peek().getMaintenanceTime() - 1);
				if(runway.peek().getMaintenanceTime() <= 0) {
					runway.peek().getNextStatus();
				}
			}
			else if (runway.peek().getStatus().equals("Take Off")) {
				runway.peek().setTakeOffTime(runway.peek().getTakeOffTime() - 1);
				if(runway.peek().getTakeOffTime() <= 0) {
					runway.peek().getNextStatus();
					leaving.add(runway.remove());
				}
			}
		}
	}
	
}