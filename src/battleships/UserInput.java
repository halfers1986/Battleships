package battleships;

import java.util.Scanner;

public class UserInput {
	static Scanner scanner = new Scanner(System.in);
	public static String yesNo() {
		String yesNo;
		do {
			yesNo = scanner.nextLine();
			if (yesNo.length() > 1) {
				System.out.println("Invalid input. Please try again.");
			} else {
				if (!yesNo.equalsIgnoreCase("n") && !yesNo.equalsIgnoreCase("y")) {
					System.out.println("Invalid input. Please try again.");
				}
			}
		} while (!yesNo.equalsIgnoreCase("n") && !yesNo.equalsIgnoreCase("y"));
		
		return yesNo;
	}
	
	
	public static int[] playerShot() {
		System.out.printf("Take your shot! Enter coordinates as a letter immediately followed by a number (eg \"A1\")%n%n");
		char[] xAxis = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
		int[] shot = new int[2]; // each shot will be an array with 2 integer values - coordinates - the same as the way the coordinates are stored for the ships
		String input;
		boolean point1Valid;
		boolean point2Valid;
		
		while (true) { // Validates the user input. If invalid, prompts to try again.
			try {
				do {
					point1Valid = false;
					point2Valid = false;
					input = scanner.nextLine();

					shot[0] = input.charAt(0); // assigns the first character of the user input to the shot array as the x-axis
					shot[1] = Integer.parseInt(input.substring(1)); // parses the rest of the user input for an int and assigns to the y-axis part of the shot array

					for (int i = 0; i < 8; i++) {
						if (Character.toUpperCase(shot[0]) == (xAxis[i])) { // checks that the character is A-H. If not, var that describes validity remains false, and input will be invalid.
							shot[0] = i + 1; // changes character to the equivalent int so that shot can be compared with other coordinates
							point1Valid = true;
							break;
						}
					}

					if (shot[1] > 0 && shot[1] < 11) { // checks the 2nd part of shot is between 1-10
						point2Valid = true;
					}

					if (!point1Valid || !point2Valid) { // if either part of the shot are invalid, message shown, and new input requested
						System.out.println("Invalid input. Please try again.");
					}

				} while (!point1Valid || !point2Valid);
				break;
			} catch (Exception e) { // any other errors (eg if end of string cannot be parsed to an int) will show invalid input message and prompt to try again
				System.out.println("Invalid input. Please try again.");
			} 
		}
		return shot;
	}
	
	public static int menuOptions() {
		int output = 0;
		while (true) {
			try {
				int input = 0;
					do {
						input = scanner.nextInt();
						scanner.nextLine();
						if (input > 4 || input < 1) {
							System.out.println("Invalid choice. Please try again.");
						}
					} while (input > 4 || input < 1);
				output = input;
				break;
			} catch (Exception e) {
				System.out.println("UserInput not recognised. Please try again.");
			}
		}
		return output;
	}	
}
