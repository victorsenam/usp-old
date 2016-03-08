/*
 * Autor: Luis Gustavo Bitencourt Almeida
 * Numero USP: 9298207
 */

public class HubsAndAuthorities {

    public static void main (String[] args) {
        int N, M, H, A;
        N = Integer.parseInt (args[0]);
        M = Integer.parseInt (args[1]);
        H = Integer.parseInt (args[2]);
        A = Integer.parseInt (args[3]);
        int inDegree[] = new int[N];
        int outDegree[] = new int[N];
        int remaining = M;

        StdOut.println (N);

        while (H > 0) {
            int hub = StdRandom.uniform (0, N);
            if (outDegree[hub] != 0 && outDegree[hub] >= M/10) continue;
            for (int i = 0; i < M/10; i++) {
                int x = StdRandom.uniform (0, N);
                StdOut.println (hub + " " + x);
                outDegree[hub] += 1;
                inDegree[x] += 1;
                remaining--;
            }
            H--;
        }

        while (A > 0) {
            int authority = StdRandom.uniform (0, N);
            if (inDegree[authority] != 0 && inDegree[authority] >= M/10) continue;
            for (int i = 0; i < M/10; i++) {
                int x = StdRandom.uniform (0, N);
                StdOut.println (x + " " + authority);
                outDegree[x] += 1;
                inDegree[authority] += 1;
                remaining--;
            }
            A--;
        }

        while (remaining > 0) {
            int x, y;
            x = StdRandom.uniform (0, N);
            y = StdRandom.uniform (0, N);
            StdOut.println (x + " " + y);
            remaining--;
        }

        /*
         * Authorities tem maior page rank que hubs.
         * Isso foi constatado com alguns testes, mas eh intuitivo.
         * Dado que authorities sao apontadas por varias paginas eh de
         * se esperar que sejam mais relevantes.
         */
    }

}
