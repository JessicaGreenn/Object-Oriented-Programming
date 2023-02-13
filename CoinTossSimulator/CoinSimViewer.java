// Name: Danyang Zhang
// USC NetID: dzhang69
// CS 455 PA1
// Fall 2022

/**
 * class CoinSimViewer
 * construct a frame to view the results in CoinSimComponent class
 * 
 */

import javax.swing.JFrame;

public class CoinSimViewer
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      frame.setSize(800, 500);
      frame.setTitle("CoinSim");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      CoinSimComponent component = new CoinSimComponent();
      frame.add(component);

      frame.setVisible(true);
   }
}
