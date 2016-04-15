import java.util.*;
import edu.princeton.cs.algs4.*;
//
// Nome: Patrick Abrahão Menani
// N USP: 8941050
// Frequencies.java
//
// Este programa necessita do RandomQueue.java compilado e do Histogram.java também
// compilado.
//
// Modo de compilação: javac-algs4 TesteVisual.java e javac-algs4 Frequencies.java
// Modo de Execução: java-algs4 TesteVisual N T | java-algs4 Frequencies
//
import edu.princeton.cs.algs4.*;

public class Frequencies {

   public static void main (String[] args) {

      int n = StdIn.readInt();
      String[] txt = StdIn.readAllStrings();
      int N = txt.length;
      String[] all = new String[n+1];
      int[] count = new int[n+1];
      int i = 0;
      Histogram H = new Histogram (n);

      for (int c = 0; c < N; c++) {

         String s = txt[c];

         // Inserção da primeira string no vetor de strings.
         if (i == 0) {
            all[i] = s;
            i++;
         }

         else {
            int j;
            for (j = 0; j < i; j++){

               // Procura uma String igual no vetor de strings.
               if (s.compareTo(all[j]) == 0) {
                  count [j]++;
                  break;
               }

               // Procura a primeira String maior que a atual no vetor de strings.
               // Caso seja verdade, movimenta os vetores uma posição pra frente.
               else if (s.compareTo(all[j]) < 0) {
                  for (int k = i; k >= j; k--) {
                     all[k+1] = all[k];
                     count[k+1] = count[k];
                  }
                  count[j] = 0;
                  all[j] = s;
                  i++;
                  break;
                }
            }
            // Caso nenhuma das anteriores seja verdade, adiciona a string atual no final do vetor.
            if (j == i) {
               all[j] = s;
               i++;
            }
         }
      }

      for (int j = 0; j < i; j++)
         count[j]++;

      for (int w = 0; w < i; w++) {
         for (int j = 0; j < count[w]; j++)
            H.addDataPoint (w);
      }

      StdDraw.setCanvasSize(500, 100);
      H.draw();
   }
}

