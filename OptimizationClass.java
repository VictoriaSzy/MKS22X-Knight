import java.util.* ;
import java.io.* ;

public class OptimizationClass implements Comparable<OptimizationClass> {
  private int row, col, numberOfMoves, val ;
  private boolean available ;
  // Constructor
  public OptimizationClass(int r, int c, int m) {
    row = r ;
    col = c ;
    numberOfMoves = m ;
    available = true ; // assume it's okay to go to in the beginning
  }
  // Accessor Methods
  public int getRow() {
    return row ;
  }
  public int getCol() {
    return col ;
  }
  public int getNumberOfMoves() {
    return numberOfMoves ;
  }
  public int getVal() {
    return val ;
  }
  public boolean isAvailable() {
    return available ;
  }
  // Mutator methods
  public void changeNumberOfMoves(int m) {
    numberOfMoves = m ;
  }
  public void changeAvailability(boolean state) {
    available = state ;
  }
  // Action methods
  public void makeAMove(int v) {
    available = false ;
    val = v ;
  }
  public void goBack() {
    available = true ;
    val = 0 ;
  }
  public int compareTo(OptimizationClass a) {
    return this.getNumberOfMoves() - a.getNumberOfMoves() ;
  }
  public static void main(String[] args) {
    
  }
}
