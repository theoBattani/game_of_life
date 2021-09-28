package fr.theo;

// import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public abstract class ImageRenderer {

  private int width, height;
  private WritableImage image;
  private PixelReader pixelReader; 
  private PixelWriter pixelWriter;

  ImageRenderer(int width, int height) {
    this.width = width;
    this.height = height;
    this.image = new WritableImage(width, height);
    this.pixelReader = image.getPixelReader();
    this.pixelWriter = image.getPixelWriter();
  }

  public int getWidth() {return this.width;}
  public int getHeight() {return this.height;}
  public Image getImage() {return this.image;}

  public Color getPixel(int x, int y) {
    return pixelReader.getColor(x, y);
  }

  public void setPixel(int x, int y, Color color) {
    pixelWriter.setColor(x, y, color);
  }

  public Image render() {
    draw();
    return this.image;
  }

  public abstract void draw();
}










