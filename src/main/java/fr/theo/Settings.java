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
    private static double zoom;
    private static double aspectRatio;
    private static boolean fullScreen;
    private static int frameRate;

    private static void writeFile() throws IOException, IllegalArgumentException, IllegalAccessException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Field field: Settings.class.getFields()) {
            writer.write(String.format("%s:%s", field.getName(), field.get(Settings.class)));
        }
        writer.close();
    }

    private static void readFile() throws IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        while (line != null) {
            String[] lineSplitted = line.split(":");
            tidyUp(lineSplitted[0], lineSplitted[1]);
            line = reader.readLine();
        }
        reader.close();
    }

    private static void tidyUp(String name, Object newValue) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final Field field = Class.forName(Settings.class.getName()).getDeclaredField(name);
        field.setAccessible(true);
        final Object oldValue = field.get(Class.forName(Settings.class.getName()));
        field.set(oldValue, newValue);
        // try {
        //     Class.forName("Settings").getField(name).set(Class.forName("Settings").getField(name).get(Settings.class), value);
        // } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
        //     e.printStackTrace();
        // }
    }

    private static void init() {
        try {
            readFile();
            initialized = true;
        } catch (SecurityException | IOException | ClassNotFoundException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public static void save() {
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
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
        return zoom;
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

    public static void setZoom(int newZoom) {
        zoom = newZoom;
    
    }
}








