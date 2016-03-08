/******************************************************************************
*  
* Nome: Leonardo Padilha
* Numero USP: 9298295
*  
* HubsAndAuthorities: recebe na linha de comando um inteiro N (representa a quan
* tidade de páginas), M (representa a quantidade de links entre páginas), NH 
* (quantidade de hubs) e NA (quantidade de authorities) e retorna um espécie
* de representação do grafo de links entre páginas de tal forma que os authorities
* (que são as páginas de numeros N a N + NA) tenham links para 10% das N páginas e
* os hubs (que são as páginas de numeros N + NA a N + NA + NH) tenham links de
* 10% das paginas.
* 
* Resultado dos experimentos:
* Ao realizar vários testes, com diferentes N, M, NA, NH e com um Transition 
* alterado, foi constatado que hubs tem sempre mais PageRank do que os 
* authorities. Exemplo:
* $ java-introcs HubsAndAuthorities 1000 1500 5 5 | java-introcs Transition | 
* java-introcs RandomSurfer 2000
* Gera hubs com, em média, 0.03 de PageRank e authorities com 0.001 de PageRank.
* 
******************************************************************************/

public class HubsAndAuthorities {
    public static void main (String[] args) {
        int i, j, a, b, k, t;
        int n = Integer.parseInt (args[0]);
        int m = Integer.parseInt (args[1]);
        int nh = Integer.parseInt (args[2]);
        int na = Integer.parseInt (args[3]);
        int[][] link = new int[n + na + nh][n + na + nh];
        t = (int)(n / 10); // equivale a 10% das paginas

        //Criando M links aleatoriamente
        for (i = 0; i < m; i++) {
            a = (int) (Math.random () * n);
            b = (int) (Math.random () * n);

            link[a][b]++;
        }

        //Criando links que partem dos authorities
        for (i = n; i < n + na; i++) {
            for (j = 0; j < t; j++) {
                b = (int) (Math.random () * n);

                link[i][b]++;
            }
        }

        ///Criando links que vao para os hubs
        for (i = n + na; i < n + na + nh; i++) {
            for (j = 0; j < t; j++) {
                a = (int) (Math.random () * n);

                link[a][i]++;
            }
        }
        
        StdOut.printf ("%d\n", n + nh + na);
        for (i = 0; i < n + nh + na; i++) {
            for (j = 0; j < n + nh + na; j++) {
                for (k = 0; k < link[i][j]; k++)
                    StdOut.printf ("%d %d  ", i, j);
            }
            StdOut.println ();
        }
    }
}