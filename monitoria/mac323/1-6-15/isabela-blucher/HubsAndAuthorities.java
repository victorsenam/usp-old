/******************************************************************************
 *  Compilation:  javac HubsAndAuthorities.java
 *  Execution:    java HubsAndAuthorities N M H A
 *  Data files:   http://introcs.cs.princeton.edu/16pagerank/tiny.txt
 *                http://introcs.cs.princeton.edu/16pagerank/medium.txt
 *
 *  Nome: Isabela Blucher 
 *  Número USP: 9298170
 *  Exercício 1.6.15 para MAC0323
 *
 *  Respondendo à pergunta do site: qual tem o maior Page Rank, os Hubs ou
 *  as Authorities. Depois de alguns testes a conclusão atingida é que os 
 *  Hubs tem um Page Rank maior que as Authorities.
 *
 ******************************************************************************/


public class HubsAndAuthorities {


    public static void main(String[] args) {

        int N = Integer.parseInt (args[0]);    // number of pages
        int M = Integer.parseInt (args[1]);
        int H = Integer.parseInt (args[2]);
        int A = Integer.parseInt (args[3]);
        int[] outDegree = new int[N];     // outDegree[i] = # links from page i to anywhere
        int[][] counts = new int[N][N];    // counts[i][j] = # links from page i to page j
        int cont = 0;
        int flag = 0;
        int m = 0;

        StdOut.println (N + H + A);
        
        // Accumulate link counts.  
        for (int k = 0; k < M; k++)  {
            int i = (int) (Math.random() * N);
            int j = (int) (Math.random() * N);
            outDegree[i]++; 
            counts[i][j]++; 
        }

        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                while (flag < counts[k][l]) {
                    StdOut.print (k + " " + l);
                    StdOut.print ("  ");
                    flag++;
                }
            flag = 0;
            }
            if (outDegree[k] > 0)
                StdOut.println ();
        }
        
        for (int k = 0; k < H; k++) {
            for (int l = 0; l < N * 0.1; l++) {
                int i = (int) (Math.random() * N);
                int j = N + k;
                StdOut.println (i + " " + j);
            }
            cont++; 
        }
        int hubs = N + cont;

        for (int k = 0; k < A; k++) {
            for (int l = 0; l < N * 0.1; l++) {
                int i = hubs + k;
                int j = (int) (Math.random() * N);
                StdOut.println (i + " " + j);

            }
        }
            

    }


}


        
