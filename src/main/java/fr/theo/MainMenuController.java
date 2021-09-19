/**
 * Sample Skeleton for 'main-menu.fxml' Controller Class
 */

package fr.theo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.animation.AnimationTimer;

public class MainMenuController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="leftCanvas"
    private Canvas leftCanvas; // Value injected by FXMLLoader
    private GraphicsContext leftGraphicsContext; 
    private Game leftGame;

    @FXML // fx:id="rightCanvas"
    private Canvas rightCanvas; // Value injected by FXMLLoader
    private GraphicsContext rightGraphicsContext; 
    private Game rightGame;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert leftCanvas != null : "fx:id=\"leftCanvas\" was not injected: check your FXML file 'main-menu.fxml'.";
        assert rightCanvas != null : "fx:id=\"rightCanvas\" was not injected: check your FXML file 'main-menu.fxml'.";

        leftGraphicsContext = leftCanvas.getGraphicsContext2D();
        rightGraphicsContext = rightCanvas.getGraphicsContext2D();

        leftGame = new Game(20, 48);
        rightGame = new Game(20, 48);

        // loop();
    }

    private void loop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }
}
