package application;

import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Creates the game board that players will view and interact with
 * @author Jasmine
 *
 */
public class GameScreen {
	private Scene board;
	private GridPane grid = new GridPane();
	private Player currentPlayer;
	private int currentTurn;
	private ArrayList<Player> players;
	private ArrayList<Circle> playerIcons;
	private Label msg1;
	private Label msg2;
	private int firstPlayerOffSetX = 25;
	private int secondPlayerOffSetX = 75;
	private int playerOffSetY = 36;
	private Button playerBtn;
	private Button endButton ;
	private Button dropOutButton ;
	Pane layout = new Pane();
	private VBox buttons = new VBox(3);
	private HBox messages = new HBox(2);
	public static Double recSize = 100.0;
	private Rectangle[] rectangles = initRects();
	public static int lastGameSpace = 47;
	//2D array that assigns a coordinate set from location list to a rectangle
	private int[][] locationList;
	private final int GRIDX = 0;
	private final int GRIDY = 1;
	// Coordinates where every pair represents a set of coordinates for the locations on the board
	private int[] coordList = { 0, 6, 0, 5, 0, 4, 0, 3, 0, 2, 0, 1, 0, 0, 1, 0, 2, 0, 2, 1, 2, 2, 2, 3, 2, 4, 2, 5, 2, 6, 3, 6,
			4, 6, 4, 5, 4, 4, 4, 3, 4, 2, 4, 1, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, 9, 1, 9, 2, 9, 3, 9, 4, 8, 4, 7, 4,
			6, 4, 6, 5, 6, 6, 7, 6, 8, 6, 9, 6, 10, 6, 11, 6, 11, 5, 11, 4, 11, 3, 11, 2, 11, 1, 11, 0 };

	/**
	 * Returns the board scene
	 * @return board
	 * 			The board scene
	 */
	public Scene getBoard() {
		return board;
	}
	
	/**
	 * Returns the grid that the game squares are on
	 * @return grid
	 * 			the grid that the game squares are on
	 */
	public GridPane getGrid() {
		return grid;
	}

	/**
	 * Returns a list of all the players in the game
	 * @return players
	 * 			A list of the players in the game
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Returns a list of all the player icons
	 * @return playerIcons
	 * 			A list of all the player icons
	 */
	public ArrayList<Circle> getPlayerIcons() {
		return playerIcons;
	}

	/**
	 * Returns a 2D array of the rectangles and their corresponding location on the grid 
	 * @return locationList
	 * 			a 2D array of the rectangles and their corresponding location on the grid 
	 */
	public int[][] getLocationList() {
		return locationList;
	}

	/**
	 * Creates the visual game board, player icons, and game play buttons
	 * @param stage
	 * 			Is the main window that displays the information
	 */
	public GameScreen(Stage stage, ArrayList<Player> players, String[][] playerInfo) {
		locationList = new int[coordList.length / 2][2];
		int spaceIndex = 0;
		for (int i = 0; i < coordList.length; i++) {
			locationList[spaceIndex][i % 2] = coordList[i];
			if (i % 2 == 1) {
				spaceIndex++;
			}
		}
		this.players = players;

		// For loop to place rectangles in the board spaces.
		for (int index = 0; index < rectangles.length; index++) {
			grid.add(rectangles[index], locationList[index][0], locationList[index][1]);
		}
		
		Label title = new Label("The Game of University") ;
		title.setFont(new Font(GUIMain.fontType , 20)) ;
		currentPlayer = players.get(0);
		playerBtn = wheelButton(stage);
		endButton = endTurnButton(stage) ;
		endButton.setVisible(false) ;
		
		dropOutButton = dropOutButton(stage) ;
		dropOutButton.setLayoutX(1111) ;
		dropOutButton.setLayoutY(740) ;
 		
		buttons.setLayoutX(600) ;
		buttons.setLayoutY(200) ;
		buttons.getChildren().addAll(title , playerBtn , endButton) ;
		buttons.setAlignment(Pos.CENTER) ;
		
		layout.getChildren().addAll(grid,buttons , dropOutButton);
		
		playerIcons = new ArrayList<Circle>();
		
		int counter = 0;
		//creates the player icon and sets the value for the players based on their choices in the SetUpScreen
		for(Player p:players) {
			p.setGridX(locationList[0][GRIDX]);
			p.setGridY(locationList[0][GRIDY]);
			p.setLocationList(locationList);
			p.setType(playerInfo[counter][2]);
			Circle icon = new Circle(25,colorConverter(p.getColor()));
			p.setOffSetY(playerOffSetY);
			if(counter == 0) {
				p.setOffSetX(firstPlayerOffSetX);
			}else {
				p.setOffSetX(secondPlayerOffSetX);
			}
			icon.setLayoutX((p.getGridX()*recSize)+p.getOffSetX());
			icon.setLayoutY((p.getGridY()*recSize)+p.getOffSetY());
			p.setIcon(icon);
			playerIcons.add(icon);
			layout.getChildren().add(icon);	
			counter++;
		}
		
		messages.setLayoutX(270);
		messages.setLayoutY(750);
		msg1 = msg(0);
		msg2 = msg(1);
		messages.getChildren().addAll(msg1, msg2);
		layout.getChildren().addAll(messages);
		
		board = new Scene(layout, 1200, 800);
		board.getStylesheets().add(getClass().getResource("MainGameBackground.css").toExternalForm());
		stage.setScene(board);
		stage.setX(0);
		stage.setY(0);
		stage.setResizable(false) ;
		
	}

	/**
	 * Generates the message about the player's current bank account balance
	 * @param i
	 * 			The index of the player in the players list
	 * @return msg
	 * 			The message to be displayed
	 */
	public Label msg(int i) {
		Label msg = new Label() ;
		msg.setFont(new Font(GUIMain.fontType, 15));
		msg.setText(players.get(i).getName() + ", you have $" + players.get(i).getBankAccount() + " in your account. ");
		return msg;
	}
	
	/**
	 * Creates the button that generates the wheel on a separate pop up
	 * @return wheelSpinButton
	 * 			the button that generates the wheel
	 */
	public Button wheelButton(Stage stage) {
		Button wheelSpinButton = new Button(currentPlayer.getName() + ", spin the wheel!");
		wheelSpinButton.setFont(new Font(GUIMain.fontType, 15));
		Stage wheelStage = new Stage();
		wheelStage.initStyle(StageStyle.UNDECORATED) ;

		EventHandler<ActionEvent> makeThatSpin = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
						Pane wheelPane = new WheelPane(currentPlayer , wheelStage);
						Scene scene = new Scene(wheelPane, 570, 490);
						scene.getStylesheets().add(getClass().getResource("wheelColors.css").toExternalForm());
						wheelStage.setScene(scene);
						wheelStage.show();
						wheelSpinButton.setVisible(false);
						endButton.setVisible(true) ;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		wheelSpinButton.setOnAction(makeThatSpin);
		return wheelSpinButton;
	}
	
	/**
	 * Creates the button to end a player's turn
	 * @param stage
	 * 			The window the button will go on
	 * @return endTurnButton
	 * 			The button to end a player's turn
	 */
	public Button endTurnButton(Stage stage) {
		Button endTurnButton = new Button("End Turn");
		endTurnButton.setFont(new Font(GUIMain.fontType, 15));
		
		EventHandler<ActionEvent> endTurn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					messages.getChildren().removeAll(msg1,msg2);
					layout.getChildren().remove(messages);
					msg1 = msg(0);
					msg2 = msg(1);
					messages.getChildren().addAll(msg1, msg2);
					layout.getChildren().add(messages);
					if(currentPlayer.getType() == "Human" && currentPlayer.getCurrentLocation() == lastGameSpace) {
						endButton.setVisible(false);
					}
					nextTurn(stage);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		endTurnButton.setOnAction(endTurn);
		return endTurnButton;
	}
	/**
	 * creates the button that will let the players end the game early if they want to
	 * @param stage - the window that the board is displayed on
	 * @return dropOut - the button that ends the game
	 */
	public Button dropOutButton(Stage stage) {
		Button dropOut = new Button("Drop Out") ;
		dropOut.setFont(new Font(GUIMain.fontType , 15)) ;
		EventHandler<ActionEvent> byeBye = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					stage.close() ;
					new WinScreen(players) ;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		dropOut.setOnAction(byeBye) ;
		return dropOut ;
	}

	
	/**
	 * Initializes the rectangles that will be used to make up the game board
	 * @return rec
	 * 			An Array of rectangles
	 */
	public Rectangle[] initRects() {
		Rectangle rec[] = new Rectangle[48];
		for (int i = 0; i < rec.length; i++) {
			rec[i] = new Rectangle(recSize, recSize);
			if(i%2 == 0) {				
				rec[i].setFill(javafx.scene.paint.Color.OLDLACE);
			}else {
				rec[i].setFill(javafx.scene.paint.Color.web("#FAE2C0"));
			}
			rec[i].setStrokeType(StrokeType.INSIDE);
			rec[i].setStrokeWidth(0);			
			rec[i].setStroke(javafx.scene.paint.Color.BLACK);
			}
		return rec;
	}
	
	/**
	 * Converts a given ID to its corresponding color
	 * @param colorID
	 * 			The ID of the color
	 * @return Color
	 * 			The color
	 */
	public Color colorConverter(String colorID) {
		switch (colorID) {
		case "Red":
			return Color.web("FF6961");
		case "Orange":
			return Color.SANDYBROWN;
		case "Yellow":
			return Color.web("#FDDD5C");
		case "Green":
			return Color.web("#9DE7D7");
		case "Blue":
			return Color.SKYBLUE;
		case "Purple":
			return Color.MEDIUMPURPLE;
		default:
			return Color.DARKGRAY;
		}
	}
	
	/**
	 * Changes the current Turn and current Player to the next player.
	 * Checks if both players are at the end of the board and generate the win screen if they are
	 * @param stage
	 * 			The window the game takes place on
	 */
	public void nextTurn(Stage stage) {
		currentTurn++;
		int playerIndex = currentTurn%players.size();
		currentPlayer = players.get(playerIndex);
		playerBtn.setText(currentPlayer.getName() + ", spin the wheel!");
		if(players.get(0).getCurrentLocation() == lastGameSpace && players.get(1).getCurrentLocation() == lastGameSpace) {
			stage.close() ;
			new WinScreen(players) ;
		//skips the current player's turn if they are already at the end
		} else if (currentPlayer.getCurrentLocation() == lastGameSpace) {
			nextTurn(stage);
		} else if (currentPlayer.getType() == "Computer") {
			takeTurn();
			try {
				messages.getChildren().removeAll(msg1,msg2);
				layout.getChildren().remove(messages);
				msg1 = msg(0);
				msg2 = msg(1);
				messages.getChildren().addAll(msg1, msg2);
				layout.getChildren().add(messages);
				//Adds a delay for the computer player turn so other players can see what happened instead of it happening all at once
				PauseTransition delay = new PauseTransition(Duration.seconds(2));
				delay.setOnFinished( event -> nextTurn(stage));
				delay.play();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (currentPlayer.getCurrentLocation() != lastGameSpace) {
			playerBtn.setVisible(true);	
			endButton.setVisible(false);
		}
	}
	
	/**
	 * Automatically does the turn for the currentPlayer
	 */
	public void takeTurn() {
		try {
			new WheelPane(currentPlayer);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}