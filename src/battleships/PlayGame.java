/**
 * 
 */
package battleships;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Contains the methods that control the structure of the game.
 */
public class PlayGame {


	/**
	 * 
	 */
	public static void initialise() {
		System.out.printf("NEW GAME beginning...%n%n");
		String answer1 = "n";
		do {
			ShipPlacer.playerShipPlacer();
			System.out.println("   YOUR SHIPS\n");
			for (int i = 0; i <= 9; i++) { // beginning of i loop (y-axis)
				for (int j = 0; j <= 8; j++) { // beginning of j loop (x-axis)
					int[] coordinateToCheck = { j, i }; // coordinate to check against ship locations
					char[] xAxis = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
					if (i == 0) {
						if (j == 0) {
							System.out.print("  "); // prints an empty space at [0, 0]
						} else if (j == 8) { // adds a new line at end of x-axis labels
							System.out.printf("%s%n", xAxis[j - 1]);
						} else { // prints x-axis labels
							System.out.printf("%s ", xAxis[j - 1]);
						}
					} else if (j == 0) { // prints y-axis labels
						System.out.printf("%d ", i);
					} else {
						boolean ship = false;
						String finisher;
						if (j == 8) {
							finisher = "%n";
						} else {
							finisher = " ";
						}
							
						for (int[] coordinate : ShipPlacer.allPlayerShipCoordinates) {
							if (Arrays.equals(coordinate, coordinateToCheck)) {
									ship = true;
							}
						} 
						
						if (ship) {
							System.out.printf("#" + finisher);
						} else {
							System.out.printf("-" + finisher);
						}
					}
				} // end of j loop (x-axis)
			} // end of i loop (y-axis)
			System.out.printf("\n%s:\t%s%n", ShipPlacer.playerCarrier.name, Arrays.deepToString(GameState.printableCoordinates(ShipPlacer.playerCarrier.listCoordinates).toArray()));
			System.out.printf("%s:\t\t%s%n", ShipPlacer.playerCruiser.name, Arrays.deepToString(GameState.printableCoordinates(ShipPlacer.playerCruiser.listCoordinates).toArray()));
			System.out.printf("%s:\t\t%s%n", ShipPlacer.playerDestroyer.name, Arrays.deepToString(GameState.printableCoordinates(ShipPlacer.playerDestroyer.listCoordinates).toArray()));
			System.out.printf("%s:\t\t%s%n", ShipPlacer.playerSubmarine.name, Arrays.deepToString(GameState.printableCoordinates(ShipPlacer.playerSubmarine.listCoordinates).toArray()));
			System.out.printf("%s:\t\t%s%n%n%n", ShipPlacer.playerPatrolBoat.name, Arrays.deepToString(GameState.printableCoordinates(ShipPlacer.playerPatrolBoat.listCoordinates).toArray()));
			System.out.println("Are you happy with your ship setup? (y/n)\n\n");
			answer1 = UserInput.yesNo();
			if (answer1.equals("n")) {
				System.out.println("Resetting your ships...\n\n\n");
				ShipPlacer.allPlayerShipCoordinates.clear();
			}
		} while (answer1.equals("n"));
		
		
		ShipPlacer.playerShips.add(ShipPlacer.playerCarrier);
		ShipPlacer.playerShips.add(ShipPlacer.playerCruiser);
		ShipPlacer.playerShips.add(ShipPlacer.playerDestroyer);
		ShipPlacer.playerShips.add(ShipPlacer.playerSubmarine);
		ShipPlacer.playerShips.add(ShipPlacer.playerPatrolBoat);
				
		System.out.println("Are you ready to begin? (y/n)\n\n");
		String answer2 = UserInput.yesNo();
		
		if (answer2.equalsIgnoreCase("n")) {
			System.out.printf("Returning to main menu...%n%n");
			Menu.menuBuilder();
		} else {
			ShipPlacer.enemyShipPlacer();
			ShipPlacer.enemyShips.add(ShipPlacer.enemyCarrier);
			ShipPlacer.enemyShips.add(ShipPlacer.enemyCruiser);
			ShipPlacer.enemyShips.add(ShipPlacer.enemyDestroyer);
			ShipPlacer.enemyShips.add(ShipPlacer.enemySubmarine);
			ShipPlacer.enemyShips.add(ShipPlacer.enemyPatrolBoat);
			System.out.printf("%n%n%n");
			playerTurn();
		}
	}


	/**
	 * 
	 */
	public static void enemyTurn() {
		int[] shot = new int[2];
		
		// DEPRECATED
		
//		Random random = new Random();
//		boolean valid;
		// Randomly generates a shot coordinate
		// checks coordinate against shots already taken (hits and misses) and if there is a match, re-generates the shot
		// runs until the shot is one that has not yet been made
//		do {
//			valid = true;
//			shot[0] = random.nextInt(8)+1;
//			shot[1] = random.nextInt(9)+1;
//			
//			for (int[] hit : GameState.enemyHits) { // checking hits for overlap
//				if (Arrays.equals(shot, hit)) {
//					valid = false;
//					break;
//				}
//			}
//			
//			for (int[] hit : GameState.enemyMisses) { // checking misses for overlap
//				if (Arrays.equals(shot, hit)) {
//					valid = false;
//					break;
//				}
//			}
//			
//		} while (!valid);
//		System.out.println("Misses so far are: " + Arrays.deepToString(GameState.enemyMisses.toArray()));
//		System.out.println("Hits so far are: " + Arrays.deepToString(GameState.enemyHits.toArray()));
//		System.out.println("All shots taken are: " + Arrays.deepToString(AIPlayer.shotsTaken.toArray()));
		shot = AIPlayer.selectNextShot();
		shotChecker(false, shot);
	}

	/**
	 * 
	 */
	public static void playerTurn() {
		GameState.boardBuilder();
		int[] shot = UserInput.playerShot();
		shotChecker(true, shot);
	}

	
	
	/**
	 * 
	 * 
	 * @param 
	 * @return
	 * @return
	 */
	public static void shotChecker(boolean isPlayer, int[] shot) {
		boolean player = isPlayer;
		Object[] result = new Object[2];
		result = hitOrMiss(player, shot);
		
		if (player) {		
			if ((boolean) (result[1])) {
				try { // try/catch allows the instruction to sleep to run without errors.
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("\n\n░█▀▄░█▀█░█▀█░█▄█░█░█░█\r\n" + "░█▀▄░█░█░█░█░█░█░▀░▀░▀\r\n"
							+ "░▀▀░░▀▀▀░▀▀▀░▀░▀░▀░▀░▀\n\n");
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("You hit the enemy's " + ShipPlacer.enemyShips.get((int) result[0]).name + "!");
					TimeUnit.MILLISECONDS.sleep(500);
					if (ShipPlacer.enemyShips.get((int) result[0]).listCoordinates.isEmpty()) {
						System.out.println("You sank the enemy's " + ShipPlacer.enemyShips.get((int) result[0]).name + "!");
				
						if (ShipPlacer.allEnemyShipCoordinates.isEmpty()) {
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("\n\n\n_____.___.                    __      __ .__        ._. \r\n"
									+ "\\__  |   |  ____   __ __     /  \\    /  \\|__|  ____ | | \r\n"
									+ " /   |   | /  _ \\ |  |  \\    \\   \\/\\/   /|  | /    \\| | \r\n"
									+ " \\____   |(  <_> )|  |  /     \\        / |  ||   |  \\\\| \r\n"
									+ " / ______| \\____/ |____/       \\__/\\  /  |__||___|  /__ \r\n"
									+ " \\/                                 \\/            \\/ \\/ \r\n"
									+ "                                                        \n\n");
							TimeUnit.MILLISECONDS.sleep(900);
							System.out.printf("%n%nReturning to main menu");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.print(".");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.print(".");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.print(".\n\n\n");
							TimeUnit.MILLISECONDS.sleep(500);
							Menu.menuBuilder();
						} else {
							TimeUnit.MILLISECONDS.sleep(500);
							enemyTurn();
						}
					} else {
						TimeUnit.MILLISECONDS.sleep(500);
						enemyTurn();
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			} else {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("You missed.\n\n");
					TimeUnit.MILLISECONDS.sleep(500);
					enemyTurn();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} else {
			if ((boolean) (result[1])) {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("\n\n░█▀▄░█▀█░█▀█░█▄█░█░█░█\r\n" + "░█▀▄░█░█░█░█░█░█░▀░▀░▀\r\n"
								+ "░▀▀░░▀▀▀░▀▀▀░▀░▀░▀░▀░▀\n\n");
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("The enemy hit your " + ShipPlacer.playerShips.get((int) result[0]).name + "!\n\n");
					TimeUnit.MILLISECONDS.sleep(500);
						if (ShipPlacer.playerShips.get((int) result[0]).listCoordinates.isEmpty()) {
							System.out.println(
									"The enemy sank your " + ShipPlacer.playerShips.get((int) result[0]).name + "!\n\n");
						if (ShipPlacer.allPlayerShipCoordinates.isEmpty()) {
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("\n\n\n_____.___.                   .__                           \r\n"
									+ "\\__  |   |  ____   __ __     |  |    ____   ______  ____   \r\n"
									+ " /   |   | /  _ \\ |  |  \\    |  |   /  _ \\ /  ___/_/ __ \\  \r\n"
									+ " \\____   |(  <_> )|  |  /    |  |__(  <_> )\\___ \\ \\  ___/  \r\n"
									+ " / ______| \\____/ |____/     |____/ \\____//____  > \\___  > \r\n"
									+ " \\/                                            \\/      \\/  \r\n"
									+ "                                                           \n\n");
							TimeUnit.MILLISECONDS.sleep(900);
							System.out.printf("%n%nReturning to main menu");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.print(".");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.print(".");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.print(".\n\n\n");
							TimeUnit.MILLISECONDS.sleep(500);
							Menu.menuBuilder();
						} else {
							TimeUnit.MILLISECONDS.sleep(500);
							playerTurn(); 
						}
					} else {
						TimeUnit.MILLISECONDS.sleep(500);
						playerTurn(); 
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				} else {
					try {
						TimeUnit.MILLISECONDS.sleep(500);
						System.out.println("The enemy missed.\n\n");
						TimeUnit.MILLISECONDS.sleep(500);
						playerTurn(); 
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
	}
	


	/**
	 * 
	 * @param isPlayer
	 * @param shot
	 * @return
	 */
	public static Object[] hitOrMiss(boolean isPlayer, int[]shot) {
		boolean player = isPlayer;
		Object[] returner = new Object[2];
		int i = 0;
				
		if (player) {
			for (i = 0; i < ShipPlacer.enemyShips.size(); i++) {
				for (int j = 0; j <= ShipPlacer.enemyShips.get(i).listCoordinates.size() - 1; j++) {
					int before = ShipPlacer.enemyShips.get(i).listCoordinates.size();
					ShipPlacer.enemyShips.get(i).listCoordinates.removeIf(coordinate -> Arrays.equals(coordinate, shot));
					int after = ShipPlacer.enemyShips.get(i).listCoordinates.size();
					if (before != after) {
						boolean hit = true;
						ShipPlacer.allEnemyShipCoordinates.removeIf(coordinate -> Arrays.equals(coordinate, shot));
						GameState.playerHits.add(shot);
						returner[0] = i;
						returner[1] = hit;
						return returner;
					}
				}
			} 
		} else {
			try {
				System.out.print("Enemy is thinking");
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.print(".");
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.print(".");
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.print(".\n\n");
				TimeUnit.MILLISECONDS.sleep(500);
				Object[] readableShot = new Object[2];
				readableShot[1] = shot[1];
				char[] xAxis = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
				char xCoordinate = 'Z';
				for (int j = 0; j < xAxis.length; j++) {
					if (j + 1 == shot[0]) {
							xCoordinate = xAxis[j];
						}
					}
				readableShot[0] = xCoordinate;
				System.out.println("The enemy has targeted " + Arrays.toString(readableShot));
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			for (i = 0; i <= ShipPlacer.playerShips.size() - 1; i++) {
				for (int j = 0; j <= ShipPlacer.playerShips.get(i).listCoordinates.size() - 1; j++) {
					int before = ShipPlacer.playerShips.get(i).listCoordinates.size();
					ShipPlacer.playerShips.get(i).listCoordinates
							.removeIf(coordinate -> Arrays.equals(coordinate, shot));
					int after = ShipPlacer.playerShips.get(i).listCoordinates.size();
					if (before != after) {
						boolean hit = true;
						GameState.enemyHits.add(shot);
						AIPlayer.shotsTaken.add(shot);
						returner[0] = i;
						returner[1] = hit;
						return returner;
					}
				}
			}
		}
		boolean hit = false;
		if (player) {
			GameState.playerMisses.add(shot);
		} else {
			GameState.enemyMisses.add(shot);
			AIPlayer.shotsTaken.add(shot);
		}
		returner[0] = i;
		returner[1] = hit;
		return returner;
	}
}
