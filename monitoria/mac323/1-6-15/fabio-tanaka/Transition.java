/******************************************************************************
 *  Nome: Fabio Henrique Kiyoiti dos Santos Tanaka
 *  Numero Usp: 9297982
 *  Compilation:  javac Transition.java
 *  Execution:    java Transition < input.txt
 *
 *  Este programa funciona de forma analoga ao Transition.java da pagina do
 *  curso, porem quando uma pagina nao tem links apontando a outras paginas
 *  ele tem 100% de chances de ir a uma outra pagina aleatoriamente. 
 *
 *  % more tiny.txt
 *  5 
 *  0 1 
 *  1 2  1 2 
 *  1 3  1 3  1 4 
 *  2 3 
 *  3 0 
 *  4 0  4 2 
 *
 *  % java Transition < tiny.txt
 *  5 5
 *   0.02 0.92 0.02 0.02 0.02
 *   0.02 0.02 0.38 0.38 0.20
 *   0.02 0.02 0.02 0.92 0.02
 *   0.92 0.02 0.02 0.02 0.02
 *   0.47 0.02 0.47 0.02 0.02
 *
 ******************************************************************************/


public class Transition {


    public static void main(String[] args) {

        double p;
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
                // Esta foi a linha criada para o caso de nao haverem links
                if (outDegree[i] == 0)
                    p = 1.0/N;
                else
                    p = .90*counts[i][j]/outDegree[i] + .10/N; 
                StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
} 

