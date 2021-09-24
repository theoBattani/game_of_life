/**
 * Sample Skeleton for 'game-view.fxml' Controller Class
 */

package fr.theo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML
  Pane pane;

  @FXML // fx:id="canvas"
  private Canvas canvas; // Value injected by FXMLLoader
  private GraphicsContext graphicsContext;

  private double zoom = 16;

  private ArrayGame game;

  private FixedFrameRateTimer timer;

  private boolean mousePressed = false;
  private double mouseX, mouseY;
  private double mouseDeltaX, mouseDeltaY;

  @FXML
  void mousePressedOnCanvas(MouseEvent event) {
    mousePressed = true;
    mouseX = event.getX();
    mouseY = event.getY();
    int x = (int) Math.floor(event.getX() / zoom);
    int y = (int) Math.floor(event.getY() / zoom);
    game.addCell(x, y);
    redraw();
  }

  @FXML
  void mouseDraggedOnCanvas(MouseEvent event) {
    if (mousePressed) {
      mouseDeltaX = event.getX() - mouseX;
      mouseDeltaY = event.getY() - mouseY;
      mouseX = event.getX();
      mouseY = event.getY();
      // System.out.printf("%f, %f : %f, %f", mouseX, mouseY, mouseDeltaX, mouseDeltaY);
      // System.out.println(event.getButton()); 
      if (event.getButton() == MouseButton.PRIMARY) {
        int x = (int) Math.floor(event.getX() / zoom);
        int y = (int) Math.floor(event.getY() / zoom);
        game.addCell(x, y);
        redraw();
      } else if (event.getButton() == MouseButton.SECONDARY) {
        DoubleProperty translateXProperty = canvas.translateXProperty();
        DoubleProperty translateYProperty = canvas.translateYProperty();
        translateXProperty.set(translateXProperty.get() + mouseDeltaX);
        translateYProperty.set(translateYProperty.get() + mouseDeltaY);
      }
    }
  }

  @FXML
  void mouseReleasedOnCanvas(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) {
      int x = (int) Math.floor(event.getX() / zoom);
      int y = (int) Math.floor(event.getY() / zoom);
      game.addCell(x, y);
      redraw();
      mousePressed = false;
    } 
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
    timer.stop();
  }

  @FXML
  void onPlay(ActionEvent event) {
    timer.start();
  }

  @FXML
  void onClear(ActionEvent event) {
    timer.stop();
    game.clear();
    redraw();
  }

  @FXML
  void onMenu(ActionEvent event) throws IOException {
    MainApp.menu();
  }

  @FXML
  void onExit(ActionEvent event) {
    System.exit(0);
    Settings.save();
  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    MainApp.getStage().widthProperty().addListener(widthProperty -> widthPropertyCallback(widthProperty));
    MainApp.getStage().heightProperty().addListener(heightProperty -> heightPropertyCallback(heightProperty));
    assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'game-view.fxml'.";
    canvas.setWidth(640);
    canvas.setHeight(400);
    canvas.widthProperty().addListener(event -> redraw());
    canvas.heightProperty().addListener(event -> redraw());
    graphicsContext = canvas.getGraphicsContext2D();
    game = new ArrayGame(256, 256);
    renderArray(game, graphicsContext);
    canvas.setVisible(false);
  }

  private void widthPropertyCallback(Observable stageWidthProperty) {
    canvas.setWidth(((DoubleExpression) stageWidthProperty).getValue() * 4);
  }

  private void heightPropertyCallback(Observable stageHeightProperty) {
    canvas.setHeight(((DoubleExpression) stageHeightProperty).getValue() * 4);
  }

  public GameController() {
      timer = new FixedFrameRateTimer(20){
        @Override
        void loop() {
            render();
        }
      };
  }

  public void render() {
    game.evolve();
    renderArray(game, graphicsContext);
  }

  public void redraw() {
    renderArray(game, graphicsContext);
  }

  public void renderArray(ArrayGame game, GraphicsContext gc) {
    for (int index = 0; index < game.getWidth() * game.getHeight(); index++) {
      gc.setFill(game.getArray()[index] == 1? Color.GREENYELLOW: Color.BLACK);
      gc.fillRect((index % game.getWidth()) * zoom, 
                  (index / game.getWidth()) * zoom, 
                  zoom, zoom);
      // gc.setStroke(Color.web("#333"));
      // gc.strokeRect((index % game.getWidth()) * 10, (index / game.getWidth()) * 10, 10, 10);
    }
  }
}













