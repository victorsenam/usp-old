// Autor:  Luiz Felipe Moumdjian Girotto                                                   
// Numero USP: 8941189                                    
// Data: 2016-02-28   

import edu.princeton.cs.algs4.*;
public class WordFrequencies2 {
    
    public static WF[] expande (WF[] arr, int N) {
        WF[] arrnew = new WF[N*2];
        System.arraycopy( arr, 0, arrnew, 0, N);
        return arrnew;
    }
    
    public static void main(String[] args) {
        
        // Este segundo modulo pega as palavras ja ordenadas
        // pelo quicksort e conta sua frequencia, eliminando
        // repeticoes, e imprimindo a frequencia de uma
        // palavra "grudada" na palavra em si.
        
        int i, j;
        int N = 1;
        WF[] arr = new WF[N];
        
        for (i = 0; !StdIn.isEmpty(); i++) {
            if (i == N) {
                arr = expande (arr, N);
                N = N*2;
            }
                
            arr[i] =  new WF();
            arr[i].word = StdIn.readString().replaceAll("[^a-zçà-öø-ÿ-]", "");

        }
        
        for (i = 0, j = 1; j < N; j++) {
            if (arr[j] ==  null) break;
            if (arr[i].word.equals(arr[j].word)) {
                arr[i].freq++;
            }
            else {
                if (arr[i].word.equals("") == false) System.out.printf ("%d%s\n", arr[i].freq, arr[i].word);
                i = j;
            }
        }
        if (arr[i].word.equals("") == false) System.out.printf ("%d%s\n", arr[i].freq, arr[i].word);
        
    }
}
