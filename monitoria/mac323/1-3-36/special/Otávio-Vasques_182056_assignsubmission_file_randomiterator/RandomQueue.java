import java.util.*;
import edu.princeton.cs.algs4.*;
/* Crative Exercise 1.3.36 - RandomQueue
	MAC 323 - Yoshiharu Kohayakawa
	Otávio Vasques - 8944665
	*/
import java.util.Random;
import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {

	private Item[] content;
	private int k;

	public RandomQueue() {
		k = 0;
		content = (Item[]) new Object[1];
	}

	public boolean isEmpty() {
		// Não recebe argumentos e retorna um boleano para se content está
		// vazio ou não.
		if (k == 0)
			return true;
		return false;
	}

	public void enqueue (Item item) {
		// Recebe um Item item e o adiciona na fila. Não retorna nada.
		if (k >= content.length)
			content = increaseSize(content);
		content[k++] = item;
	}

	public Item dequeue () {
		// Não recebe argumentos e retorna um elemento aleátorio de content
		// removendo-o.
		if (k <= content.length/2)
			content = decreaseSize(content);
		Random rdm = new Random();
		int position = rdm.nextInt(k);
		Item temp = content[position];
		content[position] = content[k-1];
		content[k-1] = temp;
		return content[--k];
	}

	public Item sample () {
		// Não recebe argumentos e retorna um elemento aleátorio de content.
		Random rdm = new Random();
		int position = rdm.nextInt(k);
		return content[position];
	}

	private Item[] increaseSize(Item[] array) {
		// Recebe um Item[] array e dobra seu tamanho. Retorna o vetor sem 
		// alterar seu conteúdo.
		int N = array.length;
		Item[] newArray = (Item[]) new Object[2*N];
		for (int i = 0; i < N; i++)
			newArray[i] = array[i];
		return newArray;
	}

	private Item[] decreaseSize(Item[] array) {
		// Recebe um Item[] array e diminui seu tamanho pela metade. Retorna o
		// vetor sem alterar seu conteúdo.
		int N = array.length;
		Item[] newArray = (Item[]) new Object[N/2];
		for (int i = 0; i < N/2; i++)
			newArray[i] = array[i];
		return newArray;
	}

	public Iterator<Item> iterator() {
		return new RandomIterator();
	}

	private class RandomIterator implements Iterator<Item> {
		private int i = k;

		public boolean hasNext() {
			return i > 0;
		}

		public Item next() {
			Random rdm = new Random();
			int position = rdm.nextInt(i);
			Item temp = content[position];
			content[position] = content[i-1];
			content[i-1] = temp;
			return content[--i];
		}

		public void remove() {}
	}
}