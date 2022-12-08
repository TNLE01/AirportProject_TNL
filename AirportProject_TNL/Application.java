/**
 * 
 * @author Truong Le
 *
 */
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Application {
	
	/**
	 * Clears the screen
	 */
	public static void clear() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
			} catch (IOException | InterruptedException ex) {}
		}
	
	/**
	 * Will randomly make planes and add them to the approaching airspace. Planes will land on the correct runway as they fly closer to the airport. Maintenance will happen to the planes before they take off again and enter the leaving airspace.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Random gen = new Random();
		
		System.out.println("Simulation Started");
		Airport airport = new Airport();
		String[] direction = {"North", "East", "South", "West"};
		
		
		
		Timer timer = new Timer();
		TimerTask simulation = new TimerTask() {
			public void run() {
				if (gen.nextInt(101) <= 33) {
					int priority = ((gen.nextInt(101) <= 33) ? 0 : 1);
					Airplane plane = new Airplane((gen.nextInt(901) + 100), priority, (gen.nextInt(11) + 5), (gen.nextInt(11) + 5), 10, 1, (direction[gen.nextInt(4)]), 0);
					airport.addToAirspace(plane);
				}
				airport.planeApproachDistance();
				airport.maintenanceAndTakeOff(airport.northRunway);
				airport.maintenanceAndTakeOff(airport.eastRunway);
				airport.maintenanceAndTakeOff(airport.southRunway);
				airport.maintenanceAndTakeOff(airport.westRunway);
				airport.planeLeavingDistance();
			}
		};
		timer.schedule(simulation, 1000, 1000);
		
		
		TimerTask data = new TimerTask() {
			public void run() {
				System.out.println("Cleared Screen"); clear();
				System.out.println("Type \"Q\" to quit the Simulation\n");
				
				System.out.println("Runway: \n" + airport.runwayData());
				
				System.out.println("Airspace: \n" + airport.getAirspace());
			}
		};
		timer.schedule(data, 1000, 3000);
		
		
		
		boolean quit = false; while (quit == false) {if(scan.nextLine().equals("Q")) {timer.cancel(); quit = true;}}
		System.out.println("Simulation Ended");
	}

}

