import edu.princeton.cs.algs4.*;

/* Exercise 1.6.15
 * Nome: Germano Huning Neuenfeld
 * Número USP: 9298340
 * Data: 05/03/2016*/

/* CONCLUSÃO:
 * Como os hubs recebem links dos vértices e os authorities mandam links pros vértices do grafo
 * quanto maior o número de vértices do grafo, na hora de escolher uma pagina aleatoriamente no 
 * RandomSurfer muito maior será a probabilidade de cair em um vértice do grafo(que não possui 
 * os hubs e authorities) e assim ter a chance de ir para um hub, do que cair em um authority 
 * e assim ir para um vértice do grafo.
 * exemplo : testando para 2000 vértices, 1000 links, 3 hubs, 3 authorities e 10000 moves os aut 
*/

public class HubsAndAuthorities {
    /*gera o grafo para a funcao transition*/
    public static void main (String[] args) {
        int n, m, k;
        n = Integer.parseInt (args[0]);
        m = Integer.parseInt (args[1]);
        k = Integer.parseInt (args[2]);
        /*gerar m pares de inteiros*/
        StdOut.println (n + 2*k);
        for (int i = 0; i < m; i++) {
            int a = ((int) (Math.random () * n));
            int b = ((int) (Math.random () * n));
            StdOut.println (a + " " + b);
        }
        int hub;
        int autho;
        int point = ((int) (.10 * n)), num = n, page;
        /*gera point pares de pages com cada hub*/
        for (int i = 0; i < k; i++) {
            hub = num; num++;
            for (int j = 0; j < point; j++) {
                page = ((int) (Math.random () * n));
                StdOut.println (page + " " + hub);
            }
        }
        /*gera point pares de pages com cada authority*/
        for (int i = 0; i < k; i++) {
            autho = num; num++;
            for (int j = 0; j < point; j++) {
                page = ((int) (Math.random () * n)); 
                StdOut.println (autho + " " + page);
            }
        }
    }
}
