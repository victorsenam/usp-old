/**
 * 
 * @author 6800149 (Rodrigo Alves Souza)
 *
 * Usage: java HubsAuthGenerator N M H A
 * Example: java HubsAuthGenerator 20 10 3 2
 *
 * Exemplo de uso completo: java-introcs HubsAuthGenerator 4 5 3 2 | java-introcs Transition | java-introcs RandomSurfer 1000000
 *
 * Pergunta: Quais links tem um maior pagerank?
 * Reposta: Rodando o RandomSurfer varias vezes eh possivel verificar que as paginas
 * com maiores pageranks sao os Hubs, ou seja, aquelas que tem o maior numero de
 * links apontando para ela
 */

public class HubsAuthGenerator {

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int M = Integer.parseInt(args[1]);
		int H = Integer.parseInt(args[2]);
		int A = Integer.parseInt(args[3]);
		StdOut.println(N);
		StdOut.println(H);
		StdOut.println(A);
		
		// Gera N nodes com M arestas randomicamente
		for (int i = 0; i < M; i++) {
			int n1 = (int)(Math.random() * N);
			int n2 = (int)(Math.random() * N);
			while (n1 == n2)
				n2 = (int)(Math.random() * N);
			StdOut.printf("%d %d\n", n1, n2);
		}
		
		// Para cada hub, seleciona randomicamente 10porcento das paginas e aponta para ele
		int hub, authority, page, pages;
		for (int i = 0; i < H; i++) {
			hub = i + N; // Proximo numero possivel para um Hub
			pages = (int)Math.ceil(N*0.1);
			for (int j = 0; j < pages; j++) {
				page = (int)(Math.random() * N);
				StdOut.printf("%d %d ", page, hub);
			}
		}
		StdOut.println();
		
		// Para cada authority, ele aponta randomicamente para 10 porcento das paginas
		for (int i = 0; i < A; i++) {
			authority = i + N + H; // Proximo numero possivel para um Authority
			pages = (int)Math.ceil(N*0.1);
			for (int j = 0; j < pages; j++) {
				page = (int)(Math.random() * N);
				StdOut.printf("%d %d ", authority, page);
			}
		}
		StdOut.println();
	}

}
