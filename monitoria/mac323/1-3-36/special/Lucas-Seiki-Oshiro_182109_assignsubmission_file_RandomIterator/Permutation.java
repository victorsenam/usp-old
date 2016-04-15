import java.util.*;
import edu.princeton.cs.algs4.*;
//Nome: Lucas Seiki Oshiro
//Número USP: 9298228

import edu.princeton.cs.algs4.*;


/* Armazena uma permutação de numeros. */
public class Permutation {
    int[] seq;

    /* Verifica se é igual a p2, a partir do elemento i. */
    private boolean isEqual (Permutation p2, int i) {
        if (i >= seq.length && i >= p2.seq.length) return true;
        if (seq[i] == p2.seq[i]) return isEqual (p2, i + 1);
        return false;
    }

    /* Constroi uma nova Permutation com N elementos. */
    public Permutation (int N) {
        seq = new int[N];
    }

    /* Verifica se é igual a p2. */
    public boolean isEqual (Permutation p2) {
        return isEqual (p2, 0);
    }

    /* Devolve uma string com os elementos da Permutation, separados por
       espaço. */
    public String toString () {
        String s = "";
        for (int x : seq) s += x + " ";
        return s;
    }

    /* Apenas para teste. */
    public static void main (String[] args) {
        Permutation p1 = new Permutation (3);
        Permutation p2 = new Permutation (3);

        for (int i = 0; i < 3; i++) p1.seq[i] = p2.seq[i] = i;
        StdOut.println ("p1: " + p1 + " p2: " + p2);
        StdOut.println (p1.isEqual (p2));

        StdOut.println ();

        p2.seq[1] = 2;
        StdOut.println ("p1: " + p1 + " p2: " + p2);
        StdOut.println (p1.isEqual (p2));
    }
}
