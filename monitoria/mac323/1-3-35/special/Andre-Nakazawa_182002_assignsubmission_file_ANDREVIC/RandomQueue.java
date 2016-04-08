import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *  Nome: Andre Victor dos Santos Nakazawa
 *  NUSP: 9298336
 *
 *  Compilation:  javac RandomQueue.java
 *  Execution:    java RandomQueue < input.txt
 *
 *  Class that implements an API for random queues.
 *
 ******************************************************************************/

import java.util.NoSuchElementException;

public class RandomQueue<Item> {
    private Item[] a;
    private int N;

    /* Creates an empty random queue */
    public RandomQueue() {
        a = (Item[]) new Object[2];
        N = 0;
    }

    /* Is this queue empty? Return true if it is */
    public boolean isEmpty() {
        return N == 0;
    }

    /* Adds an item to this queue */
    public void enqueue(Item item) {
        if (N == a.length)
            resize(2 * a.length);
        a[N++] = item;
    }

    /* Removes and returns a random item on this queue */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        int r = (int) (N * Math.random());
        Item item = a[r];
        a[r] = a[N-1];
        a[N-1] = null;
        N--;
        if (N > 0 && N == a.length/4)
            resize(a.length/2); 
        return item;
    }

    /* Returns a random item on this queue, but does not remove */
    public Item sample() {
        int r = (int) (N * Math.random());
        Item item = a[r];
        return item;
    }

    /* Resizes the underlying array */
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /* Sample client for testing */
    public static void main(String[] args) {
        RandomQueue<String> s = new RandomQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.enqueue(item);
            else if (!s.isEmpty())
                StdOut.print(s.dequeue() + " ");
        }
        StdOut.println();
    }
}

