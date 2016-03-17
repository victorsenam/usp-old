/*
 * NOME:   Éderson Cássio Lacerda Ferreira
 * N. USP: 9297975
 *
 * Ordenação de palavras pela frequência.
 * Ref.: http://www.ime.usp.br/~pf/algoritmos/aulas/quick.html
 */

public class QuickSort {

   public static void ordenar(Palavra[] palavras, int inicio, int fim) {
      while (inicio < fim) {
         int divisa = separa(palavras, inicio, fim);
         ordenar(palavras, inicio, divisa-1);
         inicio = divisa + 1;
      }
   }

   private static int separa(Palavra[] palavras, int inicio, int fim) {
      Palavra c = palavras[inicio];
      int i = inicio+1;
      int j = fim;
      
      while (true) {
         while (i <= fim && palavras[i].getFrequencia() <= c.getFrequencia())
            ++i;
         while (c.getFrequencia() < palavras[j].getFrequencia())
            --j;
         if (i >= j) break;
         
         Palavra t = palavras[i];
         palavras[i] = palavras[j];
         palavras[j] = t;
         
         ++i;
         --j;
      }
      
      palavras[inicio] = palavras[j];
      palavras[j] = c;
      return j;
   }

}
