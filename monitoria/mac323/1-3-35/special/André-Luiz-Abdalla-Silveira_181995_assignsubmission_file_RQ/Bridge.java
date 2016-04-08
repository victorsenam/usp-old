import java.util.*;
import edu.princeton.cs.algs4.*;
/*****************************************************************
	André Luiz Abdalla Silveira						8030353
	$ javac-algs4 Bridge.java
	$ java-algs4 Bridge
*****************************************************************/

import edu.princeton.cs.algs4.*;

public class Bridge {

	public static void main(String[] args) {
		RandomQueue<Card> deck = new RandomQueue<Card>();
		
		// indica se uma carta já foi posta no deck -- evitar repetições
		boolean[] jah_foi = new boolean[52]; 
		for (int  j = 0; j < 52; j++) jah_foi[j] = false;

		//formando o deck, já de forma embaralhada
		for (int i = 0; i < 52; i++) {
			int my_card = (int) (Math.random()*52);
			if (!jah_foi[my_card]) {
				Card c = new Card(my_card);
				deck.enqueue(c);
				jah_foi[my_card] = true;
			}
		}

		for (int c = 0; c < 13; c++) {
			System.out.println(deck.dequeue() + " ");
		}
	}
}