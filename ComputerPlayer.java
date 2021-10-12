package application;

/**
 * Extends player to create a computer player that automatically does its turn
 * @author Jasmine
 *
 */
public class ComputerPlayer extends Player{

	/**
	 * Initializes a Computer Player with a specified name
	 * @param name
	 * 			Name of the player 
	 */
	public ComputerPlayer(String name) {
		super(name);
	}
	
	/**
	 * Initializes a Computer Player with a specified name and color
	 * @param name
	 * 			Name of the player
	 * @param color
	 * 			Color of the player icon
	 */
	public ComputerPlayer(String name, String color) {
		super(name, color);
	}
	
	/**
	 * Plays the player's turn
	 * @param board
	 * 			The board the player interacts with during their turn
	 */
	public void playTurn(Board board) {
		executeTurn(board);
	}
}
