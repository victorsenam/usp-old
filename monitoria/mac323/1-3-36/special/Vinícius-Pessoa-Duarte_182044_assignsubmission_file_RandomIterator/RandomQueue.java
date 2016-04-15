import java.util.*;
import edu.princeton.cs.algs4.*;
/* Nome: Vinicius Pessoa Duarte             */
/* Numero USP: 8941043                      */
/* Disciplina: MAC-0323                     */
/* Exercicio: Random Iterator/1.3.36        */

import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Implementa um random queue, com a api definida no enunciado do exercicio
// anterior, implementando um iterador

public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int N = 0, max = 1;
    
    // Construtor
    public RandomQueue () {
        queue = (Item[]) new Object[max];
    }

    // Informa se a pilha esta vazia
    public boolean isEmpty () {
        return (N == 0);
    }

    // Dobra o tamanho da pilha
    private void resize () {
        max *= 2;
        Item[] newItem = (Item[]) new Object[max];
        for (int i = 0; i < max / 2; ++i)
            newItem[i] = queue[i];
        queue = newItem;
    }

    // Diminui o tamanho da pilha pela metade
    private void shrink () {
        max /= 2;
        Item[] newItem = (Item[]) new Object[max];
        for (int i = 0; i < max; ++i)
            newItem[i] = queue[i];
        queue = newItem;
    }

    // Empilha um elemento
    public void enqueue (Item item) {
        if (N == max)
            resize ();
        if (N < max / 4)
            shrink ();
        queue[N] = item;
        N++;
    }

    // Desempilha um elemento aleatorio
    public Item dequeue () {
        if (isEmpty ()) {
            StdOut.println ("underflow!");
            return null;
        }
        int sorted = (int) (Math.random () * (N - 1.0));
        Item item = queue[sorted];
        queue[sorted] = queue[--N];
        queue[N + 1] = null;
        return item;
    }

    // Mostra um elemento aleatorio da pilha
    public Item sample () {
        int sorted = (int) (Math.random () * (N - 1.0));
        return queue[sorted];
    }

    // Cria construtor
    public Iterator<Item> iterator() {
        return new ArrayIterator ();
    }

    // implementacao do construtor
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            StdOut.println ("Nao implementado");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Queue underflow");    
            Item item = dequeue ();
            return item;
        }
    }

    // Cliente de testes
    public static void main (String[] args) {
        RandomQueue<Integer> s = new RandomQueue<Integer> ();
        for (int i = 0; i < 10; i++)
            s.enqueue (i);

        for (Integer i : s)
            StdOut.println (i);
    }

}
