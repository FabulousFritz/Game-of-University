package application;
import java.util.Scanner;

/**
 * Extends player to create a human player that waits for prompts before executing actions
 * @author Jasmine
 *
 */
public class HumanPlayer extends Player{
	
	/**
	 * Initializes a Human Player with a specified name
	 * @param name
	 * 			Name of the player
	 */
	public HumanPlayer(String name) {
		super(name);
	}
	/**
	 * Initializes a Human Player with a specified name and color
	 * @param name
	 * 			Name of the player
	 * @param color
	 * 			Color of the player icon
	 */
	public HumanPlayer(String name, String color) {
		super(name,color);
	}
	
	/**
	 * Creates a deep copy of a Human player
	 * @param toCopy
	 * 			The Human player you want to make a deep copy of
	 */
	public HumanPlayer(HumanPlayer toCopy) {
		super((Player)toCopy);
	}
	
	/**
	 * Plays the player's turn
	 * @param board
	 * 			The board the player interacts with during their turn
	 */
	public void playTurn(Board board) {
		System.out.println("Press enter to spin ");
		Scanner kb = new Scanner(System.in);
		kb.nextLine();
		executeTurn(board);
		System.out.println("Press any key to end your turn");
		kb.nextLine();
	}
}
