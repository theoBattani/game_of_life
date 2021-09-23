package fr.theo;

public class Chunk {

  private int size, length;

  private int[] past, array, future;

  private int countNeighbours(int index) {
      if (index % this.size == 0) {
        if (index / this.size == 0) {
          return  this.array[index+1]         + 
                  this.array[index+this.size] + 
                  this.array[index+this.size+1];
          }
        else if (index / this.size == this.size - 1) {
          return  this.array[index+1] + 
                  this.array[index-this.size] + 
                  this.array[index-this.size+1];
          }
        return  this.array[index-this.size]   + 
                this.array[index+this.size]   +
                this.array[index-this.size+1] + 
                this.array[index+1]           + 
                this.array[index+this.size+1];
      }
      else if (index % this.size == this.size - 1) {
        if (index / this.size == 0) {
          return  this.array[index-1]         + 
                  this.array[index+this.size] + 
                  this.array[index+this.size-1];
        }
        else if (index / this.size == this.size - 1) {
          return  this.array[index-1]         + 
                  this.array[index-this.size] + 
                  this.array[index-this.size-1];
        }
        return  this.array[index-this.size]   + 
                this.array[index+this.size]   +
                this.array[index-this.size-1] +
                this.array[index-1]           + 
                this.array[index+this.size-1];
      }
      if (index / this.size == 0) {
        return  this.array[index-1]           + 
                this.array[index+1]           +
                this.array[index+this.size-1] + 
                this.array[index+this.size]   + 
                this.array[index+this.size+1];
      }
      else if (index / this.size == this.size - 1) {
        return  this.array[index-1]           + 
                this.array[index+1]           +
                this.array[index-this.size-1] + 
                this.array[index-this.size]   + 
                this.array[index-this.size+1];
      }
      return  this.array[index-this.size-1] + 
              this.array[index-this.size]   + 
              this.array[index-this.size+1] +
              this.array[index-1]           +                               
              this.array[index+1]           +
              this.array[index+this.size-1] + 
              this.array[index+this.size]   + 
              this.array[index+this.size+1];
    }

  private void computeFuture() {
    for (int index = 0; index < this.length; index++) {
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

  Chunk(int size) {
    this.size = size;
    this.length = size * size;
  }

  public int[] getPast() {return past;}
  public int[] getArray() {return array;}
  public int[] getFuture() {return future;}

  public void next() {
    for (int index = 0; index < this.size; index++)
      this.past[index] = this.array[index];
    for (int index = 0; index < this.size; index++)
      this.array[index] = this.future[index];
    computeFuture();
  }

  public void previous() {
    for (int index = 0; index < this.size; index++)
      this.array[index] = this.past[index];
    for (int index = 0; index < this.size; index++)
      this.future[index] = this.array[index];
    computeFuture();
  }

  public void clear() {
    for (int index = 0; index < this.size; index++) {
      this.past[index] = 0;
      this.array[index] = 0;
      this.array[index] = 0;
    }
  }

  public void addCell(int x, int y) {
    this.array[y * this.size + x] = 1;
    computeFuture();
  }
}









