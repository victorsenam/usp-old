/******************************************************************************
*  Nome: Felipe Caetano Silva
*  USP: 9293223
*  Este Programa recebe N palavras por StdIn e gera uma lista em StdOut com 
*  todas as palavras que aparecem no texto em ordem decrescente de frequencia
*  e quando frequencias iguais em ordem lexicografica 
*  
******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class WordFrequencies {

    public static void main(String[] args) {
        
        String[] words = StdIn.readAllStrings();
        int N = words.length;
        for (int i = 0; i < N; i++) 
            words[i] = words[i].replaceAll("[^\\p{IsAlphabetic}\\-]", "").toLowerCase();
        Arrays.sort(words);
        Record[] records = new Record[N];
        String palavra = words[0];

        // Utilizei um contador de frequencias negativo para nao ter que 
        // ler de tras pra frente na hora de printar e assim perder a ordem
        // das palavras com frequencias iguais 
        int freq = -1;
        int j = 0;
        for (int i = 1; i < N; i++) {
            if (!words[i].equals(palavra)) {
                records[j++] = new Record(palavra, freq);
                palavra = words[i];
                freq = 0;
            }
            freq--;
        }
        records[j++] = new Record(palavra, freq);
        Arrays.sort(records, 0, j);

        for (int i = 0; i < j; i++) 
            if (records[i].name.length() > 0)
                StdOut.println(records[i]);
    }
}
