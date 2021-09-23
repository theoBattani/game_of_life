package fr.theo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class ResizableCanvas extends Canvas{

    public abstract void draw(GraphicsContext gc);

    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, getWidth(), getHeight());
        draw(gc);
    }

    public ResizableCanvas() {
        widthProperty().addListener(event -> redraw());
        heightProperty().addListener(event -> redraw());
    }

    @Override
    public boolean isResizable() {return true;}

    @Override
    public double prefWidth(double height) {return getWidth();}

    @Override
    public double prefHeight(double width) {return getHeight();}

    private void redraw() {
        draw(getGraphicsContext2D());
        System.out.printf("%f, %f\n", getWidth(), getHeight());
    }
    
}
