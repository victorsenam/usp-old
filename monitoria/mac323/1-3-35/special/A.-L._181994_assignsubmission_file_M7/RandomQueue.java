import java.util.*;
import edu.princeton.cs.algs4.*;
/* Name: Antonio Carlos Santos de Lima
 * USP Number 8515986
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomQueue<Item> 
{
    private Item[] a; // array of items
    private int N; // number of elements on random queue

    /**
     * Initializes an empty random queue.
     */
    public RandomQueue () 
    {
        a = (Item[]) new Object[2];
        N = 0;
    }

    /**
     * Is this random queue empty?
     * @return true if this random queue is empty; false otherwise
     */
    public boolean isEmpty () { return N == 0; }

    /**
     * Returns the number of items in the random queue.
     * @return the number of items in the random queue.
     */
    public int size () { return N; }

    // resize the underlying array holding the elements
    private void resize (int capacity) 
    {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) 
            temp[i] = a[i];
        a = temp;
    }

    /**
     * Adds the item to this random queue.
     * @param item the item to add
     */
    public void enqueue (Item item) 
    {
        if (N == a.length) resize (2 * a.length); // double size of array if necessary
        a[N++] = item; // add item
    }

    /**
     * Removes and returns the item most recently added to this random queue.
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this random queue is empty
     */
    public Item dequeue () 
    {
        if (isEmpty ()) throw new NoSuchElementException ("Random Queue underflow");
   
        Item item = a[N-1];
        int random = StdRandom.uniform (0, N);
        a[N-1] = a[random];
        a[random] = item;
        
        item = a[N-1];
        a[N-1] = null; // to avoid loitering
        N--;
        
        // shrink size of array if necessary
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }
    
    public Item sample () 
    {
        if (isEmpty ()) throw new NoSuchElementException ("Random Queue underflow");
        return a[StdRandom.uniform (0, N)];
    }
}