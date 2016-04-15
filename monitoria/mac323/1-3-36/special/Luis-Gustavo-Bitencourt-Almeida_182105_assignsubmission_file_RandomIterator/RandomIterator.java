import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * Autor: Luis Gustavo Bitencourt Almeida
 * Numero USP: 9298207
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Iterator;

public class RandomIterator {

  public static int[] occurrences;
  public static int max;

  public static int factorial (int n) {
    if (n == 0 || n == 1) return 1;
    return n * factorial (n - 1);
  }

  public static void main (String[] args) {
    int n = Integer.parseInt (args[0]);
    int t = Integer.parseInt (args[1]);
    occurrences = new int[factorial(n)];
    max = 0;
    for (int i = 0; i < t; i++) {
      RandomQueue<Integer> rq = new RandomQueue<Integer>();
      for (int j = 1; j <= n; j++) rq.enqueue (j);
      Iterator it = rq.iterator ();
      occurrences[rq.getCurrentPermutation()]++;
      max = (occurrences[rq.getCurrentPermutation()] > max) ? occurrences[rq.getCurrentPermutation()] : max;
    }

    StdDraw.setPenRadius(.025);
    StdDraw.setXscale(0, factorial(n));
    StdDraw.setYscale(0, max + 100);
    for (int i = 0; i < factorial(n); i++)
      StdDraw.line(i, 0, i, occurrences[i]);
  }

}
