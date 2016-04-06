/******************************************************************************
 *  Nome:         Vinicius Perche de Toledo Agostini
 *  Número USP:   4367487
 *
 *  Este programa gera uma mão de Bridge aleatoriamente a partir de um baralho
 *  normal de 52 cartas.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class Bridge {

    public static void main(String[] args) {
        RandomQueue<Card> randomQueue = new RandomQueue<Card>();
        
        for (int i = 1; i < 52; i++) {
            Card c = new Card(i);
            randomQueue.enqueue(c);
        }
        
        for (int i = 0; i < 13; i++)
            StdOut.println(randomQueue.dequeue());       
    }
}