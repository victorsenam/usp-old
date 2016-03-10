/******************************************************************************
 *  Nome: Nícolas Nogueira Lopes da Silva
 *  Nº USP: 9277541
 *
 *  Compilacao:  javac-algs4 Transition.java
 *  Execucao:    java-algs4 Transition < input.txt
 *
 *  % java-algs4 Transition < input.txt
 *
 *  Obs.: Arquivo retirado do Sandbox disponibilizado pelo professor,
 *  adaptado para a realizacao do exercicio.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class Transition {

    public static void main(String[] args) {

        int N = StdIn.readInt();           // number of pages
        int[][] counts = new int[N][N];    // counts[i][j] = # links from page i to page j
        int[] outDegree = new int[N];      // outDegree[i] = # links from page i to anywhere
        double p;

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
                if (outDegree[i] != 0)
                    p = .90*counts[i][j]/outDegree[i] + .10/N;
                else
                    p = (double) 1/N;
                StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
} 

