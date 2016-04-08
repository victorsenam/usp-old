import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *  Compilation:  javac TesteVisual.java
 *  Execution:    java TesteVisual N T
 
 *  Problema 1.3.35 - Web 
 *  Pedro Sola Pimentel RM 9298079
 *
 *  Este arquivo implementa uma fila do tipo Item.
 *
 ******************************************************************************/

import java.util.Iterator;

public class RandomQueue<Item> {

    private Item[] list;
    private static int N;

    public RandomQueue () { 
        list = (Item[]) new Object[2];
        N = 0;
    }

    public boolean isEmpty () { return N == 0; }

    /* Aumenta o tamanho da lista de itens para nova capacidade
     * "capacity" */
    private void resize(int capacity) {
        /* assert capacity >= N; */
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = list[i];
        }
        list = temp;
    }

    /* Insere um item na lista "list" */
    public void enQueue (Item item) {
        if (N == list.length) resize (2 * list.length);
        list[N++] = item;
    }

    /* Retorna um item da lista presente em uma posicao
     * aleatoria e o retira da lista. */
    public Item deQueue () {
        if (!isEmpty ()) {
            int rand = (int) (Math.random () * N);
            Item temp = list[rand];
            list[rand] = list[N-1];
            list[N-1] = null;
            N --;
            return temp;
        }
        return list[-1];
    }

    /* Retorna um item da lista presente em uma posicao
     * aleatoria sem o retirar da lista */
    public Item sample () {
        if (!isEmpty ()) 
            return list[ (int) (Math.random () * N) ];
        return list[-1];
    }


}