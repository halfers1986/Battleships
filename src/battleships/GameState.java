package battleships;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState {

	// Stores whether or not cheat mode is active
	static boolean cheatMode = false;
	
	// Stores player's hits and misses
	static ArrayList<int[]> playerHits = new ArrayList<int[]>();
	static ArrayList<int[]> playerMisses = new ArrayList<int[]>();
	
	// Stores enemy's hits and misses
	static ArrayList<int[]> enemyHits = new ArrayList<int[]>();
	static ArrayList<int[]> enemyMisses = new ArrayList<int[]>();
	
	
	/**
	 * Makes coordinates arrays more readable for the player by changing the x-axis coordinate to a letter
	 * to match the letters on the board printout
	 */
	public static ArrayList<Object[]> printableCoordinates(ArrayList<int[]> listCoordinates) {
		char[] xAxis = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
		ArrayList<Object[]> readableShip = new ArrayList<Object[]>();
		
		for (int[] coordinate : listCoordinates) {
			Object[] temp = new Object[2];
			temp[1] = coordinate[1];
			char xCoordinate = 'Z';
			for (int j = 0; j < xAxis.length; j++) {
				if (j + 1 == coordinate[0]) {
					xCoordinate = xAxis[j];
				}
			}
			temp[0] = xCoordinate;
			readableShip.add(temp);
		}
		return readableShip;
	}
	
	
	/**
	 * Prints the current state of the game board. Initially prints an empty ocean,
	 * but as the player takes turns, replaces the sea (-) with playerHits (X) and
	 * playerMisses (O)
	 */
	public static void boardBuilder() {
		System.out.println("   YOUR GUESSES\t\t\t   YOUR SHIPS\n");
		for (int i = 0; i <= 9; i++) { // beginning of i loop (y-axis)
			for (int j = 0; j <= 17; j++) { // beginning of j loop (x-axis)
				int[] playerCoordinateToCheck = { j, i }; // coordinate to check against playerHits and playerMisses
				int[] enemyCoordinateToCheck = {j-9,i}; // coordinate to check against enemyHits and enemyMisses
				char[] xAxis = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',' ','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
				if (i == 0) {
					if (j == 0) {
						System.out.print("  "); // prints an empty space at [0, 0]
					} else if (j == 17) { // adds a new line at end of x-axis labels
						System.out.printf("%s%n", xAxis[j - 1]);
					} else if(j == 8) {
						System.out.printf("%s\t\t", xAxis[j - 1]);
					} else { // prints x-axis labels
						System.out.printf("%s ", xAxis[j - 1]);
					}
				} else if (j == 0 || j == 9) { // prints y-axis labels
					System.out.printf("%d ", i);
				} else {
					boolean hit = false;
					boolean miss = false;
					boolean ship = false;
					boolean enemyShip = false;
					String finisher;
					if (j == 8) {
						finisher = "\t\t";
					} else if (j == 17) {
						finisher = "%n";
					} else {
						finisher = " ";
					}
					
					if (j < 9 ) {
						for (int[] coordinate : GameState.playerMisses) {
							if (Arrays.equals(coordinate, playerCoordinateToCheck)) {
								miss = true;
							}
						}
	
						for (int[] coordinate : GameState.playerHits) {
							if (Arrays.equals(coordinate, playerCoordinateToCheck)) {
								hit = true;
							}
						}
						
						if (cheatMode) {
							for (int[] coordinate : ShipPlacer.allEnemyShipCoordinates) {
								if (Arrays.equals(coordinate, playerCoordinateToCheck)) {
									enemyShip = true;
								}
							} 
						}
						
					} else {					
						for (int[] coordinate : GameState.enemyMisses) {
							if (Arrays.equals(coordinate, enemyCoordinateToCheck)) {
								miss = true;
							}
						}
						
						for (int[] coordinate : GameState.enemyHits) {
							if (Arrays.equals(coordinate, enemyCoordinateToCheck)) {
								hit = true;
							}
						}
						
						for (int[] coordinate : ShipPlacer.allPlayerShipCoordinates) {
							if (Arrays.equals(coordinate, enemyCoordinateToCheck)) {
								ship = true;
							}
						} 
					}
					
					
					if (hit) {
						System.out.printf("X" + finisher);
					} else if (miss) {
						System.out.printf("0" + finisher);
					} else if (ship) {
						System.out.printf("#" + finisher);
					} else if (enemyShip) {
						System.out.printf("@" + finisher);
					} else {
						System.out.printf("-" + finisher);
					}
				}
			} // end of j loop (x-axis)
		} // end of i loop (y-axis)

		System.out.printf("%n%n");
		if (GameState.cheatMode) {
			System.out.println("KEY");
			System.out.println("Hit: X");
			System.out.println("Miss: 0");
			System.out.println("Your ship: #");
			System.out.println("CHEAT MODE ACTIVE!");
			System.out.println("Enemy ships displayed on your board as \"@\".");
//			System.out.println("Aircraft Carrier is at... " + Arrays.deepToString(printableCoordinates(ShipPlacer.enemyCarrier.listCoordinates).toArray()));
//			System.out.println("Cruiser is at..." + Arrays.deepToString(printableCoordinates(ShipPlacer.enemyCruiser.listCoordinates).toArray()));
//			System.out.println("Destroyer is at..." + Arrays.deepToString(printableCoordinates(ShipPlacer.enemyDestroyer.listCoordinates).toArray()));
//			System.out.println("Submarine is at..." + Arrays.deepToString(printableCoordinates(ShipPlacer.enemySubmarine.listCoordinates).toArray()));
//			System.out.printf("Patrol Boat is at..." + Arrays.deepToString(printableCoordinates(ShipPlacer.enemyPatrolBoat.listCoordinates).toArray()) + "%n%n");
		}

	} // end of boardBuilder method
	
	

//	public static void playerBoardBuilder() {
//		for (int i = 0; i <= 9; i++) { // beginning of i loop (y-axis)
//			for (int j = 0; j <= 8; j++) { // beginning of j loop (x-axis)
//				int[] coordinateToCheck = { j, i }; // coordinate to check against enemyHits and enemyMisses
//				char[] xAxis = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
//				if (i == 0) {
//					if (j == 0) {
//						System.out.print("  "); // prints an empty space at [0, 0]
//					} else if (j == 8) { // adds a new line at end of x-axis labels
//						System.out.printf("%s%n", xAxis[j - 1]);
//					} else { // prints x-axis labels
//						System.out.printf("%s ", xAxis[j - 1]);
//					}
//				} else if (j == 0) { // prints y-axis labels
//					System.out.printf("%d ", i);
//				} else {
//					boolean hit = false;
//					boolean miss = false;
//					String finisher = (j == 8) ? "%n" : " ";
//
//					for (int[] coordinate : GameState.enemyMisses) {
//						if (Arrays.equals(coordinate, coordinateToCheck)) {
//							miss = true;
//						}
//					}
//
//					for (int[] coordinate : GameState.enemyHits) {
//						if (Arrays.equals(coordinate, coordinateToCheck)) {
//							hit = true;
//						}
//					}
//
//					if (hit) {
//						System.out.printf("X" + finisher);
//					} else if (miss) {
//						System.out.printf("0" + finisher);
//					} else {
//						System.out.printf("-" + finisher);
//					}
//				}
//			} // end of j loop (x-axis)
//		} // end of i loop (y-axis)
//
//		System.out.printf("%n%n");
//
//	} // end of boardBuilder method
}
