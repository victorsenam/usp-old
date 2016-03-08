/******************************************************************************
 *  Nome:         Vinicius Perche de Toledo Agostini
 *  Número USP:   4367487
 *  Compilação:   javac-algs4 WebGraph.java
 *  Execução:     Leia o README
 *  Dependências: StdOut.java
 *
 *  Esta classe modela o usuário da web para o exercício a partir da matriz de
 *  transição do WebGraph, seguindo as regras impostas.
 *  
 *  O usuário clica em um link da página com 90% de probabilidade e com 10%
 *  vai para outra página via seu endereço.
 *  
 *  Ao instanciar RandomSurfer, é calculado o vetor de frequências para as
 *  páginas da web e então é possível calcular o Page Rank.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class RandomSurfer {
	
	WebGraph web;
	private double T;
	private int freq[];
	
	/**
	 *  Constrói um RandomSurfer e calcula o vetor
	 *  de frequências.
	 *
	 *  @param input grafo da web
	 */

	public RandomSurfer(WebGraph input) {
		web = input;
		T = 2000.0;
		freq = new int[web.pages];
		
		int curr = 0;
		
		for (int i = 0; i < T; i++) {
			double rand = Math.random();
			double sum = 0.0;
		
			for (int j = 0; j < web.pages; j++) {
				sum += web.transitionMatrix[curr][j];
				if (rand < sum) {
					curr = j;
					break;
				}
			}
			freq[curr]++;			
		}
	}
	
	/**
	 *  Calcula e imprime o Page Rank de todas as páginas
	 *  para a saída padrão.
	 */

	public void showPageRanks() {
		StdOut.println("\nRanks:\n");
		for (int i = 0; i < web.pages; i++) {
			StdOut.printf("[Page %d] = ", i);
			StdOut.printf("%.5f \n", freq[i]/T);
		}
	}

}