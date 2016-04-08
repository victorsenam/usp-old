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
import java.util.Iterator;
import java.util.Random;

/**
 * Classe que implementa uma fila de acesso aleatório.
 * 
 * @author Marcelo Schmitt
 * Número USP: 9297641
 *
 * @param <Item> - tipo dos objetos enfileirados para uma dada instancia de
 * 					RandomQueue.
 */
public class RandomQueue<Item> implements Iterable<Item> {

	private int n; //tamanho do array fila
	private int ultimo; //índice do último item da fila (-1 se a fila estiver vazia)
	private Item[] fila; //fila de itens
	private Random random; //Gerador de números aleatórios
	
	
	public RandomQueue() {
		n = 2;
		ultimo = -1;
		fila = (Item[]) new Object[n];
		random = new Random();
	}
	
	/**
	 * Is the queue empty?
	 * 
	 * @return true if the queue is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return ultimo == -1;
	}
	
	/**
	 * Add an item to the queue.
	 * 
	 * @param item - the item to be added.
	 */
	public void enqueue(Item item) {
		if(ultimo == n-1) {
			resize(n*2);
		} else if (ultimo+1 < n/4) {
			resize(n/2);
		}
		fila[++ultimo] = item;
	}
	
	/**
	 * Remove and return a random item (sample without replacement).
	 * 
	 * @return an item from the queue chosen randomly.
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new ArrayIndexOutOfBoundsException("Attempted to dequeue on an empty queue");
		}
		int posicao = random.nextInt(ultimo+1);
		Item desenfileirado = fila[posicao];
		fila[posicao] = fila[ultimo];
		ultimo--;
		if (ultimo < n/4) {
			resize(n/2);
		}
		return desenfileirado;
	}
	
	/**
	 * Return a random item, but do not remove (sample with replacement).
	 * 
	 * @return an item from the queue chosen randomly.
	 */
	public Item sample() {
		return fila[random.nextInt(ultimo+1)];
	}
	
	/**
	 * Implementando a ideia de manter a ocupação do array entre 25% e 100%.
	 */
	private void resize(int n) {
		Item[] aux = (Item[]) new Object[n];
		for (int i = 0; i <= ultimo; i++) {
			aux[i] = fila[i];
		}
		fila = aux;
		this.n = n;
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}
	
	private class RandomQueueIterator implements Iterator<Item> {

		@Override
		public boolean hasNext() {
			return !isEmpty();
		}

		@Override
		public Item next() {
			return dequeue();
		}

		@Override
		public void remove() {}
		
	}

	public static void main(String[] args) {
		System.out.println("Teste da Classe RandomQueue");
		RandomQueue<Integer> queue = new RandomQueue<Integer>();
		//Enchendo e esvaziando a fila.
		for (int i = 0; i < 7; i++) {
			queue.enqueue(i); //Graças ao autoboxing.
		}
		for (int i = 0; i < 7; i++) {
			System.out.println(queue.dequeue());
		}
		System.out.println();
		//Testando sample().
		for (int i = 0; i < 8; i++) {
			queue.enqueue(i);
		}
		System.out.println("sample(): " + queue.sample());
		for (int i = 0; i < 8; i++) {
			queue.dequeue();
		}
		System.out.println();
		//Testando a classe RandomQueueIterator
		System.out.println("Iterando com RandomQueueIterator");
		for (int i = 0; i < 8; i++) {
			queue.enqueue(i);
		}
		Iterator<Integer> randomQueueIterator = queue.iterator();
		while (randomQueueIterator.hasNext()) {
			Integer integer = randomQueueIterator.next();
			System.out.println(integer);
		}
		//Testando o erro de fila vazia
		System.out.println("Teste do erro de fila vazia");
		for (int i = 0; i < 9; i++) {
			System.out.println(queue.dequeue());
		}
	}
}
