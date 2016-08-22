import java.util.*;
import edu.princeton.cs.algs4.*;

import java.util.*;
import edu.princeton.cs.algs4.*;
public class Bridge {
	public static void main(String[] args) {
		RandomQueue<Card> queue = new RandomQueue<Card>();
		Card c;

		for (int i = 0; i < 52; i++){
			c = new Card(i);
			queue.enqueue(c);
		}

		for (int i = 0; i < 13; i++)
			StdOut.println(queue.dequeue());
	}
}
