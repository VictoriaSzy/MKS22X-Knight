public class KnightBoard {

  private int[][] board ;
  private int[][] coordinates ;

  // Constructor
  public KnightBoard(int startingRows,int startingCols) {
    if (startingRows < 0 || startingCols < 0) {
      throw new IllegalArgumentException("For this lab, we cannot create a board whose dimensions are negative!") ;
    }
    board = new int[startingRows][startingCols] ;
    for (int r = 0 ; r < startingRows ; r++) {
      for (int c = 0 ; c < startingCols ; c++) {
        board[r][c] = 0 ;
      }
    }
    //Attempt at initializing coordinates 2d array
    int[][] coor = { {2,1}, {-2,1}, {2,-1}, {-2,-1}, {1,2}, {-1,2}, {1,-2}, {-1,-2} } ;
    coordinates = coor ;
  }

  /* toString method
  blank boards display 0's as underscores
  you get a blank board if you never called solve or
  when there is no solution */
  public String toString() {
    String res = "" ;
    return res ;
  }

  /*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.*/
  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
      throw new IllegalArgumentException("You cannot call solve() and start at a row or column that does not exist!\nHint: You either put in a negative startingRow or StartingCol or went past the board size!") ;
    }
  }
  // level is the # of the knight
  private boolean solveH(int row ,int col, int level) {

  }

  /*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds. */
  public int countSolutions(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
      throw new IllegalArgumentException("You cannot call solve() and start at a row or column that does not exist!\nHint: You either put in a negative startingRow or StartingCol or went past the board size!") ;
    }
  }


}
