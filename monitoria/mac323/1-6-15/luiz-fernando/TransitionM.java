/*////////////////////////////////////////////////////////////////////
//
// Autor: Luiz Fernando Antonelli Galati                                                    
// Numero USP: 7557223                                                                                 
// Data: 05-03-2016                                             
//                                                               
// Creative Exercise 1.6.15 - Hubs and authorities
//                                                               
////////////////////////////////////////////////////////////////////*/


 
/* Este programa é um filtro que lê links da entrada padrão e produz
a matriz de transição correspondente. É uma modificação do programa
Transition.java contido na página do livro.                       
   Primeiro, o programa processa a entrada para contar quantos links
cada página contém. Depois, aplica a regra dos 90%-10% para obter a
matriz de transição.
   Se uma página x não contém links, o programa considera que há 100%
de chances de um usuário ir para uma página aleatória a partir de x. */


public class TransitionM {
    public static void main (String[] args) {
        int N, i, j;
        double p;
        int[][] counts;
        int[] outDegree;
       
        N = StdIn.readInt();       // número de páginas
        counts = new int[N][N];    // counts[i][j] = número de links da página i para a página j
        outDegree = new int[N];    // outDegree[i] = número total de links da página i
  
        while (!StdIn.isEmpty ())  {
            i = StdIn.readInt (); 
            j = StdIn.readInt (); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println (N + " " + N); 
        
        for (i = 0; i < N; i++)  {
            if (outDegree[i] == 0) {
                p = 1.0/N;
                for (j = 0; j < N; j++)
                    StdOut.printf ("%7.5f ", p);
            }
            else { 
                for (j = 0; j < N; j++) {
                    p = 0.9*counts[i][j]/outDegree[i] + 0.1/N; 
                    StdOut.printf("%7.5f ", p); 
                 }
            }
            StdOut.println(); 
        } 
    } 
}
