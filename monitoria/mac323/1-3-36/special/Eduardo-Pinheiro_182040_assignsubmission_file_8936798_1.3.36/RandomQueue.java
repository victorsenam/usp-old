import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * @author: Eduardo Pinheiro
 * NUSP:    8936798
 */
import edu.princeton.cs.algs4.*;
import java.util.Iterator;

public class RandomQueue<T> implements Iterable<T>
{
  private T[] items; /* Vetor com os items */
  private int N; /* Aponta para a primeira posição vaga */

  @SuppressWarnings("unchecked")
  public RandomQueue() {
    N = 0;
    items = (T[]) new Object[10];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public void enqueue(T item) {
    if (N == items.length) resize(2 * items.length); /* Dobra o tamnho caso necessário*/

    items[N++] = item;
  }

  /* Dobra o tamanho do vetor */
  private void resize(int new_size) {
    @SuppressWarnings("unchecked")
    T[] new_array = (T[]) new Object[new_size];
    for (int i = 0; i < N; i++)
      new_array[i] = items[i];

    items = new_array;
  }

  public T dequeue() {
    if (!isEmpty()) {
      T aux;
      int index = StdRandom.uniform(N); /* Escolhe um valor em [1, N) e retorna o item nesta posição */
      aux = items[index];
      items[index] = items[--N];

      if (N > 0 && N == items.length/4) resize(items.length/2); /* Divide por dois o tamnho, caso possível */

      return aux;
    }
    return null;
  }

  public T sample() {
    if (!isEmpty()) {
      int index = StdRandom.uniform(N);
      return items[index]; /* Retorna um item aleatório sem o remover da queue */
    }
    return null;
  }

  @Override
  public Iterator<T> iterator() {
    return new QueueIterator();
  }

  private class QueueIterator implements Iterator<T>
  {
    int i = N;

    @Override
    public boolean hasNext() {
      return i > 0;
    }

    /* Encontra o próximo valor, gerando um valor aleatório entre [0, i), passando este valor para a última posição do vetor, 
     *  decrementando i e retornando o valor. Assim todos os números tem chances iguais de serem escolhidos e não ocorem repetições */
    @Override
    public T next() {
      int index = StdRandom.uniform(i);
      T aux;
      i--;

      aux = items[index];
      items[index] = items[i];
      items[i] = aux;
      return items[i];
    }
  }

  /* Cliente */
  public static void main(String[] args) {
    RandomQueue<Integer> queue= new RandomQueue<Integer>();

    for (int i = 0; i < 1000; i++) queue.enqueue(i);

   //for (int i = 0; i < 1000000; i++) queue.dequeue();
    for (Integer t : queue) System.out.println(t);
  }
}
