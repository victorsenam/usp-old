/******************************************************************************
 *  Nome: Andre Victor dos Santos Nakazawa
 *  NUSP: 9298336
 *
 *  Compilation:  javac WordFrequencies.java
 *  Execution:    java WordFrequencies < input.txt
 *
 *  Read a text of standard input and prints out a list of the words in
 *  decreasing order of frequency.
 *
 ******************************************************************************/

import java.util.ArrayList;
import java.util.Collections;

public class WordFrequencies {
    public static ArrayList<String> dictionary = new ArrayList<>();
    public static ArrayList<Integer> counter = new ArrayList<>();

    public static void readInput() {
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString().toLowerCase();
            word = word.replaceAll("[\\p{Punct}\\s]", "");
            int index = Collections.binarySearch(dictionary, word);
            if (index >= 0)
                counter.set(index, counter.get(index) + 1);
            else
                insertWord(index, word);
        }
    }

    public static void insertWord(int index, String word) {
        if (index == -1) {
            dictionary.add(0, word);
            counter.add(0, 1);
        } else if (-index - 1 < dictionary.size()) {
            dictionary.add(-index - 1, word);
            counter.add(-index - 1, 1);
        } else {
            dictionary.add(word);
            counter.add(1);
        }
    }

    public static void writeDictionary() {
        for (int i = 0; i < dictionary.size(); i++)
            StdOut.println(dictionary.get(i) + " " + counter.get(i));
    }

    public static void main(String[] args) {
        readInput();
        writeDictionary();
    }

}

