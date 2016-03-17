/* Caio Ramos - NUSP 9292991 - IME USP 2016
    Progama que recebe uma serie e tokens pela entrada padrao e imprime as repeticoes
    em ordem decrescente.
*/

import edu.princeton.cs.algs4.*;
import java.util.Arrays;


public class WordFrequencies {
    public static void main(String args[]) {
        //le os tokens gerados por Read.java
        String[] a = StdIn.readAllStrings();
        for (int i = 0; i < a.length; i++)
            a[i] = a[i].toLowerCase();
        //ordena os tokens
        Arrays.sort(a);
        //faz a contagem dos tokens
        Pair[] p = Count.count(a);
        //ordena os tokens segundo o numero de aparicoes
        Pair.sort(p, p.length);
        //faz a impressao
        for(int i = 0; i < p.length; i++) {
            StdOut.println(p[i]);
        }

    }
}
