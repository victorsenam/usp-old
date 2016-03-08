/******************************************************************************
 *  Nome:         Vinicius Perche de Toledo Agostini
 *  Número USP:   4367487
 *  Compilação:   javac-algs4 WebGraph.java
 *  Execução:     Leia o README
 *  Dependências: In.java, StdOut.java, StdRandom.java
 *
 *  Esta classe modela o grafo da web criado com WebGenerator e calcula a
 *  matriz de transição seguindo as regras do exercício.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class WebGraph {
	
	public int pages;
	public int[] outDeg;
	public int[][] adj;
	public double[][] transitionMatrix;

	/**
	 *  Constrói um WebGraph, calculando outDeg para toda
	 *  página e também calcula a matriz de transição.
	 *
	 *  @param N número de páginas
	 *  @param M número de links
	 *  @param H número de Hubs
	 *  @param A número de Authorities
	 */

	public WebGraph (int N, int M, int H, int A) {
		pages = N + H + A;
		adj = new int[pages][pages];
		outDeg = new int[pages];
		transitionMatrix = new double[pages][pages];

		WebGenerator.generateGraph(N, M, H, A);

		In in = new In("./graph.txt");

		int lines = (H+A) * (int) Math.ceil(.10 * N) + M;

		while (lines > 0) {
			int i = in.readInt();
			int j = in.readInt();
			outDeg[i]++;
			adj[i][j]++;
			lines--;
		}
		
		for (int i = 0; i < pages; i++) {
			for (int j = 0; j < pages; j++) {
				double p;
				if (outDeg[i] != 0) p = .90 * adj[i][j] / outDeg[i] + .10 / N;
				else p = .10 / N;
				transitionMatrix[i][j] = p;
			}
		}			
	}
}
