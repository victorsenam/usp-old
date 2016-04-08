import java.util.*;
import edu.princeton.cs.algs4.*;
/***************************************************************/
/**                                                           **/
/**   Lucas Moreira Santos                          9345064   **/
/**   Creative Problem 1.3.35                                 **/
/**   Professor:  Yoshiharu Kohayakawa                        **/
/**   MAC0323 - Algoritmos e Estruturas de Dados II           **/
/**                                                           **/
/***************************************************************/

import java.util.*;

public class RandomQueue<Item> {
  private Vector<Item> items;

  public RandomQueue() {
    this.items = new Vector<Item>(10);
  }

  public boolean isEmpty() {
    return this.items.isEmpty();
  }

  public void enqueue(Item item) {
    if (this.items.size() == this.items.capacity())
      this.resize();

    this.items.add(item);
  }

  public Item dequeue() {
    if (this.isEmpty())
      throw new NoSuchElementException();

    int randomIndex = (int) Math.floor(Math.random() * this.items.size());
    Item tmp = this.items.lastElement();
    this.items.set(this.items.size() - 1, this.items.get(randomIndex));
    this.items.set(randomIndex, tmp);

    return this.items.remove(this.items.size() - 1);
  }

  public Item sample() {
    if (this.isEmpty())
      throw new NoSuchElementException();

    int randomIndex = (int) Math.floor(Math.random() * this.items.size());
    return this.items.get(randomIndex);
  }

  private void resize() {
    if (this.items.size() == this.items.capacity())
      this.items.ensureCapacity(this.items.capacity() * 2);
  }

  public Iterator<Item> iterator()  {
    return new QueueIterator();
  }

  private class QueueIterator implements Iterator<Item> {
    private int index;
    private final int[] indexes;
    private final int size;

    public QueueIterator() {
        this.index = 0;
        this.size = items.size();
        this.indexes = new int[this.size];
        int[] indexesAvailable = new int[this.size];

        for (int i = 0; i < this.size; i++)
          indexesAvailable[i] = i;

        for (int i = 0; i < this.size; i++) {
          int range = this.size - i - 1;                      // Range útil do array indexesAvailable
          int tmp = (int) Math.floor(Math.random() * range);  // Pega um dos elementos disponiveis
          this.indexes[i] = indexesAvailable[tmp];            // Coloca ele como próximo
          indexesAvailable[tmp] = indexesAvailable[range];    // Swap o elemento com o último final (e diminui um tamanho do vetor, quando o i incrementar)
        }
    }

    public boolean hasNext() {
        // System.out.printf("Validando. Index: %d. Size: %d.\n", this.index, this.size);
        return this.index < this.size;
    }

    public Item next() {
        if (this.hasNext()) {
            return items.get(this.indexes[this.index++]);
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
  }

}
