/*
 * @author: Eduardo Pinheiro
 * NUSP:    8936798
 */
import edu.princeton.cs.algs4.*;

public class Generator
{
  public static void main(String args[]) {
    /* Verifica se a quantidade de argumentos é suficiente */
    if (args.length < 4)
      throw new IllegalArgumentException("Not enough args");

    /* Variáveis */
    int N = Integer.parseInt(args[0]); /* Número de páginas */
    int M = Integer.parseInt(args[1]); /* Número de links */
    int Hubs = Integer.parseInt(args[2]);
    int Authorities = Integer.parseInt(args[3]);
    int size = N + Hubs + Authorities; /* Total de páginas */
    int Matrix[][] = new int[size][size]; /*matriz que gera os pares de links */
    boolean flag = false; /* flag para páginas sem links */

    /* Gera os pares randomicos */
    for (int i = 0; i < M; i++) {
      Matrix[StdRandom.uniform(0, N)][StdRandom.uniform(0, N)]++;
    }

    /* adiciona Hubs */
    for (int i = 0; i < Hubs; i++) {
      for (int j = 0; j < N / 10; j++) {
        Matrix[StdRandom.uniform(0, N)][N + i]++;
      }
    }

    /* adiciona Authorities */
    for (int i = 0; i < Authorities; i++) {
      for (int j = 0; j < N / 10; j++) {
        Matrix[N + Hubs + i][StdRandom.uniform(0, N)]++;
      }
    }

    /* Print */
    System.out.println(size);
    for (int i = 0; i < size; i++) {
      flag = false;
      for (int j = 0; j < size; j++) {
        while (Matrix[i][j] > 0) { /* Passa por esse while apenas M vezes durante toda a execução, mantendo o algoritmo O(N^2) */
          System.out.print(i + " " + j + "  ");
          flag = true;
          Matrix[i][j]--;
        }
      }
      if (flag)
        System.out.println();
    }
  }
}
