/* Nome: Vinicius Pessoa Duarte      */
/* Numero USP: 8941043               */
/* Disciplina: MAC-0323              */
/* Exercicio: Page Rank/1.6.15       */

/* Este programa recebe o valor de paginas a serem utilizadas, de pares a ser   */
/* erados e quantidade de hubs e authorities, retornando o numero total de      */
/* gerados e os pares, hubs e authorities gerados de forma aleatoria.           */
/* Compilar utilizando javac-algs4 HubsAut.java                               */

/* Como os hubs recebem mais referencias que os authorities, tem que o          */
/* pagerank dos hubs tende a ser maior para n-amostral convenientemente grande. */

import edu.princeton.cs.algs4.*;

public class HubsAut {
   public static void main (String[] args) {
      int n = Integer.parseInt (args[0]); // Numero de paginas
      int m = Integer.parseInt (args[1]); // Numero de pares
      int hubs = Integer.parseInt (args[2]);
      int auth = Integer.parseInt (args[3]);;
      int total = n + hubs + auth;

      StdOut.println (total);

      // Gera os pares aleatorios
      for (int k = 0; k < m; ++k) {
         int i = (int) (Math.random () * n);
         int j = (int) (Math.random () * n);
         StdOut.println (i + " " + j);
      }

      // Gera os hubs
      for (int k = 0; k < hubs; ++k) {
         int perc = 0;
         while (perc < n/10) {
            int i = (int) (Math.random () * n);
            StdOut.println (i + " " + (k + n));
            perc++;               
         }
      }

      // Gera os authorities
      for (int k = 0; k < auth; ++k) {
         int perc = 0;
         while (perc < n/10) {
            int j = (int) (Math.random () * n);
            StdOut.println ((k + n + hubs) + " " + j);
            perc++;
         }
      }
   }
}