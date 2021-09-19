/**
 * Sample Skeleton for 'main-menu.fxml' Controller Class
 */

package fr.theo;

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
    private Canvas leftCanvas; // Value injected by FXMLLoader
    private GraphicsContext leftGraphicsContext; 
    private ArrayGame leftGame;

    @FXML // fx:id="rightCanvas"
    private Canvas rightCanvas; // Value injected by FXMLLoader
    private GraphicsContext rightGraphicsContext; 
    private ArrayGame rightGame;

    private AnimationTimer timer;

    @FXML
    void onExitAction(ActionEvent event) {
        timer.stop();
        System.exit(0);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert leftCanvas != null : "fx:id=\"leftCanvas\" was not injected: check your FXML file 'main-menu.fxml'.";
        assert rightCanvas != null : "fx:id=\"rightCanvas\" was not injected: check your FXML file 'main-menu.fxml'.";

        leftGraphicsContext = leftCanvas.getGraphicsContext2D();
        rightGraphicsContext = rightCanvas.getGraphicsContext2D();

        leftGame = new ArrayGame(20, 48);
        leftGame.randomFill();
        rightGame = new ArrayGame(20, 48);
        rightGame.randomFill();
    }

    public MainMenuController() {
        timer = new AnimationTimer() {
            // int index;
            int counter = 0;
            int count = 4;
            @Override
            public void handle(long now) {
                if (counter == 0) {
                    leftGame.evolve();
                    rightGame.evolve();
                    renderArray(leftGame, leftGraphicsContext);
                    renderArray(rightGame, rightGraphicsContext);
                    // colorCells(leftGame.getCells().get(index).getNeighbourhood(), leftGame, leftGraphicsContext);
                    // colorCells(rightGame.getCells().get(index).getNeighbourhood(), rightGame, rightGraphicsContext);
                    // index++;
                }
                counter++;
                counter %= count;
            }
        };
        timer.start();
    }

    public void renderArray(ArrayGame game, GraphicsContext gc) {
        for (int index = 0; index < game.getWidth() * game.getHeight(); index++) {
            int i = index % game.getWidth();
            int j = index / game.getWidth();
            double x = i * 10;
            double y = j * 10;
            gc.setFill(game.getArray()[index] == 1? Color.WHITE: Color.BLACK);
            gc.fillRect(x, y, 10, 10);
            gc.setFill(Color.YELLOW);
            gc.fillText(String.format("%d", game.countNeighbours(index)), x, y);
        }
    }
    
    
    // private void renderGame(Game game, GraphicsContext gc) {
    //     for (Cell cell: game.getCells()) {
    //         if (cell.isAlive()) gc.setFill(Color.WHITE);
    //         else gc.setFill(Color.BLACK);
    //         gc.fillRect(
    //             cell.getPosition()[0] * 10, 
    //             cell.getPosition()[1] * 10, 
    //             10, 10
    //         );
    //     }
    // }

    // private void colorCells(ArrayList<Cell> cells, Game game, GraphicsContext gc) {
    //     for (Cell cell: cells) {
    //         if (cell.isAlive()) gc.setFill(Color.BLUE);
    //         else gc.setFill(Color.DARKBLUE);
    //         gc.fillRect(
    //             cell.getPosition()[0] * 10, 
    //             cell.getPosition()[1] * 10, 
    //             10, 10
    //         );
    //     }
    // }
}
