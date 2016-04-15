import java.util.*;
import edu.princeton.cs.algs4.*;
// Victor de Oliveira Colombo - 8988657

import java.util.*;

public class RandomQueue<E> implements Iterable<E> {

    private ArrayList<E> queue;
    private Random random;

    public RandomQueue() {
        queue = new ArrayList<E>();
        random = new Random();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public void enqueue(E a) {
        queue.add(a);
    }

    public E sample() {
        return queue.get(random.nextInt(queue.size()));
    }

    public E dequeue() {
        E a = queue.get(random.nextInt(queue.size()));
        queue.remove(a);
        return a;
    }

    public class RandomIterator implements Iterator<E> {

        private ArrayList<Integer> copy;
        private int ptr = 0;

        private RandomIterator(){
            copy = new ArrayList<Integer>();
            for(int i = 0; i < queue.size(); i++)
                copy.add(i);
            Collections.shuffle(copy);
        }

        public boolean hasNext() {
            return ptr != copy.size();
        }

        public E next() {
            return queue.get(copy.get(ptr++));
        }
    }

    public Iterator<E> iterator() {
        return new RandomIterator();
    }

}
