/*******************************************************************************
 *
 *  Nome:   JULIO KENJI UEDA
 *
 *  No.USP: 9298281
 *
 *******************************************************************************
 *
 *  Este programa eh um gerador de links para o programa Transition2.java. Ele
 *  recebe como parametro o numero de paginas N, numero de links M, numero de
 *  Hubs H e o numero de Authorities A e imprime na saida padrao N seguido de M
 *  pares de inteiros escolhidos aleatoriamente representando os links entre as 
 *  paginas, incluindo H numero de Hubs (que possuem links apontados para eles
 *  de 10% das paginas) e A numeros de Authorities (que possuem links que
 *  apontam para 10% das paginas).
 *
 *  Compilacao: javac-algs4 Generator.java
 *
 *  Execucao:   java-algs4 Generator N M H A > output.txt
 *
 *  onde: 
 *   N eh a quantidade de paginas
 *   M eh a quantidade de links
 *   H eh a quantidade de hubs
 *   A eh a quantidade de authorities
 *
 *  % java-algs4 Generator 20 10 3 3 > saida.txt
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class Generator {
    public static void main (String[] args) {

        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int H = Integer.parseInt(args[2]);
        int A = Integer.parseInt(args[3]);
        int [][] matriz = new int [N][N];        
        

        if (N % 10 != 0) {
            StdOut.println ("10% de N deve ser um numero inteiro.");
            System.exit(0);
        }
        if ((double)(H * N / 10) > M) {
            StdOut.println 
            ("Nao eh possivel criar Hubs com o numero de links.");
            System.exit(0);
        }
        if ((double)(A * N / 10) > M) {
            StdOut.println 
            ("Nao eh possivel criar Authorities com o numero de links");
            System.exit(0);
        }

        // A operacao termina apenas quando o numbero de Hubs e Authorities eh
        // atingido.
        while (true) {

            // Zera a matriz no comeco da iteracao.
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) matriz[i][j] = 0;

            int [] grauAuth = new int [N];
            int [] grauHubs = new int [N];

            // Cria aleatoriamente links da pagina i para a pagina j.
            // A quantidade de links eh determinada por M (fornecido como
            // parametro de entrada).
            int m = 0;
            while (m < M) {
                int i = (int)(Math.random() * N);
                int j = (int)(Math.random() * N);
                // Nenhuma pagina envia links para ela mesma.
                if (i != j) {
                    matriz[i][j]++;
                    m++;
                    // Calcula a quantidade de links que a pagina i enviou para
                    // outras paginas e a quantidade de links que pagina j 
                    // recebeu de paginas diferentes. Links repetidos nao sao 
                    // contabilizados.
                    if (matriz[i][j] == 1) {
                        grauHubs[j]++;
                        grauAuth[i]++;
                    }
                }
            }
            int contador = 0;
            // Calcula a quantidade de Hubs.
            for (int k = 0; k < N; k++) if (grauHubs[k] == .1*N) contador++;
            
            // Se a quantidade de Hubs eh atingida, entao eh calculado a
            // quantidade de Authorities.
            if (contador == H) {
                contador = 0;
                for (int k = 0; k < N; k++) if (grauAuth[k] == .1*N) contador++;
                // Se a quantidade de Authorities eh atingido, o laco eh
                // desfeito.
                if (contador == A) break;
            }
        }

        // Imprime o arquivo de saida em StdOut.
        StdOut.println(N);
        for (int i = 0; i < N; i++) {
            boolean pulalinha = false;
            int j = 0;
            while (j < N)
                if (matriz[i][j] > 0) {
                    StdOut.printf("%d %d  ", i, j);
                    matriz[i][j]--;
                    pulalinha = true;
                }
                else j++;
            if (pulalinha) StdOut.println();
        }
    }
}
