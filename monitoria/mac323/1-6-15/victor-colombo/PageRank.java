import edu.princeton.cs.algs4.*;

// Victor de Oliveira Colombo - 8988657

public class PageRank {

	private static int ITERATIONS = 100;

	public static void main(String[] args) {
		if(args.length > 5 || args.length < 4) {
			StdOut.printf("Wrong usage, try: java PageRank nodes edges hubs authorities [iterations]\n");
			System.exit(1);
		}
		int nodes = Integer.parseInt(args[0]), edges = Integer.parseInt(args[1]);
		int hubs = Integer.parseInt(args[2]), auth = Integer.parseInt(args[3]);
		if(args.length == 5)
			ITERATIONS = Integer.parseInt(args[4]);
		int total = nodes + hubs + auth;
		int[] degree = new int[total];
		int[][] link = new int[total][total];
		StdOut.printf("%d\n", total);
		for(int i = 0; i < edges; i++) {
			int a = StdRandom.uniform(nodes), b = StdRandom.uniform(nodes);
			StdOut.printf("%d %d\n", a, b);
			link[a][b]++;
			degree[a]++;
		}
		for(int i = nodes; i < nodes + hubs; i++) {
			int[] aux = new int[nodes];
			for(int j = 0; j < nodes; j++)
				aux[j] = j;
			StdRandom.shuffle(aux);
			for(int j = 0; j < (nodes + 9) / 10; j++) {
				StdOut.printf("%d %d\n", aux[j], i);
				degree[aux[j]]++;
				link[aux[j]][i]++;
			}
		}
		for(int i = nodes + hubs; i < nodes + hubs + auth; i++) {
			int[] aux = new int[nodes];
			for(int j = 0; j < nodes; j++)
				aux[j] = j;
			StdRandom.shuffle(aux);
			for(int j = 0; j < (nodes + 9) / 10; j++) {
				StdOut.printf("%d %d\n", i, aux[j]);
				degree[i]++;
				link[i][aux[j]]++;
			}
		}
		// Testes PageRank
		// double[][] prob = new double[total][total];
		// for(int i = 0; i < total; i++)
		// 	for(int j = 0; j < total; j++)
		// 		if(degree[i] == 0)
		// 			prob[i][j] = 1.0 / total;
		// 		else
		// 			prob[i][j] = .1 / total + (.9 * link[i][j]) / degree[i];
		// for(int i = 0; i < total; i++) {
		// 	for(int j = 0; j < total; j++) {
		// 		StdOut.printf(" %f", prob[i][j]);
		// 	}
		// 	StdOut.println();
		// }
		// double[] rank = new double[total];
		// rank[0] = 1.0;
		// for(int iter = 0; iter < ITERATIONS; iter++) {
		// 	double[] newRank = new double[total];
		// 	for(int i = 0; i < total; i++)
		// 		for(int j = 0; j < total; j++)
		// 			newRank[i] += rank[j] * prob[j][i];
		// 	rank = newRank;
		// }
		// double sum = 0;
		// StdOut.printf("Nodes:");
		// for(int i = 0; i < nodes; i++) {
		// 	sum += rank[i];
		// 	StdOut.printf(" %f", rank[i]);
		// }
		// StdOut.printf("\nHubs:");
		// for(int i = nodes; i < nodes + hubs; i++) {
		// 	sum += rank[i];
		// 	StdOut.printf(" %f", rank[i]);
		// }
		// StdOut.printf("\nAuthorities:");
		// for(int i = nodes + hubs; i < total; i++) {
		// 	sum += rank[i];
		// 	StdOut.printf(" %f", rank[i]);
		// }
		// StdOut.println();
	}
}
