package fr.theo;

import java.util.ArrayList;

public class Cell {

    private Game game; 

    private boolean status;
    private ArrayList<Cell> neighbourhood;
    private long index;

    public Cell(Game game, long index) {
        this.status = false;
        this.game = game;
        this.index = index;
        this.neighbourhood = new ArrayList<Cell>();
        this.neighbourhood.add(this);
    }

    public void setStatus(boolean newStatus) {this.status = newStatus;}

    public boolean getStatus() {return this.status;}
    public long getIndex() {return this.index;}
    public ArrayList<Cell> getNeighbourhood() {return this.neighbourhood;}

    public boolean isAlive() {return this.getStatus();}

    public void setRandomStatus() {
        this.setStatus(Math.random() > 0.5);
    }

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
        return this.squareDistanceTo(other) == 1.0 || this.squareDistanceTo(other) == 2.0;
    }
    
    public void addNeighbour(Cell newNeighbour) {
        this.neighbourhood.add(newNeighbour);
    }

    public boolean nextStatus() {
        int numberOfNeighboursAlive = 0;
        boolean newStatus;
        System.out.println(this.neighbourhood.size());
        for (Cell cell: this.neighbourhood) if (cell.isAlive()) {numberOfNeighboursAlive++;}
        if ((!this.isAlive()) && numberOfNeighboursAlive == 3) {newStatus = true;}
        else if (this.isAlive() && (numberOfNeighboursAlive == 2 || numberOfNeighboursAlive == 3)) {newStatus = true;}
        else {newStatus = false;}
        return newStatus;
    }
}













