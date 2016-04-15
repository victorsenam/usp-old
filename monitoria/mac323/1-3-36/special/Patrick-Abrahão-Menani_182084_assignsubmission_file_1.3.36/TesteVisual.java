import java.util.*;
import edu.princeton.cs.algs4.*;
//
// Nome: Patrick Abrahão Menani
// N USP: 8941050
// TesteVisual.java
//
// Este programa necessita do RandomQueue.java compilado e do Histogram.java também
// compilado.
//
// Modo de compilação: javac-algs4 TesteVisual.java e javac-algs4 Frequencies.java
// Modo de Execução: java-algs4 TesteVisual N T | java-algs4 Frequencies
//

import edu.princeton.cs.algs4.*;

public class TesteVisual {

  public static int fatorial (int n) {
     int fat = 1;
     for (int i = n; i > 0; i--)
        fat = fat * i;
     return fat;
   }

   public static void main (String[] args) {
      int N = Integer.parseInt (args[0]);
      int T = Integer.parseInt (args[1]);
      int n = fatorial (N);
      String s;
      String[] p = new String[n];
      RandomQueue<Integer> q = new RandomQueue<Integer>();

      for (int i = 0; i <= N; i++) {
            q.enqueue(i + 1);
      }

      StdOut.printf ("%d\n", n);
      for (int i = 0; i < T; i++) {
         for (Integer k : q)
            StdOut.printf ("%d", k);
         StdOut.printf ("\n");
      }
   }
}
