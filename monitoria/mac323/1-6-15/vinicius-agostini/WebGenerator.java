/******************************************************************************
 *  Nome:         Vinicius Perche de Toledo Agostini
 *  Número USP:   4367487
 *  Compilação:   javac-algs4 WebGenerator.java
 *  Execução:     Leia o README
 *  Dependências: Out.java, StdOut.java, StdRandom.java
 *
 *  Este módulo gera um grafo dirigido com M + H + A arestas representadas por
 *  pares ordenados, um em cada linha, no arquivo "graph.txt".
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class WebGenerator {

   /**
    *  Marca todas as páginas como não usadas.
	*/
	public static boolean[] reset(boolean[] used) {
		int N = used.length;
		
		for (int i = 0; i < N; i++)
			used[i] = false;
		
		return used;
	}
	
	/**
	 *  Gera pares ordenados de forma a criar Hubs ou Authorities.
	 *
	 *  @param X número de vértices novos
	 *  @param N número de páginas normais
	 *  @param page número da próxima página não criada
	 *  @param used diz quais páginas já têm links com o Hub/Authority
	 *  @param outLink diz se o link está saindo ou entrando no Hub/Authority
	 *  @param out arquivo de saída
	 */

	public static void generate(int X, int N, int page, boolean[] used, boolean outLink, Out out) {
		int cur = page;
		while (X > 0) {
			
			reset(used);

			// Gera links aleatórios com 10% das páginas
			int links = (int) Math.ceil(.10 * N);
			while (links > 0) {
				int a = StdRandom.uniform(N);
				if (used[a]) continue;
				used[a] = true;
				if (outLink == true)
					out.println(cur + " " + a);
				else
					out.println(a + " " + cur);
				links--;
			}
			X--;
			cur++;
		}
	}

	/**
	 *  Gera um grafo dirigido em forma de pares ordenados
	 *  para o arquivo "graph.txt"
	 *
	 *  @param N número de páginas
	 *  @param M número de links
	 *  @param H número de Hubs
	 *  @param A número de Authorities
	 */

	public static void generateGraph(int N, int M, int H, int A) {
		
		boolean[] used = new boolean[N];
		Out out = new Out("./graph.txt");

		while (M > 0) {
			int a = StdRandom.uniform(N);
			int b = StdRandom.uniform(N);
			
			if (a == b) continue;
			
			out.println(a + " " + b);
			M--;
		}
		// Gera Hubs
		generate(H, N, N, used, false, out);

		// Gera Authorities
		generate(A, N, N+H, used, true, out);
	}
}