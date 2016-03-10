/******************************************************************************
 *  Compilacao:   javac RandomSurfer.java
 *  Execucao:     java RandomSurfer T < input_file
 *                   T: numero de movimentos do random surfer
 *                   input_file: um arquivo gerado por Transition.java
 *  Dependencias: StdIn.java
 *                StdOut.java
 *                Transition.java
 *                Generator.java
 *
 *  Este programa simula o comportamento do random surfer. Le uma matriz de
 *  transicao e se movimenta pelas paginas de acordo com as regras, comecando
 *  da pagina 0 e fazendo o numero de movimentos dado pelo argumento na linha
 *  de comando. Conta o numero de vezes que o surfer visita cada pagina.
 *  Estima e imprime a probabilidade de o random surfer visitar cada pagina.
 *
 *  % java Generator 5 10 2 2 | java Transition | java RandomSurfer 1000000
 *  0.14819 0.04944 0.18838 0.22860 0.18532 0.07009 0.07799 0.02601 0.02597
 *
 ******************************************************************************/

public class RandomSurfer {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);    // numero de movimentos
        int M = StdIn.readInt();              // numero de paginas - ignorar, pois M = N
        int N = StdIn.readInt();              // numero de paginas
        if (M != N) throw new RuntimeException("M != N");

        // Le a matriz de transicao
        double[][] p = new double[N][N];     // p[i][j] = prob. do surfer se mover da pagina i para a j
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) 
                p[i][j] = StdIn.readDouble(); 


        int[] freq = new int[N];            // freq[i] = # de vezes que o surfer acessa a pagina i
 
        // Comeca na pagina 0. 
        int page = 0;

        for (int t = 0; t < T; t++) {

            // Escolhe uma pagina 'aleatoriamente'. 
            double r = Math.random(); 
            double sum = 0.0; 
            for (int j = 0; j < N; j++) {
                // Acha o intervalo que contem r. 
                sum += p[page][j]; 
                if (r < sum) {
                    page = j;
                    break;
                } 
            } 
            freq[page]++; 
        } 


        // Imprime o page rank. 
        for (int i = 0; i < N; i++) {
            StdOut.printf("%8.5f", (double) freq[i] / T); 
        }
        StdOut.println(); 
    }  
} 
