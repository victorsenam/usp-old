import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *  Nome: Fabio Henrique Kiyoiti dos Santos Tanaka
 *  Numero Usp: 9297982
 *  Compilation:  javac Histogram.java
 *
 *  Este eh o mesmo programa fornecido no site, mas sem a funcao main
 *
 ******************************************************************************/

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

    // draw (and scale) the histogram.
    public void draw() {
        StdDraw.setYscale(0, max + (max/10));  // to leave a little border
        StdStats.plotBars(freq);
    }

} 