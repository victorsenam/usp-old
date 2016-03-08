/******************************************************************************
 *
 *  MAC0323 - Estrutura de Dados 2
 *  Aluno: Gabriel Capella
 *  Número USP: 8962078
 *  Tarefa: Creative Ex. 1.6.15 (Hubs and authorities) de IntroCS
 *  URL: http://introcs.cs.princeton.edu/java/16pagerank/
 *  Data: 27/02/2016
 *
 *  A unica diferenca do codigo do livro eh a introducao da regra para o caso
 *  de um site nao possuir nenhum link. Caso nao possui nenhum link o usuario
 *  vai para qualquer outra pagina com probabilidade igual. 
 *
 ******************************************************************************/

public class Transition {
    public static void main(String[] args) {
        StdOut.println(StdIn.readLine()); // Exibe hubs
        StdOut.println(StdIn.readLine()); // Exibe authorities
        int N = StdIn.readInt();
        int[][] counts = new int[N][N];
        int[] outDegree = new int[N];

        while (!StdIn.isEmpty())  {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println(N + " " + N); 

        for (int i = 0; i < N; i++)  {

            for (int j = 0; j < N; j++) {
                double p;
                // Parte modificada
                if (outDegree[i] > 0)
                    p = .90*counts[i][j]/outDegree[i] + .10/N;
                else 
                    p = 1.0/N;
                StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
} 

