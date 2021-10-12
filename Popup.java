package application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * Creates a pop up window displaying text
 * @author Mallory
 *
 */
public class Popup {

	/**
	 * Creates a pop window
	 * @param title
	 * 			The title of the window
	 * @param text
	 * 			The text the window will display
	 */
    public static void display(String title, String text) {
    	
    	// Create a window
        Stage popup = new Stage();

        // Prevent interaction with other windows until the pop-up is interacted with
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle(title);
        popup.setMinWidth(250);
        popup.setMinHeight(200);

        // Set up text in the pop-up
        Label label = new Label();
        
        // Set text in window to whatever message you pass in
        label.setText(text);
        
        //Close button
        Button closeButton = new Button("Click to close");
        closeButton.setFont(new Font(GUIMain.fontType, 12));
        
        // event handler - close the popup
        closeButton.setOnAction(e -> popup.close());

        //Create a Vbox
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and show
        Scene scene = new Scene(layout);
        popup.setScene(scene);
        popup.show();
    }

}
