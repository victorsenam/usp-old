/******************************************************************************
 * Name: Marcos Vinicius do Carmo Sousa
 * NUSP: 9298274
 *
 *  Compilation:  javac-algs4 Generator.java
 *  Execution:    java-algs4 Generator
 *
 * Vocabulario:
 * auth = authorities
 *
 * Programa que gera um grafo para o programa Transition.
 * Com um numero suficiente de nos (N), links (M), auth(A) e hubs(H)
 * e possivel notar que o rank (calculado no programa externo RandomSurfer) 
 * de hubs adicionados e superior aos auth.
 * Exemplos de entradas funcionais:
 *    java-algs4 Generator N M A H
 *    java-algs4 Generator 100 200 5 5
 *    java-algs4 Generator 100 200 10 10
 *
 * Entrada nao reccomendada:
 *    java-algs4 Generator 100 200 1 1 
 *    Apenas 1 hub ou auth nao e possivel diferenciar em relacao aos links
 *    originais do grafo.
 *
 * Saida:
 *     As [N+A, N+A+H[ linhas da saida, sao referentes aos hubs adicionados
 *     As [N, N+A[  linhas da saida, sao referentes aos auth adicionados
 *     As [0, N[ linhas da saida, sao referentes ao grafo puro sem hubs e
 *     auth gerados.
 *******************************************************************************/

import edu.princeton.cs.algs4.*;
public class Generator {
   public static void main (String[] args) {
   int N = Integer.parseInt (args[0]);
   int M = Integer.parseInt (args[1]);
   int A = Integer.parseInt (args[2]);
   int H = Integer.parseInt (args[3]);
   int[][] counts = new int[N+A+H][N+A+H];
   int[] degree = new int[N+A];
   int i = M;
   System.out.println (N+A+H);
   while (i > 0) {
      int auxi = StdRandom.uniform (0, N);
      int auxj = StdRandom.uniform (0, N); 
      counts[auxi][auxj]++;
      i--;
   }
   for (i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
         degree[i] = degree[i] + counts[i][j];
      }
   }
   for (i = N; i < N+A; i++) {
      int aux = (int) (0.1 * N);
      for (int j = 0; j < aux; j++) {
         int rand = StdRandom.uniform (0, N);
         counts[i][rand]++;
      }
   }
   for (int j = N+A;  j < N+A+H; j++) {
      int aux = (int) (0.1 * N);
      for (i = 0; i < aux; i++) {
         int rand = StdRandom.uniform (0, N);
         counts[rand][j]++;
      }
   }
   for (i = 0; i < N+A+H; i++) {
      int a = 0;
      for (int j = 0; j < N+H+A; j++) {
         if (counts[i][j]!=0) {
            for (int repeat = 0; repeat < counts[i][j]; repeat++)
               StdOut.print (i + " " + j + "   ");
            a = 1;
         }
      }
      if (a == 1) {
         StdOut.println();
         a = 0;
      }
   }
   }
}

