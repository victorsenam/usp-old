/******************************************************************************
 *  Compilation:  javac generator.java
 *  Execution:    java generator N M H A
 *  Problema 1.5.14 - Web 
 *  Pedro Sola Pimentel RM 9298079
 *
 ******************************************************************************/

/* Este programa recebe os argumentos N, M, H e A,
   respectivamente, na linha de comando e devolve na 
   saida padrao as combinacoes de links e paginas conforme
   solicitado no enunciado do exercicio */

/* -----------RESPOSTA AO EXERCICIO ------------- 
   Ao realizar a simulacao com diferentes instancias, e 
   possivel perceber que os hubs sao aqueles com maior
   rank, calculado pelo algoritmo presente em "RandomSurfer

*/

public class generator {

    public static void main (String[] args) {
        int N, M, A, H, i, j;
        N = Integer.parseInt (args[0]);
        M = Integer.parseInt (args[1]);
        H = Integer.parseInt (args[2]);
        A = Integer.parseInt (args[3]);
        int contlin = 0;
        int linkT = (int) Math.ceil (M + (N * .1 * (A + H)));
        int links[][] = new int[linkT][2];
        
        /* M links */
        for (contlin = 0; contlin < M; contlin ++) {
            links[contlin][0] = ((int) (Math.random () * N));
            links[contlin][1] = ((int) (Math.random () * N));
        }
        /* hubs */
        for (i = 0; i < H; i ++) {
            for (j = 0; j < 0.1 * N; j ++, contlin ++) {
                links [contlin][0] = ((int) (Math.random () * N));
                links [contlin][1] = i + N;
            }
        }
        /* authorities */
        for (i = 0; i < A; i ++) {
            for (j = 0; j < 0.1 * N; j ++, contlin++) {
                links [contlin][1] = ((int) (Math.random () * N));
                links [contlin][0] = i + N + H;
            }
        }
        
        System.out.println (N + A + H);
        for (i = 0; i < linkT; i ++)
            System.out.println (links[i][0] + " " + links[i][1]);
        
    }
}
