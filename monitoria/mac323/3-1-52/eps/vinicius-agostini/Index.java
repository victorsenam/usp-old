/******************************************************************************
 *  Nome:         Vinicius Perche de Toledo Agostini
 *  Número USP:   4367487
 *
 *  Este módulo implementa um método para gerar uma Trie a partir da entrada
 *  e também um método para gerar um vetor de LinkedList<String> a partir
 *  de uma Trie.
 *  
 *  Exercício: 2.4.18 de http://introcs.cs.princeton.edu/java/24percolation/
 *
 ******************************************************************************/

import java.util.LinkedList;
import java.util.ListIterator;

import edu.princeton.cs.algs4.*;

public class Index {

    static int maxFreq = 0;

    /**
     * Gera uma árvore de prefixos com as palavras do texto de entrada.
     * Caso a palavra inserida já exista na árvore, o respectivo contador
     * de frequência será aumentado em 1
     *
     * @param words vetor com as palavras do texto de entrada
     * @return árvore de prefixos com todas as palavras do texto
     */
    public static TrieST<Integer> generateTrie(String[] words) {
        
        TrieST<Integer> trie = new TrieST<Integer>();
        for (String word : words) {
            
            // apenas palavras não-nulas e que iniciam com letras
            if (word.length() > 0 && Character.isAlphabetic(word.charAt(0))) {
                int val = trie.contains(word) ? (trie.get(word) + 1) : 1;
                if (val > maxFreq) maxFreq = val;
                trie.put(word, val);
            }

        }

        return trie;
    }
    
    /**
     * Gera um vetor de LinkedList a partir de uma árvore de prefixos com
     * as palavras do texto de entrada.
     * A lista na posição i do vetor contém as palavras com frequência i
     *
     * @param trie árvore de prefixos com as palavras do texto de entrada
     * @return vetor de LinkedList com as palavras do texto de entrada
     * onde a lista na posição i contém as palavras com frequência i
     */
    @SuppressWarnings("unchecked")
    public static LinkedList<String>[] sortByFrequency(TrieST<Integer> trie) {
        
        LinkedList<String>[] dicByFreq = (LinkedList<String>[]) new LinkedList[maxFreq + 1];

        for (int i = 0; i <= maxFreq; i++)
            dicByFreq[i] = new LinkedList<String>();

        for (String word : trie.keys()) {      
            int f = trie.get(word);
            dicByFreq[f].add(word);
        }

        return dicByFreq;
    }

    /**
     * Imprime as palavras do texto em ordem de frequência (em caso de
     * empate a palavra lexicográficamente menor antecede uma maior)
     * 
     * @param dic vetor de LinkedList onde a lista na posição i
     * contém as palavras com frequência i
     */
    public static void printByFrequency(LinkedList<String>[] dic) {
        
        for (int i = maxFreq; i >= 1; i--) {
        
            LinkedList<String> list = dic[i];
            ListIterator<String> it = list.listIterator();
        
            while (it.hasNext())
                StdOut.println(it.next() + " " + i);
        }
    }
}