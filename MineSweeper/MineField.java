// Name: Danyang Zhang
// USC NetID: dzhang69
// CS 455 PA3
// Fall 2022
import java.util.Random;
import java.util.Arrays;

/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {

   // <put instance variables here>
   /** instance variables
    * mineView represents a mine field. if mineView[row][col] is true, then hasMine(row,col) 
    * will be true and vice versa. 
    * 
    * totMines is the number of total mines in this mine field.
    * 
    * g is a Random object to generate random positions for the mines
    * 
    */
   private boolean[][] mineView;
   private int totMines;
   private int totRows;
   private int totCols;
   private Random g = new Random();
   
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
      @param mineData  the data for the mines; must have at least one row and one col,
                       and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {
      
      mineView = new boolean[mineData.length][];
      for (int i = 0; i < mineData.length; i++) {
          mineView[i] = Arrays.copyOf(mineData[i], mineData[i].length);
      }
      totRows = mineView.length;
      totCols = mineView[0].length;
      totMines = 0;
      for(int i = 0; i < totRows; i++){
         for(int j = 0; j < totCols; j++){
            if(mineView[i][j]){
               totMines++;

            }
         }
      }

   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      mineView = new boolean[numRows][numCols];
      totRows = numRows;
      totCols = numCols;
      totMines = numMines;
      resetEmpty();
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {
      assert inRange(row, col);
      resetEmpty();

      for(int i = 0; i < totMines; i++){
         int r = g.nextInt(totRows);
         int c = g.nextInt(totCols);
         while((r == row && c == col) || (mineView[r][c] == true)){
            r = g.nextInt(totRows);
            c = g.nextInt(totCols);
         }

         mineView[r][c] = true;
      }

   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state a minefield created with the three-arg constructor is in 
         at the beginning of a game.
    */
   public void resetEmpty() {
      for(int i = 0; i < totRows; i++){
         for(int j = 0; j < totCols; j++){
            if(mineView[i][j]){
               mineView[i][j] =  false;
            }
         }
      }

      
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      assert inRange(row, col);
      int cnt = 0;
      for(int i = -1; i < 2; i++){
         for(int j = -1; j < 2; j++){
            if((!inRange(row + i, col + j)) || (i == 0 && j == 0)){
               continue;
            }
            if(mineView[row + i][col + j]){
               cnt++;
            }
         }
         
      }
      return cnt;       
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {

      return (0 <= row) && (row < totRows) && (0 <= col) && (col < totCols);
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return totRows;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return totCols;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      assert inRange(row, col);
      return mineView[row][col];       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
      return totMines;       // DUMMY CODE so skeleton compiles
   }

   
   
   public String toString() {
      String mineStr = "[";
      for(int i = 0; i < mineView.length; i++){
         mineStr += Arrays.toString(mineView[i]) + "\n";
      
      }
      mineStr += "]" + "\n";
      
      String res = "totMines=" + numMines() + "\n" +"totRows=" + numRows() + "\n" + "totCols=" + numCols() + "\n" + "mineView=" + "\n" + mineStr;
      
      return res;
   }
   
   // <put private methods here>
         
}

