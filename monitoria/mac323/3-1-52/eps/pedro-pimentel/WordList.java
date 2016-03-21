/******************************************************************************
 *  Compilation:  javac WordFrequencies.java
 *  Execution:    java WordFrequencies
 *
 *  Problema 3.1.52 - Web 
 *  Pedro Sola Pimentel RM 9298079
 *
 ******************************************************************************/

import java.util.ArrayList; /* get, add, ArrayList<Word> */
import java.util.Collections; /* sort */
import java.util.Scanner; /* hasNext, next, useDelimiter */

/*  Nesta classe, esta implementada a lista de palavras <Word>
    junto aos metodos de para imprimir ou adicionar uma palavra */

public class WordList {

    private static int freq, index = 0;
    private static ArrayList<Word> list;
    private static Word wo;
    Scanner scanner = new Scanner(System.in).useDelimiter("[^\\p{L}\\p{Nd}\\-]+");

    /* Construtor */
    public WordList () {
        int i = 0;
        list = new ArrayList<Word>();
        while (scanner.hasNext()) {
            wo = new Word (scanner.next().toLowerCase());
            i ++;
            addWord (wo);
        }
    }

    /* Adiciona uma palavra w a lista de palavras list 
    e adiciona na frequencia desta palavra caso ela ja esteja
    presente na lista. */
    public void addWord (Word w) {
        for (int i = 0; i < index; i ++) {
            if (list.get(i).word.compareTo (w.word) == 0) {
                list.get(i).add();
                return;
            }
        }
        list.add(w);
        index ++;
    }

    /* Ordena a lista por frequencia e lexicograficamente. Dando
    mais importancia a frequencia das palavra. */
    public void sort () {
        Collections.sort (list);

    }

    /* Imprime a lista de palavras list */
    public static void print () {
        for (int i = 0; i < index; i++)
            System.out.printf (list.get(i).word + " " +
                list.get(i).getFreq() + "\n");

    }

}
