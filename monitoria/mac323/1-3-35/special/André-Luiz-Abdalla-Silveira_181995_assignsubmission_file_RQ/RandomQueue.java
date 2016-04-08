import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class RandomQueue<Item> {
	public Item[] v;
	public int num = 0;

	public RandomQueue() {
		v = (Item[]) new Object[10];
	}

	public boolean isEmpty() {return num == 0;}
 
	private void resize	() {
		int L = v.length;
		Item[] aux = (Item[]) new Object[2*L];
		for (int i = 0; i < L; i++) aux[i] = v[i];
		v = aux;
	}

	public void enqueue (Item it) {
		if (num == v.length) resize();
		v[num++] = it;
	}

	private int randy (int size) {
		int resp = (int) (Math.random()*size);
		return resp;
	}

	public Item dequeue () {
		if (isEmpty()) throw new NoSuchElementException("Não há elementos");
		int indice = randy(num);
		Item resp = v[indice];
		v[indice] = v[num-1];
		v[num-1] = null;
		num--;
		return resp;
	}

	public Item sample () {
		if (isEmpty()) throw new NoSuchElementException("Não há elementos");
		int indice = randy(num);
		Item resp = v[indice];
		return resp;
	}

	public static void main(String[] args) {
		RandomQueue<Integer> v = new RandomQueue<Integer>();
		StdOut.print("Digite dez números:  ");
		for (int i = 0; i < 10; i++) {
			int item = StdIn.readInt();
			v.enqueue(item);
		}
		StdOut.println("Imprimindo os elementos em ordem aleatória");
		while (!v.isEmpty()) StdOut.print(v.dequeue() + " ");
		StdOut.println("");
	}
}