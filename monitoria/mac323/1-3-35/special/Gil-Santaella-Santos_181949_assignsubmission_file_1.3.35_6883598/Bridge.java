import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * Gil Santaella Santos 6883598
 *
 * javac Bridge.java
 * java Bridge
 *
 */

/*
 * Gera um deck de cartas e sorteia uma mao de 13 cartas
 */

import edu.princeton.cs.algs4.*;

public class Bridge {

	private RandomQueue<Card> deck;

	public Bridge() {
		deck = new RandomQueue<Card>();
	}

	public void dealHand() {
		int handSize = 13;

		for (int i = 0; i < handSize; i++)
			StdOut.println(deck.dequeue());
	}

	public void shuffleDeck() {
		int deckSize = 52;
        
    	for (int i = 0; i < deckSize; i++)
    		deck.enqueue(new Card(i));
	}

	public static void main(String[] args) {

		Bridge br = new Bridge();
		br.shuffleDeck();
		br.dealHand();
	}
}