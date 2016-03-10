/******************************************************************************
*  Nome: Felipe Caetano Silva
*  USP: 9293223
*  
*
*  Este Programa recebe "n" números de páginas, "m" links entre páginas, 
*  "h" número de hubs e "a" número de authorities na linha de comando
*  e gera m pares de links entre as n páginas aleatoriamente. Em seguida cria
*  links que apontam pra os "h" hubs de 10% das páginas e links apontados
*  pelos "a" authorities para 10% das páginas. A saída está no formato
*  necessário como entrada para o programa "Transitions"
*
*  Comentário sobre os Hubs e Authorithies: 
*  Na maioria dos casos os Hubs obtiveram um Page Rank muito superior ao Page
*  Rank dos Authorities, e em diversas vezes obtiveram melhor desempenho que 
*  todas as outras o páginas. Notou-se também que quanto menor o número de 
*  de links menor a diferença de desempenho entre hubs e as páginas casuais
******************************************************************************/
import edu.princeton.cs.algs4.*;

public class Generator {
    public static void main(String[] args) {
        int n, m, h, a, random;
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
        h = Integer.parseInt(args[2]);
        a = Integer.parseInt(args[3]);
        
        int[][] pairs = new int[n + h + a][n + h + a];
        int[] grau = new int[n + h + a];

        for (int i = 0; i < m; i++) {
            random = StdRandom.uniform(n);
            pairs[random][ StdRandom.uniform(n)]++;
            grau[random]++;
        }

        int tenpercent = n/10;
        for (int i = 0; i < h; i++){
            for (int j = 0; j < tenpercent; j++) {
                random = StdRandom.uniform(n);
                pairs[random][n + i]++;
                grau[random]++;
            }
        }
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < tenpercent; j++) {
                random = StdRandom.uniform(n);
                pairs[n + h + i][random]++;
                grau[n + h + i]++;
            }
        }

        StdOut.println(n + h + a);
        for (int i = 0; i < (n + h + a); i++) {
            if (grau[i] > 0) {
                for (int j = 0; j < (n + h + a); j++) {
                    while (pairs[i][j] > 0) {
                        StdOut.printf ("%d %d  ", i, j);
                        pairs[i][j]--;
                        grau[i]--;
                    }
                }
                StdOut.println();
            }
        }
    }
}
