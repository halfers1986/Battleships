/**
 * 
 */
package battleships;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 */
public class ShipBuilder {

	Random random = new Random();
	
	String name;
	int length;
	boolean sunk;
	int[][] coordinates;
	ArrayList<int[]> listCoordinates;
	
	/**
	 * Constructor to build each ship.
	 * @param name
	 * @param length
	 */
	public ShipBuilder(String name, int length) {
		this.name = name;
		this.length = length;
		this.sunk = false;
		this.coordinates = new int[length][];
		this.coordinates = initialCoordinates(length); // runs the method that will define all of the coordinates of the ship and adds them to the array
		this.listCoordinates = new ArrayList<int[]>(); // in order to manipulate the ship's coordinates (i.e. remove when a hit is scored) need to pass the coordinates into an ArrayList
		for (int i = 0; i < this.coordinates.length; i++) { // passes the array into the Array list
			this.listCoordinates.add(this.coordinates[i]); // wanted to use addAll here rather than using for loop, but didn't work. Don't understand enough about arrays to get why.
		}
		
	}

	
	/**
	 * Takes the length of the ship passed to it and creates an array which
	 * describes the position of the ship on the board as a set of initial coordinates.
	 * 
	 * Coordinates may overlap with other ships, but this is dealt with in the ShipPlacer methods.
	 * 
	 * 
	 * @param length2
	 * @return array
	 */
	private int[][] initialCoordinates(int length2) { // print statements in this method were for debugging. They have been commented out.
		int[][] coordinates = new int[length2][];
		int[] bowCoordinate = new int[2];
		int[] sternCoordinate = new int[2];
		String orientation = (random.nextInt(2) == 1) ? "Vertical" : "Horizontal";
//		System.out.println("Orientation is " + orientation);
		String direction1;
		String direction2;
		bowCoordinate[0] = random.nextInt(7) + 1; // declares the x-axis point of the initial coordinate
		bowCoordinate[1] = random.nextInt(8) + 1;// declares the y-axis point of the initial coordinate
//		System.out.println("bow coordinate is " + Arrays.toString(bowCoordinate));

		
		// if the ship is being placed vertically, determines if there is space,
		// and which direction the stern will be (up or down)
		if (orientation.equals("Vertical")) { 
			String fitsUp = "yes";
			String fitsDown = "yes";
			do {
				direction1 = (random.nextInt(2) == 1) ? "Upwards" : "Downwards"; // randomly decides which direction the
																					// stern of the ship will be from
																					// the bow coordinates
//			System.out.println("ShipBuilder being placed " + direction1);
				if (direction1.equals("Downwards") && bowCoordinate[1] + (length2 - 1) > 9) {
//					System.out.println("Down didn't fit, reorienting");
					direction1 = "Upwards";
					fitsDown = "no";
//					System.out.println("ShipBuilder being placed " + direction1);
				}
				if (direction1.equals("Upwards") && bowCoordinate[1] - (length2 - 1) < 1) {
//					System.out.println("Up didn't fit, reorienting");
					direction1 = "Downwards";
					fitsUp = "no";
//					System.out.println("ShipBuilder being placed " + direction1);
				}
				if (fitsUp.equals("no") && fitsDown.equals("no")) { // if there is no space either up or down, re-rolls
																	// the initial y-coordinate
//					System.out.println("ShipBuilder doesn't fit. Rerolling bow coordinate");
					bowCoordinate[1] = random.nextInt(8) + 1;
//					System.out.println("New bow coordinate is " + Arrays.toString(bowCoordinate));
				}
			} while (fitsUp.equals("no") && fitsDown.equals("no")); // repeat while the ship will not fit
			sternCoordinate[1] = (direction1.equals("Upwards")) ? bowCoordinate[1] - (length2 - 1): bowCoordinate[1] + (length2 - 1); // assigns the stern y-coordinate based on conditions above
			sternCoordinate[0] = bowCoordinate[0]; // if the ship is being placed vertically, x-coordinate does not
													// change

			coordinates[0] = bowCoordinate; // adds bow coordinates to the full coordinates array
			coordinates[(length2 - 1)] = sternCoordinate; // adds stern coordinates to the full coordinates array
			if (length2 > 2) { // loops over remaining spaces in the full coordinates array to fill in the
								// missing coordinates
				int counter = 1; // tracks how many iterations we are going through so that the correct number
									// can be added or subtracted
				for (int i = 1; i < length2 - 1; i++) {
					int[] coordinateI = new int[2]; // temporary storage for each new coordinate
					coordinateI[0] = bowCoordinate[0]; // if the ship is being placed horizontally, y-coordinate does
														// not change
					if (direction1.equals("Downwards")) {
						coordinateI[1] = bowCoordinate[1] + counter; // assigns the x coordinate to the right of the bow
					} else {
						coordinateI[1] = bowCoordinate[1] - counter; // assigns the x coordinate to the left of the bow
					}
					coordinates[i] = coordinateI; // stores the temporary array in the correct position in the ship
													// coordinates array
					counter++;
				}
			}
		}

		
		// if the ship is being placed horizontally, determines if there is
		// space, and which direction the stern will be (left or right)
		
		if (orientation.equals("Horizontal")) { 
			String fitsLeft = "yes";
			String fitsRight = "yes";
			do {
				direction2 = (random.nextInt(2) == 1) ? "Left" : "Right"; // randomly decides which direction the stern
																			// of the ship will be from the bow
																			// coordinates
//			System.out.println("ShipBuilder being placed " + direction2);
				if (direction2.equals("Left") && bowCoordinate[0] - (length2 - 1) < 1) {
//					System.out.println("Left didn't fit, reorienting");
					direction2 = "Right";
					fitsLeft = "no";
//					System.out.println("ShipBuilder being placed " + direction2);
				}
				if (direction2.equals("Right") && bowCoordinate[0] + (length2 - 1) > 8) {
//					System.out.println("Right didn't fit, reorienting");
					direction2 = "Left";
					fitsRight = "no";
//					System.out.println("ShipBuilder being placed " + direction2);
				}
				if (fitsLeft.equals("no") && fitsRight.equals("no")) { // if there is no space either left or right,
																		// re-rolls the initial x-coordinate
//					System.out.println("ShipBuilder doesn't fit. Rerolling bow coordinate");
					bowCoordinate[0] = random.nextInt(7) + 1;
//					System.out.println("New bow coordinate is " + Arrays.toString(bowCoordinate));
				}
			} while (fitsLeft.equals("no") && fitsRight.equals("no")); // repeat while the ship will not fit
			sternCoordinate[0] = (direction2.equals("Left")) ? bowCoordinate[0] - (length2 - 1) : bowCoordinate[0] + (length2 - 1); // assigns the stern x-coordinate based on conditions above
			sternCoordinate[1] = bowCoordinate[1]; // if the ship is being placed horizontally, y-coordinate does not
													// change

			
			
			coordinates[0] = bowCoordinate; // adds bow coordinates to the full coordinates array
			coordinates[(length2 - 1)] = sternCoordinate; // adds stern coordinates to the full coordinates array
			
			
			
			if (length2 > 2) { // loops over remaining spaces in the full coordinates array to fill in the
								// missing coordinates
				int counter = 1; // tracks how many iterations we are going through so that the correct number
									// can be added or subtracted
				for (int i = 1; i < length2 - 1; i++) {
					int[] coordinateI = new int[2]; // temporary storage for each new coordinate
					coordinateI[1] = bowCoordinate[1]; // if the ship is being placed horizontally, y-coordinate does
														// not change
					if (direction2.equals("Right")) {
						coordinateI[0] = bowCoordinate[0] + counter; // assigns the x coordinate to the right of the bow
					} else {
						coordinateI[0] = bowCoordinate[0] - counter; // assigns the x coordinate to the left of the bow
					}
					coordinates[i] = coordinateI; // stores the temporary array in the correct position in the ship
													// coordinates array
					counter++;
				}
			}

		}
		return coordinates;
	}
}
