/*************************************************************************
 *  Nome: Andre Victor dos Santos Nakazawa
 *  NUSP: 9298336
 *
 *  Compilation:  javac BulgingSquares.java
 *  Execution:    java BulgingSquares.java N M H A p
 *  Dependencies: StdOut.java
 *
 *  N = Nodes
 *  M = Edges
 *  H = Hubs
 *  A = Authorities
 *  p = percentage
 *
 *  This is a program that takes as input a page count N, a link count M,
 *  the number of hubs H and authorities H and a percentage p and prints to
 *  standard output N + H + A followed by M random pairs of integers from 0 to
 *  H+N-1 and from H to H+N+A. Then, it prints p*N random pairs to each hub and
 *  p*N random pairs from each authorities.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class RandomWeb {
    public static void main(String[] args) {
        int N, M, H, A;
        double p;
        
        N = Integer.parseInt(args[0]);
        M = Integer.parseInt(args[1]);
        H = Integer.parseInt(args[2]);
        A = Integer.parseInt(args[3]);
        p = Double.parseDouble(args[4]);

        StdOut.println(Integer.toString(N + H + A));
        
        for (int i = 0; i < M; i++) {
            StdOut.print((int) ((H + N) * Math.random()) + " ");
            StdOut.println((int) (H + (N + A) * Math.random()));
        }

        for (int i = 0; i < H; i++) {
            int[] box = new int[N];
            for (int j = 0; j < N; j++)
                box[j] = H + j;

            for (int n = 0; n < p*N; n++) {
                int r = n + (int) ((N - n) * Math.random());
                StdOut.println(box[r] + " " + i);
                
                int aux = box[r];
                box[r] = box[n];
                box[n] = aux;
            }
        }
        
        for (int i = H + N; i < H + N + A; i++) {
            int[] box = new int[N];
            for (int j = 0; j < N; j++)
                box[j] = H + j;

            for (int n = 0; n < p*N; n++) {
                int r = n + (int) ((N - n) * Math.random());
                StdOut.println(i + " " + box[r]);
                
                int aux = box[r];
                box[r] = box[n];
                box[n] = aux;
            }
        }
    }
}

