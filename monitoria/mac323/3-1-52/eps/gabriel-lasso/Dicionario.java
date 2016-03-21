/*
   Exercício 52 de http://introcs.cs.princeton.edu/java/31datatype/

   Implementa a classe do dicionario, que tenta sempre manter as palavras em ordem lexicográfica e conta quantas repeticoes houveram

   Gabriel Kuribara Lasso
   NUSP 9298016
 */
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Dicionario {
    private int N;
    private int tamanho;
    private Palavra[] palavras;

    // Insere em ordem lexicográfica mantendo os caracteres em lower case
    public void insere (String pal) {
        int b = buscaBinaria (pal);
        if (tamanho == N) aumenta();
        if (b >= tamanho) {
            palavras[b] = new Palavra (pal.toLowerCase());
            tamanho++;
        }
        else {
            if (pal.equals (palavras[b].pal)) palavras[b].count++;
            else {
                for (int i = tamanho; i > b; i--)
                    palavras[i] = palavras[i-1];
                palavras[b] = new Palavra (pal.toLowerCase());
                tamanho++;
            }
        }
    }

    private void aumenta () {
        Palavra[] aux = palavras;
        palavras = new Palavra[2*N];
        for (int i = 0; i < N; i++) {
            palavras[i] = aux[i];
        }
        N = 2*N;
    }

    private int buscaBinaria (String pal) {
        int e, m, d; 
        e = -1; d = tamanho;
        while (e < d-1) {  
            m = (e + d)/2;
            if (palavras[m].pal.compareTo(pal) < 0) e = m;
            else d = m; 
        }
        return d;
    }

    // Imprime o dicionario em ordem decrescente de frequência
    public void freqPrint () {
        Palavra[] f = new Palavra[tamanho];
        for (int i = 0; i < tamanho; i++)
            f[i] = palavras[i];
        freqSort (f);
        for (int i = 0; i < tamanho; i++)
            StdOut.printf ("%s %d\n", f[i].pal, f[i].count);
    }

    private void freqSort (Palavra[] p){
        freqSort (0, tamanho, p);
    }

    private void freqSort (int p, int r, Palavra[] v) {
        if (p < r-1) {
            int q = (p + r)/2;
            freqSort (p, q, v);
            freqSort (q, r, v);
            merge (p, q, r, v);
        }
    }

    private void merge (int p, int q, int r, Palavra[] v) {
        Palavra[] w = new Palavra[r-p];
        int i, j, k;

        i = p; j = q;
        k = 0;

        while (i < q && j < r) {
            if (v[i].count >= v[j].count)  w[k++] = v[i++];
            else  w[k++] = v[j++];
        }
        while (i < q)  w[k++] = v[i++];
        while (j < r)  w[k++] = v[j++];
        for (i = p; i < r; ++i)  v[i] = w[i-p];
    }

    public Dicionario () {
        N = 16;
        palavras = new Palavra[N];
        tamanho = 0;
    }
}
