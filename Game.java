package application;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Sets up and plays the game
 * @author Felicity
 *
 */
public class Game {

	private ArrayList<Player> players = new ArrayList<Player>();
	private Board board = new Board();
	private boolean isGameOver = false;

	/**
	 * Gathers all the information about the players and sets up the game.
	 */
	public void setUp() {

		// Get the user's name and make their reference
		System.out.println("Player 1, what should I call you?");
		Scanner keyboard = new Scanner(System.in);
		String name = keyboard.nextLine();
		players.add(new HumanPlayer(name));
		// greet the user.
		System.out.println("Hi " + name + "!");

		// ask if they want another human player or a computer
		System.out.println("Is another human playing? (Yes / No)");
		String response = keyboard.nextLine();
		System.out.println("response: " + response);
		if (response.contentEquals("Yes") || response.contentEquals("Y") || response.contentEquals("yes")
				|| response.contentEquals("y")) {
			System.out.println("Hi Player 2 , what name should I call you?");
			name = keyboard.nextLine();
			players.add(new HumanPlayer(name));
		} else if (response.contentEquals("No") || response.contentEquals("N") || response.contentEquals("no")
				|| response.contentEquals("n")) {
			System.out.println("What should I call the computer player?");
			name = keyboard.nextLine();
			players.add(new ComputerPlayer(name));
		} else {
			System.out.println("Invalid answer. I will let you play against me, the computer Overlord.");
			players.add(new ComputerPlayer("Computer Overlord"));
		}
		System.out.println("Great! Let's get started!");

	}

	/**
	 * Goes through the game play and prompts the user input.
	 * Determine the winner once both players have reached the end.
	 */
	public void play() {

		// this is a long while loop to wait until both players reach the end of the
		// board.
		while (isGameOver == false) {
			int checkIsGameOver = 0;
			int lastSpace = board.getLength();
			for (int index = 0; index < players.size(); index++) {
				Player currentPlayer = players.get(index);
				if (currentPlayer.getCurrentLocation() < lastSpace) {
					System.out.println(currentPlayer.getName() + " it is your turn to spin the wheel.") ;
					currentPlayer.playTurn(board);
					checkIsGameOver++;
				}
			}
			if (checkIsGameOver == 0) {
				isGameOver = true;
			}
		}

		System.out.println() ;
		Player winner = players.get(0);
		System.out.println(winner.getName() + " has $" + winner.getBankAccount() + " in their Bank Account!") ;
		for (int index = 1; index < players.size(); index++) {
			Player check = players.get(index);
			System.out.println(check.getName() + " has $" + check.getBankAccount() + " in their Bank Account!" );
			if (check.getBankAccount() > winner.getBankAccount()) {
				winner = check;
			}
			if ((check.getName()).contentEquals("Computer Overlord")) {
				winner = check;
				System.out.println("The Overlord always wins when the Overlord plays.") ;
				System.out.println("The Overlord drains your bank account.") ;
			}
		}
		System.out.println() ;
		System.out.println("The winner is " + winner.getName() + " with $" +
		winner.getBankAccount() + "!") ;

	}
}
