package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Creates the screen that presents who won the game at the end of the game.
 * @author Felicity
 *
 */
public class WinScreen {
	/**
	 * Creates the screen that presents who won the game at the end of the game
	 * @param players - the List of all the players playing the game.
	 * 
	 */
	public WinScreen (ArrayList<Player> players) {
		Stage winWindow = new Stage() ;
		winWindow.setTitle("And the winner is...") ;
		VBox layout = new VBox(3) ;
		Player[] winners = winningPlayers(players) ;
		Label winnerNameText = new Label(winStatement(winners)) ;
		winnerNameText.setFont(new Font(GUIMain.fontType, 25)) ;
		
		Label winnerMoneyText = new Label() ;
		if (winners[1] != null) {
			winnerMoneyText.setText("They both had $" + winners[0].getBankAccount() + " in their Bank Accounts!") ;
		} else {
			winnerMoneyText.setText("They had $" + winners[0].getBankAccount() + " in their Bank Account!") ;
		}
		winnerMoneyText.setFont(new Font(GUIMain.fontType, 25));
		
		layout.getChildren().addAll(winnerNameText , winnerMoneyText ,  goToStart(winWindow)) ;
		layout.setAlignment(Pos.CENTER) ;
		
		Scene winScreen = new Scene(layout , 700 , 500) ;
		winWindow.setScene(winScreen) ;
		winScreen.getStylesheets().add(getClass().getResource("StartEndBackground.css").toExternalForm());
		winWindow.show() ; 
	}
	
	/** Finds the winning players and puts them in a list in case they have the same bank account balance
	 * @param players - the list of the players
	 * @return winner - the player with the highest bank account balance
	 */
	public Player[] winningPlayers (ArrayList<Player> players) {
		Player[] winners = new Player[2] ;
		
		winners[0] = players.get(0) ;
		for (int i = 1 ; i < players.size() ; i++) {
			if (winners[0].getBankAccount() < players.get(i).getBankAccount()) {
				winners[0] = players.get(i) ;
			} else if (winners[0].getBankAccount() == players.get(i).getBankAccount()) {
				winners[1] = players.get(i) ;
			}
		}
		return winners ;	
	}
	
	/**
	 * Generates the win statement based on a tie
	 * @param winners - the list of the winners
	 * @return the statement about who won.
	 */
	public String winStatement (Player[] winners) {
		if (winners[1] != null) {
			return winners[0].getName() + " and " + winners [1].getName() + " are the winners!" ;
		} else {
			return winners[0].getName() + " is the winner!" ;
		}
	}
	
	/** Makes button to return to start screen
	 * @return the button that returns the player to the start menu.
	 */
	public Button goToStart(Stage stage) {
		Button goToStartButton = new Button("Go To Start") ;
		goToStartButton.setFont(new Font(GUIMain.fontType , 15)) ;
		EventHandler<ActionEvent> makeIt = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.close();
					Stage newScreen = new Stage() ;
					newScreen.setTitle("The Game of University") ;
					new StartScreen(newScreen) ;
					newScreen.show() ;
				} catch (Exception ex) {
					ex.printStackTrace() ;
				}
			}
		};
		goToStartButton.setOnAction(makeIt) ;
		return goToStartButton ;
	}
}
