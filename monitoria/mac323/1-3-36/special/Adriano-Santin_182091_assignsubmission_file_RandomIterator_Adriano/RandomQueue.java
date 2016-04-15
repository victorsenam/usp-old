import java.util.*;
import edu.princeton.cs.algs4.*;
/*///////////////////////////////////////////////////////////////////////
//                                                                     //
// Nome: Adriano Tetsuaki Ogawa Santin                                 //
// Número USP: 8944387                                                 //
// Creative Problem 1.3.36 (Random Iterator)                           //
//                                                                     // 
///////////////////////////////////////////////////////////////////////*/

import java.util.Iterator;
import java.util.NoSuchElementException;

// Clsse de fila aleatória.
public class RandomQueue<Item> implements Iterable<Item> {

   private Item[] q;
   private int N;
   private int first;
   private int last;

   public RandomQueue () {
      q = (Item[]) new Object[2];
      N = 0;
      first = 0;
      last = 0;
   }

   // Checa se a fila está vazia.
   public boolean isEmpty () {
      return N == 0;
   }

   // Muda o tamanho do vetor para max.
   private void resize (int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = N;
    }

   // Coloca um novo item na fila.
   public void enqueue (Item item) {
      if (N == q.length)
         resize (2 * q.length);
      q[last++] = item;
      if (last == q.length)
         last = 0;
      N++;
   }

   // Retira um item aleatório da fila e retorna ele.
   public Item dequeue () {
      if (isEmpty ()) throw new NoSuchElementException ("Queue underflow");
      int i = (int) (Math.random () * N);
      Item item = q[first + i];
      q[first + i] = q[first];
      q[first++] = null;
      N--;
      if (first == q.length)
         first = 0;
      if (N > 0 && N == q.length/4)
         resize (q.length/2);
      return item;
   }

   // Devolve um item aleatório da fila mas não o retira dela.
   public Item sample () {
      if (isEmpty ()) throw new NoSuchElementException ("Queue underflow");
      int i = (int) (Math.random () * N);
      return q[first + i];
   }

   // Iterador.
   public Iterator<Item> iterator () {
      return new ArrayIterator();
   }
   
   private class ArrayIterator implements Iterator<Item> {
      private int size = N;
      
      public boolean hasNext () {
         return size > 0;
      }
      
      public void remove () {
         throw new UnsupportedOperationException ();
      }
      
      public Item next () {
         if (!hasNext()) throw new NoSuchElementException();
         int i = (int) (Math.random () * size);
         Item item = q[(i + first) % q.length];
         q[(i + first) % q.length] = q[size - 1];
         q[size - 1] = item;
         size--;

         StdOut.println("first:: " +first);
         return item;
      }
   }

   public static void main (String args[]) {
        RandomQueue<Integer> q = new RandomQueue<Integer>();

        q.enqueue(10);
        q.enqueue(11);
        Iterator<Integer> it = q.iterator();
        StdOut.println(it.next());
        StdOut.println("has " + it.hasNext());
        StdOut.println("remove " + q.dequeue());
        StdOut.println("has " + it.hasNext());
        StdOut.println(it.next());
   }
}
