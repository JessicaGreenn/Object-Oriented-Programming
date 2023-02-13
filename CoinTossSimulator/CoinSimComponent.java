// Name: Danyang Zhang
// USC NetID: dzhang69
// CS 455 PA1
// Fall 2022

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Color;

/**
   This component draws a bar chart for three outcomes of 1000 simulated coin toss trials.

*/

public class CoinSimComponent extends JComponent
{  

   // added instance variable for instrumentation
   private int callCount;

   
   /**
      five constants:
      @constant VERTICAL_BUFFER: vertical buffer zone on the top and bottom 
   *  of the window is set to be 25 pixels high
      @constant BAR_WIDTH: bar width is set to be 25 pixels wide
      
      @constant HEAD_TAIL_COLOR: color of the bar showing the one-head-and-one-tail outcome
      @constant TWO_HEADS_COLOR: color of the bar showing the two-head outcome
      @constant TWO_TAILS_COLOR: color of the bar showing the two-tail outcome
      
   */
   private final int BAR_WIDTH = 50;
   private final int VERTICAL_BUFFER = 25;
   
   private final Color HEAD_TAIL_COLOR = Color.GREEN;
   private final Color TWO_HEADS_COLOR = Color.RED;
   private final Color TWO_TAILS_COLOR = Color.BLUE;
   
   // instantiate a CoinTossSimulator to do trials
   private CoinTossSimulator t1 = new CoinTossSimulator();
   
   // added constructor for instrumentation 
   // Note: for old version without instance variables an empty default 
   // constructor didn't need to be defined explicitly (see:
   // https://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html
   // for rules about this)
   
   //simulate 1000 trials
   public CoinSimComponent() {
      callCount = 0;
      t1.run(1000);
      
   }
   
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;
      
           
      // the following two lines are for instrumentation
      callCount++;
      System.out.println("Called paintComponent(" + callCount + ")");
      
      
      
      /* set scale and bottom line: yDraw */
      double s = (double)(getHeight() - 2 * VERTICAL_BUFFER)/t1.getNumTrials();
      int yDraw = getHeight() - VERTICAL_BUFFER;
      
      

      // draw the three bars 
      
      /**
         four variables:
         
         @variable applicationHeight*: height of each bar in application units
         @variable xDraw: location of the left side of each bar
         @variable *Percent: the percent number in each bar's label
         @variable *Label: the label under the bar
      */
      
      /* draw the two-heads bar */
      int applicationHeight1 = t1.getTwoHeads();
      int xDraw = getWidth() / 4 - BAR_WIDTH / 2;
      int twoHeadsPercent = (int)Math.round((double)(t1.getTwoHeads() * 100) / t1.getNumTrials());
      String headsLabel = "Two Heads: " + t1.getTwoHeads() +" (" + twoHeadsPercent + "%)";
      
      // instantiate a Bar to draw 
      Bar bHeads = new Bar(yDraw, xDraw, BAR_WIDTH, applicationHeight1, s, TWO_HEADS_COLOR, headsLabel);
      bHeads.draw(g2);
      
      /* draw the head-and-tail bar */
      
      int applicationHeight2 = t1.getHeadTails();
      xDraw = getWidth() / 4 * 2 - BAR_WIDTH / 2;
      int HeadTailPercent = (int)Math.round(((double)(t1.getHeadTails()) / t1.getNumTrials()) * 100);
      String headTailLabel = "A Head and a Tail: " + t1.getHeadTails() +" (" + HeadTailPercent + "%)";
      
      // instantiate a Bar to draw 
      Bar bHeadTail = new Bar(yDraw, xDraw, BAR_WIDTH, applicationHeight2, s, HEAD_TAIL_COLOR, headTailLabel);
      bHeadTail.draw(g2);
      
      /* draw the two-tails bar */
      
      int applicationHeight3 = t1.getTwoTails();
      xDraw = getWidth() / 4 * 3 - BAR_WIDTH / 2;
      int twoTailsPercent = (int)Math.round(((double)(t1.getTwoTails()) / t1.getNumTrials()) * 100);
      String tailsLabel = "Two Tails: " + t1.getTwoTails() +" (" + twoTailsPercent + "%)";
      
      // instantiate a Bar to draw 
      Bar bTails = new Bar(yDraw, xDraw, BAR_WIDTH, applicationHeight1, s, TWO_TAILS_COLOR, tailsLabel);
      bTails.draw(g2);
   
   }
}
