/* Recebe a quantidade de nós N e a quantidade de arestas M
// e gera um grafo dirigido aleatório, dado na forma de pares
// numerados i j, que significa i -> j
//
// Exemplo de chamada:
// % java-algs4 Generator 5 10
//
// Autor: João Francisco Lino Daniel
//              7578279
*/

import edu.princeton.cs.algs4.*;

public class Generator {

    public static int N, M, HA;

    public static int[] randomAndDifferents() {
        int[] numeros = new int[HA];

        numeros[0] = (int) (Math.random() * N);
        for (int i = 1; i < HA; i++) {
            do {
                numeros[i] = (int) (Math.random() * N);
            } while (numeros[i] == numeros[i-1]);
        }

        return numeros;
    }

    public static void main(String[] args) {
        N = Integer.parseInt(args[0]);  // quantidade de nós
        M = Integer.parseInt(args[1]);  // quantidade de links
        HA = Integer.parseInt(args[2]); // quantidade de hubs e authorities

        int contador = 0;

        int[] authorities = randomAndDifferents();
        int[] hubs = randomAndDifferents();

        StdOut.println(N);

        for (int h = 0; h < HA; h++) {
            for (int i = 0; i < N/10; i++) {
                int x = (int) (Math.random() * N);
                StdOut.println(x + " " + hubs[h]);
            }
        }

        
        for (int a = 0; a < HA; a++) {
            for (int i = 0; i < N/10; i++) {
                int x = (int) (Math.random() * N);
                StdOut.println(authorities[a] + " " + x);
            }
        }

        for (int i = 0; i < (M - (HA * N/5)); i++) {
            int x = (int) (Math.random() * N);
            int y = (int) (Math.random() * N);
            StdOut.println(x + " " + y);
        }
    }
}