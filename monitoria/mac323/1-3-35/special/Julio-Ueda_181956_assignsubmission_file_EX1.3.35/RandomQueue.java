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
/*******************************************************************************
 *
 * Nome:   JULIO KENJI UEDA
 *
 * No.USP: 9298281
 *
 *******************************************************************************
 *
 * Este programa armazena itens na pilha e desempilha de forma aleatoria.
 *
 * Compilacao: javac-algs4 RandomQueue.java
 *
 * Execucao:   java-algs4 RandomQueue argumento1 argumento2 ...
 *
 * % java-algs4 RandomQueue 1 2 3 4 5
 *
 ******************************************************************************/


import edu.princeton.cs.algs4.*;
import java.util.*;

public class RandomQueue<Item> implements Iterable<Item> {

   private Item[] conteudo; // vetor de Item
   private int N;           // quantidade de Itens

  // Construtor da fila.
   public RandomQueue(int tamanho) {
      conteudo = (Item[]) new Object[tamanho];
   }

   // Redimensiona o tamanho da fila pelo valor especificado.
   private void redimensionar(int tamanho) {
      Item[] temp = (Item[]) new Object[tamanho];
      for (int i = 0; i < N; i++) temp[i] = conteudo[i];
      conteudo = temp;
   }

   // Coloca o Item na fila.
   public void coloca(Item item) {
      if (N == conteudo.length)                 // Se numero de Itens = tamanho
         redimensionar(2 * conteudo.length);    // fila dobra de tamanho
      conteudo[N++] = item;                     // Item entra na fila
   }

   // Retira aleatoriamente um Item da fila.
   public Item tira() {
      int aleatorio = (int)(N * Math.random()); // gera um numero aleatorio
      Item item = conteudo[aleatorio];          // item aleatorio escolhido
      conteudo[aleatorio] = conteudo[--N];      // ultimo Item entra no lugar
      conteudo[N] = null;                       // evita loitering
      if (N > 0 && N < conteudo.length / 4)     // Se numero de Itens = pouco
         redimensionar(conteudo.length / 4);    // fila diminui de tamanho
      return item;                              // devolve um Item aleatorio
   }

   // Devolve aleatoriamente um Item da fila (sem remocao).
   public Item amostra() {
      int aleatorio = (int)(N * Math.random()); // gera um numero aleatorio
      return conteudo[aleatorio];               // devolve um Item aleatorio
   }

   // Implementacao do Iterador aleatorio.
   public Iterator<Item> iterator() {
      return new RandomIterator();
   }

   public boolean isEmpty() {
       return true;
    }

   private class RandomIterator implements Iterator<Item>{
      private int i = N;
      public boolean hasNext() {
         return i > 0;
      }
      public Item next() {
         if (!hasNext()) throw new NoSuchElementException();
         int aleatorio = (int)(i * Math.random());
         Item item = conteudo[aleatorio];
         conteudo[aleatorio] = conteudo[--i];
         conteudo[i] = item;
         return item;
      }
   }
   // unit test
   public static void main(String[] args) {

      // Cria uma nova Random Queue de tamanho 1 e pode colocar mais de 1 item
      // na lista para testar o redimensionamento.
      RandomQueue<String> teste = new RandomQueue<String>(1); 
      for (int i = 0; i < args.length; i++) teste.coloca(args[i]);

      // Teste do Iterator aleatorio.
      StdOut.println("Teste do Iterator:");
      for (String saida: teste) StdOut.println(saida);
      StdOut.println();

      // Teste da amostra (possui repeticao de itens).
      StdOut.println("Teste da amostra:");
      for (int i = 0; i < args.length; i++) StdOut.println(teste.amostra());
      StdOut.println();

      // Teste de tirar Itens da fila.
      StdOut.println("Tirando da fila:");
      for (int i = 0; i < args.length; i++) StdOut.println(teste.tira());
      StdOut.println();
   }
}
