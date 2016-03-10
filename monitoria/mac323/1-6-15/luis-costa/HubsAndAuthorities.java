/* Nome: Luís Felipe de Melo Costa Silva
// Número USP: 9297961
// Programa: HubsAndAuthorities.java
// Observação: A construção desse programa foi criada baseando-se 
//             no arquivo Transition.java, de
//             http://introcs.cs.princeton.edu/java/16pagerank/Transition.java,
//             pelo menos no começo de sua construção.
//
//  Compilação:              javac HubsAndAuthorities.java
//  Execução:                java HubsAndAuthorities N M H A
//  Arquivos necessários:    StdOut.java
//               
//
// O arquivo StdOut.java se encontra em
// http://introcs.cs.princeton.edu/java/15inout/StdOut.java.html
*/ 

public class HubsAndAuthorities {

    public static void main (String[] args) {
        
        int N = Integer.parseInt (args[0]); /* Número de páginas. */
        int M = Integer.parseInt (args[1]); /* Número de links. */
        int H = Integer.parseInt (args[2]); /* Número de Hubs. */
        int A = Integer.parseInt (args[3]); /* Número de Authorities. */

        int n = N + H + A; /* Número total de páginas. */
        
        int[][] counts = new int[n][n]; /* Número de páginas. */
        int i, j, aux, row, col;

        boolean print; /* Variável utilizada para imprimir de maneira
                          mais elegante. */

        /* Criando os links. */
        for (i = 0; i < M; i++)  {
            row = (int) (N * Math.random());
            col = (int) (N * Math.random());
            counts[row][col]++;
        }

        /* Criando Hubs. */
        for (i = 0; i < H; i++) {
            for (j = 0; j < (0.1 * N); j++) {
                row = (int) (N * Math.random());
                col = N + i;
                counts[row][col]++;
            }
        }

        /* Criando Authorities. */
        for (i = 0; i < A; i++) {
            for (j = 0; j < (0.1 * N); j++) {
                row = N + H + i;
                col = (int) (N * Math.random());
                counts[row][col]++;
            }
        }

        /* Imprimindo. */
        StdOut.println (n); 

        for (i = 0; i < n; i++)  {
            print = false;
            for (j = 0; j < n; j++) {
                aux = counts[i][j];
                while (aux > 0) {
                    print = true;
                    StdOut.print (i + " " + j + " ");
                    aux--;
                }
            }
            if (print == true)
                StdOut.println(); 
        }
    }
}
