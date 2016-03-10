/* Ludmila Ferreira Vicente e Silva    NUSP 7557136 */
/* mac0323 - miniep_1_6_15                          */ 

public class GraphGenerator {

    public static void main (String[] args) {
        
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int H = Integer.parseInt(args[2]);
        int A = Integer.parseInt(args[3]);

        int[][] adj = new int[N + H + A][N + H + A];
        int[] aux = new int[N];

        // Inicializa a matriz com zeros
        for (int i = 0; i < N + H + A; i++) 
            for (int j = 0; j < N + H + A; j++) 
                adj[i][j] = 0;
            
        

        for (int i = 0; i < N; i++) aux[i] = 0;

        // Geração do Grafo
        // Cria arestas aleatoriamente baseado em M
        for (int k = 0; k < M; k++) {
            int i = StdRandom.uniform (N);
            int j = StdRandom.uniform (N);
            adj[i][j]++;
        }

        // Geração de hubs
        for (int k = 0; k < H; k++) {
            int hub = N + k;
		    int[] toHubs = new int[N];
		    for (int i = 0; i < N; i++) toHubs[i] = 0;
		    int count = 0;
		    while (count < (int) (0.1*N)) {
			    int i = StdRandom.uniform(N);
			    if(toHubs[i] == 0) {
				    toHubs[i] = 1;
				    adj[hub][i]++;
				    count++;
                }
            }
        }

	    // Geração de authorities
        for (int k = 0; k < A; k++) {
            int count = 0;
		    int aut = N + H + k;
		    int[] authTo = new int[N];
		    for (int i = 0; i < N; i++) authTo[i] = 0;
		    while (count < (int) (0.1*N)) {
			    int i = StdRandom.uniform(N);
			    if (authTo[i] == 0) {
				    authTo[i] = 1;
				    adj[aut][i]++;
				    count++;
                }
		    }
	    }
      
        // Imprime saida do grafo
        StdOut.printf ("%d\n", N+H+A);
        for (int i = 0; i < N + H + A; i++ ) 
            for (int j = 0; j < N + H + A; j++) 
                if (adj[i][j] != 0) 
                    for (int k = 0; k < adj[i][j]; k++) 
                        StdOut.printf ("%d %d ", i, adj[i][j]);


    }
}
