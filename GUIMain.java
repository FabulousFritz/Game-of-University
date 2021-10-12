package application;


import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class will open the start menu of the Game and Initializes it.
 * @author Celia 
 * 
 */
public class GUIMain extends Application {
	private Stage window ;
	// used static because we use it throughout the game and now we only need to
	// change it in one spot
	public static String fontType = "Berlin Sans FB Demi Bold";
	
	public static void main(String[] args) {
		launch(args) ;    
	}

	@Override
	/**
	 * Creates the window that the game will take place on
	 * @param primaryStage
	 * 			The main window the game takes place on
	 */
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage ;
		new StartScreen(window) ;

		// set up the window and get it started
		window.setTitle("The Game of University") ;
		window.show() ;
	}
}