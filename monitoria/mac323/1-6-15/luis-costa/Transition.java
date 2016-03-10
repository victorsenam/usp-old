/* Nome: Luís Felipe de Melo Costa Silva
// Número USP: 9297961
// Programa: Transition.java
// Observação: Alteração do programa Transition.java de 
// http://introcs.cs.princeton.edu/java/16pagerank/Transition.java
//
//  Compilação:              javac Transition.java
//  Execução:                java Transition < exemplo.txt
//  Arquivos necessários:    StdOut.java, StdIn.java.
//               
//
// O arquivo StdOut.java se encontra em
// http://introcs.cs.princeton.edu/java/15inout/StdOut.java.html
// O arquivo StdIn.java se encontra em 
// http://introcs.cs.princeton.edu/java/15inout/StdIn.java.html
// 
// exemplo.txt
// 10
// 0 0 0 2 
// 1 4 
// 2 1 2 4 
// 3 0 3 6 
// 4 0 4 2 4 3 4 4 4 5 
// 7 2 
// 8 3 
// 9 4
*/ 

public class Transition {


    public static void main(String[] args) {

        int N = StdIn.readInt();           /* Número de páginas. */
        int[][] counts = new int[N][N];    /* counts[i][j] = número de
                                              links da página i para a
                                              página j.*/
        int[] outDegree = new int[N];      /* outDegree[i] = número de
                                              links da página i para
                                              qualquer outra. */

        /* Acumula a contagem dos links. */  
        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println (N + " " + N); 


        /* Imprimindo a distribuição de probalidade para a linha i */
        for (int i = 0; i < N; i++) {
            /* Se a página i não tem links, o usuário vai para uma 
               página aleatória com 100% de certeza. Essa 
               probabilidade é dividida pelas N páginas que existem.*/   
            if (outDegree[i] == 0) 
                for (int j = 0; j < N; j++) {
                    double p = (double) 1/N; 
                    StdOut.printf ("%7.5f ", p);
                }
            /* Se a página i tem links, aplica-se a regra geral. */ 
            else
                for (int j = 0; j < N; j++) {
                    double p = .90*counts[i][j]/outDegree[i] + .10/N; 
                    StdOut.printf ("%7.5f ", p);
                }     
            StdOut.println(); 
        } 
    } 
} 
