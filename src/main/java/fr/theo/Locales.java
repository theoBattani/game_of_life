package fr.theo;

public class Locales {

    public final double MIN_ZOOM = 8;
    public final double MAX_ZOOM = 32;
    
    public final int SMALL_CHUNK_SIZE = 128;
    public final int MEDIUM_CHUNK_SIZE = 256;
    public final int LARGE_CHUNK_SIZE = 512;

    public enum Direction {
        UP         ( 0, -1),
        DOWN       ( 0,  1),
        RIGHT      ( 1,  0),
        LEFT       (-1,  0),
        UP_LEFT    (-1, -1),
        UP_RIGHT   ( 1, -1),
        DOWN_LEFT  (-1,  1),
        DOWN_RIGHT ( 1,  1),
        ;

        private int x, y;

        Direction(int x, int y) {this.x = x; this.y = y;}
        public int getX() {return this.x;}
        public int getY() {return this.y;}
    }
}








