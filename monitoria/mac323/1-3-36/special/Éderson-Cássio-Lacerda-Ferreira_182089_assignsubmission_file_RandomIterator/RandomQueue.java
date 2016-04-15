import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * NOME:   Éderson Cássio Lacerda Ferreira
 * N. USP: 9297975
 */
 
import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {

   private Item[] itens;
   private int n;
   
   public RandomQueue() {
      itens = (Item[]) new Object[128];
      n = 0;
   }

   public boolean isEmpty() {
      return n == 0;
   }
   
   public void enqueue(Item item) {
      if (n == itens.length) realloc(itens.length * 2);
      itens[n++] = item;
   }
   
   public Item dequeue() {
      int pos = StdRandom.uniform(n);
      Item i = itens[pos];
      Item l = itens[--n];
      itens[n] = null;
      itens[pos] = l;
      if (n > 0 && n == itens.length / 4) realloc(itens.length / 2);
      return i;
   }
   
   public Item sample() {
      return itens[StdRandom.uniform(n)];
   }
   
   private void realloc(int size) {
      Item[] novo = (Item[]) new Object[size];
      for (int i = 0; i < n; i++) novo[i] = itens[i];
      itens = novo;
   }
   
   public Iterator<Item> iterator() {
      return new RandomIterator();
   }
   
   private class RandomIterator implements Iterator<Item> {
   
      private int count;
      
      public RandomIterator() {
         count = 0;
         shuffle();
      }
   
      public boolean hasNext() {
         return count < n;
      }
      
      public void remove() {
         /* proibido! */
      }
      
      public Item next() {
         return itens[count++];
      }
      
      private void shuffle() {
        for (int i = 0; i < n; i++) {
            int pos = i + (int) (Math.random() * (n - i));
            Item s = itens[pos];
            itens[pos] = itens[i];
            itens[i] = s;
        }
      }
      
   }

}
