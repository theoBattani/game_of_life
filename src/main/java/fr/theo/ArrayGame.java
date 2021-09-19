package fr.theo;

public class ArrayGame {
    
    private int width, height;
    private int[] array, future;

    public int getWidth() {return this.width;}
    public int getHeight() {return this.height;}
    public int[] getArray() {return this.array;}
    public int[] getFuture() {return this.future;}
    
    ArrayGame(int width, int height) {
        this.width = width;
        this.height = height;
        this.array = new int[this.width * this.height];
        this.future = new int[this.width * this.height];
    }

    private int countNeighbours(int index) {
        if (index % this.width == 0) {
            if (index / this.width == 0) {
                return this.array[index+1] + this.array[index+this.width] + this.array[index+this.width+1];
            }
            if (index / this.width == this.height - 1) {
                return this.array[index+1] + this.array[index-this.width] + this.array[index-this.width+1];
            }
            return  this.array[index-this.width]   + this.array[index+this.width] +
                    this.array[index-this.width+1] + this.array[index+1] + this.array[index+this.width+1];
        }
        if (index % this.width == this.width - 1) {
            if (index / this.width == 0) {
                return this.array[index-1] + this.array[index+this.width] + this.array[index+this.width-1];
            }
            if (index / this.width == this.height - 1) {
                return this.array[index-1] + this.array[index-this.width] + this.array[index-this.width-1];
            }
            return  this.array[index-this.width]   + this.array[index+this.width] +
                    this.array[index-this.width-1] + this.array[index-1] + this.array[index+this.width-1];
        }
        if (index / this.width == 0) {
            return  this.array[index-1]            + this.array[index+1] +
                    this.array[index+this.width-1] + this.array[index+this.width] + this.array[index+this.width+1];
        }
        if (index / this.width == this.height - 1) {
            return  this.array[index-1]            + this.array[index+1] +
                    this.array[index-this.width-1] + this.array[index-this.width] + this.array[index-this.width+1];
        }
        return  this.array[index-this.width-1] + this.array[index-this.width] + this.array[index-this.width+1] +
                this.array[index-1]            + this.array[index]            + this.array[index+1]            +
                this.array[index-this.width-1] + this.array[index-this.width] + this.array[index-this.width+1];
    }

    private void computeFuture() {
        for (int index = 0; index < this.width * this.height; index++) {
            switch (this.countNeighbours(index)) {
                case 2:
                    this.future[index] = this.array[index];
                    break;
                case 3:
                    this.future[index] = 1;
                    break;
                default:
                    this.future[index] = 0;
                    break;
            }
        }
    }

    public void evolve() {
        this.array = this.future;
        this.computeFuture();
    }

    public void randomFill() {
        for (int index = 0; index < this.width * this.height; index++) {
            this.array[index] = Math.random() < 0.5? 1: 0;
        }
        this.computeFuture();
    }
}