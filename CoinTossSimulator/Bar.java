// Name: Danyang Zhang
// USC NetID: dzhang69
// CS 455 PA1
// Fall 2022

// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {
   
   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale).
  
      @param bottom  location of the bottom of the bar
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param applicationHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label under the bar
   */
   
   /**
      the defined instance variables to draw a bar:
  
      @variable y  location of the upper-left point of the bar
      @variable x  location of the left side of the bar
      @variable width  width of the bar (in pixels)
      @variable hei  height of the bar
      @variable clr  the color of the bar
      @variable lbl  the label under the bar
      @variable bot  location of the bottom of the bar
      
   */
   private int y;
   private int x;
   private int wid;
   private int hei;
   private Color clr;
   private String lbl;
   private int bot;

   /**
      two constants:
      @constant VERTICAL_BUFFER: vertical buffer zone on the top and bottom 
   *  of the window is set to be 25 pixels high
      @constant BAR_WIDTH: bar width is set to be 25 pixels wide
   */
   private final int VERTICAL_BUFFER = 25;
   private final int BAR_WIDTH = 50;

   public Bar(int bottom, int left, int width, int applicationHeight, 
              double scale, Color color, String label) {
      
      bot = bottom;
      y = bottom - (int)Math.round(scale * applicationHeight);
      x = left;
      wid = width;
      hei = (int)Math.round(scale * applicationHeight);
      clr = color;
      lbl = label;
   
   }
   
   /**
      Draw the labeled bar.
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
      
      /* draws the bar */
      g2.setColor(clr);
      Rectangle rect = new Rectangle(x, y, wid, hei);
      g2.fill(rect);
      
      /* draws the label */
      g2.setColor(Color.BLACK);
      Font font = g2.getFont();
      FontRenderContext context = g2.getFontRenderContext();
      Rectangle2D labelBounds = font.getStringBounds(lbl, context);
      int widthOfLabel = (int) labelBounds.getWidth();
      int heightOfLabel = (int) labelBounds.getHeight();
      g2.drawString(lbl, x + BAR_WIDTH / 2 - widthOfLabel / 2, bot + VERTICAL_BUFFER / 2 + heightOfLabel / 2);

   }
}
