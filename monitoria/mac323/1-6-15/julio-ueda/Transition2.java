/******************************************************************************* 
 *
 *  Nome:   JULIO KENJI UEDA
 *  No.USP: 9298281
 *
 *******************************************************************************
 *
 *  Compilacao: javac-algs4 Transition.java
 *  Execucao:   java-algs4 Transition < input.txt
 *
 *  % java-algs4 Transition2 < matriz.txt
 *
 *  Este programa eh um filtro que le links da entrada padrao e produz a matriz
 *  de transicao correspondente na saida padrao.
 *  Primeiro, eh contando os links de saida de cada pagina.
 *  Entao eh aplicado a regra 90-10 para gerar a matriz de transicao.
 *  Caso a pagina nao possua links de saida, assume-se que o usuario va para uma
 *  pagina aleatoria.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class Transition2 {

    public static void main(String[] args) {

        int N = StdIn.readInt();
        int[][] numLinks = new int[N][N];
        int[] grauSaida = new int[N];

        // Conta o numero de links.  
        while (!StdIn.isEmpty())  {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            grauSaida[i]++; 
            numLinks[i][j]++; 
        }
        StdOut.println(N + " " + N); 

        // Imprime a distribuicao de probabilidade para a linha i. 
        for (int i = 0; i < N; i++)  {

            // Imprime a probabilidade para a linha j. 
            for (int j = 0; j < N; j++) {
                double p;
                
                // Se a pagina nao possuir links de saida, entao a probabilidade
                // do usuario ir para uma pagina aleatoria eh 1/N.
                if (grauSaida[i] == 0) p = 1.0 / N;
                
                // Caso contrario, eh aplicado a regra 90-10.
                else p = (.90 * numLinks[i][j] / grauSaida[i]) + .10 / N; 
                StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
} 
