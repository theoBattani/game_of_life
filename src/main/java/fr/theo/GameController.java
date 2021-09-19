/**
 * Sample Skeleton for 'game-view.fxml' Controller Class
 */

package fr.theo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class GameController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="canvas"
    private Canvas canvas; // Value injected by FXMLLoader

    @FXML
    void mousePressedCanvas(MouseEvent event) {

    }

    @FXML
    void onExit(ActionEvent event) {
      System.exit(0);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'game-view.fxml'.";

    }
}
