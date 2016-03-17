//GUSTAVO H F SILVA - 9298260

import java.util.*;
import edu.princeton.cs.algs4.*;

public class WordCounter {

    public static String readText() {
        return StdIn.readAll();
    }

    public static String[] splitText(String text) {
        //regex para alfanumericos, espacos e caracteres com acentos
        text = text.replace("\n", " ");
        text = text.toLowerCase();
        text = text.replaceAll("[^A-Za-z0-9\u00C0-\u017F \\-]", "");
        //regex para transformar varios espacos em um só
        text = text.replaceAll("\\s+", " ");
        return text.split(" ");
    }

    public static List<WordEntry> countWords(String[] words) {
        List<WordEntry> wordlist = new ArrayList<>();
        boolean hasword;
        for(String word : words) {
            hasword = false;
            for(WordEntry entry : wordlist) {
                if(entry.getWord().equals(word)) {
                    entry.incrementCounter();
                    hasword = true;
                    break;
                }
            }
            if(!hasword)
                wordlist.add(new WordEntry(1, word));
        }
        Collections.sort(wordlist, new WordComparator());
        return wordlist;
    }

    public static void printWords(List<WordEntry> cwords) {
        for(WordEntry entry : cwords) {
            System.out.println(entry.getWord() + " "+entry.getCounter());
        }
    }

    public static void main(String[] args) {
        String text = WordCounter.readText();
        String[] words = WordCounter.splitText(text);
        List<WordEntry> cwords = WordCounter.countWords(words);
        WordCounter.printWords(cwords);
    }
}
