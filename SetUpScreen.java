package application;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Creates the screen for players to enter name and customize their color
 * @author Felicity
 *
 */
public class SetUpScreen {
	private Scene setUpMenu ;
	private ArrayList<Player> players = new ArrayList<Player>() ;
	private HBox playerSelection[] ;
	private String playerInfo[][] ;
	private final int NAME = 0 ;
	private final int COLOR = 1 ;
	private final int TYPE = 2 ;
	private TextField playerNames[] ;
	private boolean isP1ColourChosen = false ;
	private boolean isP2ColourChosen = false ;
	private Label askForColour = new Label() ;

	/**
	 * Creates the screen for players to enter name and customize their color
	 * @param stage
	 * 			Is the main window that displays the information
	 */
	public SetUpScreen(Stage stage) {
		VBox layout = new VBox(7) ;
		setUpMenu = new Scene(layout , 700 , 500) ;
		playerSelection = new HBox[2] ;
		playerInfo = new String[2][3] ;
		playerInfo[0][TYPE] = "Human" ; 
		playerInfo[1][TYPE] = "Human" ; 
		playerNames = new TextField[2] ;
		for(int i = 0 ; i < playerSelection.length ; i++) {
			playerSelection[i] = initPlayerSelectionInfo(i) ;
			layout.getChildren().add(playerSelection[i]) ;
		}
		Button startBtn = initStartButton(stage) ;
		layout.getChildren().addAll(startBtn , askForColour) ;
		setUpMenu.getStylesheets().add(getClass().getResource("SetUpBackground.css").toExternalForm());
		stage.setScene(setUpMenu) ;
	}

	/**
	 * Creates the selecting info for a player
	 * @param playerIndex
	 * 			The index of the player
	 * @return
	 * 			The selecting info
	 */
	public HBox initPlayerSelectionInfo(int playerIndex) {
		// make a little icon to show the color that the player selected
		int iconRadius = 30 ;
		Circle icon = new Circle(iconRadius) ;
		icon.setFill(Color.DARKGRAY) ;
		
		ChoiceBox<String> typeBox;
		//Makes sure that the first player is always a human
		if(playerIndex != 0) {
			typeBox = new ChoiceBox<String>(FXCollections.observableArrayList("Human" , "Computer")) ;
		}else {
			typeBox = new ChoiceBox<String>(FXCollections.observableArrayList("Human")) ;
		}
		typeBox.setValue("Human") ;
		TextField name = new TextField("Player " + (playerIndex + 1) + "'s Name") ;
		playerNames[playerIndex] = name ;
		
		//lists color options for the player icon
		ChoiceBox<String> colourBox = new ChoiceBox<String>(
				FXCollections.observableArrayList("Colour" , "Red" , "Orange" , "Yellow" , "Green" , "Blue" , "Purple")) ;
		colourBox.setValue("Colour") ;
		colourBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue , Number number , Number number2) {
				String playerColor = colourBox.getItems().get((Integer) number2) ;
				if (playerIndex == 0) {
					isP1ColourChosen = true ;
				} else if (playerIndex == 1) {
					isP2ColourChosen = true ;
				}
				// find which color the player selected
				playerInfo[playerIndex][COLOR] = playerColor ;
				
				switch (playerColor) {
				case "Red":
					icon.setFill(Color.web("FF6961")) ;
					break ;
				case "Orange":
					icon.setFill(Color.SANDYBROWN) ;
					break ;
				case "Yellow":
					icon.setFill(Color.web("#FDDD5C")) ;
					break ;
				case "Green":
					icon.setFill(Color.web("#9DE7D7")) ;
					break ;
				case "Blue":
					icon.setFill(Color.SKYBLUE) ;
					break ;
				case "Purple":
					icon.setFill(Color.MEDIUMPURPLE) ;
					break ;
				default:
					icon.setFill(Color.DARKGRAY) ;
				}
			}

		}) ;

		// add the items to a horizontal box, which will be added to a vertical box
		HBox setUp = new HBox(7) ;
		setUp.getChildren().addAll(icon , typeBox , name , colourBox) ;
		typeBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue , Number number , Number number2) {
				playerInfo[playerIndex][TYPE] = typeBox.getItems().get((Integer) number2) ;
				if (playerInfo[playerIndex][TYPE].contentEquals("Computer")) {
					name.setText("Computer Steve") ;
				}
			}

		}) ;
		return setUp ;
	}

	/**
	 * Initializes the start button and its functionality
	 * @return
	 * 			The start button
	 */
	public Button initStartButton(Stage stage) {
		Button startGameButton = new Button("Get Started!") ;
		startGameButton.setFont(new Font(GUIMain.fontType , 20)) ;
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				askForColour.setFont(new Font(GUIMain.fontType , 20)) ;
				if (isP1ColourChosen == true && isP2ColourChosen == true) {
					// takes all the info the player gave
					// and makes a player based on that information.
					for(int i = 0; i < playerSelection.length; i++ ) {
						playerInfo[i][NAME] = playerNames[i].getText() ;
						if (playerInfo[i][NAME].contentEquals("Player " + (i + 1) + "'s Name")) {
							playerInfo[i][NAME] = "Player " + (i + 1) ;
						}
						if (playerInfo[i][TYPE].contentEquals("Human")) {
							players.add(new HumanPlayer(playerInfo[i][NAME] , playerInfo[i][COLOR])) ;
						} else if (playerInfo[i][TYPE].contentEquals("Computer")) {
							players.add(new ComputerPlayer(playerInfo[i][NAME] , playerInfo[i][COLOR])) ;
						} else {
							players.add(new ComputerPlayer(playerInfo[i][NAME] , playerInfo[i][COLOR])) ;
						}
						
						
					}
					new GameScreen(stage , players, playerInfo) ;
				} else if (isP1ColourChosen) {
					askForColour.setText("Player 2, please choose a colour before we start the game!") ;
				} else if (isP2ColourChosen) {
					askForColour.setText("Player 1, please choose a colour before we start the game!") ;
				} else {
					askForColour.setText("Players, please choose a colour before we start the game!") ;
				}
			} 
		} ;

		startGameButton.setOnAction(event) ;
		return startGameButton ;
	}
}