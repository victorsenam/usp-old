import java.util.*;
import edu.princeton.cs.algs4.*;
/*///////////////////////////////////////////////////////////////////////
//                                                                     //
// Nome: Adriano Tetsuaki Ogawa Santin                                 //
// Número USP: 8944387                                                 //
// Creative Problem 1.3.36 (Random Iterator)                           //
//                                                                     // 
///////////////////////////////////////////////////////////////////////*/

import java.util.Arrays;

public class TesteVisual {

   // Cria uma fila aleatória de N inteiros.
   public static RandomQueue<Integer> criaFila (int N) {
      RandomQueue<Integer> fila = new RandomQueue<Integer>();
      for (int i = 1; i <= N; i++)
         fila.enqueue (i);
      return fila;
   }

   // Cria um vetor de permutações onde cada objeto contém
   // uma string com a permutação e um int com a frequência.
   public static Permutacao[] criaPerm (RandomQueue<Integer> fila, int N, int T) {
      Permutacao[] perm = new Permutacao[T];
      int tam = 0;
      for (int i = 0; i < T; i++) {
         int[] a = new int[N];
         int j = 0;
         for (int x : fila)
            a[j++] = x;
         String A = Arrays.toString (a);
         int k;
         for (k = 0; perm[k] != null; k++) {
            if (A.equals (perm[k].str())) {
               perm[k].incFreq();
               break;
            }
         }
         if (perm[k] == null)
            perm[k] = new Permutacao (A);
         if (k > tam) tam = k;
      }
      Permutacao[] newperm = new Permutacao[tam];
      for (int i = 0; i < tam; i++)
         newperm[i] = perm[i];
      return newperm;
   }

   // Programa gera T permutações de N inteiros. Conta a
   // frequência de cada permutação e imprime um histograma.
   public static void main (String[] args) {
      int N = Integer.parseInt (args[0]);
      int T = Integer.parseInt (args[1]);
      RandomQueue<Integer> fila = criaFila (N);
      Permutacao[] perm = criaPerm (fila, N, T);

      Histogram hist = new Histogram (perm.length);
      for (int i = 0; i < perm.length; i++)
         hist.addDataPoint (i, perm[i].freq());
      StdDraw.setCanvasSize (800, 100);
      hist.draw ();
   }
}
