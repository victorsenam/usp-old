/*  Mac0323
 *  Gil Santaella Santos 6883598
 *
 * javac WordFrequencies.java
 * java WordFrequencies < input.txt
 *
 */

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.*;

// Recebe um texto na entrada padrao e identifica as palavras
public class WordFrequencies {

    class Word implements Comparable<Word> {
        int count = 0;
        String word;

        public int compareTo(Word other) {
            if (this.count < other.count)
                return -1;
            else if (this.count > other.count)
                return 1;
            else if (this.word.length() < other.word.length())
                return 1;
            return 0;
            
        }
    }

    private static ArrayList<Word> table;

    // Verifica se a tabela contem a String s, retorna o indice
    // caso contenha e -1 caso contrario
    public static int TableContains(String s) {
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).word.equals(s))
                return i;
        }
        return -1;
    }

    // Confere se a string esta na tabela, se não estiver, adiciona,
    // se estiver, soma um no contador da palavra
    private static void AddToTable(String s) {
        int index = TableContains(s);

        if (table.isEmpty() || index == -1) {
            Word nova = new WordFrequencies().new Word();
            nova.word = s;
            nova.count += 1;
            table.add(nova);
        } else
            table.get(index).count += 1;
    }

    // Usa expressao regular para ignorar virgulas e pontos
    // que estejam na string
    private static String CheckString(String s) {
        String pattern = "([a-z]+-?[a-z]*)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);
        if (m.find()) {
            s = m.group();
            return s;
        }
        return "";
    }

    public static void main(String[] args) {
        table = new ArrayList<Word> ();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString().toLowerCase();
            s = CheckString(s);
            if (!s.isEmpty())
                AddToTable(s);
        }

        StdOut.println("Numero de palavras: " + table.size());
        
        Collections.sort(table);
        Collections.reverse(table);

        for (int i = 0; i < table.size(); i++) {
            StdOut.println(table.get(i).word + " " + table.get(i).count);
        }
    }
}