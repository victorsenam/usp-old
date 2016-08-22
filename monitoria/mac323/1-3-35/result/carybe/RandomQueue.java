import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
/*************************************************************************
 *  Nome: Carybé Gonçalves Silva
 *  Nº USP: 8033961
 *
 *  Compilation:  javac RandomQueue.java
 *  Execution:    java RandomQueue
 *  
 *  Random Queue implementation with an array clearly based on "DoblingQueue". 
 *  Resizes by doubling and halving and shuffles at dequeueing.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomQueue<Item> implements Iterable<Item> {
	private Item[] q;            // queue elements
	private int N = 0;           // number of elements on queue
	private int first = 0;       // index of first element of queue
	private int last  = 0;       // index of next available slot

	// cast needed since no generic array creation in Java
	public RandomQueue() {
		q = (Item[]) new Object[2];
	}

	public boolean isEmpty() { return N == 0;    }
	public int size()        { return N;         }

	// resize the underlying array
	private void resize(int max) {
		assert(max >= N);
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) temp[i] = q[(first + i) % q.length];
		q = temp;
		first = 0;
		last  = N;
	}

	public void enqueue(Item item) {
		// double size of array if necessary and recopy to front of array
		if (N == q.length) resize(2*q.length);   // double size of array if necessary
		q[last++] = item;                        // add item
		if (last == q.length) last = 0;          // wrap-around
		N++;
	}

	// remove the least recently added item 
	public Item dequeue() {

		// Sample
		if (isEmpty()) throw new RuntimeException("Queue underflow");
		int rand = (StdRandom.uniform(N) + first) % (q.length);
		Item item = q[rand];
	
		// Exchange the random item selected with the first one in the queue
		q[rand] = q[first];
		q[first] = null;                            // to avoid loitering
		N--;
		first++;
		if (first == q.length) first = 0;           // wrap-around
		// shrink size of array if necessary
		if (N > 0 && N == q.length/4) resize(q.length/2); 
		return item;
	}

	public Item sample() {
		if (isEmpty()) throw new RuntimeException("Queue underflow");
		int rand = (StdRandom.uniform(N) + first) % (q.length);
		Item item = q[rand];
		return item;
	}

	public Iterator<Item> iterator() { return new QueueIterator(); }

	// an iterator, doesn't implement remove() since it's optional
	private class QueueIterator implements Iterator<Item> {
		private int i = 0;
		public boolean hasNext()  { return i < N;                               }
		public void remove()      { throw new UnsupportedOperationException();  }

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = q[(i + first) % q.length];
			i++;
			return item;
		}
	}

	// a test client
	public static void main(String[] args) {
		RandomQueue<String> queue = new RandomQueue<String>();
		queue.enqueue("Delete");
		queue.enqueue("This");
		queue.enqueue("is");
		queue.enqueue("a");
		queue.enqueue("test.");
		queue.dequeue();

		for (String s : queue)
			System.out.println(s);

		System.out.println();

		for (int i = 0; i < queue.size() ; i++ ) {
			StdOut.println(queue.sample());
		}

		System.out.println();

		while (!queue.isEmpty())  {
			System.out.println(queue.dequeue());
		}
	}


}
