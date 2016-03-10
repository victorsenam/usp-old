/******************************************************************************
 *  Compilacao:   javac Transition.java
 *  Execucao:     java Transition < input_file
 *                   input_file: um arquivo gerado por Generator.java
 *  Dependencias: Generator.java
 *                StdIn.java
 *                StdOut.java
 *
 *  Estre programa e um filtro que le links do standard input e produz a
 *  correspondente matriz de transicao no standard output. Primeiro, processa
 *  a entrada para armazenar a quantidade de links de cada pagina. Entao
 *  aplica a regra 90-10 para computar a matriz de transicao.
 *
 *  % java Generator 5 10 2 2 | java Transition
 *  9 9
 *  0.01111 0.01111 0.01111 0.46111 0.01111 0.01111 0.46111 0.01111 0.01111 
 *  0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 
 *  0.23611 0.23611 0.23611 0.01111 0.23611 0.01111 0.01111 0.01111 0.01111 
 *  0.37111 0.19111 0.01111 0.19111 0.01111 0.19111 0.01111 0.01111 0.01111 
 *  0.01111 0.91111 0.01111 0.01111 0.01111 0.01111 0.01111 0.01111 0.01111 
 *  0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 
 *  0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 0.11111 
 *  0.01111 0.91111 0.01111 0.01111 0.01111 0.01111 0.01111 0.01111 0.01111 
 *  0.01111 0.01111 0.01111 0.01111 0.91111 0.01111 0.01111 0.01111 0.01111 
 *
 ******************************************************************************/


public class Transition {


    public static void main(String[] args) {

        int N = StdIn.readInt();           // numero de paginas
        int[][] counts = new int[N][N];    // counts[i][j] = # links da pagina i para a j
        int[] outDegree = new int[N];      // outDegree[i] = # links da pagina i para qualquer outra

        // Armazena a quantidade de links entre paginas comuns.
        while (!StdIn.isEmpty())  {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println(N + " " + N); 


        // Imprime a distribuicao de probabilidade para a linha i
        for (int i = 0; i < N; i++)  {

            // Imprime a probabilidade para a coluna j
            for (int j = 0; j < N; j++) {
                double p;
                if (outDegree[i] == 0) // pagina i nao tem links para outra pagina
                   p = (double) 1/N;
                else
                   p = .90*counts[i][j]/outDegree[i] + .10/N; 
                StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
} 
