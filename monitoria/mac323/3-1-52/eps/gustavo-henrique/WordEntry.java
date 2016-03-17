//GUSTAVO H F SILVA - 9298260
import java.util.*;
import edu.princeton.cs.algs4.*;

public class WordEntry {
    private int counter;
    private String word;

    public WordEntry(int counter, String word) {
        this.counter = counter;
        this.word = word;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCounter() {
        return this.counter;
    }

    public String getWord() {
        return this.word;
    }

    public void incrementCounter() {
        this.counter++;
    }
}
