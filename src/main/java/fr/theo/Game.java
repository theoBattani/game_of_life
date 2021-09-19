
package fr.theo;

import java.util.ArrayList;

public class Game {

    private long width, height;
    private ArrayList<Cell> cells;

    Game(long width, long height) {
        this.width = width;
        this.height = height;
        this.initCells();
    }

    public long getWidth() {return this.width;}
    public long getHeight() {return this.height;}
    public ArrayList<Cell> getCells() {return this.cells;}

    private void initCells() {
        this.cells = new ArrayList<Cell>();
        long length = this.width * this.height;
        for (long index = 0; index < length; index ++) {
            Cell newCell = new Cell(this, index);
            this.cells.add(newCell);
            for (Cell cell: this.cells) {
                if (cell.isNeighbourOf(newCell)) {
                    newCell.addNeighbour(cell);
                    cell.addNeighbour(newCell);
                }
            }
        }
    }

    public void randomCells() {
        for (Cell cell: this.cells) cell.setRandomStatus();
    }

    public void update() {
        boolean[] nextStatus = new boolean[this.cells.size()];
        for (Cell cell: this.cells) nextStatus[(int) cell.getIndex()] = cell.nextStatus();
        for (Cell cell: this.cells) cell.setStatus(nextStatus[(int) cell.getIndex()]);
    }
}