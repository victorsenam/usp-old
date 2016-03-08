/******************************************************************************
 *  Nome:         Vinicius Perche de Toledo Agostini
 *  Número USP:   4367487
 *  Compilação:   javac-algs4 HubsAndAuthorities.java
 *  Execução:     java-algs4 HubsAndAuthorities N M H A
 *  Dependências: In.java, Out.java, StdOut.java, StdRandom.java
 *
 *  Este programa gera um grafo dirigido com M + H + A arestas representadas por
 *  pares ordenados, um em cada linha, no arquivo "graph.txt".
 *  
 *  Este grafo é interpretado como a web e é então realizado o algoritmo de
 *  Page Rank para determinar o rank de cada página, como pedido no exercício
 *  1.6.15 de http://introcs.cs.princeton.edu/java/16pagerank/
 *
 *  Resultados: Observei experimentalmente que Hubs têm ranks notavelmente
 *  mais altos que a maioria das páginas normais por ser altamente provável
 *  que o usuário chegue neles através de links e Authorities têm ranks
 *  notavelmente baixos, já que apenas têm links saindo deles.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class HubsAndAuthorities {
	
	public static void main (String[] args) {

		int N = Integer.parseInt(args[0]);	// número de páginas
		int M = Integer.parseInt(args[1]);	// número de links
		int H = Integer.parseInt(args[2]);	// número de Hubs
		int A = Integer.parseInt(args[3]);	// número de Authorities

		WebGraph wGraph = new WebGraph(N, M, H, A);
		RandomSurfer rSurfer = new RandomSurfer(wGraph);
		rSurfer.showPageRanks();
	}
}
