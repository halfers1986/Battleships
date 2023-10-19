/**
 * 
 */
package battleships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class ShipPlacer {
	
	// Stores the player ships
	static ShipBuilder playerCarrier;
	static ShipBuilder playerCruiser;
	static ShipBuilder playerDestroyer;
	static ShipBuilder playerSubmarine;
	static ShipBuilder playerPatrolBoat;
	static ArrayList<ShipBuilder> playerShips = new ArrayList<>();
	static ArrayList<int[]> allPlayerShipCoordinates = new ArrayList<int[]>();
	
	// Stores the enemy ships
	static ShipBuilder enemyCarrier;
	static ShipBuilder enemyCruiser;
	static ShipBuilder enemyDestroyer;
	static ShipBuilder enemySubmarine;
	static ShipBuilder enemyPatrolBoat;
	static ArrayList<ShipBuilder> enemyShips = new ArrayList<>();
	static ArrayList<int[]> allEnemyShipCoordinates = new ArrayList<int[]>();

	
	/**
	 * Adds each of the player's ships to the board in random positions.
	 */
	public static void playerShipPlacer() {
		
		// rolls the first ship. Since there is nothing to overlap, overlap checker is not run.
		playerCarrier = new ShipBuilder("Aircraft Carrier", 5);
		passToAllPlayerShipCoordinates(playerCarrier); // an ArrayList that contains all of the ship coordinates placed so far, so that they can be easily checked against new ships
		
		do { // roll the ship. While there is an overlap with an already placed ship, re-roll.
			playerCruiser = new ShipBuilder("Cruiser", 4);
			overlapChecker(playerCruiser.coordinates, allPlayerShipCoordinates);		
		} while (overlapChecker(playerCruiser.coordinates, allPlayerShipCoordinates));
		passToAllPlayerShipCoordinates(playerCruiser);
		
		do { // roll the ship. While there is an overlap with an already placed ship, re-roll.
			playerDestroyer = new ShipBuilder("Destroyer", 3);
			overlapChecker(playerDestroyer.coordinates, allPlayerShipCoordinates);		
		} while (overlapChecker(playerDestroyer.coordinates, allPlayerShipCoordinates));
		passToAllPlayerShipCoordinates(playerDestroyer);
		
		do { // roll the ship. While there is an overlap with an already placed ship, re-roll.
			playerSubmarine = new ShipBuilder("Submarine", 3);
			overlapChecker(playerSubmarine.coordinates, allPlayerShipCoordinates);		
		} while (overlapChecker(playerSubmarine.coordinates, allPlayerShipCoordinates));
		passToAllPlayerShipCoordinates(playerSubmarine);
		
		do { // roll the ship. While there is an overlap with an already placed ship, re-roll.
			playerPatrolBoat = new ShipBuilder("Patrol Boat", 2);
			overlapChecker(playerPatrolBoat.coordinates, allPlayerShipCoordinates);		
		} while (overlapChecker(playerPatrolBoat.coordinates, allPlayerShipCoordinates));
		passToAllPlayerShipCoordinates(playerPatrolBoat);

	}
	
	

	/**
	 * Adds each of the enemy's ships to the board in random positions.
	 */
	public static void enemyShipPlacer() {
		
		// rolls the first ship. Since there is nothing to overlap, overlap checker is not run.
		enemyCarrier = new ShipBuilder("Aircraft Carrier", 5);
		passToAllEnemyShipCoordinates(enemyCarrier);
		
		do { // roll the ship. While there is an overlap with an already placed ship, re-roll.
			enemyCruiser = new ShipBuilder("Cruiser", 4);
			overlapChecker(enemyCruiser.coordinates, allEnemyShipCoordinates);		
		} while (overlapChecker(enemyCruiser.coordinates, allEnemyShipCoordinates));
		passToAllEnemyShipCoordinates(enemyCruiser);
		
		do { // roll the ship. While there is an overlap with an already placed ship, re-roll.
			enemyDestroyer = new ShipBuilder("Destroyer", 3);
			overlapChecker(enemyDestroyer.coordinates, allEnemyShipCoordinates);		
		} while (overlapChecker(enemyDestroyer.coordinates, allEnemyShipCoordinates));
		passToAllEnemyShipCoordinates(enemyDestroyer);
		
		do { // roll the ship. While there is an overlap with an already placed ship, re-roll.
			enemySubmarine = new ShipBuilder("Submarine", 3);
			overlapChecker(enemySubmarine.coordinates, allEnemyShipCoordinates);		
		} while (overlapChecker(enemySubmarine.coordinates, allEnemyShipCoordinates));
		passToAllEnemyShipCoordinates(enemySubmarine);
		
		do { // roll the ship. While there is an overlap with an already placed ship, re-roll.
			enemyPatrolBoat = new ShipBuilder("Patrol Boat", 2);
			overlapChecker(enemyPatrolBoat.coordinates, allEnemyShipCoordinates);		
		} while (overlapChecker(enemyPatrolBoat.coordinates, allEnemyShipCoordinates));
		passToAllEnemyShipCoordinates(enemyPatrolBoat);
		
		try {
			System.out.println("Placing enemy Aircraft Carrier...");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Placing enemy Cruiser...");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Placing enemy Destroyer...");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Placing enemy Submarine...");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Placing enemy Patrol Boat...");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	
	/**
	 * Adds the coordinates of a placed enemy ship to an ArrayList that can be parsed for easy access to this information later.
	 * @param shipBuilder
	 */
	public static void passToAllEnemyShipCoordinates(ShipBuilder shipBuilder) {
		for (int[] coordinate : shipBuilder.coordinates) {
			allEnemyShipCoordinates.add(coordinate);
		}
	}
	
	
	/**
	 * Adds the coordinates of a placed player ship to an ArrayList that can be parsed for easy access to this information later.
	 * @param shipBuilder
	 */
	public static void passToAllPlayerShipCoordinates(ShipBuilder shipBuilder) {
		for (int[] coordinate : shipBuilder.coordinates) {
			allPlayerShipCoordinates.add(coordinate);
		}
	}
	
	/**
	 * Checks if a ship (a) has any coordinates in common with any ships already placed (b).
	 * @param a
	 * @param b
	 * @return true if an overlap exists. Otherwise false.
	 */
	public static boolean overlapChecker(int[][] a, ArrayList<int[]> b) {
		boolean overlap = false;
		// loops over each coordinate in the ship
		for (int[] coordinate : a) {
			//loops over each coordinate in the ArrayList containing coordinates of all ships placed so far
			for (int[] coordinate2 : b) {
				// checks if the ship has any coordinates in common with any that have already been placed
				// if they do, exits the loop and returns overlap = true
				if (Arrays.equals(coordinate, coordinate2)) {
					overlap = true;
					return overlap;
				}
			}
		}
		return overlap;
	}
}
