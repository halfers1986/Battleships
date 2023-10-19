/**
 * 
 */
package battleships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 */
public class AIPlayer {
	
	static Random random = new Random();
	
	static boolean firstShot = true;
	static int largestShipLeft = 5;
	static ArrayList<int[]> shotsTaken = new ArrayList<int[]>();
	static ArrayList<int[]> adjacentCoordinates = new ArrayList<int[]>();
	static ArrayList<int[]> candidateCoordinates = new ArrayList<int[]>();
	
	// check where x coordinate is different and y coordinate is same  (and vice-versa) if Math.abs(difference)/2 < largestShipLeft - if so, add to candidate coordinates
	public static void calculateCandidateCoordinates() {
		int[] candidate = new int[2];
		
		// checking for gaps between guesses already made, where any guess at the midpoint is guaranteed to hit the largest ship, if it is there
		for (int[] coordinate1 : shotsTaken) {
			for (int[] coordinate2 : shotsTaken) {
//				System.out.println("Checking for a candidate vertical gap between " + Arrays.toString(coordinate1) + "and" + Arrays.toString(coordinate2) + ". Largest ship left has length " + largestShipLeft);
				if (coordinate1[0] == coordinate2[0] && coordinate1[1] != coordinate2[1]) { // checking for vertical gaps
					if (Math.abs(coordinate1[1] - coordinate2[1])/2 < largestShipLeft && coordinate1[0] - coordinate2[0] > largestShipLeft) { // if gap exists, aim for the midpoint
//						System.out.println("Candidate gap found between " + Arrays.toString(coordinate1) + " and " + Arrays.toString(coordinate2));
						double difference = (coordinate1[1] - coordinate2[1]);
						if (difference % 2 == 0) {
							candidate[1] = (int) (coordinate1[1] - difference/2);
						} else {
							int plusOrMinus = random.nextInt(2);
							if (plusOrMinus == 0) {
								candidate[1] = (int) (coordinate1[1] - (difference/2 + 0.5));
							} else {
								candidate[1] = (int) (coordinate1[1] - (difference/2 - 0.5));
							}
						}
						candidate[0] = coordinate2[0];
//						System.out.println("Potential candidate coordinate is: " + Arrays.toString(candidate));
						boolean duplicate = false;
						for (int[] coordinate : candidateCoordinates) {
							if (Arrays.equals(coordinate, candidate)) {
								duplicate = true;
								break;
							}
						}
						if (!duplicate) {
//							System.out.println("Not a duplicate, adding to candidates...");
							candidateCoordinates.add(candidate); // adds to potential guesses
						} else {
//							System.out.println("Duplicate. Cannot add.");
						}
					}
				}
				
//				System.out.println("Checking for a candidate horizontal gap between " + Arrays.toString(coordinate1) + "and" + Arrays.toString(coordinate2) + ". Largest ship left has length " + largestShipLeft);
				if (coordinate1[0] != coordinate2[0] && coordinate1[1] == coordinate2[1]) { // checking for horizontal gaps
					if (Math.abs(coordinate1[0] - coordinate2[0])/2 < largestShipLeft && coordinate1[0] - coordinate2[0] > largestShipLeft) { // if gap exists, aim for the midpoint
//						System.out.println("Candidate gap found between " + Arrays.toString(coordinate1) + " and " + Arrays.toString(coordinate2));
//						System.out.println("Candidate gap found!");
						double difference = (coordinate1[0] - coordinate2[0]);
						if (difference % 2 == 0) {
							candidate[0] = (int) (coordinate1[0] - difference/2);
						} else {
							int plusOrMinus = random.nextInt(2);
							if (plusOrMinus == 0) {
								candidate[0] = (int) (coordinate1[0] - (difference/2 + 0.5));
							} else {
								candidate[0] = (int) (coordinate1[0] - (difference/2 - 0.5));
							}
						}
						candidate[1] = coordinate2[1];
//						System.out.println("Potential candidate coordinate is: " + Arrays.toString(candidate));
						boolean duplicate = false;
						for (int[] coordinate : candidateCoordinates) {
							if (Arrays.equals(coordinate, candidate)) {
								duplicate = true;
								break;
							}
						}
						if (!duplicate) {
//							System.out.println("Not a duplicate, adding to candidates...");
							candidateCoordinates.add(candidate); // adds to potential guesses
						} else {
//							System.out.println("Duplicate. Cannot add.");
						}
					}
				}
			}
		}
		
		// edge cases
		int[][] edges = {{1,1},{2,1},{3,1},{4,1},{5,1},{6,1},{7,1},{8,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9}};
		for (int[] coordinate1 : shotsTaken) {
			for (int i = 0; i < edges.length; i++) {
				if (coordinate1[0] == edges[i][0] && coordinate1[1] != edges[i][1]) { // checking for vertical gaps
					if (Math.abs(coordinate1[1] - edges[i][1])/2 < largestShipLeft && coordinate1[0] - edges[i][0] > largestShipLeft) { // if gap exists, aim for the midpoint
//						System.out.println("Candidate gap found between " + Arrays.toString(coordinate1) + " and " + Arrays.toString(coordinate2));
						double difference = (coordinate1[1] - edges[i][1]);
						if (difference % 2 == 0) {
							candidate[1] = (int) (coordinate1[1] - difference/2);
						} else {
							int plusOrMinus = random.nextInt(2);
							if (plusOrMinus == 0) {
								candidate[1] = (int) (coordinate1[1] - (difference/2 + 0.5));
							} else {
								candidate[1] = (int) (coordinate1[1] - (difference/2 - 0.5));
							}
						}
						candidate[0] = edges[i][0];
//						System.out.println("Potential candidate coordinate is: " + Arrays.toString(candidate));
						boolean duplicate = false;
						for (int[] coordinate : candidateCoordinates) {
							if (Arrays.equals(coordinate, candidate)) {
								duplicate = true;
								break;
							}
						}
						if (!duplicate) {
//							System.out.println("Not a duplicate, adding to candidates...");
							candidateCoordinates.add(candidate); // adds to potential guesses
						} else {
//							System.out.println("Duplicate. Cannot add.");
						}
					}
				}
				if (coordinate1[0] != edges[i][0] && coordinate1[1] == edges[i][1]) { // checking for horizontal gaps
					if (Math.abs(coordinate1[0] - edges[i][0])/2 < largestShipLeft && coordinate1[0] - edges[i][0] > largestShipLeft) { // if gap exists, aim for the midpoint
//						System.out.println("Candidate gap found between " + Arrays.toString(coordinate1) + " and " + Arrays.toString(coordinate2));
//						System.out.println("Candidate gap found!");
						double difference = (coordinate1[0] - edges[i][0]);
						if (difference % 2 == 0) {
							candidate[0] = (int) (coordinate1[0] - difference/2);
						} else {
							int plusOrMinus = random.nextInt(2);
							if (plusOrMinus == 0) {
								candidate[0] = (int) (coordinate1[0] - (difference/2 + 0.5));
							} else {
								candidate[0] = (int) (coordinate1[0] - (difference/2 - 0.5));
							}
						}
						candidate[1] = edges[i][1];
//						System.out.println("Potential candidate coordinate is: " + Arrays.toString(candidate));
						boolean duplicate = false;
						for (int[] coordinate : candidateCoordinates) {
							if (Arrays.equals(coordinate, candidate)) {
								duplicate = true;
								break;
							}
						}
						if (!duplicate) {
//							System.out.println("Not a duplicate, adding to candidates...");
							candidateCoordinates.add(candidate); // adds to potential guesses
						} else {
//							System.out.println("Duplicate. Cannot add.");
						}
					}
				}
				
			}
		}
		
		if (largestShipLeft == 5) {
			int i;
			for (i = 1; i <= 9; i++) {
				boolean guessInRow = false;
				for (int[] coordinate : shotsTaken) {
					if (coordinate[0] == i) {
						guessInRow = true;
						break;
					}
				}
				if (!guessInRow) {
					candidate[0] = i;
					int roll = random.nextInt(2);
					candidate[1] = (roll == 1) ? 5 : 4;
					candidateCoordinates.add(candidate);					
				}
			}
			for (i = 1; i <= 8; i++) {
				boolean guessInColumn = false;
				for (int[] coordinate : shotsTaken) {
					if (coordinate[0] == i) {
						guessInColumn = true;
						break;
					}
				}
				if (!guessInColumn) {
					candidate[0] = i;
					candidate[1] = 5;
					candidateCoordinates.add(candidate);					
				}
			}
		}
		
		for (int i = 0; i < shotsTaken.size(); i++) {
			int [] check = shotsTaken.get(i);
//			System.out.println("Checking if I need to remove: " + Arrays.toString(check));
			candidateCoordinates.removeIf(coordinate -> Arrays.equals(coordinate, check));
		}
//		System.out.println("Candidate coordinates are: " + Arrays.deepToString(candidateCoordinates.toArray()));
	}
	
	
	/**
	 * checks
	 * @param hit
	 */
	public static void calculateAdjacentCoordinates() {
		for (int[] hits : GameState.enemyHits) {
			if (hits[0] - 1 != 0) { // adds coordinate above a hit (if not outside of grid) to adjacent coordinates
				int[] adjacent  = {hits[0]-1,hits[1]};
				boolean duplicate = false;
				for (int[] coordinate : adjacentCoordinates) {
					if (Arrays.equals(coordinate, adjacent)) {
						duplicate = true;
						break;
					}
					}
				if (!duplicate) {
					adjacentCoordinates.add(adjacent); // adds to potential guesses
				}
			}
			if (hits[0] + 1 < 10) { // adds coordinate directly below a hit (if not outside of grid) to adjacent coordinates
				int[] adjacent  = {hits[0]+1,hits[1]};
				boolean duplicate = false;
				for (int[] coordinate : adjacentCoordinates) {
					if (Arrays.equals(coordinate, adjacent)) {
						duplicate = true;
						break;
					} 
				}
				if (!duplicate) {
					adjacentCoordinates.add(adjacent); // adds to potential guesses
				}
			}
			if (hits[1] - 1 != 0) { // adds coordinate directly to the left of a hit (if not outside of grid) to adjacent coordinates
				int[] adjacent  = {hits[0],hits[1]-1};
				boolean duplicate = false;
				for (int[] coordinate : adjacentCoordinates) {
					if (Arrays.equals(coordinate, adjacent)) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					adjacentCoordinates.add(adjacent); // adds to potential guesses
				}
			}
			if (hits[1] + 1 < 9) { // adds coordinate directly to the right of a hit (if not outside of grid) to adjacent coordinates
				int[] adjacent  = {hits[0],hits[1]+1};
				boolean duplicate = false;
				for (int[] coordinate : adjacentCoordinates) {
					if (Arrays.equals(coordinate, adjacent)) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					adjacentCoordinates.add(adjacent); // adds to potential guesses
				}
			}
			
//			for (int[] shot : shotsTaken) {
//				adjacentCoordinates.removeIf(coordinate -> Arrays.equals(shot, coordinate));
//			}
			
			for (int i = 0; i < shotsTaken.size(); i++) {
					int [] check = shotsTaken.get(i);
//					System.out.println("Checking if I need to remove: " + Arrays.toString(check));
					adjacentCoordinates.removeIf(coordinate -> Arrays.equals(coordinate, check));
//					System.out.println("Adjacent coordinates after check: " + Arrays.deepToString(adjacentCoordinates.toArray()));
			}
//			
//			for (int i = 0; i < GameState.enemyHits.size(); i++) {
//					int[] check = GameState.enemyHits.get(i);
////					System.out.println("Checking if I need to remove: " + Arrays.toString(check));
//					adjacentCoordinates.removeIf(coordinate -> Arrays.equals(coordinate, check));
////					System.out.println("Adjacent coordinates after check: " + Arrays.deepToString(adjacentCoordinates.toArray()));
//			}
		}
	}
	
	public static int[] selectNextShot() {
		int[] shot = new int[2];
		
		calculateAdjacentCoordinates();
		if (!adjacentCoordinates.isEmpty()) {
//			System.out.println("Candidate adjacent coordinates are: " + Arrays.deepToString(adjacentCoordinates.toArray()));
			if (adjacentCoordinates.size() > 1) {
				shot = adjacentCoordinates.get(random.nextInt(0, adjacentCoordinates.size() - 1));
				return shot;
			} else {
				shot = adjacentCoordinates.get(0);
				return shot;
			}
		} else {
			calculateCandidateCoordinates();
			if (!candidateCoordinates.isEmpty()) {
//				System.out.println("Candidate coordinates are: " + Arrays.deepToString(candidateCoordinates.toArray()));
				if (candidateCoordinates.size() > 1) {
					shot = candidateCoordinates.get(random.nextInt(0, candidateCoordinates.size() - 1));
					return shot;
				} else {
					shot = candidateCoordinates.get(0);
					return shot;
				}
			} else {
				boolean valid;
				do {
					valid = true;
					shot[0] = random.nextInt(8)+1;
					shot[1] = random.nextInt(9)+1;
					
					for (int[] hit : GameState.enemyHits) { // checking hits for overlap
						if (Arrays.equals(shot, hit)) {
							valid = false;
							break;
						}
					}
					
					for (int[] hit : GameState.enemyMisses) { // checking misses for overlap
						if (Arrays.equals(shot, hit)) {
							valid = false;
							break;
						}
					}
					
				} while (!valid);
				return shot;
			}
		}
	}
	
	 
	}


