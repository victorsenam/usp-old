//GUSTAVO H F SILVA - 9298260

import java.util.*;
import edu.princeton.cs.algs4.*;
public class WordComparator implements Comparator<WordEntry> {
    @Override
    public int compare(WordEntry o1, WordEntry o2) {
        return o2.getCounter() - o1.getCounter();
    }
}
