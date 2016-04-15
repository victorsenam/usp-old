import java.util.*;
import edu.princeton.cs.algs4.*;
//Nome: Lucas Seiki Oshiro
//Número USP: 9298228

import edu.princeton.cs.algs4.*;

public class TesteVisual {

    /* Ordena o vetor v de permutações de N numeros em ordem crescente. O
       algoritmo é baseado no algoritmo de ordenação digital do site do prof.
       Paulo Feofiloff. */
    public static void sort (Permutation[] v, int N) {
        int R = N + 1;
        int W = v[0].seq.length;
        int n = v.length;
        int[] fp = new int[R + 1];
        Permutation[] aux = new Permutation[n];
        
        for (int d = W - 1; d >= 0; d--) {
            for (int r = 0; r < R; r++) fp[r] = 0;
                
            for (int i = 0; i < n; i++) {
                int r = v[i].seq[d];
                fp[r + 1] += 1;
            }

            for (int r = 1; r <= R; r++)
                fp[r] += fp[r - 1];

            for (int i = 0; i < n; i++) {
                int r = v[i].seq[d];
                aux[fp[r]] = v[i];
                fp[r]++;
            }

            for (int i = 0; i < n; i++)
                v[i] = aux[i];
        }
    }

    /* Conta as repetições de cada permutação do vetor v, e devolve um vetor
     com o número de repetições. */
    public static int[] freqs (Permutation[] v) {
        int[] f = new int[v.length];
        int cont = 0;
        Permutation p = v[0];
        f[0]++;

        for (int i = 1; i < v.length; i++) {
            if (!p.isEqual (v[i])) {
                cont++;
                p = v[i];
            }
            f[cont]++;
        }
        cont++;
        
        int[] f2 = new int[cont];
        for (int i = 0; i < cont; i++) f2[i] = f[i];
        
        return f2;
    }

    /* Main */
    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        int T = Integer.parseInt (args[1]);
        
        RandomQueue <Integer> rq = new RandomQueue <Integer> ();
        Permutation[] perms = new Permutation[T];

        for (int i = 1; i <= N; i++) rq.enqueue (i);
        
        for (int i = 0; i < T; i++) {
            perms[i] = new Permutation (N);
            int j = 0;
            for (int x : rq) perms[i].seq[j++] = x;
        }

        sort (perms, N);
        int[] f = freqs (perms);
        Histogram hist = new Histogram (f.length);
        
        for (int i = 0; i < f.length; i++)
            for (int j = 0; j < f[i]; j++)
                hist.addDataPoint (i);
        

        hist.draw ();
    }
}
