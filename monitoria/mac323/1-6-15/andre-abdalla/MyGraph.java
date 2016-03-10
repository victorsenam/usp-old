/* ************************************************
Nome: André Luiz Abdalla Silveira
N. USP: 8030353

% java-algs4 MyGraph P H A
P: número de páginas
H: número de hubs
A: numero de autoridades

Para escolher o números eu uso
************************************************  */

import edu.princeton.cs.algs4.*;

public class MyGraph {

	public static int picking (double[] prob, int p) {
		double r = Math.random();
		int i;
		for (i = 0; i < p; i ++) {
			if (r < prob[i]) break;
		}
		return i;
	}

	public static void main(String[] args) {
		int pages = Integer.parseInt(args[0]); // Quantas páginas
		int hubs = Integer.parseInt(args[1]); // Hubs
		int auth = Integer.parseInt(args[2]); // Authorities
		int links = 10 * pages; //conexões entre paginas
		double[] prob1 = new double[pages]; // prob saída
		double[] prob2 = new double[pages]; // prob chegada
		int[][] combine = new int[pages][pages];
		int i, j; // Vertices a serem conectados
		int cont;

		for (i = 0; i < pages; i++) {
			for (j = 0; j <pages; j++) {
				combine[i][j] = 0;
			}
		}

		// Nessa fase preliminar, eu definirei as
		// probilidades a serem inseridas nos vetores
		// prob1 e prob2.
		double p_h = 1.0 / (9*hubs + pages);
		for (cont = 0; cont < pages; cont++) {
			if (cont < hubs) prob1[cont] = (double) hubs * p_h;
			else prob1[cont] = p_h;
		}

		double p_a = 1.0 / (9*auth + pages);
		for (cont = 0; cont < pages; cont++) {
			if (cont < (pages - auth)) prob2[cont] = (double) auth * p_a;
			else prob2[cont] = p_a;
		}

		for (cont = 1; cont < pages; cont++) {
			prob1[cont] = prob1[cont-1] + prob1[cont];
			prob2[cont] = prob2[cont-1] + prob2[cont];
		}


		// Para evitar que haja uma página sem links -
		//  - o que seria pouco verossimil - a primeira
		// fase desse gerador de links aleatórios, é
		// atribuir para cada página, ao menos um link.
		cont = 0;
		for (i = 0; i < pages; i++ ) {
			while (true) {
				j = picking (prob2, pages);

				if (i != j && j != pages) {
					combine [i][j]++;
					cont++;
					break;
				}
			}
		}
		
		while (cont != links) {
			while (true) {
				while (true) { 
					i = picking (prob1, pages);
					j = picking (prob2, pages);
					if (i != pages && j != pages) break;
				} // Evitar problemas com Index out of Bounds
				
				if (i != j && combine[i][j] == 0) {
					combine[i][j]++;
					cont++;
					break;
				} 
			}
		}
		System.out.println (pages);

		for (i = 0; i < pages; i++) {
			for (j = 0; j < pages; j++) {
				if (combine[i][j] == 1) {
					System.out.print (i + " " + j + " ");
				}
			}
			System.out.println ("");
		}

	}
}