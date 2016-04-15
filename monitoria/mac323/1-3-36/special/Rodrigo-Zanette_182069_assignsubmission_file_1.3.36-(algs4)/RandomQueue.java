import java.util.*;
import edu.princeton.cs.algs4.*;
/*Nome: Rodrigo Zanette de Magalhães*/
/*Nº USP:9298090                    */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomQueue<Item>{
    private int N; /*total de itens já inseridos na fila*/
    private Item[] fila;
    
    public RandomQueue () {
        /*Cria a RQ; o primeiro item que entrou é sempre fila[0] e
        o último é sempre fila[N-1] para N>=1*/
        fila = (Item[]) new Object[2];
        N=0;
    }
    
    public boolean isEmpty () {
        if (N==0) { return true; }
        else { return false; }
    }
    public int QueueSize () { return N; }
    
    public void ExpandQueue (int new_size) {
        Item aux[] = (Item[]) new Object[new_size];
        for (int i=0; i<N; i++) { aux[i]=fila[i]; }
        fila=aux;
    }

    public void enqueue (Item new_item) {
        if (N==fila.length) { ExpandQueue (2*fila.length); }
        fila[N++]=new_item;
    }
    
    public Item sample () { return fila[(int) (Math.random ()*N)]; }

    public Iterator<Item> rIterator() { return new RandomIterator(); }    
    private class RandomIterator implements Iterator<Item> {
        public boolean hasNext() { return N>0; }
        public void remove() { throw new UnsupportedOperationException();  }

        private Item dequeue () {
            int i=(int) (Math.random ()*N);
            Item aux=fila[i]; fila[i]=fila[N-1];
            N--; return aux;
        }
    
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item aux=dequeue(); 
            return aux;
        }
    }
}

