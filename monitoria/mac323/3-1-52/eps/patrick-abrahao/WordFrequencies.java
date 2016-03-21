//
// Nome: Patrick Abrahão Menani
// N USP: 8941050
// WordFrequencies.java
//
// Esse programa le as palavras da saída padrão e aloca em um vetor de strings txt, 
// posteriormente cria um vetor e vai adicionando as palavras já em ordem lexicogáfica
// e suas frequencias em um vetor de int chamado count.
// Ele utiliza o critério de comparação dessa forma:
//  - Caso as strings sejam iguais ele adiciona +1 no contador dessa palavra;
//  - Caso ele ache uma palavra maior que a atual, ele movimenta toda o vetor
//    de strings uma posição a frente e o faz o mesmo para os de frequencia, e 
//    adiciona a string na posição correta j;
//  - Caso nenhuma das anteriores sejam realizadas ele adiciona a palavra no final.
// No final, após corrigir o contador, ele vai imprimindo de acordo com o primeiro 
// valor MAX do vetor count, e substituindo o valor da posição impressa para 0,
// logo já estará em ordem de frequência e lexicográfica.
// Quando a soma do vetor count é zero ele finaliza a impressão.
//
// Modo de Execução: java-algs4 WordFrequencies < texto.txt
//

import edu.princeton.cs.algs4.*;

public class WordFrequencies {

  // Soma todos os valores do vetor e retorna um int igual a soma.
  public static int sum (int[] v, int n) {
     int sum = 0;
     for (int i = 0; i < n; i++)
        sum = sum + v[i];
     return sum;
   }

   public static void main (String[] args) {

      String[] txt = StdIn.readAllStrings();
      int N = txt.length;
      String[] all = new String[N];
      int[] count = new int[N];
      int i = 0;

      // Tratamento de palavras vindas do texto.
      for (int c = 0; c < N; c++) {
         txt[c] = txt[c].toLowerCase().replace(",","").replace(".","").replace(":","").replace(";","").replace("!","").replace("?","");
      }

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

      // Corrige o vetor de contadores.
      for (int j = 0; j < i; j++)
         count[j]++;

      // Imprime em ordem de frequencia e lexicográfica para frequeências iguais.
      while (sum(count, i) > 0) {
         int k = 0, w = 0;
         for (int j = 0; j < i; j++) {
            if (count[j] > k) {
               k = count[j];
               w = j;
            }
         }
         StdOut.printf ("%s %d\n", all[w], k);
         count[w] = 0;
      }

   }
}

