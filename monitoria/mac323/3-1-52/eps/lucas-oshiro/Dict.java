//Nome: Lucas Seiki Oshiro
//Numero USP: 9298228

import edu.princeton.cs.algs4.*;

/* Esta classe é um dicionario com as palavras do texto, e suas respetivas
   frequencias. As palavras ficam armazenadas numa árvore binária de busca,
   criada a partir de sua ordem lexicográfica.

   OBS: Os algoritmos usados nas árvores binárias e o mergesort foram baseados 
   nos do site do professor Paulo Feofiloff, aprendidos em MAC0121. */
public class Dict {
    private Word r;
    private int n;

    /* Construtor */
    public Dict () {
        r = null;
        n = 0;
    }

    /* Adiciona a Word w ao dicionario. Caso exista uma Word com cuja palavra
       seja igual à de 2, sua frequencia é incrementada, e w não é 
       adicionada. */
    private void addWord (Word w) {
        Word f, p;

        if (w == null) return;
        if (r == null) {
            r = w;
            n++;
            return;
        }

        f = r;
        p = null;

        while (f != null) {
            int cmp;
            p = f;
            cmp = f.compareLex (w);
            if (cmp == 0) {
                f.incFreq ();
                return;
            }
            else if (cmp > 0) f = f.left;
            else f = f.right;
        }

        if (p.compareLex (w) > 0) p.left = w;
        else p.right = w;

        n++;
    }

    /* Gera uma String com cada palavra contida em ws e sua respectiva
       frequencia em cada linha */
    private String createString (Word[] ws) {
        String s = "";
        String s2;
        for (int i = n - 1; i >= 0; i--) s = s + ws[i].toString () + '\n';
        return s;
    }

    /* Copia os objetos da classe Word da arvore binaria para o vetor ws, em
       ordem lexicografica decrescente, recursivamente. */
    private int wordsLex (Word r, Word[] ws, int i) {
        if (r == null) return i;
        i = wordsLex (r.right, ws, i);
        ws[i++] = r;
        i = wordsLex (r.left, ws, i);
        return i;
    }

    /* Devolve um vetor de Word com os objetos da arvore binaria, em ordem
       lexicográgica. */
    private Word[] wordsLex () {
        Word[] ws = new Word[n];
        int i = wordsLex (r, ws, 0);
        mergesort (0, n, ws);
        return ws;
    }

    /* Recebe os vetores v[p..q-1] e v[q..r-1] e rearranja v[p..r-1] em ordem
       crescente. */
    private void interc (int p, int q, int r, Word[] v) {
        int i = p;
        int j = q;
        int k = 0;

        Word[] w = new Word[r - p];

        while (i < q && j < r) {
            if (v[i].compareFreq (v[j]) <= 0) w[k++] = v[i++];
            else w[k++] = v[j++];
        }

        while (i < q) w[k++] = v[i++];
        while (j < r) w[k++] = v[j++];
        for (i = p; i < r; i++) v[i] = w[i - p];
    }

    /* Ordena v[p..r], previamente ordenado em ordem lexicográfica, em ordem
       crescente de frequencia. O algoritmo é estável, portanto, a ordem
       lexicográfica servirá como desempate. */
    private void mergesort (int p, int r, Word[] v) {
        if (p < r - 1) {
            int q = (p + r) / 2;
            mergesort (p, q, v);
            mergesort (q, r, v);
            interc (p, q, r, v);
        }
    }

    /* Adiciona a palavra contida na String s ao dicionario. */
    public void addWord (String s) {
        s = s.toLowerCase ();
        if (s.isEmpty ()) return;
        Word w = new Word (s);
        addWord (w);
    }

    /* Devolve uma String, com cada palavra do dicionario em uma linha, e suas
       respectivas frequencias. */
    public String toString () {
        String s = createString (wordsLex ());
        return s;
    }

}
