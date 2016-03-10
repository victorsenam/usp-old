/*************************************************************************
 *  Creative Ex. 1.6.15 (Hubs and authorities)
 *
 *  Nome: Carybé Gonçalves Silva
 *  Nº USP: 8033961
 *
 *  Compilation: javac-algs4 Generator.java 
 *  Execution:	java-algs4 Generator N M H A
 *
 *  Dependencies: StdOut.java StdRandom.java
 *
 * Example:
 *  % java-algs4 Generator 10 50 5 5 
 *
 *************************************************************************/
import edu.princeton.cs.algs4.*;
import java.util.Arrays;
import java.util.Collections;

public class Generator {

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);  // Number of "normal" nodes
		int M = Integer.parseInt(args[1]);  // Number of links of such nodes (between themselves to the hubs and authorities)
		int H = Integer.parseInt(args[2]);   // Number of hubs
		int A = Integer.parseInt(args[3]);   // Number of authorities
		int total = N+H+A; // Total number of nodes
		Integer[] nodes = new Integer[total];
		int i, j, chub, cauth, rand, xchg;

		StdOut.println(total);

		for (i = 0; i < total ; i++) {
			nodes[i] = i;
		}

		// Random shuffling of the array that represents each node
		Collections.shuffle(Arrays.asList(nodes));

		// Sorting random nodes links
		for (i = 0 ; i < M ; i++) {
			StdOut.println(StdRandom.uniform(total) + " " + StdRandom.uniform(total));
		}

		// Sorting random hubs links
		for (i = 0 ; i < H ; i++) {
			chub = (int) Math.ceil(StdRandom.uniform(total/10f,total));
			for (j = 0 ; j<chub ; j++) {
				rand = StdRandom.uniform(total-j); // Random index excluding the "last one"
				
				// Exchange of the number in the random index with the number
				// in the "last one" index
				xchg = nodes[rand];
				nodes[rand] = nodes[total - j - 1];
				nodes[total - j - 1] = xchg;
				
				StdOut.println(xchg + " " + (i+N));
			}
		}

		// Sorting random authorities links (same process of the hubs)
		for (i = 0 ; i < A ; i++) {
			cauth = (int) Math.ceil(StdRandom.uniform(total/10f,total));
			for (j = 0 ; j<cauth ; j++) {
				rand = StdRandom.uniform(total-j);
				
				xchg = nodes[rand];
				nodes[rand] = nodes[total - j - 1];
				nodes[total - j - 1] = xchg;
				
				StdOut.println((i+N+H) + " " + xchg);
			}
		}
	}
}
