import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *  Prof: Yoshiharu Kohayakawa
 *  Aluno: Mauricio Tavares Ozaki
 *  N USP: 7577427
 *  Data de Entrega: 27/03/2016
 *  Creative Problem 1.3.35 (Random queue; Algs4)
 *  Obs.: Eu criei uma fila seguindo a dica dada pelo autor no 
 *        enunciado e tive certa dificuldade em entender o Iterator.
 *        Na coompilacao, apareceu um warning:
 *        Note: RandomQueue.java uses unchecked or unsafe operations.
 *        Note: Recompile with -Xlint:unchecked for details.
 *        Um colega tambem comentou sobre isso no forum, acredito que 
 *        nesse ep isso nao sera um problema.
 *  Depedencias: Cards
 ******************************************************************************/


import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] carta;
    private int tam;
    //Como sabemos que cartas de um unico baralho serao colocadas
    //na fila, entao ja criei uma fila de tamanho 52. Segui a ideia do
    //enunciado.
    public RandomQueue() {
        carta = (Item[]) new Object[52];
        tam=0;
    }
    
    //Quando o tam==0, entao todos as cartas foram retiradas do baralho ou fila
    public boolean isEmpty() {
        return tam==0;
    }
    
    //Enfileira um item
    public void enqueue(Item c) {
         carta[tam++]=c;
    }
    
    //Um elemento aleatorio eh retirado da fila, eu escolho o elemento atraves
    //do Math.random. Depois eu rearranjo a fila, a partir do item retirado, ou seja,
    //a carta i recebe a i+1, a i+1 recebe a i+2 e assim por diante.
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Fila Vazia");
        int rand=(int)((tam)*Math.random());
        Item temp=carta[rand];
        for (int i=rand; i<carta.length-1; i++) {
            carta[i]=carta[i+1];
        }
        carta[tam-1]=null;
        tam--;        
        return temp;
    }
    
    //Mesma ideia que o dequeue, porem a carta nao eh removida.
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Fila Vazia");
        int rand=(int)(52*Math.random());      
        return carta[rand];
    }  
    
    //Iterador da lista, baseado nas ideias dos 
    //FixedCapacityStackOfStrings.java e ResizingArrayStack.java
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private int i=tam-1;

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return carta[i--];
        }
    }    
        
    public static void main(String[] args) { 
        //Main foi feito para testar o funcionamento do RandomQueue
        RandomQueue<String> r = new RandomQueue<String>();
        
        for (int i=0; i<52; i++) {
            Card card = new Card(i);
            String buffer = card.toString();
            r.enqueue(buffer);
        }
        
        for (int i=0; i<13; i ++) {
            System.out.println(r.dequeue());            
        }        
    }
}