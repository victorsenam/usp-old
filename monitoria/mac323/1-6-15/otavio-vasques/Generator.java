/* Gerador de web aleátorio - Creative Exercise 14
	MAC 323 - Yoshiharu Kohayakawa
	Otávio Vasques - 8944665

	O programa recebe os 4 argumentos abaixo na linha de comando.

    N (quantidade de vértices)
    M (quantidade de arestas)
    H (quantidade de hubs)
    A (quantidade de authorities)

	Por sugestão do Yoshi os hubs vão receber 0.1*N links e as
	authorities vão apontar para 0.1*N páginas.

	As páginas normais estão em [0, N), os hubs em [N, N+H) e as authorities
	em [N+H, A).

	O número total de vértice será de N + H + A.

	Após fazer os testes nota-se que quando o numero de links é menor que o de
	páginas o pagerank dos hubs aumenta em relação aos links normais.
	O pagerank dos authorities se mantém pequeno em qualquer cenário.
*/


import edu.princeton.cs.algs4.*;

public class Generator {

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]); // vértices
		int M = Integer.parseInt(args[1]); // arestas
		int H = Integer.parseInt(args[2]); // hubs
		int A = Integer.parseInt(args[3]); // authorities
		int total = N+H+A;                 // total de vértices
		int p1;
		int p2;
		double temp;
		String[] linkTables = new String[total];

		for (int i = 0; i < total; i++)
			linkTables[i] = "";

		StdOut.println(total);


		// links normais
		for (int i = 0; i < M; i++) {
			temp = StdRandom.uniform() * (N);
			p1 = (int) temp;
			p2 = p1;
			while(p2 == p1) {
				temp = StdRandom.uniform() * (N);
				p2 = (int) temp;
			}
			linkTables[p1] += String.valueOf(p1) + " " + String.valueOf(p2) + "    ";
		}

		// hubs
		for (int i = 0; i < H; i++)
			for (int j = 0; j < 0.1*N; j++) {
				temp = StdRandom.uniform() * (N);
				p1 = (int) temp;
				linkTables[p1] += String.valueOf(p1) + " " + String.valueOf(N+i) + "    ";
			}

		// authorities
		for (int i = 0; i < A; i++)
			for (int j = 0; j < 0.1*N; j++) {
				temp = StdRandom.uniform() * (N);
				p1 = (int) temp;
				linkTables[N+H+i] += String.valueOf(N+H+i) + " " + String.valueOf(p1) + "    ";
			}

		for (int i = 0; i < total; i++)
			StdOut.println(linkTables[i]);
	}
}
