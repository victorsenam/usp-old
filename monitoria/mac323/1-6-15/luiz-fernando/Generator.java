/*///////////////////////////////////////////////////////////////////
//
// Autor: Luiz Fernando Antonelli Galati                                                    
// Numero USP: 7557223                                                                                 
// Data: 05-03-2016                                             
//                                                               
// Creative Exercise 1.6.15 - Hubs and authorities
//                                                               
///////////////////////////////////////////////////////////////////*/



public class Generator {
    /* Esta função recebe, via linha de comando, um número de páginas
    N e um número de links M. Imprime na saída padrão o número N 
    seguido de M pares de inteiros que variam de 0 a N-1.          */
    public static void main (String[] args) {
        int N, M, a, b, i;
        double r;
        
        N = Integer.parseInt (args[0]);
        M = Integer.parseInt (args[1]);
        
        System.out.println (N);
        for (i = 0; i < M; i++) {
            r = Math.random ();
            a = (int) (r * N);
            r = Math.random ();
            b = (int) (r * N);
            while (a == b) {
                r = Math.random ();
                b = (int) (r * N);
            }
            System.out.println (a + " " + b);
        }        
    }
}