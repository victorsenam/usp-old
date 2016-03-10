/* Nome: Luís Felipe de Melo Costa Silva
// Número USP: 9297961
// Programa: RandomSurfer.java
// Observação: Este programa foi copiado de 
// http://introcs.cs.princeton.edu/java/16pagerank/RandomSurfer.java
//
//  Compilação:              javac RandomSurfer.java
//  Execução:                java RandomSurfer T
//  Arquivos necessários:    StdOut.java, StdIn.java
//               
//
// O arquivo StdOut.java se encontra em
// http://introcs.cs.princeton.edu/java/15inout/StdOut.java.html
// O arquivo StdIn.java se encontra em 
// http://introcs.cs.princeton.edu/java/15inout/StdIn.java.html
// 
// Exemplo de execução
//  $ java HubsAndAuthorities 5 10 4 3 | java Transition | java RandomSurfer 1000000
//  0.15116 0.03250 0.17436 0.03228 0.19093 0.11810 0.07792 0.06163 0.06372 0.03271 0.03251 0.03219
// 
// Onde:
// Os 5 primeiros números correspondem a páginas aleatórias.
// Os próximos 4 são Hubs.
// Os últimos 3 são Authorities
//
*/  

public class RandomSurfer {
    public static void main (String[] args) {
        int T = Integer.parseInt(args[0]);    /* Número de movimentos. */
        int M = StdIn.readInt();              /* Número de páginas, 
                                                 que é ignorado quando 
                                                 M == N. */
        int N = StdIn.readInt();              /* Número de páginas. */
        if (M != N) throw new RuntimeException ("M does not equal N");

        // Read transition matrix.
        double[][] p = new double[N][N];     /* p[i][j] = prob. de o 
                                                internauta ir da 
                                                página i para a j. */
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) 
                p[i][j] = StdIn.readDouble(); 


        int[] freq = new int[N];            /* freq[i] = número de 
                                               vezes que o internauta
                                               chega na página i. */
 
        /* Começando na página 0. */
        int page = 0;

        for (int t = 0; t < T; t++) {

            /* Faz um movimento aleatório. */ 
            double r = Math.random(); 
            double sum = 0.0; 
            for (int j = 0; j < N; j++) {
                /* Acha o intevalo contendo r. */
                sum += p[page][j]; 
                if (r < sum) {
                    page = j;
                    break;
                } 
            } 
            freq[page]++; 
        } 


        /* Imprime os page ranks. */
        for (int i = 0; i < N; i++) {
            StdOut.printf("%8.5f", (double) freq[i] / T); 
        }
        StdOut.println(); 
    }  
} 