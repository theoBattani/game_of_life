package fr.theo;

import java.util.ArrayList;

public class Cell {

    private Game game; 

    private boolean status;
    private ArrayList<Cell> neighbours;
    private long index;

    public Cell(Game game, long index) {
        this.status = false;
        this.game = game;
        this.index = index;
    }

    public boolean getStatus() {return this.status;}
    public long getIndex() {return this.index;}
    public ArrayList<Cell> getNeighbours() {return this.neighbours;}

    public long[] getPosition() {
        return new long[] {
            this.index % this.game.getWidth(),
            this.index / this.game.getWidth()
        };
    }

    public double squareDistanceTo(Cell other) {
        long[] thisPos = this.getPosition();
        long[] otherPos = other.getPosition();
        long dx = thisPos[0] - otherPos[0];
        long dy = thisPos[1] - otherPos[1];
        return dx * dx + dy * dy;
    }

    public boolean isNeighbourOf(Cell other) {
        return false;
    }
}
