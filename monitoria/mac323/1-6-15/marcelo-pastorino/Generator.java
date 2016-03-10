/******************************************************************************
 *  Nome: Marcelo Baiano Pastorino Trylesinski  
 *  Número USP: 9297996
 *
 *  Compilation:  javac-algs4 Generator.java
 *  Execution:    java-algs4 Generator N M H A
 *
 *  O Generator eh um programa que recebe como entrada o numero de paginas N, o 
 *  numero de links M, o numero de hubs H e o numero de authorities A. E imprime
 *  na saida padrao o numero de paginas N seguida de M pares de inteiros de 0 a 
 *  N-1.
 *
 ******************************************************************************/


import edu.princeton.cs.algs4.*;

public class Generator {
	public static void main (String[] args) {
		int N = Integer.parseInt (args[0]);
		int M = Integer.parseInt (args[1]);
		int H = Integer.parseInt (args[2]);
		int A = Integer.parseInt (args[3]);
		int num = N + H + A;

		int[][] ligacoes = new int[num][num];

		for (int i = 0; i < M; i++) {
			ligacoes[StdRandom.uniform (N)][StdRandom.uniform (N)]++;
		}

		for (int i = N; i <= N + H - 1; i++) {
			for (int j = 0; j < (int) (N / 10.0); j++) {
				int page = StdRandom.uniform (N);
				ligacoes[page][i]++;
			}
		}
		
		for (int i = N + H; i <= N + H + A - 1; i++) {
			for (int j = 0; j < (int) (N / 10.0); j++) {
				int page = StdRandom.uniform (N);
				ligacoes[i][page]++;
			}
		}

		StdOut.println (num);

		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				for (int k = 0; k < ligacoes[i][j]; k++) {
					StdOut.printf ("%d %d ", i, j);
				}
			}
			StdOut.println ();
		}
	}
}