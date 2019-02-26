public class KnightBoard {

  private int[][] board ;
  private int[][] coordinates = { {2,1}, {-2,1}, {2,-1}, {-2,-1}, {1,2}, {-1,2}, {1,-2}, {-1,-2} } ;
  // represents possible movements
  int a ; // this will represent the area of the board

  public static void main(String[] args) {
    /*for (int i = 0 ; i < 5 ; i++) {
      System.out.println("The current index is " + i) ;
      runTest(i) ;
    }
    System.out.println("We're going to create 4 boards (from 5x5 to 8x8) and run solve!") ;
    System.out.println("\nLet's create a KnightBoard of size 5x5!") ;
    KnightBoard b = new KnightBoard(5,5) ;
    System.out.println("Here is how the board looks in the beginning:\n" + b.toString()) ;
    System.out.println("Expected response for solve is true and we got: " + b.solve(0,0) + "\n" + b.toString()) ;
    System.out.println("\nLet's create a KnightBoard of size 6x6!") ;
    KnightBoard c = new KnightBoard(6,6) ;
    System.out.println("Here is how the board looks in the beginning:\n" + c.toString()) ;
    System.out.println("Let's try to solve the board from 0,0!") ;
    System.out.println(c.solve(0,0) + "\n" + c.toString()) ;
    System.out.println("\nLet's create a KnightBoard of size 7x7!") ;
    KnightBoard d = new KnightBoard(7,7) ;
    System.out.println("Here is how the board looks in the beginning:\n" + d.toString()) ;
    System.out.println("Let's try to solve the board from 0,0!") ;
    System.out.println(d.solve(0,0) + "\n" + d.toString()) ;
    System.out.println("\nLet's create a KnightBoard of size 8x8!") ;
    KnightBoard e = new KnightBoard(8,8) ; // this takes a long time
    System.out.println("Here is how the board looks in the beginning:\n" + e.toString()) ;
    System.out.println("Let's try to solve the board from 1,1!") ;
    System.out.println(e.solve(0,0) + "\n" + e.toString()) ;
    System.out.println("***************** COUNTING SOLUTIONS ***********************************") ;
    KnightBoard aa = new KnightBoard(5,5) ;
    System.out.println("We created a board of 5x5. Let's count how many solutions there are, starting from 0,1!") ;
    System.out.println("The expected # of solutions is 0 and we got: " + aa.countSolutions(0,1)) ;
    KnightBoard bb = new KnightBoard(5,5) ;
    System.out.println("We created another board of 5x5. Let's count how many solutions there are, starting from 2,4!") ;
    System.out.println("The expected # of solutions is 56 and we got: " + bb.countSolutions(2,4)) ;*/
  }

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
    a = board.length * board[0].length ;
  }

  /* toString method
  blank boards display 0's as underscores
  you get a blank board if you never called solve or when there is no solution
  */
  public String toString() {
    String res = "" ;
    for (int r = 0 ; r < board.length ; r++) {
      for (int c = 0 ; c < board[0].length ; c++) {
        if (board[r][c] == 0) res += " _ " ;
        else if (board[r][c] < 10) {
          res += " " + board[r][c] + " " ;
        }
        else {
          res += board[r][c] + " " ;
        }
      }
      res += "\n" ;
    }
    return res ;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds
  *@return true when the board is solvable from the specified starting position
  */
  public boolean solve(int startingRow, int startingCol) {
    //System.out.println("Solve is starting!") ;
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
      throw new IllegalArgumentException("You cannot call solve and start at a row or column that does not exist!\nHint: You either put in a negative startingRow or StartingCol or went past the board size!") ;
    }
    //System.out.println("We've made it through half of the code in solve! Now we're going to check whether the board is empty!") ;
    for (int[] row : board) {
      for (int tile : row) {
        if (tile != 0) {
          throw new IllegalStateException("The board is not empty! Therefore, we cannot and will not solve it!") ;
        }
      }
    }
    //System.out.println("The board was empty so we can continue!") ;
    return solveH(startingRow, startingCol, 1) ;
  }
  private boolean solveOptimization(int row, int col, int level) {
    if (level == row * col) {
      board[row][col] = level ;
      return true ;
    }
    return false ;
  }
  /** level is the # of the knight
  *@return true when the board is solvable from row,col based on level
  */
  private boolean solveH(int row ,int col, int level) {
    // check whether row and col won't cause an error
    if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false ;
    // this replaces the booleans positive and notOutside that I had before
    if (level > a) {
      // we're at the last possible position of the board
      return true ;
    }
    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) return false ;
    if (board[row][col] != 0) return false ;
    //System.out.println("We're not at the end so we're going past the base cases now!") ;
    for (int i = 0 ; i < coordinates.length ; i++) {
      board[row][col] = level ;
      //System.out.println(this.toString()) ;
      if (solveH(row + coordinates[i][0], col + coordinates[i][1], level + 1)) return true ;
      board[row][col] = 0 ;
    }
    return false ;
  }

  /** would only work on smaller boards!
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
      throw new IllegalArgumentException("You cannot call countSolutions and start at a row or column that does not exist!\nHint: You either put in a negative startingRow or StartingCol or went past the board size!") ;
    }
    for (int r = 0 ; r < board.length ; r++) {
      for (int c = 0 ; c < board[0].length ; c++) {
        if (board[r][c] != 0) {
          throw new IllegalStateException("You cannot call countSolutions with a non-empty board!") ;
        }
      }
    }
    return countH(startingRow, startingCol, 1) ;
  }
  /** Helper method for countSolutions
  @return number of solutions to the knights tour by calling itself again and again until
  *it reaches the end where there are no more tiles available
  */
  public int countH(int row, int col, int level) {
    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) return 0 ;
    if (board[row][col] != 0) return 0 ;
    if (level == a) return 1 ;
    int total = 0 ;
    for (int i = 0 ; i < coordinates.length ; i++) {
      board[row][col] = level ;
      //System.out.println("This is how the board looks before we try another move: \n" + this.toString()) ;
      total += countH(row + coordinates[i][0], col + coordinates[i][1], level + 1 ) ;
      board[row][col] = 0 ;
    }
    return total ;
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //testcase must be a valid index of your input/output array
  public static void runTest(int i){
    KnightBoard b;
    int[]m =   {4,5,5,5,5};
    int[]n =   {4,5,4,5,5};
    int[]startx = {0,0,0,1,2};
    int[]starty = {0,0,0,1,2};
    int[]answers = {0,304,32,56,64};
    if(i >= 0 && i < 6){
      try{
        int correct = answers[i];
        b = new KnightBoard(m[i%m.length],n[i%m.length]);

        int ans  = b.countSolutions(startx[i],starty[i]);

        if(correct==ans){
          System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
        }else{
          System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
        }
      }catch(Exception e){
        System.out.println("FAIL Exception case: "+i);

      }
    }
  }
}
