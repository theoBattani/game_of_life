/**
 * Sample Skeleton for 'game-view.fxml' Controller Class
 */

package fr.theo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="canvas"
    private Canvas canvas; // Value injected by FXMLLoader
    private GraphicsContext graphicsContext;
    private ArrayGame game;
    private AnimationTimer timer;

    @FXML
    void mousePressedCanvas(MouseEvent event) {
      int x = (int) Math.floor(event.getX() / 10);
      int y = (int) Math.floor(event.getY() / 10);
      game.addCell(x, y);
      redraw();
    }

    @FXML
    void onExit(ActionEvent event) {
      System.exit(0);
    }

    @FXML
    void onNext(ActionEvent event) {
      game.next();
      redraw();
    }

    @FXML
    void onPrevious(ActionEvent event) {
      game.previous();
      redraw();
    }

    @FXML
    void onPause(ActionEvent event) {
      System.out.println("pause");
    }

    @FXML
    void onPlay(ActionEvent event) {
      System.out.println("play");
    }

    @FXML
    void onMenu(ActionEvent event) throws IOException {
      MainApp.menu();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
      assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'game-view.fxml'.";
      graphicsContext = canvas.getGraphicsContext2D();
      game = new ArrayGame(64, 40);
      renderArray(game, graphicsContext);
    }
    
    public void redraw() {
      renderArray(game, graphicsContext);
    }

    public void renderArray(ArrayGame game, GraphicsContext gc) {
        for (int index = 0; index < game.getWidth() * game.getHeight(); index++) {
            gc.setFill(game.getArray()[index] == 1? Color.GREEN: Color.DARKGREY);
            gc.fillRect((index % game.getWidth()) * 10, (index / game.getWidth()) * 10, 10, 10);
            gc.setStroke(Color.BLACK);
            gc.strokeRect((index % game.getWidth()) * 10, (index / game.getWidth()) * 10, 10, 10);
        }
    }
}
