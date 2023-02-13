import java.util.Arrays;
// Name: Danyang Zhang
// USC NetID: dzhang69
// CS 455 PA3
// Fall 2022


/** 
   MineFieldTester
      class to test the MineField class
 */
public class MineFieldTester {
   
   // <put instance variables here>
   
   public static void main(String[] args) {
      MineField mf = new MineField(5, 9, 10);
      int row = 4;
      int col = 4;
      
      mf.populateMineField(row, col);
      System.out.println(mf.toString());
      System.out.println();
      
      int[][] nam = new int[5][9];
      
      for(int i=0;i<5;i++){
         for(int j=0;j<9;j++){
            nam[i][j] = mf.numAdjacentMines(i,j);
         }
      }
      System.out.println(displayNAM(nam));
      System.out.println();
      

      mf.populateMineField(row, col);
      System.out.println(mf.toString());
      System.out.println();
      
      for(int i=0;i<5;i++){
         for(int j=0;j<9;j++){
            nam[i][j] = mf.numAdjacentMines(i,j);
         }
      }
      System.out.println(displayNAM(nam));
      System.out.println();
      
      boolean[][] mineData = new boolean[5][9];
      for(int i = 0; i < 5; i++){
         for(int j = 0; j < 9; j++){
            if(mf.hasMine(i,j)){
               mineData[i][j] = true;
            }else{
               mineData[i][j] = false;
            }
         }
      }
      
      
      
      MineField mf1 = new MineField(mineData);
      System.out.println(mf1.toString());
      System.out.println();
      
      mf1.resetEmpty();
      System.out.println(mf1.toString());
      System.out.println();
      
      mineData = new boolean[9][9];
      for(int i = 0; i < 9; i++){
         for(int j = 0; j < 9; j++){
            if(i == 3 && j == 6){
               mineData[i][j] =  true;
            }else{
               mineData[i][j] = false;
            }
         }
      }
      

      
      MineField mf2 = new MineField(mineData);
      System.out.println(mf2.toString());
      System.out.println();
      
      
   }
   
   private static String displayNAM(int[][] nam){
      
      
      
      String res = "[" + "\n";
      for(int i=0;i< nam.length;i++){
         res += Arrays.toString(nam[i]) + "\n";
      }
      
      res += "]" + "\n";
      
      return res;
      
   }
   
   
}
