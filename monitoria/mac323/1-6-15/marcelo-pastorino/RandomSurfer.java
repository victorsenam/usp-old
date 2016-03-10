/******************************************************************************
 *  Nome: Marcelo Baiano Pastorino Trylesinski  
 *  Número USP: 9297996
 *
 *  Compilation:  javac-algs4 RandomSurfer.java 
 *  Execution:    java RandomSurfer T < input.txt
 *
 *  O RandomSurfer simula o comportamento do random surfer. Ele lê a matriz de 
 *  transição e viaja pelos links. Começa pela página 0 e utiliza T numero de movimentos.
 *  Imprime o page rank. 
 *
 *  Obs.: O programa atual é uma modificação do programa de mesmo nome RandomSurfer
 *  do livro Introdution to Programming in Java de Robert Sedgewick e Kevin Wayne.
 *
 ******************************************************************************/


import edu.princeton.cs.algs4.*;

public class RandomSurfer {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);    // number of moves
        int M = StdIn.readInt();              // number of pages  - ignore since M = N
        int N = StdIn.readInt();              // number of pages
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

        StdOut.println ();
        StdOut.println ("Ranks:");
        StdOut.println ();

        // Print page ranks. 
        for (int i = 0; i < N; i++) {
            StdOut.printf("Pagina %d: %8.5f", i, (double) freq[i] / T);
            StdOut.println (); 
        }
        StdOut.println(); 
    }  
} 
