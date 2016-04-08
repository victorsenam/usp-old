import java.util.*;
import edu.princeton.cs.algs4.*;
/******************************************************************************
 *
 * Nome:   JULIO KENJI UEDA
 *
 * No.USP: 9298281
 *
 *******************************************************************************
 *
 * Este programa gera uma mao aleatoria de 13 cartas (Bridge) a partir da
 * RandomQueue.
 *
 * Compilacao: javac-algs4 Bridge.java
 *
 * Execucao:   java-algs4 Bridge
 *
 * % java-algs4 Bridge
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class Bridge { 
   private int numero; // dois = 0, tres = 1,..., reis = 11, as = 12
   private int naipe;  // paus = 0, ouro = 1, copas = 2, espada = 3
   
   // Cria uma nova carta baseado em um inteiro (0 = 2P, 1 = 3P, ..., 51 = AE)
   public Bridge(int i) {
      numero = i % 13;
      naipe  = i / 13;
   }

   // Mostra cartas como "2P", "9C", "JS", "AO"
   public String toString() {
      String numeros = "23456789DJQKA"; // (D) dez
      String naipes  = "POCE"; // (P) paus, (O) ouro, (C) copas, (E) espada
      return numeros.charAt(numero) +  "" + naipes.charAt(naipe);
   }

   public static void main(String[] args) {

      // Cria um fila de 52 cartas.
      RandomQueue<Bridge> fila = new RandomQueue<Bridge>(52);
      
      for (int i = 0; i < 52; i++) {   // repete 52 vezes
         Bridge carta = new Bridge(i); // constroi uma carta
         fila.coloca(carta);           // coloca a carta na fila
      }
      for (int i = 0; i < 13; i++)     // repete 13 vezes
         StdOut.println(fila.tira());  // tira uma carta de fila e mostra
   }
}
