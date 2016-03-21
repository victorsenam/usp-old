/******************************************************************************
 * Nome: Ângelo Gregório Lovatto
 * Número USP: 9293435
 *
 * O programa WordFrequencies imprime uma lista das palavras na entrada padrão
 * para a saída padrão em ordem decrescente de frequencia, e ordem lexicográfica
 * entre palavras de mesma frequência. Para isso, faz o uso de árvores binárias
 * de busca, jogando as palavras em uma árvore de busca baseada na ordem
 * lexicográfica e depois "jogando" os nós desta (em ordem esquerda-raiz-
 * direita) em outra árvore de busca baseada na frequencia. Após isso, apenas
 * imprime a última em ordem esquerda-raiz-direita.
 *
 *****************************************************************************/

import edu.princeton.cs.algs4.*;
public class WordFrequencies {

    public static void main (String[] args) {
        String wd;
        int N = 0;
        BST words = new BST();


        while (!StdIn.isEmpty()) {
            wd = StdIn.readString();
            wd = wd.toLowerCase();
            wd = wd.replaceAll("[^\\p{IsAlphabetic}^\\p{IsDigit}^-]", "");
            words.addWord(wd);
        }

        if (words.size() == 0) throw new RuntimeException("No words in input");

        words.sortbyCount();
        words.printTree();

    }

}

