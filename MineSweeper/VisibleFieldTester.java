import java.util.Arrays;
// Name: Danyang Zhang
// USC NetID: dzhang69
// CS 455 PA3
// Fall 2022


/**
  VisibleFieldTester class
  Test the VisibleField class
   
 */
public class VisibleFieldTester {

   
   public static void main(String[] args) {
      /*
      MineField mf = new MineField(9, 9, 10);
      int row = 4;
      int col = 4;
      mf.populateMineField(row, col);
      System.out.println(mf.toString());
      System.out.println();
      
      VisibleField vf = new VisibleField(mf);
      System.out.println(vf.toString());
      System.out.println();
      System.out.println("Is (0,2) uncovered?" + vf.isUncovered(0,2));
      System.out.println("Is game over?" + vf.isGameOver());
      System.out.println();
      vf.uncover(row,col);
      System.out.println(vf.toString());
      System.out.println();
      
      System.out.println("Is (4,4) uncovered?" + vf.isUncovered(row,col));
      System.out.println("Is game over?" + vf.isGameOver());
      System.out.println();
      */
      
      boolean[][] mineData = new boolean[9][9];

      for(int i = 0; i < 9; i++){
         for(int j = 0; j < 9; j++){
            if(i == 3 && j == 6){
               mineData[i][j] =  true;
            }else{
               mineData[i][j] = false;
            }
         }
      }
      
      
      MineField mf1 = new MineField(mineData);
      VisibleField vf1 = new VisibleField(mf1);
      System.out.println(mf1.toString());
      System.out.println();
      System.out.println(vf1.toString());
      System.out.println();
      
      
      System.out.println("Is (3,6) uncovered?" + vf1.isUncovered(3,6));
      System.out.println("Is game over?" + vf1.isGameOver());
      System.out.println();
      /* vf1.cycleGuess(3,3); */
      /* vf1.cycleGuess(3,3); */
      
      vf1.uncover(0,4);
      System.out.println(vf1.toString());
      System.out.println();
      
      /*vf1.uncover(3,6);
      System.out.println(vf1.toString());
      System.out.println();
      
      System.out.println("Is (3,6) uncovered?" + vf1.isUncovered(3,6));*/
      System.out.println("Is game over?" + vf1.isGameOver());
      

      
   } 


   
}
