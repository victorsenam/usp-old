/******************************************************************************
 *  Nome:         Vinicius Perche de Toledo Agostini
 *  Número USP:   4367487
 *
 *  Este programa lista as palavras do texto de entrada. A lista está em
 *  ordem decrescente de frequência e segue o formato:
 *
 *  palavra1 frequencia1
 *  .
 *  .
 *  .
 *  palavraN frequenciaN
 *
 *  A organização das palavras e contagem de frequência é feita utilizando
 *  a biblioteca Algs4, com o uso de TrieST.java, implementação de uma árvore
 *  de prefixos que suporta os 256 caracteres ASCII.
 *
 *  Por que uma Trie?
 *      -> mantém as palavras ordenadas
 *      -> put() e contains() roda em tempo proporcional ao tamanho da palavra
 *         no pior caso.
 * 
 *  A ordenação por frequências é realizada com o uso de um vetor de LinkedList
 *  da biblioteca java.util; essa configuração me permite ter uma lista de strings
 *  para cada valor de frequência.
 *
 *  Por que um vetor de LinkedList<String>?
 *      -> add() roda em tempo constante para uma LinkedList
 *      -> com v[i].add(x) posso adicionar a string x diretamente na lista de
 *         palavras com frequência i
 *
 *  Exercício: 3.1.52 de http://introcs.cs.princeton.edu/java/31datatype/
 *
 ******************************************************************************/

import java.util.LinkedList;

import edu.princeton.cs.algs4.*;

public class WordFrequencies {
    
    /**
     * Lê o texto de entrada e separa as palavras
     *
     * @return vetor com todas as palavras do texto
     */
    public static String[] getWords() {
        String text = StdIn.readAll().toLowerCase();
        String[] words = text.split("[^a-zA-Z0-9À-ÿ-]");

        return words;
    }

    public static void main(String[] args) {
            
        int maxFreq = 0;

        String[] words = getWords();
        
        TrieST<Integer> dic = Index.generateTrie(words);
        LinkedList<String>[] dicByFreq = Index.sortByFrequency(dic);
        Index.printByFrequency(dicByFreq);
            
    }

}
