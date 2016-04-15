import java.util.*;
import edu.princeton.cs.algs4.*;
/*///////////////////////////////////////////////////////////////////////
//                                                                     //
// Nome: Adriano Tetsuaki Ogawa Santin                                 //
// Número USP: 8944387                                                 //
// Creative Problem 1.3.36 (Random Iterator)                           //
//                                                                     // 
///////////////////////////////////////////////////////////////////////*/

public class Histogram {
   private final double[] freq;   // freq[i] = # occurences of value i
   private double max;            // max frequency of any value
   
   // Create a new histogram. 
   public Histogram(int N) {
      freq = new double[N];
   }
   
   // Add one occurrence of the value i. 
   public void addDataPoint(int i) {
      freq[i]++; 
      if (freq[i] > max) max = freq[i]; 
   }

   // adicionei essa função para ler as frequencias de modo diferente
   public void addDataPoint (int i, int f) {
      freq[i] = f;
      if (freq[i] > max) max = freq[i];
   }
   
   // draw (and scale) the histogram.
   public void draw() {
      StdDraw.setYscale(-1, max + 1);  // to leave a little border
      StdStats.plotBars(freq);
   }
   
   // See Program 2.2.6.
   //public static void main(String[] args) {
   // int N = Integer.parseInt(args[0]);   // number of coins
   // int T = Integer.parseInt(args[1]);   // number of trials
      
      // create the histogram
   // Histogram histogram = new Histogram(N+1); 
   // for (int t = 0; t < T; t++) {
   //    histogram.addDataPoint(Bernoulli.binomial(N));
   // }
      
      // display using standard draw
   // StdDraw.setCanvasSize(500, 100);
   // histogram.draw();
   //} 
} 
