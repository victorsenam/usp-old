import java.util.*;
import edu.princeton.cs.algs4.*;
/*******************************************************************************

TesteVisual

Nome:   Matheus Tavares Bernardino
Nº USP: 9292987
Data:   27/03/2016


Descrição:
Esta classe implementa um teste visual para o RandomIterator da classe
RandomQueue. Dado um N, a classe empilha os inteiros de 1 até N (incluindo 1 e
N) em uma RandomQueue e itera aleatoriamente sobre essa pilha T vezes. Então, é
desenhado um histograma que representa quantas vezes cada permutação da
sequência "1...N" foi gerada como resultado do RandomIterator.

Compilação:    javac-algs4 TesteVisual.java
Execução:      java-algs4 TesteVisual N T

Ex de execução:
    $ java-algs4 TesteVisual 6 100000
*******************************************************************************/


import edu.princeton.cs.algs4.*;

public class TesteVisual {

    /*retorna uma permutacao aleatória dos inteiros da RandomQueue lista. Essa
    permutação é feita iterando aleatoriamente sobre lista.*/
    private static String permutacao (RandomQueue<Integer> lista) {
        String s = "";
        for (int k : lista)
            s = s.concat (Integer.toString(k));
        return s;
    }

    //retorna o fatorial de N
    private static int fatorial (int N) {
        int fat = 1;
        for (int i = 1; i <= N; i++) {
            fat = fat * i;
        }
        return fat;
    }

    /*Realiza T iterações aleatórias sobre a RandomQueue lista (que contem
    inteiros de 1 até N) e constroi um Histograma com a frequência em que cada
    uma das N! permutações de "1...N" foram resultado da iteração.*/
    private static void constroiHistograma
    (RandomQueue<Integer> lista, int N, int T) {
        int totalPerms = fatorial (N);
        String[] perms = new String [totalPerms];    //para saber quais
        Histogram hist = new Histogram (totalPerms); //permutações já apareceram

        int newSpace = 0;       //espaço livre para inserir uma permutação nova
        for (int i = 0; i < T; i++) {
            String s = permutacao (lista);
            boolean permNova = true;
            for (int j = 0; j < newSpace; j++) {
                if (s.compareTo (perms[j]) == 0) {
                    hist.addDataPoint (j);
                    permNova = false;
                    break;
                }
            }
            if (permNova) {
                perms[newSpace] = s.toString();
                hist.addDataPoint (newSpace);
                newSpace++;
            }
        }
        hist.draw();
    }

    /*Constroi a RandomQueue enfilerando os inteiros de 1 até N, nesta ordem e
    retorna a lista.*/
    private static RandomQueue<Integer> constroiLista (int N) {
        RandomQueue<Integer> lista = new RandomQueue ();
        for (int i = 1; i <= N; i++) {
            lista.enqueue (i);
        }
        return lista;
    }

    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        int T = Integer.parseInt (args[1]);
        RandomQueue<Integer> lista = constroiLista (N);
        constroiHistograma (lista, N, T);
    }

}
