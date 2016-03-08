/* Nome: Vinicius Pessoa Duarte      */
/* Numero USP: 8941043               */
/* Disciplina: MAC-0323              */
/* Exercicio: Page Rank/1.6.15       */

/* Este programa e analogo a versao do site, i.e., calcula matriz de transicao, porem, */
/* evita que ocorra divisao por zero caso determinado vertice nao tenha saidas. Caso   */
/* isso ocorra, a probabilidade de saida do vertice passa a ser uniforme.              */

import edu.princeton.cs.algs4.*;

public class Transition {

    public static void main(String[] args) {

        int N = StdIn.readInt();
        int[][] counts = new int[N][N];
        int[] outDegree = new int[N];
 
        while (!StdIn.isEmpty())  {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println(N + " " + N); 

        for (int i = 0; i < N; i++)  {

            for (int j = 0; j < N; j++) {
                double p = 0;
                if (outDegree[i] != 0) // Condicional evita divisao por zero
                    p = .90*counts[i][j]/outDegree[i] + .10/N;
                else                   // Define distribuicao homogenea para a transicao
                    p = 1.0/N;
                StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
} 

