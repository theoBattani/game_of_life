package fr.theo;

// import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageRenderer {
  public WritableImage image;
  public ImageView view;
  public ImageView getView() {return view;}
  public void render() {
    // PixelWriter pixelWriter = image.getPixelWriter();
    // for (int y = 0; y < chunk.getSize(); y++)
    //   for (int x = 0; x < chunk.getSize(); x++)
    //     pixelWriter.setColor(x, y, chunk.getArray()[y*chunk.getSize()+x]==1?Color.GREENYELLOW:Color.BLACK);
  }
}
