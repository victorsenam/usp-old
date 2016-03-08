//Nome; Lucas Seiki Oshiro
//Numero USP: 9298228

/* Obs: Os hubs ganham das authorities no PageRank. As authorities em geral não 
   possuem possuem um numero de visualizações que se destaque em relação às
   outras páginas. */

import edu.princeton.cs.algs4.*;

public class Generator {
    //Imprime a matriz mat
    public static void printMat (int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++)
                StdOut.print (mat[i][j] + " ");
            StdOut.println ();
        }
    }

    //main
    public static void main (String[] args) {
        int N = Integer.parseInt (args[0]);
        int M = Integer.parseInt (args[1]);
        int H = Integer.parseInt (args[2]);
        int A = Integer.parseInt (args[3]);
        int[][] mat = new int[N][N];

        //Gera M links aleatorios
        for (int i = 0; i < M; i++) {
            int x = StdRandom.uniform (N);
            int y = StdRandom.uniform (N);
            mat[x][y]++;
        }

        int[] hubs = new int[H];
        int[] auths = new int[A];

        //Escolhe H paginas diferentes para serem hubs
        for (int i = 0; i < H; i++) {
            int x = StdRandom.uniform (N);
            for (int j = 0; j < i; j++) {
                if (hubs[j] == x) {
                    j = 0;
                    x = StdRandom.uniform (N);
                }
            }
            hubs[i] = x;
        }

        //Escolhe A paginas diferentes para serem authorities
        for (int i = 0; i < A; i++) {
            int x = StdRandom.uniform (N);
            for (int j = 0; j < i; j++) {
                if (auths[j] == x) {
                    j = 0;
                    x = StdRandom.uniform (N);
                }
            }
            auths[i] = x;
        }

        //Faz os hubs serem apontados por 10 paginas
        for (int i = 0; i < H; i++) {
            int npages = 0;
            for (int j = 0; j < N; j++)
                if (mat[j][hubs[i]] > 0) npages++;

            while (npages < N / 10) {
                int x = StdRandom.uniform (N);
                
                while (mat[x][hubs[i]] > 0)
                    x = StdRandom.uniform (N);
                    
                mat[x][hubs[i]]++;
                npages++;
            }
        }

        //Faz as authorities apontarem para 10 paginas
        for (int i = 0; i < A; i++) {
            int npages = 0;
            for (int j = 0; j < N; j++)
                if (mat[auths[i]][j] > 0) npages++;

            while (npages < N / 10) {
                int x = StdRandom.uniform (N);
                
                while (mat[auths[i]][x] > 0)
                    x = StdRandom.uniform (N);
                    
                mat[auths[i]][x]++;
                npages++;
            }
        }

        //Imprime N e os pares 
        StdOut.println (N);
        for (int i = 0; i < mat.length; i++) {
            boolean pulaLinha = false;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] > 0) pulaLinha = true;
                for (int k = 0; k < mat[i][j]; k++)
                    StdOut.printf ("%d %d  ", i, j);
            }
            if (pulaLinha) StdOut.println ();
        }

    }
}
