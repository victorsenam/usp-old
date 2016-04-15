import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * @author: Eduardo Pinheiro
 * NUSP:    8936798
 */
import edu.princeton.cs.algs4.*;

public class TesteVisual
{
  public static void main(String[] args) {
    if (args.length < 2)
      throw new IllegalArgumentException("Not enough args");

    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    RandomQueue<Integer> queue = new RandomQueue<Integer>();
    Permutation p = new Permutation();

    for (int j = 1; j <= N; j++) {
      queue.enqueue(j); /* Coloca na queue todos os valores de 1 à N */
    }

    for (int i = 0; i < T; i++) {
      Integer k[] = new Integer[N];
      int l = 0;
      for (Integer t : queue) {
        k[l] = t; /* Usa o Random Iterator para criar uma permutação dos valores da queue */
        l++;
      }
      p.append(k); /* Adiciona ao objeto que guarda as permutações únicas com sua frequência de aparição */
    }
    Integer[] n = p.frequencies(); /* Recebe um vetor com a frequência de cada permutação gerada */

    /*for (int i = 0; i < n.length; i++) StdOut.println(n[i]);*/

    Histogram hist = new Histogram(n.length);
    for (int i = 0; i < n.length; i++) {
      while (n[i] > 0) {
        hist.addDataPoint(i); /* Adiciona ao histograma a frequência encontrada */
        n[i]--;
      }
    }
    hist.draw();
  }
}
