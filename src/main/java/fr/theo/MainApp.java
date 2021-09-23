package fr.theo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {
    private static Stage stage;

    private void initSettings() {
        Settings.setChunkSize(256);
    }

    @Override
    public void start(Stage s) throws IOException {
        initSettings();
        stage=s;
        stage.setTitle("Game of life");
        setRoot("main-menu");
    }

    static void play() throws IOException {setRoot("game-view");}
    static void menu() throws IOException {setRoot("main-menu");}
    static void settings() throws IOException {setRoot("settings-menu");}

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml,stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
        Scene scene = new Scene(loadFXML(fxml));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        abstract class A {
            public void truc() {System.out.print("A");}
        }
        class B extends A {
            @Override
            public void truc() {System.out.print("B");}
        }
        B b = new B();
        b.truc();
        launch(args);
    }
}
