import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * @author: Eduardo Pinheiro
 * NUSP:    8936798
 */
import edu.princeton.cs.algs4.*;
import java.util.Arrays;

/* Lista que guarda as permutações e suas frequências para a criação do histograma */
public class Permutation
{
  private class Node
  {
    Integer[] items = null;
    int frequence = 0;
    Node next;
  }

  private Node first;
  private int size;

  public Permutation() {
    first = new Node();
    size = 0;
  }

  /* Adiciona a lista uma nova permutação, caso a permutação já foi encontrada, aumenta a sua frequência */
  public void append(Integer[] item) {
    Node aux = first;

    while (aux != null && aux.next != null && !Arrays.equals(aux.next.items, item)) aux = aux.next;

    if (aux != null && aux.next != null && Arrays.equals(aux.next.items, item)) {
      aux.next.frequence++;
      return;
    }

    Node new_node = new Node();
    new_node.items = item;
    new_node.frequence = 1;
    aux.next = new_node;
    size++;
  }

  /* Imprime na saída padrão as permutações, únicas, nesta lista */
  public void print() {
    Node aux = first.next;
    while (aux != null) {
      for (int i = 0; i < aux.items.length; i++) {
        StdOut.print(aux.items[i] + " ");
      }
      StdOut.println();
      aux = aux.next;
    }
  }

  /* Retorna um vetor com a frequência de cada permutação */
  public Integer[] frequencies() {
    Node aux = first.next;
    Integer[] n = new Integer[size];
    int i = 0;
    while (aux != null) {
      n[i] = aux.frequence;
      aux = aux.next;
      i++;
    }
    return n;
  }

  /* Cliente */
  public static void main(String[] args) {
    Permutation p = new Permutation();

    for (int i = 0; i < 3; i++) {
      Integer[] n = new Integer[5]; 
      for (int j = 0; j < 5; j++) {
        n[j] = StdIn.readInt();
      }
      p.append(n);
    }
    p.print();
    Integer[] n = p.frequencies();
   for (int  i = 0; i <  n.length; i++) {
     StdOut.print(n[i] + " ");
   }
  }
}
