/******************************************************************************
 *  Nome: Luís Felipe de Melo Costa Silva
 *  Número USP: 9297961
 *
 *  Arquivo:       WordFrequencies.java
 *  Compilação:    javac-algs4 WordFrequencies.java
 *  Execução:      java-algs4 WordFrequencies.java < test.java
 *  Dependências:  StdIn.java StdOut.java Merge.java
 * 
 *  Lê um texto da entrada padrão e devolve na saída padrão a lista 
 *  das palavras em ordem de frequência. 
 *
 ******************************************************************************/
import java.util.*;
import edu.princeton.cs.algs4.*;

public class WordFrequencies {

    public static int findGreater (int[] a, int N) {
        int greater = 0;
        for (int i = 0; i < N; i++) 
            if (a[i] > greater) greater = a[i];
        return greater;
    }

    public static void main (String[] args) {

        // Lê as palavras, coloca todos os caracteres em minúsculo e separa as 
        // palavras.
        String s = StdIn.readAll ();
        s = s.toLowerCase ();
        String[] t = s.split ("[^a-zà-õú-]");

        // Ordena as palavras.
        Merge.sort (t);

        // Pula as strings vazias.
        int start = 0, end = t.length;

        for (int i = 0; i < t.length; i++)
            if (t[i].equals("")) {
                start++;
            }

        // Calculando o número de palavras distintas.
        int N = 1;
        for (int i = start; i < end; i++) {
            if (i+1 < end && !(t[i].equals(t[i+1])))
                N++;
        }

        // Contando as palavras.
        String[] frequency = new String [N];
        int[] count = new int[N];

        int j = 1, k = 0;

        for (int i = start; i < end; i++) {
            if (i+1 < end && t[i].equals(t[i+1]))
                j++;
            else {
                frequency[k] = t[i];
                count[k] = j;
                k++;
                j = 1;
            }
        }        
        
        // Encontrando a maior frequência.
        int greater = findGreater (count, N);

        // Imprimindo os resultados.
        for (int i = greater; i > 0; i--) {
            j = 0;
            while (j < N) {
                if (i == count[j])
                    StdOut.println (frequency[j] + " " + count[j]);
                j++;
            }
        } 
    }    
}
