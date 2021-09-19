/**
 * Sample Skeleton for 'main-menu.fxml' Controller Class
 */

package fr.theo;

import java.io.IOException;
import java.net.URL;
// import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;

public class MainMenuController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="leftCanvas"
    private Canvas canvas; // Value injected by FXMLLoader
    private GraphicsContext graphicsContext; 
    private ArrayGame game;

    private AnimationTimer timer;

    @FXML
    void onExitAction(ActionEvent event) {
        timer.stop();
        System.exit(0);
    }

    @FXML
    void onPlay(ActionEvent event) throws IOException {
        MainApp.play();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert canvas != null : "fx:id=\"leftCanvas\" was not injected: check your FXML file 'main-menu.fxml'.";

        graphicsContext = canvas.getGraphicsContext2D();

        game = new ArrayGame(64, 48);
        game.randomFill();
    }

    public MainMenuController() {
        timer = new AnimationTimer() {
            int counter = 0;
            int count = 4;
            @Override
            public void handle(long now) {
                if (counter == 0) {
                    game.evolve();
                    renderArray(game, graphicsContext);
                }
                counter++;
                counter %= count;
            }
        };
        timer.start();
    }

    public void renderArray(ArrayGame game, GraphicsContext gc) {
        for (int index = 0; index < game.getWidth() * game.getHeight(); index++) {
            gc.setFill(game.getArray()[index] == 1? Color.LIGHTGREY: Color.DARKGREY);
            gc.fillRect((index % game.getWidth()) * 10, (index / game.getWidth()) * 10, 10, 10);
        }
    }
}
