/******************************************************************************
 *  Nome:         Vinicius Perche de Toledo Agostini
 *  Número USP:   4367487
 *
 *	Este módulo implementa a classe RandomQueue<Item>.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

@SuppressWarnings("unchecked")
public class RandomQueue<Item> {

	private Item[] q;			// fila
	private int N;				// número de elementos
	
	/**
	 * Constrói uma fila vazia com capacidade 3.
	 */
	public RandomQueue() {
		q = (Item[]) new Object[3];
		N = 0;
	}
	
	/**
	 * A fila está vazia?
	 * @return true caso sim; false caso contrário
	 */
	public boolean isEmpty() {
		return N == 0;
	}
	
	/**
	 * @return número de elementos
	 */
	public int size() {
		return N;
	}
	
	/**
	 * Aumenta ou diminui a capacidade da fila dependendo
	 * da necessidade
	 */
	private void resize(int capacity) {
		assert capacity >= N;
		Item[] temp = (Item[]) new Object[capacity];
		
		for (int i = 0; i < N; i++)
			temp[i] = q[i];
		
		q = temp;
	}
	
	/**
	 * Adiciona um elemento ao final da fila
	 */
	public void enqueue(Item item) {
		if (N == q.length) resize(2*q.length);
		q[N++] = item;
	}
	
	/**
	 * Retorna e remove um elemento aleatório
	 * @return próximo elemento aleatório
	 */
	public Item dequeue() {
		int r = StdRandom.uniform(N);
		Item item = q[r];
		q[r] = q[N-1];
		q[N-1] = null;
		N--;
		return item;		
	}
	
	/**
	 * Não remove o elemento, apenas retorna
	 * @return próximo elemento aleatório
	 */
	public Item sample() {
		int r = StdRandom.uniform(N);
		return q[r];
	}
	
	public static void main(String[] args) {
		RandomQueue<Integer> randomQueue = new RandomQueue<Integer>();
		
		for (int i = 1; i < 52; i++)
			randomQueue.enqueue(i);
		
		StdOut.println(randomQueue.sample());

		for (int i = 0; i < 13; i++) {
			StdOut.print(randomQueue.dequeue() + " ");
		}
		
	}

}
