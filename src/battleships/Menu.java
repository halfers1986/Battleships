/**
 * 
 */
package battleships;

import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class Menu {
	
	public static void menuBuilder() {
		System.out.printf("\t\tMENU%n%n");
		System.out.println("1)\tNew Game");
		System.out.println("2)\tInstructions");
		System.out.println("3)\tActivate CHEAT MODE");
		System.out.printf("4)\tExit%n%n");
		System.out.println("Type your option (1-4) and press enter...");
		int output = UserInput.menuOptions();
		switch (output) { 
		case 1: PlayGame.initialise();
			break;
		case 2: instructions();
			break;
		case 3: cheatMode();
			break;
		case 4: exit();
			break;
		}
	}


	
	public static void instructions() {
		System.out.printf("Sorry, I haven't the instructions yet. But it's pretty self explanatory, no?%n%n%n");
		System.out.println("Returning to main menu...\n\n\n");
		Menu.menuBuilder();
	}

	
	public static void cheatMode() {
		if (!GameState.cheatMode) {
			System.out.printf("Do you want to activate CHEAT MODE? (y/n)%n");
			String yesNo = UserInput.yesNo();
			
			if (yesNo.equalsIgnoreCase("n")) {
				System.out.printf("Returning to main menu...%n%n%n");
				Menu.menuBuilder();
			} else {
				GameState.cheatMode = true;
				System.out.printf("%n%nCHEAT MODE ACTIVATED%n%n");
				System.out.printf("Returning to main menu...%n%n%n");
				Menu.menuBuilder();
			}
		}
		
		if (GameState.cheatMode) {
			System.out.printf("Do you want to deactivate CHEAT MODE? (y/n)%n");
			String yesNo = UserInput.yesNo();
			
			if (yesNo.equalsIgnoreCase("n")) {
				System.out.printf("Returning to main menu...%n%n%n");
				Menu.menuBuilder();
			} else {
				GameState.cheatMode = false;
				System.out.printf("%n%nCHEAT MODE DEACTIVATED%n%n");
				System.out.printf("Returning to main menu...%n%n%n");
				Menu.menuBuilder();
			}
		}
	}
	
	public static void exit() {
		System.out.printf("Are you sure you want to quit? (y/n)%n");
		String yesNo = UserInput.yesNo();
		
		if (yesNo.equalsIgnoreCase("n")) {
			System.out.printf("Returning to main menu...%n%n%n");
			Menu.menuBuilder();
		} else {
			try {
				System.out.print("Exiting");
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.print(".");
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.print(".");
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.print(".\n\n");
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
