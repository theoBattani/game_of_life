module fr.theo {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens fr.theo to javafx.fxml;
    exports fr.theo;
}