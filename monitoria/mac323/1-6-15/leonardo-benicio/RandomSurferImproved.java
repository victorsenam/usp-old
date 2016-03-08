/******************************************************************************
 *  Compilation:  javac RandomSurfer.java
 *  Execution:    java RandomSurfer N
 *
 *  % java Transition < tinyGraph.txt | java RandomSurfer 1000000
 *   0.27297 0.26583 0.14598 0.24729 0.06793
 *
 ******************************************************************************/

public class RandomSurferImproved {
    public static void main(String[] args) {

        // number of moves
        int T = Integer.parseInt(args[0]);

        // Transition matrix: p[i][j] = prob. that surfer moves from page i to 
        double[][] p = StdArrayIO.readDouble2D();

        // dimension of the transition matrix
        int N = p.length;

        // freq[i] = # times surfer hits page i
        int[] freq = new int[N];
 
        // simulate the random surfer, starting at a random state
        int state = StdRandom.uniform(N);
        
        for (int t = 0; t < T; t++) {
            state = StdRandom.discrete(p[state]);
            if (Double.isNaN(state)) {
                StdOut.println("OI4: " + state);
            }
            freq[state]++;
        }

        // Print page ranks. 
        for (int i = 0; i < N; i++) {
            StdOut.printf("%8.5f", (double) freq[i] / T); 
        }
        StdOut.println(); 
    }  
} 