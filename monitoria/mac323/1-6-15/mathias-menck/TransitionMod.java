/*
 * Nome: Mathias Van Sluys Menck
 * numUSP: 4343470
*/


/* Versão modificada de transition. */

public class TransitionMod {
    public static void main(String[] args) {

        int H = StdIn.readInt();   /* Adicionei H e A para que as hubs e authorities fossem */
        int A = StdIn.readInt();   /* diferenciaveis das paginas normais. */
        int N = A + H + StdIn.readInt();       // number of pages
        int[][] counts = new int[N][N];    // counts[i][j] = # links from page i to page j
        int[] outDegree = new int[N];      // outDegree[i] = # links from page i to anywhere

        // Accumulate link counts.  
        while (!StdIn.isEmpty())  {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println(H + " " + A); /* Essa linha foi adicionada */
        StdOut.println(N + " " + N); 


        // Print probability distribution for row i. 
        for (int i = 0; i < N; i++)  {

            // Print probability for column j. 
            for (int j = 0; j < N; j++) {
                double p;
                /* Este if é garante que se a pagina não apontar para
                 * ninguém nao seja utilizada a regra de 10%/90% */
                if(outDegree[i] == 0)
                    p = 1/(double)N;
                else
                    p = .90*counts[i][j]/outDegree[i] + .10/N; 
                
                StdOut.printf("%7.5f ", p);
            } 
            StdOut.println(); 
        } 
    } 
} 

