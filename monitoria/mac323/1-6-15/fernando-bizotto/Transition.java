/*Programa Transition.java do Sedgewick apenas levemente modificado para se adequar à entrada de RandomSurfer.java*/ 

import edu.princeton.cs.algs4.*;

public class Transition {


    public static void main(String[] args) {

        int N = StdIn.readInt();           // number of pages
        int[][] counts = new int[N][N];    // counts[i][j] = # links from page i to page j
        int[] outDegree = new int[N];      // outDegree[i] = # links from page i to anywhere

        // Accumulate link counts.  
        while (!StdIn.isEmpty())  {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println(N + " " + N); 


        // Print probability distribution for row i. 
        for (int i = 0; i < N; i++)  {

            // Print probability for column j. 
            for (int j = 0; j < N; j++) {
            	double p;
            	if (outDegree[i] == 0) {			/*Única modificação em Transition.java para que o programa 		*/
            		p = 1.0/N;						/*imprima matriz de transição correta para grafos inicialmente	*/
            	}									/*desconexos													*/
            	else {
            		p = .90*counts[i][j]/outDegree[i] + .10/N; 
            	}
            	StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
}
