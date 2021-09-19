
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

    private void initCells() {
        this.cells = new ArrayList<Cell>();
        long length = this.width * this.height;
        for (long index = 0; index < length; index ++) {
            Cell newCell = new Cell(this, index);
            this.cells.add(newCell);
            for (Cell cell: this.cells) {
                System.out.printf("%d, %d : %f\n", newCell.getIndex(), cell.getIndex(), newCell.squareDistanceTo(cell));
            }
        }
    }
}