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
 * Nome: Marcelo Baiano Pastorino Trylesinski
 * Número USP: 9297996
 *
 *  Compilation:  javac-algs4 RandomQueue.java
 *
 *  A estrutura implementada neste programa é uma fila aleatória.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.*;

public class RandomQueue<Item> {
    private Item[] q;
    private int N;
    private int first;
    private int last;

    RandomQueue () {
        q = (Item[]) new Object[2];
        N = 0;
        first = 0;
        last = 0;
    }

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

    public boolean isEmpty () {
        return N == 0;
    }

    public void enqueue (Item item) {
        if (N == q.length) 
            resize (2*q.length);
        q[last++] = item;
        if (last == q.length) 
            last = 0;
        N++;
    }

    public Item dequeue () {
        if (isEmpty ()) 
            throw new NoSuchElementException("Queue underflow");
        int randVal = StdRandom.uniform (N);
        int index = (first + randVal) % q.length; 
        Item temp = q[index];
        q[index] = q[first];
        q[first] = null;
        N--;
        first++;
        if (first == q.length) 
            first = 0;
        if (N > 0 && N == q.length/4) 
            resize (q.length/2); 
        return temp;
    } 

    public Item sample () {
        if (isEmpty ()) 
            throw new NoSuchElementException("Queue underflow");
        int randVal = StdRandom.uniform (N);
        int index = (first + randVal) % q.length; 
        return q[index];
    } 
}