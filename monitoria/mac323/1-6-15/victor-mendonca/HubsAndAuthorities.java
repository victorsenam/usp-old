/**************************************************************************
 * Nome: Victor Domiciano Mendonca
 * Numero USP: 8641963
 *
 * Este programa recebe um numero N de paginas, M de links, H de hubs e A
 * de authorities, gera M pares aleatorios entre as N paginas e 
 * aproximadamente (N * H) / 10 links para os hubs e (N * A) / 10 links
 * saindo de authorities. Imprime K = N + H + A seguido dos pares 
 * aleatorios entre as paginas, hubs e authorities.
 *
 * Conclusao empirica: hubs quase sempre tem maior page rank que
 * authorities.
 *
 *************************************************************************/

import edu.princeton.cs.algs4.*;

public class HubsAndAuthorities {
   public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      int H = Integer.parseInt(args[2]);
      int A = Integer.parseInt(args[3]);
      int K = N + H + A;
      int i, j, p, q;
      int[][] links = new int[K][K];
      for(i = 0; i < K; i++)
         for(j = 0; j < K; j++)
            links[i][j] = 0;
      // gera os M pares aleatorios entre as N paginas
      for(p = 0; p < M; p++) {
         i = StdRandom.uniform(N);
         j = StdRandom.uniform(N);
         links[i][j] = links[i][j] + 1;
      } 
      q = Math.round((float) N / 10);
      // gera os pares aleatorios para cada hub
      for(i = N; i < K - A; i++)
         for(j = 0; j < q; j++) {
            p = StdRandom.uniform(N);
            links[p][i] = links[p][i] + 1;
         }
      // gera os pares aleatorios para cada authority
      for(i = N + H; i < K; i++)
         for(j = 0; j < q; j++) {
            p = StdRandom.uniform(N);
            links[i][p] = links[i][p] + 1;
         }
      System.out.println(K);
      for(i = 0; i < K; i++) {
         for(j = 0; j < K; j++)
            for(p = 0; p < links[i][j]; p++)
               System.out.print(i + " " + j + " ");
         System.out.println();
      }
   }
}

