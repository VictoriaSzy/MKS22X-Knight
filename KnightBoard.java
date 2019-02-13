public class KnightBoard {

  private int[][] board ;
  private int[][] coordinates ; // represents possible movements

  /** Constructor:
  *@throws IllegalArgumentException when either parameter is <= 0.
  */
  public KnightBoard(int startingRows,int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException("For this lab, we cannot create a board whose dimensions are negative or 0!") ;
    }
    board = new int[startingRows][startingCols] ;
    for (int r = 0 ; r < startingRows ; r++) {
      for (int c = 0 ; c < startingCols ; c++) {
        board[r][c] = 0 ;
      }
    }
    // Attempt at initializing coordinates 2d array
    int[][] coor = { {2,1}, {-2,1}, {2,-1}, {-2,-1}, {1,2}, {-1,2}, {1,-2}, {-1,-2} } ;
    coordinates = coor ;
  }

  /* toString method
  blank boards display 0's as underscores
  you get a blank board if you never called solve or when there is no solution
  */
  public String toString() {
    String res = "" ;
    if (!solve(0,0)) {
      // the board is not solvable

    }
    for (int r = 0 ; r < board.length ; r++) {
      for (int c = 0 ; c < board[0].length ; c++) {
        if (board[r][c] == 0) res += "_ " ;
      }
      res += "\n" ;
    }
    return res ;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds
  *@return true when the board is solvable from the specified starting position
  *Any m × n board with m ≤ n, a closed knight's tour is always possible
  *unless one or more of these three conditions are met:
  * ~ m and n are both odd
  * ~ m = 1, 2, or 4
  * ~ m = 3 and n = 4, 6, or 8.
  */
  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
      throw new IllegalArgumentException("You cannot call solve() and start at a row or column that does not exist!\nHint: You either put in a negative startingRow or StartingCol or went past the board size!") ;
    }
    int m = board.length ;
    int n = board[0].length ;
    if (m % 2 == 1 && n % 2 == 1) return false ;
    if (m == 1 || m == 2 || m == 4) return false ;
    if (m == 3 && (n == 4 || n == 6 || n == 8)) return false ;
    return solveH(0,0,1) ;
  }
  /** level is the # of the knight
  *@return true when the board is solvable from row,col based on level
  */
  private boolean solveH(int row ,int col, int level) {
    int a = board.length * board[0].length ;
    if (level >= a) {
      // we're at the last possible position of the board
      if (board[row][col] == 0) {
        // we can put the knight down here
        board[row][col] = level ;
        return true ;
      }
      else {
        return false ;
      }
    }
    else {
      // these booleans represent the different possibilities of moving the knight
      boolean case1 = solveH(row + coordinates[0][0], col + coordinates[0][1], level++) ;
      boolean case2 = solveH(row + coordinates[1][0], col + coordinates[1][1], level++) ;
      boolean case3 = solveH(row + coordinates[2][0], col + coordinates[2][1], level++) ;
      boolean case4 = solveH(row + coordinates[3][0], col + coordinates[3][1], level++) ;
      boolean case5 = solveH(row + coordinates[4][0], col + coordinates[4][1], level++) ;
      boolean case6 = solveH(row + coordinates[5][0], col + coordinates[5][1], level++) ;
      boolean case7 = solveH(row + coordinates[6][0], col + coordinates[6][1], level++) ;
      boolean case8 = solveH(row + coordinates[7][0], col + coordinates[7][1], level++) ;
      return (case1 || case2 || case3 || case4 || case5 || case6 || case7 || case8) ;
    }
  }

  /** would only work on smaller boards!
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
      throw new IllegalArgumentException("You cannot call solve() and start at a row or column that does not exist!\nHint: You either put in a negative startingRow or StartingCol or went past the board size!") ;
    }
  }


}
