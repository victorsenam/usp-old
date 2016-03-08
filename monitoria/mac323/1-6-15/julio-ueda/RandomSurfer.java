/*******************************************************************************
 *
 *  Nome:   JULIO KENJI UEDA
 *
 *  No.USP: 9298281
 *
 *******************************************************************************
 *
 *  Esta eh a mesma versao do RandomSufer.java original. Apenas os nomes das
 *  variaveis foram modificados.
 * 
 *  Compilacao: javac-algs4 RandomSurfer.java
 *
 *  Execucao:   java-algs4 RandomSurfer T < input.txt
 *
 *  onde:
 *   T: numero de movimentos
 *
 *  % java-algs4 RandomSurfer 1000000 < entrada.txt
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

public class RandomSurfer {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        int M = StdIn.readInt();
        int N = StdIn.readInt();
        
        // O programa termina se a entrada nao for uma matriz quadrada.
        if (M != N) throw new RuntimeException("M nao eh igual a N");

        // Le a matriz de transicao
        double[][] p = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) 
                p[i][j] = StdIn.readDouble(); 

        // Numero de vezes que o usuario acessa a pagina i.
        int[] freq = new int[N];
 
        // Inicia na pagina 0.
        int pagina = 0;

        for (int t = 0; t < T; t++) {

            // Faz um movimento aleatorio. 
            double aleatorio = Math.random(); 
            double soma = 0.0; 
            for (int j = 0; j < N; j++) {
                // Encontra o intervalo que contem o numero aleatorio. 
                soma += p[pagina][j]; 
                if (aleatorio < soma) {
                    pagina = j;
                    break;
                } 
            } 
            freq[pagina]++; 
        } 

        // Imprime Page Ranks em StdOut. 
        for (int i = 0; i < N; i++) {
            StdOut.printf("%8.5f", (double) freq[i] / T); 
        }
        StdOut.println(); 
    }  
} 