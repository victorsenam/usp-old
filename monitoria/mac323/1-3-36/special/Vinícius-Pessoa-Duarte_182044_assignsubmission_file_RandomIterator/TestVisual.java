import java.util.*;
import edu.princeton.cs.algs4.*;
/* Nome: Vinicius Pessoa Duarte             */
/* Numero USP: 8941043                      */
/* Disciplina: MAC-0323                     */
/* Exercicio: Random Iterator/1.3.36        */

import edu.princeton.cs.algs4.*;

// Cliente que plota a distribuicao das permutacoes de um dado N

public class TestVisual {

    // Cria uma lista com todas as permutacoes geradas pelo random iterator
    public static void permutations (String[] index, int N, int T) {
        for (int t = 0; t < T; ++t) {
            RandomQueue<String> queue = new RandomQueue<String> ();
            
            for (int n = 1; n <= N; ++n)
                queue.enqueue (Integer.toString (n));       
            
            index[t] = "";

            for (String item : queue)
                index[t] = index[t].concat (item);
        }       
    }

    // Ordena as permutacoes por frequencia decrescente
    public static int order (String[] matrix, int[] count) {
        WordFrequencies.joinEqualStr (matrix);
        int pos = WordFrequencies.trimCount (matrix, count);
        WordFrequencies.sortDescending (matrix, count, pos);
        return pos;     
    }

    // Plota o histograma da frequencia das permutacoes
    public static void drawFreq (int pos, int[] count) {
        Histogram histogram = new Histogram(pos+1); 
        
        for (int t = 0; t < pos; ++t) {
            for (int i = 0; i < count[t]; ++i)
                histogram.addDataPoint(t);
        }

        StdDraw.setCanvasSize(500, 500);
        histogram.draw();
    }
 
    // Metodo principal
    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        int T = Integer.parseInt (args[1]);
        String[] matrix = new String[T];

        permutations (matrix, N, T);

        int[] count = new int[T];

        int pos = order (matrix, count);        

        drawFreq (pos, count);
    }
}