import java.util.*;
import edu.princeton.cs.algs4.*;
public class RandomQueue<Item> {
   private Item[] a;         // array of items
   private int N;            // number of elements on stack

   // Initialize an empity Queue
   public RandomQueue(){
      a = (Item[]) new Object[2];
      N = 0;
   }

   // return true if queue is empity
   public boolean isEmpty() {
        return N == 0;
   }

   // resize the underlying array holding the elements
   private void resize(int capacity) {
      assert capacity >= N;
      Item[] temp = (Item[]) new Object[capacity];
      for (int i = 0; i < N; i++) {
         temp[i] = a[i];
      }
      a = temp;
    }

   // Add an item
   public void enqueue(Item item){
      if (N == a.length) resize(2*a.length);    // double size of array if necessary
      a[N++] = item;                            // add item   
   }

   // Remove and return a random item
   public Item dequeue(){
      int remove = StdRandom.uniform(N);

      Item item = a[remove];
      a[remove] = a[N-1];
      a[N-1] = null;

      N--;

      // shrink size of array if necessary
      if (N > 0 && N == a.length/4) resize(a.length/2);

      return item;
   }

   // return a random item, but do not remove
   public Item sample(){
      int remove = StdRandom.uniform(N);
      
      return a[remove];
   }


   // test client
   public static void main(String[] args){
      RandomQueue<Integer> queue = new RandomQueue<Integer>();

      for (int i = 0; i < 10; i++){
         queue.enqueue(i);
      }

      for (int i = 0; i < 10; i++){
         StdOut.print(queue.dequeue() + " ");
      }
      StdOut.print("\n");
   }
}
