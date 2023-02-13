// Name: Danyang Zhang
// USC NetID: dzhang69
// CS 455 PA1
// Fall 2022

/**
 * class CoinTossSimulatorTester
 * Unit test program for CoinTossSimulator class
 * 
 */
import java.util.Scanner;

public class CoinTossSimulatorTester {

   public static void main(String[] args){
      CoinTossSimulator t1 = new CoinTossSimulator();
      
      /* After constructor */
      System.out.println("After constructor: ");
      System.out.println("Number of trials [exp:0]: " + t1.getNumTrials());
      System.out.println("Two-head tosses: " + t1.getTwoHeads());
      System.out.println("Two-tail tosses: " + t1.getTwoTails());
      System.out.println("One-head one-tail tosses: " + t1.getHeadTails());
      System.out.println("Tosses add up correctly? " + (t1.getNumTrials() == t1.getTwoHeads() + t1.getTwoTails() + t1.getHeadTails()));
      System.out.println();
      
      Scanner in  = new Scanner(System.in);

      System.out.println("Enter number of trials:");
      int firstTrials = in.nextInt();

      /* Error-checking */
      while(firstTrials <= 0){
         System.out.println("ERROR: Number entered must be greater than 0.");
         System.out.println("Enter number of trials:");
         firstTrials = in.nextInt();
      }
      
      /* conduct first trials, the expected result equals to the input, the real result is accessed through methods */
      t1.run(firstTrials);
      System.out.println("After run(" + firstTrials + "): ");
      System.out.println("Number of trials [exp:" + firstTrials + "]: " + t1.getNumTrials());
      System.out.println("Two-head tosses: " + t1.getTwoHeads());
      System.out.println("Two-tail tosses: " + t1.getTwoTails());
      System.out.println("One-head one-tail tosses: " + t1.getHeadTails());
      System.out.println("Tosses add up correctly? " + (t1.getNumTrials() == t1.getTwoHeads() + t1.getTwoTails() + t1.getHeadTails()));
      System.out.println();
      
      /* conduct subsequent trials to test the "run" method in the CoinTossSimulator class */
      t1.run(10);
      System.out.println("After run(" + (firstTrials + 10) + "): ");
      System.out.println("Number of trials [exp:" + (firstTrials + 10) + "]: " + t1.getNumTrials());
      System.out.println("Two-head tosses: " + t1.getTwoHeads());
      System.out.println("Two-tail tosses: " + t1.getTwoTails());
      System.out.println("One-head one-tail tosses: " + t1.getHeadTails());
      System.out.println("Tosses add up correctly? " + (t1.getNumTrials() == t1.getTwoHeads() + t1.getTwoTails() + t1.getHeadTails()));
      System.out.println();

      t1.run(100);
      System.out.println("After run(" + (firstTrials + 110) + "): ");
      System.out.println("Number of trials [exp:" + (firstTrials + 110) + "]: " + t1.getNumTrials());
      System.out.println("Two-head tosses: " + t1.getTwoHeads());
      System.out.println("Two-tail tosses: " + t1.getTwoTails());
      System.out.println("One-head one-tail tosses: " + t1.getHeadTails());
      System.out.println("Tosses add up correctly? " + (t1.getNumTrials() == t1.getTwoHeads() + t1.getTwoTails() + t1.getHeadTails()));
      System.out.println();
      
      /* After reset */
      /* test the "reset" method in the CoinTossSimulator class */
      t1.reset();
      System.out.println("After reset: ");
      System.out.println("Number of trials [exp:0]: " + t1.getNumTrials());
      System.out.println("Two-head tosses: " + t1.getTwoHeads());
      System.out.println("Two-tail tosses: " + t1.getTwoTails());
      System.out.println("One-head one-tail tosses: " + t1.getHeadTails());
      System.out.println("Tosses add up correctly? " + (t1.getNumTrials() == t1.getTwoHeads() + t1.getTwoTails() + t1.getHeadTails()));
      System.out.println();
      
      /* conduct subsequent trials to test the "run" method in the CoinTossSimulator class, 
      *  number of trials is set to be 1000 */
      t1.run(1000);
      System.out.println("After run(1000): ");
      System.out.println("Number of trials [exp:1000]: " + t1.getNumTrials());
      System.out.println("Two-head tosses: " + t1.getTwoHeads());
      System.out.println("Two-tail tosses: " + t1.getTwoTails());
      System.out.println("One-head one-tail tosses: " + t1.getHeadTails());
      System.out.println("Tosses add up correctly? " + (t1.getNumTrials() == t1.getTwoHeads() + t1.getTwoTails() + t1.getHeadTails()));
      System.out.println();



   }
}

