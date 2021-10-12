package application;

import java.util.Random;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Creates the wheel that players spin to get their move amount and used the move amount to
 * update the player's location on the game board
 * @author Jasmine
 *
 */
public class WheelPane extends StackPane {
	private static int min = 1080;
	private static int max = 1440;
	private Move wheelAmount;
	private int moveAmount;
	private RotateTransition rt = new RotateTransition();
	private static String eventMsg[] = { " paid for parking, lost $", 
										" forgot your lunch! Lost $",
										" got a tutor, lost $", 
										" applied to grad school, lost $", 
										" paid for a textbook, lost $",
										" found money on the ground! Gained $", 
										" got free food at an event! Gained $", 
										" got paid! Gained $",
										" got a grant! Gained $", 
										" got money for your birthday! Gained $",
										" nothing happened! "};

	/**
	 * Creates the wheel that players spin to get their move amount and uses the move amount to
	 * update the player's location on the game board
	 * @param player
	 * 			The current player who is spinning the wheel
	 */
	public WheelPane(Player player , Stage stage) {
		PieChart wheel = new PieChart();
		wheel.setLegendVisible(false);
		wheel.setPrefSize(200, 200);
		int i = 0;
		int maxWheelAmount = 10;
		while (i < maxWheelAmount) {
			wheel.getData().add(new PieChart.Data((i + 1) + "", 10));
			i++;
		}

		Button spinButton = new Button("Spin");
		spinButton.setFont(new Font(GUIMain.fontType, 15));
		spinButton.setPrefSize(50, 20);
		Button closeButton = closeWindowButton(player , stage) ;
		closeButton.setFont(new Font(GUIMain.fontType, 15));
		closeButton.setVisible(false) ;
		

		EventHandler<ActionEvent> spinWheel = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				rt.setNode(wheel);
				rt.setDuration(Duration.millis(2000));
				int rotation = getRandRotationWithExclusion();
				rt.setByAngle(rotation);
				wheelAmount = new Move();
				wheelAmount.setWheelSpin(rotation);
				moveAmount = wheelAmount.getWheelSpin();
				rt.play();
			}
		};
		EventHandler<ActionEvent> onFinish = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				spinButton.setVisible(false) ;
				closeButton.setVisible(true) ;
				player.move(moveAmount);
			}
		} ;
		rt.setOnFinished(onFinish) ;

		// Create the triangle that points to the number the player lands after spinning the wheel
		Polyline triangle = new Polyline();
		triangle.getPoints().addAll(50.0, 0.0, 0.0, 50.0, 100.0, 50.0, 50.0, 0.0);
		triangle.setScaleX(0.5);
		triangle.setScaleY(0.5);
		triangle.setLayoutY(10000);
		triangle.setFill(Color.GRAY);

		spinButton.setOnAction(spinWheel);

		StackPane.setAlignment(triangle, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(closeButton , Pos.CENTER) ;
		getChildren().addAll(triangle, wheel, spinButton , closeButton);

	}
	
	/**
	 * Automatically generates a move amount, uses the move amount to update the player's 
	 * location on the game board and calls a method to display what happened during the turn
	 * @param player
	 * 			The current player who is spinning the wheel
	 */
	public WheelPane(Player player) {
		Move move = new Move();
		moveAmount = move.spin();
		player.move(moveAmount);
		updateStatus(player);
	}


	/**
	 * Makes sure that the wheel will land in a random place that is not between two numbers
	 * @return rotation
	 * 			The random number that represents the rotation 
	 */
	public static int getRandRotationWithExclusion() {
		Random random = new Random();
		int rotation = random.nextInt((max - min) + 1) + min;
		int[] exclude = new int[] { 1098, 1134, 1170, 1206, 1242, 1278, 1314, 1350, 1386, 1422 };
		for (int exclusions : exclude) {
			if (rotation == exclusions) {
				return rotation += 5;
			}
		}
		return rotation;
	}

	/**
	 * Generates a random number to chose a relevant message from eventMsg.
	 * @param i					Represents if the message should be positive or negative. 0 for negative and 4 for positive
	 * @return eventMsg[index]	The message to be displayed.
	 */
	public String chooseMsg(int i) {
		Random rand = new Random();
		int index = rand.nextInt(5);
		index += i;
		return eventMsg[index];
	}
	
	/**
	 * Updates the player's bankAccount using a randomly generated number and generates a pop up to notify the player of the change
	 * Also checks if the player has reach the end of the board; if they have, it does not generate number and generates a pop up to notify 
	 * the player that they have reached the end of the board
	 * @param player
	 * 			The current player
	 */
	public void updateStatus(Player player) {
		int num = player.getCurrentLocation();
		String aOrAn = "a ";
		
		//If a player reach the end, display a pop up to inform them
		if(player.getType() == "Human" && num == GameScreen.lastGameSpace) {
			Popup.display("Status Update", "You have reached the end!");	
		}else if (player.getType() == "Computer" && num == GameScreen.lastGameSpace) {
			Popup.display("Status Update", player.getName() + " has reached the end!");
		
		//Updates the player's information based on what they spun if they have not reached the end of the board
		}else if (num != GameScreen.lastGameSpace){		
			int spaceVal;
			int msgIndex = 0;
			
			//makes sure no money is gained or lost on the first and last game space
			if (num == 0 || num == 47) { 
				spaceVal = 0;
			}else { 
				spaceVal = (int)((102.0*Math.random()) - 51.0);
			}
			
			//chooses an appropriate message index based on if the value of the space was negative or not
			if (spaceVal < 0) {
				msgIndex = 0;
			}else if (spaceVal > 0) {
				msgIndex = 5;
			}
			
			//If the players land on an 8, change a to an to make sure the sentence grammar is still correct
			player.updateVal(spaceVal);
			if(moveAmount == 8) {
				aOrAn = "an ";
			}
			
			//Displays an appropriate message based on the value the player spun and if they are a human or computer
			if(player.getType() == "Human" && spaceVal != 0) {
				Popup.display("Status Update", " You spun " + aOrAn + moveAmount + " and" + chooseMsg(msgIndex) + Math.abs(spaceVal) + " ");
			}else if (player.getType() == "Computer" && spaceVal != 0){
				Popup.display("Status Update", " " + player.getName() + " spun " + aOrAn + moveAmount + " and" + chooseMsg(msgIndex) + Math.abs(spaceVal) + " ");
			}else if (player.getType() == "Human") {
				Popup.display("Status Update", " You spun " + aOrAn + moveAmount + " and" + eventMsg[10]);
			}else {
				Popup.display("Status Update", " " + player.getName() + " spun " + aOrAn + moveAmount + " and" + eventMsg[10]);
			}
		}
	}
	
	/**
	 * Generates the button that will close the window and initiate the pop up to tell the player what happened to them
	 * @param player - the current player
	 * @param stage - the window for the wheel
	 * @return closeButton - the button that will close the wheel window
	 */
	public Button closeWindowButton(Player player , Stage stage) {
		Button closeButton = new Button ("Close") ;
		EventHandler<ActionEvent> Close = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				stage.close() ;
				updateStatus(player) ;
			}
		};
		closeButton.setOnAction(Close) ;
		return closeButton ;
	}
}
