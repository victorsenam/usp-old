import java.util.*;
import edu.princeton.cs.algs4.*;
//
// Nome: Patrick Abrahão Menani
// N USP: 8941050
// RandomQueue.java
//

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class RandomQueue<Item> implements Iterable<Item>{
   private Item[] q;
   private int N;

   public RandomQueue () {
      q = (Item[]) new Object[2];
      N = 0;
   }

   public boolean isEmpty () {
      return N == 0;
   }

   private void resize (int max) {
      assert max >= N;
      Item[] temp = (Item[]) new Object[max];
      for (int i = 0; i < N; i++) {
         temp[i] = q[i];
      }
      q  = temp;
   }

   public void enqueue (Item item) {
      if (N == q.length) resize (2*q.length);
      q[N++] = item;
   }

   public Item dequeue () {
      if (isEmpty ()) throw new NoSuchElementException ("Queue underflow");
      int r = StdRandom.uniform(N-1);
      Item randomItem = q[r];
      Item lastItem = q[N-1];
      q[N-1] = null;
      q[r] = lastItem;
      N--;
      if (N > 0 && N == q.length/4) resize (q.length/2);
      return randomItem;
   }

   public Item sample () {
      if (isEmpty ()) throw new NoSuchElementException("Queue underflow");
      int r = StdRandom.uniform(N-1);
      Item randomItem = q[r];
      return randomItem;
   }

   public Iterator<Item> iterator () {
      return new RandomQueueIterator ();
   }

   private class RandomQueueIterator implements Iterator<Item> {
      int i, c = N - 1;
      boolean[] j = new boolean [N];

      public RandomQueueIterator () {
         i = StdRandom.uniform(N - 1);
      }

      public boolean hasNext () {
         return c > 0;
      }

      public void remove () {
         throw new UnsupportedOperationException ();
      }

      public Item next () {
         if (!hasNext ()) throw new NoSuchElementException ();
         while(j[i]) {
            i = StdRandom.uniform(N - 1);
         }
         c--;
         j[i] = true;
         return q[i];
      }
   }
}

