/*************************************************************************
 * Nome: Victor Domiciano Mendonca
 * Numero USP: 8641963
 *
 * O problema foi resolvido da seguinte forma: a cada string lida, ela é 
 * convertida para letras minusculas e colocada em um vetor em ordem 
 * lexicografica do maior para o menor (isso mesmo, na ordem inversa).
 * Na execucao do mergesort, ha um reajuste na ordem e as strings acabam 
 * por ficar na ordem correta. Quando o texto de entrada termina, o 
 * programa retira as strings repetidas e adiciona a frequencia de cada 
 * uma em um vetor de inteiros cujos indices correspondem ao vetor de 
 * strings. Ao fim, o vetor de inteiros e colocado em ordem decrescente 
 * atraves do mergesort.
 *
 *************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.Scanner;

public class WordFrequencies {
   public static void main(String[] args) {
      int n = 0, size = 10, cont = 1;
      int[] v;
      Scanner sc;
      String[] str = new String[size];
      sc = new Scanner (System.in).useDelimiter("[ !'(),./0-9:;?ªº\n\r]+");
      while (sc.hasNext()) {
         str[n] = sc.next();
         str[n] = str[n].toLowerCase();
         for (int i = n; i > 0; i--)
            // organiza em ordem lexicografica do maior para o menor
            // (mergesort corrige)
            if (str[i].compareTo(str[i - 1]) > 0) {
               String swap = str[i];
               str[i] = str[i - 1];
               str[i - 1] = swap;
            }
         // redimensiona o vetor caso esteja cheio
         if (n == size - 1) {
            size *= 2;
            String[] buffer = new String[size];
            for (int i = 0; i <= n; i++)
               buffer[i] = str[i];
            str = buffer;
         }
         n++;
      }
      v = new int[n];
      // retira as repeticoes e adiciona a frequencia
      for (int i = 0; i < n; i++) {
         if (str[i].compareTo(str[i + 1]) == 0) {
            cont++;
            i--;
            n--;
            for (int j = i + 1; j < n; j++)
               str[j] = str[j + 1];
         }
         else {
            v[i] = cont;
            cont = 1;
         }
      }
      mergesort(0, n, v, str);
      for (int i = 0; i < n; i++)
         StdOut.println(str[i] + " " + v[i]);
   }

   public static void mergesort(int p, int r, int[] v, String[] str) {
      if (p < r - 1) {
         int q = (p + r) / 2;
         mergesort (p, q, v, str);
         mergesort (q, r, v, str);
         intercala (p, q, r, v, str);
      }
   }

   public static void intercala(int p, int q, int r, int[] v,
                                 String[] str) {
      int i = p, j = q, k = 0;
      int[] w = new int[r - p];
      String[] z = new String[r - p];
      while (i < q && j < r)
         if (v[i] > v[j]) {
            w[k] = v[i];
            z[k++] = str[i++];
         }
         else {
            w[k] = v[j];
            z[k++] = str[j++];
         }
      while (i < q) {
         w[k] = v[i];
         z[k++] = str[i++];
      }
      while (j < r) {
         w[k] = v[j];
         z[k++] = str[j++];
      }
      for (i = p; i < r; i++) {
         v[i] = w[i - p];
         str[i] = z[i - p];
      }
   }
}

