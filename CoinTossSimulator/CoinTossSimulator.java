// Name: Danyang Zhang
// USC NetID: dzhang69
// CS 455 PA1
// Fall 2022

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */

import java.util.Random;

public class CoinTossSimulator {
   
   /**
      the defined instance variables to perform a trial:
  
      @variable trialTimes number of trials performed since last reset
      @variable numTwoHeads number of trials that came up two heads since last reset
      @variable numTwoTails  number of trials that came up two tails since last reset
      @variable numHeadTails  number of trials that came up one head and one tail since last reset
      
   */

   private int trialTimes;
   private int numTwoHeads;
   private int numTwoTails;
   private int numHeadTails;
   
   // @variable generator* random generators to simulate trials for each coin
   private Random generator1 = new Random();
   private Random generator2 = new Random();
   
   // @constant SIDES number of possible outcome in one trial for one coin
   private final int SIDES = 2;


   public CoinTossSimulator() {
      
   /**
      Creates a coin toss simulator with no trials done yet.
   */
      trialTimes = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      
      for (int i = 0; i < numTrials; i++){
         int coin1 = generator1.nextInt(SIDES);
         int coin2 = generator2.nextInt(SIDES);
         
         
         // use if statement to count number of three outcomes
         if (coin1 == 0 && coin2 == 0){
            numTwoHeads++;
         }else if(coin1 == 1 && coin2 == 1){
            numTwoTails++;
         }else{
            numHeadTails++;
         }

      }
      
      
      // update number of trials performed since last reset
      trialTimes += numTrials;
 
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return trialTimes; 
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return numTwoHeads; 
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return numTwoTails; 
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return numHeadTails; 
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      trialTimes = 0;
      numHeadTails = 0;
      numTwoHeads = 0;
      numTwoTails = 0;

   }

}
