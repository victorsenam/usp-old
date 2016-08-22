import java.util.*;
import edu.princeton.cs.algs4.*;
/* **************************************** */
/*  Nome: Camila Naomi Kodaira              */
/*  Numero USP: 4266602                     */
/* **************************************** */

/* **************************************** */
/* Random Queue                             */
/* Esse programa foi baseado no programa    */
/* ResizingArrayStack.java que pode ser     */
/* encontrado no site:                      */
/* http://algs4.cs.princeton.edu/13stacks/  */
/* ResizingArrayStack.java.html             */
/*                                          */
/* RandomQueue e uma classe que guarda uma  */
/* colecao de itens e devolve sempre um     */
/* item aleatorio dessa colecao             */
/*                                          */
/* O programa teste recebe uma sequencia de */
/* palavras pelo terminal e devolve essas   */
/* palavras em uma ordem aleatoria          */
/*                                          */
/* Linha de Comando:                        */
/* $ java-algs4 RandomQueue                 */
/* [Seu texto aqui]                         */
/*                                          */
/* Output:                                  */
/* [Uma lista aleatoria das palavras em     */
/*  'Seu texto aqui']                       */
/* **************************************** */

public class RandomQueue<Item> {
  private Item[] queue;
  private int n;

  // Construtor
  // Constroi uma nova RanomQueue vazia de tamanho 2
  public RandomQueue () {
    queue = (Item[]) new Object[2];
    n = 0;
  }

  // Verifica se a RandomQueue esta vazia,
  // Se estiver retorna true, caso contrario retorna false
  public boolean isEmpty () {
    if (n == 0) {
      return true;
    }
    return false;
  }

  // Altera o tamanho da RandomQueue para capacity
  private void resize (int capacity) {
    int i;
    Item[] temp = (Item[]) new Object[capacity];
    for (i = 0; i < n; i++) {
      temp[i] = queue[i];
    }
    queue = temp;
  }
  
  // Adiciona item na RandomQueue
  public void enqueue (Item item) {
    if (n == queue.length) {
      resize (2 * queue.length);
    }
    queue[n] = item;
    n++;
  }

  // Remove e retorna um item aleatorio da RandomQueue
  public Item dequeue () {
    if (isEmpty () == true) {
      StdOut.println ("Erro: Tentando retirar Item de uma RandomQueue vazia");
      return null;
    }
    int rand;
    Item item;
    rand = (int) (Math.random() * n);
    item = queue[rand];
    n--;
    queue[rand] = queue[n];
    if (n > 0 && n == (queue.length / 4)) {
      resize (queue.length / 2);
    }
    return item;
  }

  // Retorna um item da RandomQueue sem remove-lo
  public Item sample () {
    if (isEmpty () == true){
      StdOut.println ("Erro: Tentando verificar Item de uma RandomQueue vazia");
      return null;
    }
    int rand;
    rand = (int) (Math.random() * n);
    return queue[rand];
  }

  // Programa teste, recebe uma sequencia de palavras no terminal
  // e devolve essas palavras em uma ordem aleatoria
  public static void main (String args[]) {
    int i;
    RandomQueue<String> s = new RandomQueue<String> ();
    while (!StdIn.isEmpty()) {
      s.enqueue (StdIn.readString());
    }
    for (i = s.n; i > 0; i--) {
      StdOut.println (s.dequeue());
    }
  }
}
