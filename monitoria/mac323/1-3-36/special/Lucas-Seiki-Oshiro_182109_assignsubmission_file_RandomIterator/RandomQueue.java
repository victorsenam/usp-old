import java.util.*;
import edu.princeton.cs.algs4.*;
//Nome: Lucas Seiki Oshiro
//Número USP: 9298228

import edu.princeton.cs.algs4.*;
import java.util.Iterator;

/* Representa uma fila aleatória. */
public class RandomQueue <Item> implements Iterable <Item> {
    private Object[] queue;
    private int N;
    private int begin;
    private int end;

    /* Iterador */
    private class RandomQueueIterator <Item> implements Iterator <Item> {
        private RandomQueue <Item> rq;

        /* Constroi o iterador, copiando as referências do vetor queue que não
           são null. */
        public RandomQueueIterator (Object[] queue) {
            rq = new RandomQueue <Item> ();
            for (int i = 0; i < queue.length; i++)
                if (queue[i] != null) rq.enqueue ((Item) queue[i]);
        }

        public boolean hasNext () {
            return !rq.isEmpty ();
        }

        /* Não usado. */
        public void remove () {}

        /* Devolve o proximo item. */
        public Item next () {
            return rq.dequeue ();
        }
    }

    /* Constrói uma RandomQueue vazia. */
    public RandomQueue () {
        N = 10;
        queue = new Object[N];
        begin = end = 0;
    }

    /* Redimensiona a fila. Só deve ser usado quando a fila estiver cheia. */
    private void resize () {
        Object[] q2 = new Object[N * 2];
        
        for (int i = 0; i < N; i++) {
            q2[i] = queue[(i + begin) % N];
        }

        end = N;
        N *= 2;
        begin = 0;
        queue = q2;
    }

    /* Verifica se a fila está vazia. */
    public boolean isEmpty () {
        return begin == end;
    }

    /* Inserem item na fila. */
    public void enqueue (Item item) {
        queue[end] = item;
        end = (end + 1) % N;
        if (end == begin) resize ();
    }

    /* Devolve um elemento, e o retira da fila. */
    public Item dequeue () {
        int i;
        if (begin < end) i = StdRandom.uniform (begin, end);
        else i = StdRandom.uniform (begin, end + N) % N;

        Item x = (Item) queue[i];
        queue[i] = queue[begin];
        begin = (begin + 1) % N;

        return x;
    }

    /* Devolve um elemento, que não é removido da fila. */
    public Item sample () {
        int i;
        if (begin < end) i = StdRandom.uniform (begin, end);
        else i = StdRandom.uniform (begin, end + N) % N;

        return (Item) queue[i];
    }

    /* Devolve um iterador do objeto */
    public Iterator <Item> iterator () {
        return new RandomQueueIterator (queue);
    }

    /* Apenas para teste. */
    public static void main (String[] args) {
        RandomQueue <Integer> rq = new RandomQueue <Integer> ();
        for (int i = 0; i < 100; i++) rq.enqueue (i);

        for (Integer i : rq) StdOut.println (i);
    }
}
