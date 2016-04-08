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
/*
 * Gil Santaella Santos 6883598
 *
 * javac RandomQueue.java
 * java RandomQueue < input.txt
 *
 */

/*
 * API para uma fila aleatoria
 */

import edu.princeton.cs.algs4.*;

public class RandomQueue<Item> {

    private int size;
    private Item[] rqueue;

    public RandomQueue() {
        rqueue = (Item[]) new Object[2];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item item) {
        if (size == rqueue.length)
            resize(2*size);
        rqueue[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            StdOut.println("Fila vazia");
            return null;
        }
        int index = StdRandom.uniform(size);
        Item aux = rqueue[index];
        rqueue[index] = rqueue[size-1];
        rqueue[--size] = null;

        if (size > 0 && size == rqueue.length/4) 
            resize(rqueue.length/2);
        
        return aux;
    }

    public Item sample() {
        return rqueue[StdRandom.uniform(size)];
    }

    private void resize(int capacity) {
        if (capacity > size) {
            Item[] temp = (Item[]) new Object[capacity];
            for (int i = 0; i < size; i++)
                temp[i] = rqueue[i];
            rqueue = temp;
        }
    }

    public static void main(String[] args) {

        RandomQueue<String> rq = new RandomQueue<String>();

        while (!StdIn.isEmpty())
            rq.enqueue(StdIn.readString());

        StdOut.println(rq.sample());

        while(!rq.isEmpty()) {
            StdOut.println(rq.dequeue() + " - " + rq.getSize()); 
        }

    }
}
