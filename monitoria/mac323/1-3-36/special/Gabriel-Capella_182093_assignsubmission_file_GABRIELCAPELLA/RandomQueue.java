import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *
 *  MAC0323 - Estrutura de Dados 2
 *  Aluno: Gabriel Capella
 *  Numero USP: 8962078
 *  Tarefa: Creative Problem 1.3.35 (Random queue; Algs4)
 *  Data: 18/03/2016
 *
 ******************************************************************************/
import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {
    private int N = 0;
    private Item[] queue = (Item[]) new Object[1];

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = queue[i];
        queue = temp;
    }

    // is the queue empty?
    public boolean isEmpty() {
        if (N == 0) return true;
        return false;
    }

    // add an item
    public void enqueue(Item item) {
        if (N == queue.length) resize(2 * queue.length);
        queue[N] = item;
        N++;
    }

    // remove and return a random item
    public Item dequeue() {
        int aleatorio = StdRandom.uniform(N);
        N--;

        Item item = queue[aleatorio];
        queue[aleatorio] = queue[N];
        queue[N] = null;

        if (N > 0 && N == queue.length/4) resize(queue.length/2);
        return item;
    }
    
    // return a random item, but do not remove
    public Item sample() {
        int aleatorio = StdRandom.uniform(N);
        Item item = queue[aleatorio];
        return item;
    }

    public Iterator<Item> iterator(){
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        public boolean hasNext() {
            return !isEmpty();
        }
        public void remove() {}
        public Item next() {
            return dequeue();
        }
    }
}