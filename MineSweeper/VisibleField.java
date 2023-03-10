// Name: Danyang Zhang
// USC NetID: dzhang69
// CS 455 PA3
// Fall 2022
import java.util.Arrays;

/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield). Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // The following are the covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // The following are the uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this opened square

   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>
   private MineField myMineField;
   private int[][] locStatus;
   private boolean gameOver;
   private int minesGuessed;

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the locations covered, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      myMineField = mineField;
      int rows = mineField.numRows();
      int cols = mineField.numCols();
      locStatus = new int[rows][cols];
      for(int i = 0; i < rows; i++){
         for(int j = 0; j < cols; j++){
            locStatus[i][j] = COVERED;
         }

      }
      minesGuessed = 0;
      gameOver = false;
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {
      
      for(int i = 0; i < myMineField.numRows(); i++){
         for(int j = 0; j < myMineField.numCols(); j++){
            locStatus[i][j] = COVERED;
         }

      }
      minesGuessed = 0;
      gameOver = false;
      
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return myMineField;       
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      assert getMineField().inRange(row, col);
      return locStatus[row][col];       // DUMMY CODE so skeleton compiles
   }

   
   /**
      Returns the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  This value will
      be negative if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      int totMines = myMineField.numMines();
      if(totMines - minesGuessed < 0){
         return 0;
      }
      return totMines - minesGuessed;      

   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      assert getMineField().inRange(row, col);
      if(locStatus[row][col] == COVERED){
         locStatus[row][col] = MINE_GUESS;
         minesGuessed++;
      }else if(locStatus[row][col] == MINE_GUESS){
         locStatus[row][col] = QUESTION;
         minesGuessed--;
      }else if(locStatus[row][col] == QUESTION){
         locStatus[row][col] = COVERED;
      }
      
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      assert getMineField().inRange(row, col);
      if(myMineField.hasMine(row, col)){
         locStatus[row][col] = EXPLODED_MINE;
         gameOver = true;
         for(int i = 0; i < myMineField.numRows(); i++){
            for(int j = 0; j < myMineField.numCols(); j++){
               if(myMineField.hasMine(i, j) && (locStatus[i][j] < 0) && (locStatus[i][j] != MINE_GUESS)){
                  locStatus[i][j] = MINE;
               }
               if((!myMineField.hasMine(i, j)) && (locStatus[i][j] == MINE_GUESS)){
                  locStatus[i][j] = INCORRECT_GUESS;
               }
            }

         }
         return false;
      }else if(myMineField.numAdjacentMines(row, col)==0){
         aotoUncover(row, col);
         if(isGameWin()){
            gameOver = true;
            return true;
         }
      }
      
      locStatus[row][col] = myMineField.numAdjacentMines(row, col);
      if(isGameWin()){
         gameOver = true;
      }else{gameOver = false;}
      
      return true;       
   }
 
   
   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game has ended
    */
   public boolean isGameOver() {
      return gameOver;       
   }
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      assert getMineField().inRange(row, col);

      return !((locStatus[row][col] == COVERED) || (locStatus[row][col] == MINE_GUESS) || (locStatus[row][col] == QUESTION));       
   }
   
   public String toString(){
      String res = 
      res = "[" + "\n";
      for(int i = 0; i < locStatus.length; i++){
         res += Arrays.toString(locStatus[i]) + "\n";
      }
      res += "]";
      return res;
      
   }
   
 
   // <put private methods here>
   private void aotoUncover(int row, int col){
      // we need the x and y of the start position, the old value, and the new value
      // the flood fill has 4 parts
      // firstly, make sure the row and col are inbounds
      if (row < 0 || row >= myMineField.numRows() || col < 0 || col >= myMineField.numCols()){
         return;
      }

      
      /*
      if(myMineField.hasMine(row, col)){
         return;
      }
      */
      
      // secondly, check if the current position is guessed a mine
      if(locStatus[row][col] == MINE_GUESS){
         return;
      }
      
     
      // check if the current position equals the old value
      if(locStatus[row][col] != COVERED && locStatus[row][col] != QUESTION){
         
         return;
      }
  
      // thirdly, check if it's the boundary and set the current position to the new value and return
      if(myMineField.numAdjacentMines(row, col)!=0){
         locStatus[row][col] = myMineField.numAdjacentMines(row, col);
         return;
      }
      
      
      locStatus[row][col] = myMineField.numAdjacentMines(row, col);

      // fourthly, attempt to fill the neighboring positions
      aotoUncover(row-1, col);
      aotoUncover(row, col-1);
      aotoUncover(row, col+1);
      aotoUncover(row+1, col);
      aotoUncover(row-1, col-1);
      aotoUncover(row+1, col-1);
      aotoUncover(row-1, col+1);
      aotoUncover(row+1, col+1);
      
      
   }
   
   private boolean isGameWin(){
      int cnt = 0;
      for(int i = 0; i < locStatus.length; i++){
         for(int j = 0; j < locStatus[0].length; j++){
            if(locStatus[i][j] >= 0 && locStatus[i][j] <= 8){
               cnt++;
            }
         }

      }
      
      int totSquares = (myMineField.numRows()) * (myMineField.numCols());
      /*
      System.out.println("totSquares="+totSquares);
      System.out.println("numMines="+myMineField.numMines());
      System.out.println("numUncovered="+cnt);
      System.out.println();
      */
      if(totSquares - cnt == myMineField.numMines()){
         /*
         System.out.println("You won!");
         System.out.println();
         */
         return true;
      }else{
         /*
         System.out.println("Not yet over!");
         System.out.println();
         */
         return false;
      }
   }
   
}
