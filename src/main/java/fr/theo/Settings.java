package fr.theo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class Settings {

    private static boolean initialized = false;

    private static String filePath = "src/main/resources/config/settings.txt";
    
    // Game settings
    private static int chunkSize;

    // display settings
    private static double cellSize;
    private static double aspectRatio;
    private static boolean fullScreen;
    private static int frameRate;

    private static void writeFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Field field: Settings.class.getFields()) {
            writer.write(String.format("%s:%s", field.getName(), field.getGenericType()));
        }
        writer.close();
    }

    private static void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        while (line != null) {
            String[] lineSplitted = line.split(":");
            tidyUp(lineSplitted[0], lineSplitted[1]);
            line = reader.readLine();
        }
        reader.close();
    }

    private static void tidyUp(String name, Object value) {
        try {
            Settings.class.getField(name).set(Settings.class, value);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        try {
            readFile();
            initialized = true;
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    // Getters

    public static int getChunkSize() {
        if (!initialized) init();
        return chunkSize;
    }

    public static double getCellSize() {
        if (!initialized) init();
        return cellSize;
    }

    public static double getAspectRatio() {
        if (!initialized) init();
        return aspectRatio;
    }

    public static boolean getFullScreen() {
        if (!initialized) init();
        return fullScreen;
    }

    public static int getFrameRate() {
        if (!initialized) init();
        return frameRate;
    }
    
    public static void setChunkSize(int newChunkSize) {
        chunkSize = newChunkSize;
    }

}








