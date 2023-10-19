/**
 * 
 */
package battleships;

/**
 * Attempting to build a simple battleships game.
 * Goal is to have the programme place battleships of varying sizes on a virtual board.
 * Board will display to player, who can then guess a coordinate.
 * If coordinate is a hit or a miss, the board will change to reflect this.
 */
public class Battleships {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		System.out.println("__________          __     __   .__                   .__     .__                 \r\n"
				+ "\\______   \\_____  _/  |_ _/  |_ |  |    ____    ______|  |__  |__|______   ______ \r\n"
				+ " |    |  _/\\__  \\ \\   __\\\\   __\\|  |  _/ __ \\  /  ___/|  |  \\ |  |\\____ \\ /  ___/ \r\n"
				+ " |    |   \\ / __ \\_|  |   |  |  |  |__\\  ___/  \\___ \\ |   Y  \\|  ||  |_> >\\___ \\  \r\n"
				+ " |______  /(____  /|__|   |__|  |____/ \\___  >/____  >|___|  /|__||   __//____  > \r\n"
				+ "        \\/      \\/                         \\/      \\/      \\/     |__|        \\/  ");
		
		System.out.printf("%n%n\t\t\tWelcome to BATTLESHIPS!%n%n%n");
		System.out.printf("This game is still a work in progress. That's my excuse and I'm sticking with it.%n%n%n");
		
		Menu.menuBuilder();
		
		System.out.println("End of program.");

	} // end of main method
	
	
	
}