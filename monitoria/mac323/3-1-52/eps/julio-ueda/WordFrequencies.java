/*******************************************************************************
*
* Nome:   JULIO KENJI UEDA
*
* No.USP: 9298281
*
********************************************************************************
*
* Este programa recebe um texto na entrada padrao e devolve na saida padrao uma 
* lista de palavras organizadas em ordem de frequencia. Caso duas palavras 
* possuam o mesmo numero de frequencias, a lexicograficamente menor aparece 
* antes.
*
* Compilacao: javac-algs4 WordFrequencies.java
* Execucao:   java-algs4 WordFrequencies < input.txt
*
* % java-algs4 WordFrequencies < entrada.txt
*
* % more entrada.txt
* Este é um texto muito legal. Ele contém palavras interessantes e sensacionais,
* por exemplo, estalactite e defenestrar. Ele também contém repetições.
*
* % java-algs4 WordFrequencies < entrada.txt
*
* contém 2
* e 2
* ele 2
* defenestrar 1
* estalactite 1
* este 1
* exemplo 1
* interessantes 1
* legal 1
* muito 1
* palavras 1
* por 1
* repetições 1
* sensacionais 1
* também 1
* texto 1
* um 1
* é 1
*
*******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.*;

public class WordFrequencies {

   static class Celula {
      String elemento;
      int frequencia;
      Celula proximo;

      public Celula(String elemento) {
         this.elemento = elemento;
         this.frequencia = 1;
         this.proximo = null;
      }
   }

   static class Lista {
      Celula primeira;
      Celula atual;
      int freqmax;

      public Lista() {
         primeira = atual;
         atual = null;
         freqmax = 0;
      }

      public void adiciona(Celula nova) {
         if (atual == null) { // primeira celula
            atual = nova;
            primeira = atual;
         }
         else {
            // Se a palavra eh repetida, aumenta a frequencia.
            if (nova.elemento.compareTo(atual.elemento) == 0)
               atual.frequencia++;
            // Caso contrario a nova Celula entra na posicao seguinte.
            else {
               atual.proximo = nova;
               atual = atual.proximo;
            }
         }
      }

      // Calcula o valor maximo de repeticao em uma palavra.
      public void freqmax() {
         atual = primeira;
         while (atual != null) {
            if (freqmax < atual.frequencia) freqmax = atual.frequencia;
            atual = atual.proximo;
         }
      }

      // Imprime a lista em orderm decrescente de frequencia lexicograficamente.
      public void imprime() {
         freqmax();
         for (int i = freqmax; i > 0; i--) {
         atual = primeira;
            while (atual != null) {
               if (atual.frequencia == i)
                  StdOut.printf("%s %d\n", atual.elemento, atual.frequencia);
               atual = atual.proximo;
            }
         }
      }
   }

   public static void main (String[] args) {

      // Caracteres que serao descartados.
      String chars = "[\"'!@#$%¨&*()_=+`´{}^~\\[\\]<,>\\|.:;?//0123456789]";

      // Armazena o texto em uma String.
      String texto = StdIn.readAll();

      // Trata o texto.
      texto = texto.toLowerCase().replaceAll(chars, "").replaceAll("( |\n|\r)+", " ");

      // Divide o texto em palavras.
      String[] palavra = texto.split(" ");

      // Ordena as palavras.
      Arrays.sort(palavra);

      Lista lista = new Lista();
      
      for (int i = 0; i < palavra.length; i++) {
         Celula nova = new Celula(palavra[i]);
         lista.adiciona(nova);
      }
      lista.imprime();
   }
}
