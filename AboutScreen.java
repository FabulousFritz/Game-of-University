package application ;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 * Changes the window to display an about section. The about screen tells the user information about the game.
 * @author Felicity
 *
 */
public class AboutScreen {
	/**
	 * Changes the window to display an about section
	 * @param stage
	 * 			The window we want to change
	 */
	public AboutScreen (Stage stage) {
		VBox layout = new VBox(2) ;
		Text aboutText = new Text("\n The Game of University is based on the board game: \"The Game of Life.\" \n"
				+ " It follows students around as they make their way through university, \n"
				+ " trying not to accumulate too much debt as they go around the board. \n"
				+ " They Spin a Wheel and their piece will move around the board until \n"
				+ " they reach the end. \n"
				+ " At the end the one least in debt is the winner! \n \n"
				+ " This game was created by the BRILLIANT MINDS of: \n"
				+ " Thanh Hien Nguyen-Mai, Celia Skaley, Mallory Zorman, and Felicity Merrick. \n \n") ;
		aboutText.setTextAlignment(TextAlignment.CENTER) ;
		aboutText.setFont(new Font("Calibri" , 20)) ;
		layout.setAlignment(Pos.CENTER) ;
		
		
		Button backToStart = new Button("Return to Start") ;
		
		backToStart.setOnAction(e -> new StartScreen(stage)) ;
		backToStart.setFont(new Font("Berlin Sans FB Demi Bold" , 20)) ;
		backToStart.setAlignment(Pos.BOTTOM_CENTER);
		
		layout.getChildren().addAll(aboutText , backToStart) ;
		layout.setAlignment(Pos.TOP_CENTER);
		
		Scene aboutPage = new Scene(layout , 650 , 450) ;
		aboutPage.getStylesheets().add(getClass().getResource("StartEndBackground.css").toExternalForm());
		
		stage.setScene(aboutPage) ;
	}

}