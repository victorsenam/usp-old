/******************************************************************************
 *
 *  MAC0323 - Estrutura de Dados 2
 *  Aluno: Gabriel Capella
 *  Número USP: 8962078
 *  Tarefa: Creative Ex. 1.6.15 (Hubs and authorities) de IntroCS
 *  URL: http://introcs.cs.princeton.edu/java/16pagerank/
 *  Data: 27/02/2016
 *
 *  O codigo eh o mesmo do livro.
 *
 ******************************************************************************/

public class Markov {

    public static void main(String[] args) { 
        int T = Integer.parseInt(args[0]);
        StdOut.println(StdIn.readLine());
        StdOut.println(StdIn.readLine());
        
        int N = StdIn.readInt();
        StdIn.readInt();

        double[][] p = new double[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) 
                p[i][j] = StdIn.readDouble(); 

        double[] rank = new double[N]; 
        rank[0] = 1.0; 
        for (int t = 0; t < T; t++) {

            double[] newRank = new double[N]; 
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) 
                   newRank[j] += rank[k]*p[k][j]; 
            } 
            rank = newRank;
        }
        StdOut.println(); 
        for (int i = 0; i < N; i++) {
            StdOut.printf("%2d  %5.3f\n", i, rank[i]);  
        }
    } 
} 
