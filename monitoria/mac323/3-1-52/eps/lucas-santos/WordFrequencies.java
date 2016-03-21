/***************************************************************/
/**                                                           **/
/**   Lucas Moreira Santos                          9345064   **/
/**   Creative Exercise 3.1.52                                **/
/**   Professor:  Yoshiharu Kohayakawa                        **/
/**   MAC0323 - Algoritmos e Estruturas de Dados II           **/
/**                                                           **/
/***************************************************************/

/*
  O algoritmo é divido em algumas etapas
  1) Quebrar o texto em palavras, já deixando todas as palavras em lower case;
  2) Ordenar o conjunto de palavras;
  3) Contar a frequência das palavras;
  4) Ordenar os pares (frequência, palavra) primeiro pela maior frequência e depois pela menor palavra
  5) Imprimo os pares ordenados
  6) gg

  Nota: implementei manualmente uma classe Pair (par) porque o Java
  não tem algo tão bonito (eu acho) quanto pair do C++. (shame on you Java)
*/

import edu.princeton.cs.algs4.*;
import java.util.*;

public class WordFrequencies {

  public static void main(String[] args) {
    List<String> words = new ArrayList<String>();

    while(!StdIn.isEmpty()) {
      String in = StdIn.readString();
      words.add(in.toLowerCase());
    }

    // Ordeno o conjunto de palavras
    Collections.sort(words);

    // Conto a frequência das palavras já criando os pares
    List<Pair> pairs = new ArrayList<Pair>();
    int x = -1;
    for (int i = 0; i < words.size(); i++) {
      if (x == -1 || words.get(i).compareTo(pairs.get(x).getWord()) != 0) {
        Pair novo = new Pair(0, words.get(i));
        pairs.add(novo);
        x++;
      }

      pairs.get(x).increaseFrequency();
    }

    // Ordeno o conjunto de pares
    Collections.sort(pairs, new PairComparator());

    // Imprimo os pares
    for (int i = 0; i <= x; i++) {
      StdOut.println(pairs.get(i).getWord() + " " + String.valueOf(pairs.get(i).getFrequency()));
    }

    // gg
	}
}
