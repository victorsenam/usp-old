
/*************************************
 * 
 * MAC0323 - 1º sem de 2016
 * 
 * São Paulo, 5 de março de 2016
 * 
 * Creative Exercise 1.6.15 (Hubs and authorities; IntroCS)
 *
 * Gino Chen Hsiang-Jan
 * Número USP: 748536
 * 
*************************************/

import edu.princeton.cs.algs4.*;

/*
 * Como não há espeficificação de como deve ser o formato de saída dos arquivos
 * irei padronizar que:
 * 
 * 1. Os códigos 0 a A - 1     (quantidade de authorities) dos vérticas são authorities
 * 2. Os códigos A a H + A - 1 (quantidade de hubs) dos vérticas são hubs
 * 
 * 
 * O programa terá como saída N N H A antes da matrix de transição.
 * 
 * Desta forma não será necessário um campo a mais para especificar qual tipo de vértices o programa RandomSurfer
 * está lidando. 
 */
public class GenLinks {
	static int Rnd(int N) {
		return (int)(Math.random() * N); 
	}
	static int Rnd(int Primeiro, int Ultimo) {
		return Rnd(Ultimo - Primeiro + 1) + Primeiro; 
	}
	public static void main(String[] args) {
		int N ; // (quantidade de vértices)
		int M ; // (quantidade de arestas)
		int H ; // (quantidade de hubs)
		int A ; // (quantidade de authorities)
		
		if (args.length < 4)
			throw new RuntimeException("Número de parâmetros inválido!");
		N = Integer.parseInt(args[0]);
		M = Integer.parseInt(args[1]);
		H = Integer.parseInt(args[2]);
		A = Integer.parseInt(args[3]);
		if (H + A > N)
			throw new RuntimeException("Um ou mais parâmetros: hubs, authorities e vertices está incorreto!");
		
		int N10p = (N * 10 + 5) / 100; // (10 % quantidade de vértices) arredonda para cima, caso N % 10 >= 5
		int Cross[][] = new int[N][N];
		
		int i;
		int j;
		
		for (i = 0; i < N; ++i)
			for (j = 0; j < N; ++j)
				Cross[i][j] = 0;
		
		int nTarget        ; // vértice "link para"
		int MR = M; // Arestas restantes
		
		i = 0;
		
		// Authorities
		while (MR > 0 && i < A) {
			for (j = 0; j < N10p; ++j) {
				++Cross[i][Rnd(N)];
				--MR;
			}
			++i;
		}
		// Hubs
		while (MR > 0 && i < H + A) {
			for (j = 0; j < N10p; ++j) {
				++Cross[Rnd(N)][i];
				--MR;
			}
			++i;
		}
		// Resto
		while (MR > 0) {
			for (j = 0; j < N10p; ++j) {
				++Cross[Rnd(A + H, N - 1)][Rnd(N)];
				--MR;
			}
		}
		StdOut.println(N + " " + H + " " + A);
		
		int k;
		boolean EmptyLine; 
		
		for (i = 0; i < N; ++i) {
			EmptyLine = true;
			for (j = 0; j < N; ++j) {
				if (Cross[i][j] > 0)
					EmptyLine = false;
				for (k = 0; k < Cross[i][j]; ++k)
					StdOut.print(" " + i + " " + j);
			}
			if (!EmptyLine)
				StdOut.println();
		}
	}
}
