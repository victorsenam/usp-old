/************
 * Nome: Matheus de Mello Santos Oliveira
 * NUSP: 8642821
 * ***************/

import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordFrequencies {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Word> sol = new ArrayList<Word>();
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            String[] w = line.split("[^0-9\\p{L}-]");
            int N = w.length;
            for (int i = 0; i < N; i++)
                if (!w[i].isEmpty())
                    list.add(w[i].toLowerCase());
        }
        Collections.sort(list);
        int[] f = new int[list.size()];
        for (int i = 0; i < list.size()-1; i++) {
            String x = list.get(i);
            String y = list.get(i+1);
            if (x.equals(y)) {
                f[i]++;
                list.remove(i+1);
                i--;
            }
        }
        int i = 0;
        for (String x: list) {
            Word a = new Word();
            a.w = x;
            a.f = f[i++]+1;
            sol.add(a);
        }
        Collections.sort(sol, new Comparator<Word>() {
            public int compare(Word x, Word y) {
                if (x.f > y.f) return -1;
                if (x.f < y.f) return 1;
                return 0;}});
        for (Word x: sol)
            StdOut.println(x);
    }
}

