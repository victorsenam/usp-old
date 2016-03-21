import edu.princeton.cs.algs4.StdIn;
public class WordFrequencies {
    private String word;
    private int freq;
    
    public WordFrequencies (String palavra, int frequencia) {
        /*cria uma "struct" - não é bem assim, mas... - que guarda uma palavra
        e um inteiro que conta a frequência que essa palavra apareceu no texto*/
        word = palavra; freq = frequencia;
    }
    
    public static WordFrequencies[] CreateArray (int size) {
        /*cria um um vetor dessa "struct"*/
        WordFrequencies[] a = new WordFrequencies [size];
        return a;
    }
    
    public static int searchword (WordFrequencies a[], String palavra, int total) {
        /*procura pra ver se uma palavra do texto já foi armazenada na lista de
        palavras*/
        int i;
        for (i=0; i<total; i++) {
            if (a[i].word.equals(palavra)==true) { return i; }
        }
        return -1;
    }
    
    public static void CopyArray (WordFrequencies a[], WordFrequencies b[], int total) {
        /*copia o vetor b para o vetor a*/
        int i;
        for (i=0; i<total; i++) { a[i] = new WordFrequencies (b[i].word, b[i].freq); }
    }
    
    private static void intercala_text (int p, int q, int r, WordFrequencies v[]) {
        /*intercaladora de um mergesort tendo como base a ordem lexicográfica da
        lista de palavras do texto*/
        int i, j, k;
        WordFrequencies[] w = new WordFrequencies [r-p];
        i=p; j=q; k=0;
        while (i<q && j<r) {
            if (v[i].word.compareTo(v[j].word) < 0) {
                w[k++] = new WordFrequencies (v[i].word, v[i].freq); i++;
            }
            else {
                w[k++] = new WordFrequencies (v[j].word, v[j].freq); j++;
            }
        }
        while (i<q) { w[k++] = new WordFrequencies (v[i].word, v[i].freq); i++; }
        while (j<r) { w[k++] = new WordFrequencies (v[j].word, v[j].freq); j++; }
        for (i=p; i<r; i++) {
            v[i] = new WordFrequencies (w[i-p].word, w[i-p].freq);
        }
    }
    
    private static void mergesort_private_text (int p, int r, WordFrequencies v[]) {
        /*mergesort que ordena a lista de palavras lexicograficamente*/
        if (p<r-1) {
            int q=(p+r)/2;
            mergesort_private_text (p, q, v);
            mergesort_private_text (q, r, v);
            intercala_text (p, q, r, v);
        }
    }
    
    private static void intercala_numbers (int p, int q, int r, WordFrequencies v[]) {
        /*intercalador de um mergesort que ordena as frequências em ordem decrescente*/
        int i, j, k;
        WordFrequencies[] w = new WordFrequencies [r-p];
        i=p; j=q; k=0;
        while (i < q && j < r) {
            if (v[i].freq >= v[j].freq) {
                w[k++] = new WordFrequencies (v[i].word, v[i].freq); i++;
            }
            else {
                w[k++] = new WordFrequencies (v[j].word, v[j].freq); j++;
            }
        }
        while (i<q) { w[k++] = new WordFrequencies (v[i].word, v[i].freq); i++; }
        while (j<r) { w[k++] = new WordFrequencies (v[j].word, v[j].freq); j++; }
        for (i=p; i<r; i++) {
            v[i] = new WordFrequencies (w[i-p].word, w[i-p].freq);
        }
    }
    
    private static void mergesort_private_numbers (int p, int r, WordFrequencies v[]) {
        /*mergesort que ordena as frequências de forma decrescente*/
        if (p<r-1) {
            int q=(p+r)/2;
            mergesort_private_numbers (p, q, v);
            mergesort_private_numbers (q, r, v);
            intercala_numbers (p, q, r, v);
        }
    }
    
    public static void mergesort (WordFrequencies v[], int n) {
        mergesort_private_text (0, n, v);
        mergesort_private_numbers (0, n, v);
    }
    
    public static void main (String args[]) {
        int i, j=0, aux, size = 8;

        WordFrequencies[] wordlist = CreateArray (size);
        WordFrequencies[] auxlist;
        String[] palavras = StdIn.readAllStrings();
        
        for(i=0; i<palavras.length; i++) { palavras[i] = palavras[i].toLowerCase();}
        
        if (palavras.length>0) {
            wordlist[j++] = new WordFrequencies (palavras[0], 1);
        }
        for (i=1; i<palavras.length; i++) {
            if (j==size) {
                auxlist = CreateArray (size*2);
                CopyArray (auxlist, wordlist, size);
                wordlist=auxlist; size=size*2;
            }
            aux=searchword (wordlist, palavras[i], j);
            if (aux!=-1) { wordlist[aux].freq = wordlist[aux].freq+1;}
            else { wordlist[j++] = new WordFrequencies (palavras[i], 1); }
        }
        
        mergesort (wordlist, j);
        
        for (i=0; i<size; i++) {
            System.out.printf ("%s %d\n", wordlist[i].word, wordlist[i].freq);
        }
    }
}

