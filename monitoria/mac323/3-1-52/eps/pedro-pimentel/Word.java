/******************************************************************************
 *  Compilation:  javac WordFrequencies.java
 *  Execution:    java WordFrequencies
 *
 *  Problema 3.1.52 - Web 
 *  Pedro Sola Pimentel RM 9298079
 *
 ******************************************************************************/

import java.lang.Comparable; /* Comparable<Word> */

public class Word implements Comparable<Word> {

    private int freq;
    public final String word;

    /* Construtor */
    public Word (String w) {
        freq = 0;
        word = new String (w.toLowerCase());
        add ();
    }

    public void add () {
        freq ++;
    }
    public int getFreq() {
        return freq;
    }

    /* Especifica como o metodo sort() deve agir para ordenar
    a lista de palavras */

    public int compareTo(Word w) {
        if(this.freq > w.freq) 
            return -1;
        else if(this.freq < w.freq)
            return 1;

        return this.word.compareTo(w.word);
    }

}