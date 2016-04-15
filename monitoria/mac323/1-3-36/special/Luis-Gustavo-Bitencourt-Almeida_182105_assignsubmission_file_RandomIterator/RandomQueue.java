import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * Autor: Luis Gustavo Bitencourt Almeida
 * Numero USP: 9298207
 */


import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomQueue<Item> implements Iterable<Item> {

  private Vector<Item> q;
  private static final int INITIAL_SIZE = 2;
  private int current;

  @SuppressWarnings("unchecked")
  public RandomQueue () {
    q = new Vector<Item> (INITIAL_SIZE);
  }

  public boolean isEmpty () {
    return q.isEmpty ();
  }

  public void enqueue (Item item) {
    if (q.size() == q.capacity ())
      resize (2*q.size ());

    q.add (item);
  }

  public Item dequeue () {
    if (isEmpty ())
      throw new NoSuchElementException ("Queue underflow.");

    int rd = StdRandom.uniform (0, q.size());
    Item tmp = q.get (0);
    q.set (0, q.get (rd));
    q.set (rd, tmp);
    return q.remove (0);
  }

  public Item sample () {
    int rd = StdRandom.uniform (0, q.size());
    return q.get (rd);
  }

  @SuppressWarnings ("unchecked")
  private void resize (int newSize) {
    q.ensureCapacity (newSize);
  }

  public Iterator<Item> iterator () {
    return new RandomQueueIterator ();
  }

  public int getCurrentPermutation () {
    return current;
  }

  private class RandomQueueIterator implements Iterator<Item> {

    private int permutation[][];
    private int index;
    private int size;
    private int pm;

    public RandomQueueIterator () {
      permutation = new int[factorial (q.size())][6];
      pm = 0;
      generatePermutations (new int[q.size()+1], 0, q.size() - 1, 1, 0);
      current = StdRandom.uniform (0, factorial(q.size()));
    }

    public boolean hasNext () {
      return index < size;
    }

    public Item next() {
      if (hasNext()) {
          return q.get(permutation[current][index++]);
      }
      throw new NoSuchElementException();
    }

    public void remove () {
      throw new UnsupportedOperationException ();
    }

    private void generatePermutations (int p[], int m, int n, int k, int mask) {
      if (k > n - m + 1) {
        for (int i = 1; i <= n - m + 1; i++)
          permutation[pm][i-1] = p[i];
        pm++;
        return;
      }
      for (int i = m; i <= n; i++) {
        if (((1 << i)&mask) == 0) {
          p[k] = i;
          generatePermutations (p, m, n, k+1, ((1 << i) | mask));
        }
      }
    }

    private boolean can (int p[], int k, int m) {
       for (int i = 1; i <= k; i++)
          if (m == p[i])
             return false;
       return true;
    }

    private int factorial (int n) {
      if (n == 0 || n == 1) return 1;
      return n * factorial (n - 1);
    }

  }

}
