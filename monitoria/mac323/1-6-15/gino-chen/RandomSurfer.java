/*************************************
 * 
 * MAC0323 - 1º sem de 2016
 * 
 * São Paulo, 5 de março de 2016
 * 
 * Creative Exercise 1.6.15 (Hubs and authorities; IntroCS)
 *
 * Gino Chen Hsiang-Jan
 * Número USP: 748536
 * 
*************************************/

/******************************************************************************
 *  Compilation:  javac RandomSurfer.java
 *  Execution:    java RandomSurfer T
 *  Data files:   http://introcs.cs.princeton.edu/16pagerank/tiny.txt
 *                http://introcs.cs.princeton.edu/16pagerank/medium.txt
 *
 *  % java Transition < tiny.txt | java RandomSurfer 1000000
 *   0.27297 0.26583 0.14598 0.24729 0.06793
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.*;

public class RandomSurfer {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);    // number of moves
        int M = StdIn.readInt();              // number of pages  - ignore since M = N
        int N = StdIn.readInt();              // number of pages
		int H = StdIn.readInt(); // (quantidade de hubs)
		int A = StdIn.readInt(); // (quantidade de authorities)
        
        if (M != N) throw new RuntimeException("M does not equal N");

        // Read transition matrix.
        double[][] p = new double[N][N];     // p[i][j] = prob. that surfer moves from page i to page j
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) 
                p[i][j] = StdIn.readDouble(); 


        int[] freq = new int[N];            // freq[i] = # times surfer hits page i
 
        // Start at page 0. 
        int page = 0;

        for (int t = 0; t < T; t++) {

            // Make one random move. 
            double r = Math.random(); 
            double sum = 0.0; 
            for (int j = 0; j < N; j++) {
                // Find interval containing r. 
                sum += p[page][j]; 
                if (r < sum) {
                    page = j;
                    break;
                } 
            } 
            freq[page]++; 
        } 

        int MaiorFreq       = 0;
        int IndiceMaiorFreq = -1;

        int i;
        // Print page ranks. 
        for (i = 0; i < N; i++) {
        	if (freq[i] > MaiorFreq) {
        		MaiorFreq = freq[i];
        		IndiceMaiorFreq = i;
        	}
            StdOut.printf("%8.5f", (double) freq[i] / T); 
        }
        StdOut.println();
        if (i < A)
        	StdOut.println("Maior frequencia é um Hub");
        else if (i < A + H)
        	StdOut.println("Maior frequencia é um Authority");
        else
        	StdOut.println("Maior frequencia não é Hub nem Authority");
    }  
} 
