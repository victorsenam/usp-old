/******************************************************************************
 *  Nome: Marcelo Baiano Pastorino Trylesinski  
 *  Número USP: 9297996
 *
 *  Compilation:  javac Transition.java
 *  Execution:    java Transition < input.txt
 *
 *  Esse programa é um filtro que le links da entrada padrão e produz uma matriz
 *  de transição na saida padrão. Primeiro, lê o número de páginas N e depois os
 *  links. Então, aplica a regra 90-10 para calcular a matriz de transição. Quando
 *  a página não tem links para outras páginas, aplica a regra dos 10%.
 *
 *  Obs.: O programa atual é uma modificação do programa de mesmo nome Transition
 *  do livro Introdution to Programming in Java de Robert Sedgewick e Kevin Wayne.
 *
 ******************************************************************************/

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
                if (outDegree[i] == 0) {
                    p = 1.0 / N;
                } else {
                    p = .90*counts[i][j]/outDegree[i] + .10/N; 
                }
                StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
} 
